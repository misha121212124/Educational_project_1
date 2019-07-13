package model;

import controller.DataProvider;
import controller.GameController;
import controller.HeroKeyListener;
import controller.HeroMouseListener;
import view.MainField;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;


public class Renderer{

    private static JFrame jFrame;
    private static MainField mainField;

    private static int fieldWidth = 0;
    private static int fieldHeight = 0;

    private static final int GAME_WIDTH = 400;
    private static final int GAME_HEIGHT = 250;

    private static int gameWidth = 0;
    private static int gameHeight = 0;


    Renderer(){
        init();
    }

    public static int getGameWidth() {
        return GAME_WIDTH;
    }

    public static int getGameHeight() {
        return GAME_HEIGHT;
    }

    public static int getFieldWidth() {
        return fieldWidth;
    }

    public static int getFieldHeight() {
        return fieldHeight;
    }

    private static void getBestSize(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        boolean done = false;
        while(!done){
            fieldWidth +=GAME_WIDTH;
            fieldHeight +=GAME_HEIGHT;

            if(fieldHeight>screenSize.width || fieldHeight>screenSize.height){
                fieldWidth -=GAME_WIDTH;
                fieldHeight -=GAME_HEIGHT;
                done = true;
            }
        }

        int xDiff = screenSize.width-fieldWidth;
        int yDiff = screenSize.height-fieldHeight;
        int factor = fieldWidth / GAME_WIDTH;

        DataProvider.setFactor(factor);

        gameWidth = fieldWidth / factor + xDiff/factor;
        gameHeight = fieldHeight / factor + yDiff/factor;

        fieldWidth = screenSize.width;
        fieldHeight = screenSize.height;

    }

    private void init() {
        getBestSize();

        jFrame = new JFrame();

        mainField = new MainField();
        jFrame.addKeyListener(new HeroKeyListener());
        jFrame.addMouseListener(new HeroMouseListener());
        DataProvider.setDimension(Toolkit.getDefaultToolkit().getScreenSize());
        mainField.setPreferredSize(DataProvider.getDimension());
        jFrame.add(mainField);
        makeFullscreen();
        jFrame.pack();
        jFrame.setResizable(false);

        jFrame.setTitle("Application");
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Game.quit();
            }
        });

        GameController.startGame();
    }

    private static void makeFullscreen(){
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = env.getDefaultScreenDevice();

        if(graphicsDevice.isFullScreenSupported()){
            jFrame.setUndecorated(true);
            graphicsDevice.setFullScreenWindow(jFrame);
        }
    }
}