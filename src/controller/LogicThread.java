package controller;

public class LogicThread extends Thread {

    private static int dirr = 0;

    @Override
    public void run() {
        while (true) {
            //cycle();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
//    private  void cycle(){
//        dirr = getDirr();
//        switch (dirr){
//            case 0:changeX(10);break;
//            case 1:changeX(10);break;
//            case 3:changeX(-10);break;
//            case 4:changeX(-10);break;
//        }
//        if(dirr){
//            changeX(10);
//        }else{
//            changeX(-10);
//        }
//
//        //changeY(1);
//        nextImNumber();
//    }
}
