package controller;

import java.awt.*;
public class GameController {

    private static boolean access = true;


    public static void startGame(){
        if(access){
            access = false;
            LogicThread logicThread = new LogicThread();
            logicThread.start();
        }
    }
}
