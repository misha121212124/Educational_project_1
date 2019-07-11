package model.objects;

import model.Coordanate;
import model.PaintElement;

import java.awt.*;

public class BarrierObject extends VisualObject {

    public BarrierObject(Coordanate coor, Image image, int type) {
        super(coor, image, type);

    }

    @Override
    public int compare(VisualObject visualObject) {
        int result = super.compare(visualObject);
        if(result==0)
            result = new Integer(-getCoor().getX()).compareTo(visualObject.getCoor().getX());
        return result;
    }

    @Override
    protected int getDividerX() {
        return 5;
    }

    @Override
    protected int getDividerY() {
        return 5;
    }

    @Override
    public boolean isOnField(int x1, int y1, int x2, int y2) {
        return super.isOnField(x1-40, y1, x2+40, y2);
    }

    //    @Override
//    public PaintElement getPaintElement(boolean mod) {
//        Image image = this.image;
//        int width = image.getWidth(null)/5;
//        int height = image.getHeight(null)/5;
//        return new PaintElement(this,width,height,mod);
//    }
}
