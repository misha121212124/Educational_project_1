package model.objects;

import model.Coordanate;

import java.awt.*;

public class TreeObject extends VisualObject {

    public TreeObject(Coordanate coor, Image image, int type) {
        super(coor, image, type);
    }

    @Override
    public boolean isOnField(int x1, int y1, int x2, int y2) {
        return super.isOnField(x1-10, y1, x2+10, y2+100);
    }
}
