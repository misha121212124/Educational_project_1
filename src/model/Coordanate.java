package model;

public class Coordanate{

    private int x;
    private int y;
//    private int correctionX = 0;
//    private int correctionY = 0;
//    private boolean flag = true;
    public Coordanate(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(((Coordanate)obj).x == this.x &&
                ((Coordanate)obj).y == this.y)
            return true;
        else return false;
    }
    public boolean isBetween(int x1,int y1, int x2, int y2){
        if(x<x1||x>x2||y<y1||y>y2)return false;
        else return true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

//    public int getAbsoluteX(){
//        return x + correctionX;
//    }
//
//    public int getAbsoluteY(){
//        return y + correctionY;
//    }
//
//    public void correctCoor(int x, int y){
//        if(flag){
//            this.correctionX = x;
//            this.correctionY = y;
//            flag = false;
//        }
//    }
}
