package controller.actions.spells;

import controller.DataProvider;
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
    private PaintEffect paintEffect;
    final int speed;
    final double xChange, yChange;
    final double turningAngle;

    public Action(int x1, int y1, int x2, int y2,int width,int height,int speed, BufferedImage image, int range) {
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
        double k;
        if(y2!=y1)
        k= (y2-y1)/(double)(x2-x1);
        else {
            k=0;
            //System.err.println("!!!!!!!!!!!!!!%%%%%%%%%%%%%%%%\n"+x1+" "+y1+" "+x2+" "+y2+" ");
        }

        if(x2-x1<0)xChange = -speed/Math.sqrt(k*k+1);
        else xChange = speed/Math.sqrt(k*k+1);
        yChange = xChange*k;
        coor = new Coordanate(currX/1000,currY/1000);
        Graphics2D g2d = image.createGraphics();
        this.image = rotateImage(image, turningAngle);

        Thread action = new Thread(){
            @Override
            public void run() {
                boolean flag = true;
                while (flag){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    currX+=getMudsladwX();
                    currY+=getMudsladwY();
                    coor.setCoor(coor,currX/1000,currY/1000);
                    double distance =Math.sqrt( Math.abs(x1-currX/1000)*Math.abs(x1-currX/1000)+
                            Math.abs(y1-currY/1000)*Math.abs(y1-currY/1000));
                    if(distance>=range){
                        flag = false;
                        DataProvider.removeInterimObject(getPaintEffect());
                    }
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
        if(paintEffect==null)paintEffect = new PaintEffect(new EffectObject(coor,image,0),width,height,false);
        return paintEffect;
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
        double result;
        if((x2>x1&&y1>y2)||(x1>x2&&y2>y1))
        result =  -(Math.acos(Math.sqrt(x*x/(x*x+y*y))));
        else result = (Math.acos(Math.sqrt(x*x/(x*x+y*y))));
        if(x2<x1){
            result+=3.14;
            DataProvider.getMainCharacter().setDirr(4);
        }else DataProvider.getMainCharacter().setDirr(0);
        return result;
    }

}
