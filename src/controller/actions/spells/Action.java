package controller.actions.spells;

import model.Coordanate;
import model.PaintEffect;
import model.objects.EffectObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Action {
    private  int currX, currY;
    private int x1,x2,y1,y2;
    private int width,height;
    private BufferedImage image;
    private Coordanate coor;
    final int speed;
    final double xChange, yChange;
    final double turningAngle;

    public Action(int x1, int y1, int x2, int y2,int width,int height,int speed, BufferedImage image) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.image = image;
        turningAngle = getAngle();
        currX = x1*1000;
        currY = y1*1000;
        double k = (y2-y1)/(double)(x2-x1);
        xChange = speed/Math.sqrt(k*k+1);
        yChange = xChange*k;
        coor = new Coordanate(currX/1000,currY/1000);
        Graphics2D g2d = image.createGraphics();
        this.image = rotateImage(image, turningAngle);
        //((Graphics2D)g).drawImage(buff, 200,200, null);


        Thread action = new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    currX+=getMudsladwX();
                    currY+=getMudsladwY();
                    ////////////???????//////////////
                    coor.setCoor(coor,currX/1000,currY/1000);
                }


            }
        };
        action.start();
    }

    private BufferedImage rotateImage(BufferedImage buffImage, double radian) {
        double sin = Math.abs(Math.sin(radian));
        double cos = Math.abs(Math.cos(radian));
        int width = buffImage.getWidth();
        int height = buffImage.getHeight();
        int nWidth = (int) Math.floor((double) width * cos + (double) height * sin);
        int nHeight = (int) Math.floor((double) height * cos + (double) width * sin);
        BufferedImage rotatedImage = new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = rotatedImage.createGraphics();
        //graphics.setColor(Color.WHITE);
        //graphics.fillRect(0, 0, nWidth, nHeight);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics.translate((nWidth - width) / 2, (nHeight - height) / 2);
        graphics.rotate(radian, (double) (width / 2), (double) (height / 2));
        graphics.drawImage(buffImage, 0, 0,null);
        graphics.dispose();
        this.width=(int)(this.width*rotatedImage.getWidth()*1.0/width);
        this.height=(int)(this.height*rotatedImage.getHeight()*1.0/height);
        //this.coor.setCoor(coor,);
        return rotatedImage;
    }

    public PaintEffect getPaintEffect(){
        return new PaintEffect(new EffectObject(coor,image,0),width,height,false);
    }

    int getMudsladwX() {
        return (int) (xChange*1000);
    }

    int getMudsladwY() {
        return (int) (yChange*1000);
    }

    double getAngle(){
        double x = x2-x1;
        double y = y2 - y1;
        return (Math.acos(Math.sqrt(x*x/(x*x+y*y))));
    }

}
