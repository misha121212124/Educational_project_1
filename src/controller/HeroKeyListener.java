package controller;

import model.Map;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HeroKeyListener implements KeyListener {
    private int flag = 0;
    private int keysPressed[] = new int[200];
    private MainCharacter mainChara = DataProvider.getMainCharacter();
    private int ch1=5,ch2=3,ch=2;
    private boolean mode = true;

    public HeroKeyListener() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true){
                    int mul = 1;
                    if(flag>0){
                        int code = 0;

                        if(keysPressed[40]==1)code+=10;
                        if(keysPressed[39]==1)
                            if(mainChara.getDirr()==0)
                                code+=1;
                            else
                                mainChara.setDirr(0);
                        if(keysPressed[38]==1)code+=20;
                        if(keysPressed[37]==1)
                            if(mainChara.getDirr()==4)
                                code+=2;
                            else
                                mainChara.setDirr(4);
                        if(keysPressed[16]==1)mul = 4;

                        switch (code){
                            case 1:
                                mainChara.setDirr(0);
                                mainChara.changeX(ch1);
                                break;
                            case 2:
                                mainChara.setDirr(4);
                                mainChara.changeX(-ch1);
                                break;
                            case 11:
                                mainChara.setDirr(0);
                                mainChara.changeX(ch2);
                                mainChara.changeY(ch2);
                                break;
                            case 12:
                                mainChara.setDirr(4);
                                mainChara.changeX(-ch2);
                                mainChara.changeY(ch2);
                                break;
                            case 21:
                                mainChara.setDirr(0);
                                mainChara.changeX(ch2);
                                mainChara.changeY(-ch2);
                                break;
                            case 22:
                                mainChara.setDirr(4);
                                mainChara.changeX(-ch2);
                                mainChara.changeY(-ch2);
                                break;
                            case 10:
                                //mainChara.setDirr(0);
                                mainChara.changeY(ch1);
                                break;
                            case 20:
                                //mainChara.setDirr(0);
                                mainChara.changeY(-ch1);
                                break;
                        }
                        mainChara.nextImNumber();
                    }
                    try {
                        Thread.sleep(100/mul/ch);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    int x1=0,x2=0,y1=0,y2=0; byte value = 0;
    int flagg = 0;
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 112){
            mode = !mode;
            return;
        }
        //System.out.println(e.getKeyCode());

        if(mode){
            if(keysPressed[e.getKeyCode()]==0) {
                flag++;
                keysPressed[e.getKeyCode()] = 1;
                //System.out.println(mode);
            }
        }else{
//            if(e.getKeyCode()==27){
//                Map.writeNewMap();
//                DataProvider.getMap();
//            }
            if(e.getKeyCode()==10){
                flagg++;
                //System.out.println(x1+" "+y1+" "+x2+" "+y2);
            }else
                switch (flagg){
                    case 0:
                        x1 = x1*10+e.getKeyCode()-48;
                        break;
                    case 1:
                        y1 = y1*10+e.getKeyCode()-48;
                        break;
                    case 2:
                        x2 = x2*10+e.getKeyCode()-48;
                        break;
                    case 3:
                        y2 = y2*10+e.getKeyCode()-48;
                        break;
                    case 4:
                        value = (byte) (e.getKeyCode()-48);
                        flagg++;
                        break;
                }
            if(flagg ==5){
                flagg=0;
                //System.out.println(x1+" "+y1+" "+x2+" "+y2+" "+value);
                Map.writeData(x1,y1,x2,y2,value);
                x1=0;x2=0;y1=0;y2=0;
                Map.writeNewMap();
                DataProvider.setMap();
            }
            //System.out.println(flagg);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println(DataProvider.getMainCharacter().getCoordinate().getX()+" "+DataProvider.getMainCharacter().getCoordinate().getY());
        if(flag>0){
            flag--;
            keysPressed[e.getKeyCode()]=0;
        }

    }
}
