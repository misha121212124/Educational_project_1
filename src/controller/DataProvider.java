package controller;

import model.*;
import model.Renderer;
import model.objects.BarrierObject;
import model.objects.BuildingObject;
import model.objects.TreeObject;
import model.objects.VisualObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class DataProvider {

    private static Map map;
    private static MainCharacter mainChar = new MainCharacter();;

    private static Dimension dimension;

    private static int factor = 0;

    private static ArrayList<PaintElement> objects = new ArrayList<PaintElement>();
    private static ArrayList<PaintEffect> interimObjects = new ArrayList<PaintEffect>();

    static {
        map = new Map();
        loadObjects();
    }

    public static void setMap(){
        map = new Map();
        loadObjects();
    }

    public static int getFactor() {
        return factor;
    }

    public static void setFactor(int factor) {
        DataProvider.factor = factor;
    }

    public static Dimension getDimension() {
        return dimension;
    }

    public static void setDimension(Dimension dimension) {
        DataProvider.dimension = dimension;
    }

    public static Map getMap() {
        return map;
    }

    public static MainCharacter getMainCharacter() {
        return mainChar;
    }


    public static VisualObject getObject(Coordanate coor, int type){
        Image image;
        VisualObject visualObject;
        switch (type){
            case 2:
                image = new ImageIcon("resources/environment/barrier/barrier5.png").getImage();
                visualObject = new BarrierObject(coor,image,2);
                break;
            case 5:
                int i = new Random().nextInt(3)+1;
                image = new ImageIcon("resources/environment/tree"+i+".png").getImage();
                visualObject = new TreeObject(coor,image,5);
                break;
            case 7:
                ////////////////////////////////////////
                image = new ImageIcon("resources/environment/building1.png").getImage();
                visualObject = new BuildingObject(coor,image,1,7);
                break;
            default: visualObject = null;
        }

        return visualObject;
    }

    public static Coordanate getFieldStart() {
        int x1 = mainChar.getX()- model.Renderer.getFieldWidth()/2;
        int y1 = mainChar.getY()- model.Renderer.getFieldHeight()/2;
        int x2 = mainChar.getX()+ model.Renderer.getFieldWidth()/2;
        int y2 = mainChar.getY()+ model.Renderer.getFieldHeight()/2;
        if(x1<0){
            x1 = 0;
            x2 = model.Renderer.getFieldWidth();
        }
        if(y1<0){
            y1 = 0;
            y2 = model.Renderer.getFieldHeight();
        }
        if(x2>Map.getMapWidth()){
            x2 = Map.getMapWidth();
            x1 = x2 - model.Renderer.getFieldWidth();
        }
        if(y2>Map.getMapHeight()){
            y2 = Map.getMapHeight();
            y1 = y2 - Renderer.getFieldHeight();
        }
        return new Coordanate(x1,y1);
    }

    public static void loadObjects() {
        for (int i = 1; i<10;i++)
            for (VisualObject temp: DataProvider.getMap().getObjects()[i]) {
                    objects.add(temp.getPaintElement(false));
            }
        objects.sort(new Comparator<PaintElement>() {
            @Override
            public int compare(PaintElement o1, PaintElement o2) {
                return o1.getVisOb().compare(o2.getVisOb());
            }
        });

    }

    public static ArrayList<PaintElement> getObjects() {
        return objects;
    }

    public static ArrayList<PaintEffect> getInterimObjects() {
        return interimObjects;
    }

    public static void addInterimObject(PaintEffect interimObject) {
        DataProvider.interimObjects.add(interimObject);
    }

    public static void removeInterimObject(PaintEffect interimObject) {
        DataProvider.interimObjects.remove(interimObject);
    }
}
