package view;
import controller.DataProvider;
import controller.MainCharacter;
import model.*;
import model.Renderer;
import model.objects.VisualObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import javax.imageio.ImageIO;
import javax.swing.*;

import static controller.DataProvider.*;

public class MainField extends JPanel implements Runnable{
    Image image = new ImageIcon("resources/map/map.png").getImage();
    private Thread animator;
    MainCharacter mainChar;
    static final int DELAY = 16;
    private int fpsCount = 0;
    double rotate = 0;

    public MainField() {
        mainChar = DataProvider.getMainCharacter();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawDonut(g);
    }

    private void drawDonut(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        g2d.setRenderingHints(rh);

        ArrayList<PaintElement> list = new ArrayList<>();
        list.add(new PaintBackground(image));
        list.add(new PaintElement(new VisualObject(mainChar.getCoordinate(),mainChar.getImage(),0),mainChar.getWidth(),
                mainChar.getHeight(),true));

        Coordanate fieldStart = DataProvider.getFieldStart();
        int x1 = fieldStart.getX();
        int y1 = fieldStart.getY();
        int x2 = x1+Renderer.getFieldWidth();
        int y2 = y1+Renderer.getFieldHeight();

        for (PaintElement temp: DataProvider.getObjects()) {
            if(temp.isOnField(x1, y1, x2, y2)){
                list.add(temp);
            }
        }
        for (PaintEffect temp:DataProvider.getInterimObjects()) {
            list.add(temp);
        }

        list.sort(new Comparator<PaintElement>() {
            @Override
            public int compare(PaintElement o1, PaintElement o2) {
                return o1.compare(o2);
            }
        });

        PaintElement.loadPosition(mainChar);
        for (PaintElement temp: list) {
            //PaintElement.loadPosition(mainChar);
            temp.draw(g2d);
        }
        fpsCount++;
    }

    @Override
    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();

    }

    @Override
    public void run() {
        //int time = 0;
        long beforeTime, timeDiff, sleep;
        Thread counter = new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //System.out.println(fpsCount);
                    fpsCount = 0;
                }
            }
        };
        counter.start();
        while (true){
            beforeTime = System.currentTimeMillis();
            repaint();
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY-timeDiff;
            if (sleep < 0) {
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}