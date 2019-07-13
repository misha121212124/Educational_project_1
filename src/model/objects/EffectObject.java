package model.objects;

import model.Coordanate;

import java.awt.*;

public class EffectObject extends VisualObject {
    public EffectObject(Coordanate coor, Image image, int type) {
        super(coor, image, type);
        System.out.println(coor.getX());
    }
}
