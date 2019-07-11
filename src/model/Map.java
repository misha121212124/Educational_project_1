package model;

import controller.DataProvider;
import controller.MainCharacter;
import model.objects.VisualObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.ArrayList;

public class Map {
    private int map[][];
    public static final int multiplier = 3;
    private ArrayList<VisualObject>  objects[] = new ArrayList[10];
    private static int mapWidth;
    private static int mapHeight;
    static byte[] matrix;
    //public static Image image;

    public static void writeNewMap(){
        try {
            FileOutputStream outFile = new FileOutputStream("resources/map/map3.map");
            outFile.write(matrix);
            outFile.flush();
            outFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }

    public static void writeData(int x1, int y1, int x2, int y2, byte value){
        int x = 0,y=0;
        int k = 1;
        for(int i = 13; i<matrix.length; i++){
            if(matrix[i]==13){
                i++;
                y++;
                x = 0;
            }else {
                switch (value){
                    case 2:
                        if(x>=x1&&x<=x2&&y>=y1-1&&y<=y1+1)
                            matrix[i]=(byte) ('0'+1);
                        if(x>=x1+46*k&&x<=x2&&y==y1){
                            matrix[i]=(byte) ('0'+2);
                            k++;
                        }
                        break;
                    default:
                        if(x>=x1&&x<=x2&&y>=y1)
                            matrix[i]=(byte) ('0'+value);
                        break;
                }


                x++;
            }

        }
    }

    public static int getMapWidth() {
        return mapWidth;
    }

    public static int getMapHeight() {
        return mapHeight;
    }

    public Map(){
        System.out.println("start");
        try {
            FileInputStream inFile = new FileInputStream("resources/map/map3.map");
            matrix = new byte[inFile.available()];
            inFile.read(matrix);

            for(int i = 0;i<5;i++)mapWidth+=(matrix[i]-'0')*(int) Math.pow(10,4-i)*multiplier;
            for (int i = 6;i<11;i++)mapHeight+=(matrix[i]-'0')*(int) Math.pow(10,10-i)*multiplier;

//            mapWidth +=multiplier;//??
//            mapHeight+=multiplier;//??

            System.out.println(mapWidth+" "+mapHeight);
            map = new int[mapWidth][mapHeight];
            int x = 0, y = 0;
            for (int i = 0; i<10; i++) {
                objects[i] = new ArrayList<VisualObject>();
            }
            MainCharacter mainChar = DataProvider.getMainCharacter();
            //BufferedImage bi = new BufferedImage(1000,1000,BufferedImage.TYPE_INT_RGB);
            //WritableRaster raster = bi.getRaster();
            for(int i = 13; i<matrix.length; i++){
                if(matrix[i]==13){
                    i++;
                    y++;
                    x = 0;
                }else {
                    Coordanate coor = new Coordanate(
                            x*multiplier,
                            y*multiplier);

                    ///////////////////////
//                    switch (matrix[i]-'0'){
//                        case 0:
//                            raster.setPixel(x,y,new int[]{136, 178, 211});
//                            break;
//                        case 1:
//                            raster.setPixel(x,y,new int[]{133, 178, 120});
//                            break;
//                        case 2:
//                            raster.setPixel(x,y,new int[]{97, 238, 242});
//                            break;
//                        case 3:
//                            raster.setPixel(x,y,new int[]{0,0,0});
//                            break;
//                        case 4:
//                            raster.setPixel(x,y,new int[]{0,0,0});
//                            break;
//                        case 5:
//                            raster.setPixel(x,y,new int[]{49, 177, 99});
//                            break;
//                        case 6:
//                            raster.setPixel(x,y,new int[]{0,0,0});
//                            break;
//                        case 7:
//                            raster.setPixel(x,y,new int[]{0,0,0});
//                            break;
//                        case 8:
//                            raster.setPixel(x,y,new int[]{0,0,0});
//                            break;
//                        case 9:
//                            raster.setPixel(x,y,new int[]{0,0,0});
//                            break;
//                        default:
//                            raster.setPixel(x,y,new int[]{0,0,0});
//                    }///////////////////////
                    if (matrix[i]-'0'!=0){
                        for(int x1 = x*multiplier-Math.abs(mainChar.getWidth()/4);
                            x1 < x*multiplier+Math.abs(mainChar.getWidth()/4); x1++)
                            for (int y1= y*multiplier-multiplier; y1 < y*multiplier+multiplier; y1++){
                                //System.out.println(x1+" "+y1);
                                if(x1>=0&&x1<mapWidth&&y1>=0&&y1<mapHeight)
                                    map[x1][y1] = 1;
                            }
                        if(matrix[i]-'0'!=1)
                            objects[matrix[i]-'0'].add(DataProvider.getObject(coor,matrix[i]-'0'));
                    }

                    x++;
                }

            }



            ///image = bi.getScaledInstance(1000,1000, Image.SCALE_SMOOTH);

//            try {
//                ImageIO.write(bi,"png",new File("Test.png"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


            //DataProvider.loadObjects();
            mainChar.setMap(map);

//            for (ArrayList<Coordanate> temp:objects) {
//                System.out.println(temp.size());
//            }
            inFile.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[][] getMap() {
        return map;
    }

    public ArrayList<VisualObject>[] getObjects() {
        return objects;
    }
}
