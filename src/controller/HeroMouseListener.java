package controller;

import controller.actions.spells.Spell1;
import model.Coordanate;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HeroMouseListener implements MouseListener {
    boolean flag = false, flag2 = false;

    public HeroMouseListener() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true){
                    if (flag || flag2) {

                        Coordanate coor = DataProvider.getMainCharacter().getCoordinate();
                        Coordanate coor2 = DataProvider.getMainCharacter().getPosition();
                        int height = DataProvider.getMainCharacter().getHeight();
                        int width = DataProvider.getMainCharacter().getWidth();
                        int x0 = coor.getX();
                        int y0 = coor.getY();
                        int x = x0 +(int) MouseInfo.getPointerInfo().getLocation().getX()-coor2.getX();
                        int y =y0 +(int) MouseInfo.getPointerInfo().getLocation().getY() - coor2.getY();

                        DataProvider.addInterimObject(new Spell1(coor.getX()+width/4,coor.getY()-height/3,
                                x,y,40,15).getPaintEffect());
                        flag2 = false;
                    }
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        flag2 = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        flag = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        flag = false;

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
