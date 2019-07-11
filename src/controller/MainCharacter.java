package controller;

import model.Coordanate;
import model.Map;
import model.Renderer;
import model.objects.VisualObject;

import javax.swing.*;
import java.awt.*;

public class MainCharacter {
    private int x=150,y=190;
    private int map[][];

    private Image[] images = new Image[8];
    private int imNumber = 0;
    private int dirr = 0;

    public MainCharacter(){
        //this.map = DataProvider.getMap().getMap();
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int getDirr() {
        return dirr;
    }

    public void setDirr(int dirr) {
        this.dirr = dirr;
    }

    {
        for(int i = 1; i<=8;i++){
            ImageIcon imageIcon = new ImageIcon("resources/skeleton/skeleton"+i+".png");
            images[i-1] = imageIcon.getImage();
        }
    }

    public int getX() {
        return x;
    }

    public void changeX(int x) {
        int mod = 0;
        if(x>0){
            mod = 1;
        }else{
            mod = -1;
        }
//        int end= this.x+x;
//        for(int i = this.x; i*mod<=end*mod;){
//            this.x +=mod;
//            for(int j = 1; j<=9;j++)
//                for (int y = getY()-10; y>getY()-Map.multiplier-10;y--)
//                    if(DataProvider.getMap().getObjects()[j].contains(new VisualObject(new Coordanate(i,y),null))){
//                        this.x -=mod;
//                        i =this.x+x;
//                        j = 10;
//                        y = getY()-Map.multiplier-10;
//                        //System.out.println(getX()+" "+getY());
//                    }
//            i+=mod;
//        }
        try {
            for(int j = 1; j<=Math.abs(x);j++){
                //System.out.println(map[this.x+mod+this.getWidth()/2+this.getMudslide()][this.y+this.getHeight()]);
                if(map[this.x+mod][this.y]!=1)
                    this.x+=mod;
                else
                    for (int k = 1;k<=7;k++)
                    if(map[this.x+mod][this.y+k*Map.multiplier]!=1){
                        //this.x+=mod;
                        this.y++;
                        //j+=k;
                        break;
                    }
                    else
                        if(map[this.x+mod][this.y-k*Map.multiplier]!=1){
                            //this.x+=mod;
                            this.y--;
                            //j+=k;
                            break;
                        }

                //System.out.println(this.x+mod+" "+(this.y));
            }
        }catch (ArrayIndexOutOfBoundsException ex){
        }



        if(this.x >(Map.getMapWidth()-10))
            this.x =Map.getMapWidth()-10;
        if(this.x<20)
            this.x =20;

    }

    public int getY() {
        return y;
    }

    public void changeY(int y) {
        int mod;
        if(y>0){
            mod = 1;
        }else{
            mod = -1;
        }
        try {
            for(int j = 1; j<=Math.abs(y);j++)
                if(map[this.x][this.y+mod]!=1)
                    this.y+=mod;
                else
                    for(int k=1;k<=7;k++)
                        if(map[this.x+k*Map.multiplier][this.y+mod]!=1){
                            this.x++;
                            setDirr(0);
                            //this.y+=mod;
                            //j+=k;
                            break;
                        }else
                            if(map[this.x-k*Map.multiplier][this.y+mod]!=1){
                                this.x--;
                                setDirr(4);
                                //this.y+=mod;
                                //j+=k;
                                break;
                            }
            //System.out.println(this.x+" "+this.y);
        }catch (ArrayIndexOutOfBoundsException ex){

        }

        if(this.y >(Map.getMapHeight()))
            this.y =Map.getMapHeight();
        if(this.y<20)
            this.y =20;
    }

    public  Coordanate getPosition(){
        int posX = 0;
        int posY = 0;
        if(getX()< model.Renderer.getFieldWidth()/2){
            posX = getX();
        }else{
            if(Map.getMapWidth()-getX()< model.Renderer.getFieldWidth()/2){
                posX = model.Renderer.getFieldWidth()-(Map.getMapWidth()-getX());
            }else
                posX = model.Renderer.getFieldWidth()/2;
        }
        if(getY()< model.Renderer.getFieldHeight()/2){
            posY = getY();
        }else {
            if(Map.getMapHeight()-getY()< model.Renderer.getFieldHeight()/2){
                posY = model.Renderer.getFieldHeight()-(Map.getMapHeight()-getY());
            }else
                posY = Renderer.getFieldHeight()/2;
        }
        //System.out.println(posX+" "+x);
        return new Coordanate(posX,posY);
    }

    public Coordanate getCoordinate(){
        return new Coordanate(x,y);
    }

    public Image getImage() {
        Image image = images[imNumber];
        return image;
    }

//    public static Image getObject(int type){
//        Image image;
//        switch (type){
//            case 5:
//                int i = new Random().nextInt(3)+1;
//                image = new ImageIcon("resources/environment/tree"+i+".png").getImage();
//                break;
//            default: image = null;
//        }
//
//        return image;
//    }
    private boolean flag = true;
    public void nextImNumber(){
        if(flag){
            imNumber++;
            if(imNumber>7) imNumber=0;
        }
        flag = !flag;
    }

    public int getWidth(){
        switch (dirr){
            case 0:return 90;
            case 1:return 90;
            case 2:return 90;
            case 3:return -90;
            case 4:return -90;
            default: return 90;
        }
    }

    public int getMudslide(){
        switch (dirr){
            case 0:return 0;
            case 1:return 0;
            case 2:return 0;
            case 3:return 90;
            case 4:return 90;
            default: return 90;
        }
    }

    public int getHeight(){
        return 90;
    }
}
