package controller.actions.spells;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Spell1 extends Action{
    static BufferedImage buff[];
    static {
        buff = new BufferedImage[10];
        try {
            buff[0] = ImageIO.read(new File("resources/spells/spell1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Spell1(int x1, int y1, int x2, int y2, int width, int height/*, Image image*/) {
        super(x1,y1,x2,y2,width,height,6,buff[0]);
    }

}
