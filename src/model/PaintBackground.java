package model;

import model.objects.VisualObject;

import java.awt.*;

public class PaintBackground extends PaintElement {
    public PaintBackground(Image image) {
        super(new VisualObject(new Coordanate(0,0),image,0),
                image.getWidth(null),image.getHeight(null),false);
    }

    @Override
    public void draw(Graphics2D g2d) {
        Coordanate coor = position;
        int muslideX = absolutePosition.getX() - coor.getX();
        int muslideY = absolutePosition.getY() - coor.getY();

        if(coor.getX()>Renderer.getFieldWidth()/2)
            muslideX = Map.getMapWidth() - Renderer.getFieldWidth();
        if(coor.getY()>Renderer.getFieldHeight()/2)
            muslideY = Map.getMapHeight() - Renderer.getFieldHeight();

        g2d.drawImage(visOb.getImage(),visOb.getCoor().getX()-muslideX,
                visOb.getCoor().getY()-muslideY,
                width,height,null);
    }
}
