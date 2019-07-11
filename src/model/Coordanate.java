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

    private void setCoor(int x, int y){
        this.x =x;
        this.y =y;
    }

    public void setCoor(Coordanate coor, int x, int y){
        coor.setCoor(x,y);
    }
}
