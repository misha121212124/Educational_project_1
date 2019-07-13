package model.objects;

import model.Coordanate;
import model.Map;

import java.awt.*;

public class BuildingObject extends VisualObject {
    private int im;

    public BuildingObject(Coordanate coor, Image image, int im, int type) {
        super(coor, image, type);
        this.im = im;
    }

    @Override
    protected int getDividerX() {
        return 2;
    }

    @Override
    protected int getDividerY() {
        return 2;
    }

    @Override
    public boolean isOnField(int x1, int y1, int x2, int y2) {
        return super.isOnField(x1-200, y1-200, x2+200, y2+200);
    }

    @Override
    public int compare(VisualObject visualObject) {
        switch (im){
            case 1:
                int mudslide=0;
                if(visualObject.getCoor().getY()<=getCoor().getY()&&
                        visualObject.getCoor().getY()>=getCoor().getY()-image.getHeight(null)){
                    mudslide = visualObject.getCoor().getX()-getCoor().getX();
                    mudslide*=getDividerX();
                    mudslide-=100;
                    mudslide/= Map.multiplier;
                    if(mudslide<=0)
                         mudslide /= 4;
                    else mudslide = -mudslide/2;
                }
                Coordanate newCoor = new Coordanate(getCoor().getX(),getCoor().getY()+mudslide);
                return new VisualObject(newCoor,null,0).compare(visualObject);

            default: return  super.compare(visualObject);
        }
    }

}
