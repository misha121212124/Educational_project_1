package model;

import controller.GameController;

import java.awt.*;

public class Game {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Renderer ex = new Renderer();
        });

//            ArrayList<Coordanate> list = new ArrayList<>();
//            list.add(new Coordanate(10,15));
//            if(list.contains(new Coordanate(10,16))) System.out.println("!!");
//            else System.out.println("упс");
        //GameController.startGame();
    }
    public static void quit(){
        System.exit(0);
    }
}
