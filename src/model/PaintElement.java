package model;

import controller.DataProvider;
import controller.MainCharacter;
import model.objects.VisualObject;

import java.awt.*;

public class PaintElement{
    //private Coordanate coordanate;
    protected int width,height;
    //private Image image;
    private boolean mod;
    protected MainCharacter mainChar;
    protected VisualObject visOb;
    static protected Coordanate position;
    static protected Coordanate absolutePosition;

    public PaintElement(VisualObject visualObject, int width, int height, boolean mod) {
        this.visOb = visualObject;
        //this.coordanate = visualObject.getCoor();
        this.width = width;
        this.height = height;
        //this.image = visualObject.getImage();
        this.mod = mod;
        mainChar = DataProvider.getMainCharacter();
    }

    public Coordanate getCoord() {
        return visOb.getCoor();
    }

    public void draw(Graphics2D g2d){
        if(!mod){
//            Coordanate coor = mainChar.getPosition();
            Coordanate coor = position;
            int muslideX = absolutePosition.getX() - coor.getX();
            int muslideY = absolutePosition.getY() - coor.getY();

            if(coor.getX()>Renderer.getFieldWidth()/2)
                muslideX = Map.getMapWidth() - Renderer.getFieldWidth();
            if(coor.getY()>Renderer.getFieldHeight()/2)
                muslideY = Map.getMapHeight() - Renderer.getFieldHeight();

            g2d.drawImage(visOb.getImage(),visOb.getCoor().getX()- width /2-muslideX,
                    visOb.getCoor().getY()-height-muslideY,
                    width,height,null);
        }else{
            Coordanate coor = position;
            g2d.drawImage(visOb.getImage(),coor.getX()-mainChar.getWidth()/2-mainChar.getMudslide()/30,
                    coor.getY()-mainChar.getHeight(), width,height,null);
        }

    }

    public int compare(PaintElement paintElement){
        return visOb.compare(paintElement.visOb);
    }

    public static void loadPosition(MainCharacter mainChar) {
        position = mainChar.getPosition();
        absolutePosition = mainChar.getCoordinate();
    }

    public boolean isOnField(int x1, int y1, int x2, int y2) {
        return visOb.isOnField(x1,y1,x2,y2);
    }

    public VisualObject getVisOb() {
        return visOb;
    }
}
