package model.objects;

import model.Coordanate;
import model.PaintElement;

import java.awt.*;

public class VisualObject {
    private Coordanate coor;
    Image image;
    public final int type;

    public VisualObject(Coordanate coor, Image image, int type) {
        this.coor = coor;
        this.image = image;
        this.type = type;
    }

    public Coordanate getCoor() {
        return coor;
    }

    public Image getImage() {
        return image;
    }

    protected int getDividerX() {
        return 10;
    }

    protected int getDividerY() {
        return 10;
    }

    public int compare(VisualObject visualObject){
        int result;
        if(visualObject.type!=7)
            result = new Integer(getCoor().getY()).compareTo(visualObject.getCoor().getY());
        else {
            result = -visualObject.compare(this);
            //System.out.println("!!!!!");
        }
        return result;
    }

    public PaintElement getPaintElement(boolean mod){
        Image image = this.image;
                int width = image.getWidth(null)/getDividerX();
                int height = image.getHeight(null)/getDividerY();
        return new PaintElement(this,width,height,mod);
    }

    public boolean isOnField(int x1,int y1, int x2, int y2){
        return coor.isBetween(x1-20,y1,x2+20,y2+50);
    }
}
