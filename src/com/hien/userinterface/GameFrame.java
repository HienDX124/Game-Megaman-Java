package com.hien.userinterface;

import javax.swing.JFrame;

import com.hien.effect.CacheDataLoader;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

public class GameFrame extends JFrame {

    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 600;
    GamePanel gamePanel;

    public GameFrame() {
        
        Toolkit toolkit = this.getToolkit();
        Dimension dimension = toolkit.     getScreenSize();

        this.setBounds((dimension.width - SCREEN_WIDTH)/2, (dimension.height - SCREEN_HEIGHT)/2, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        double timeStartLoading = System.nanoTime();
        try {
            CacheDataLoader.getInstance().LoadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.nanoTime() - timeStartLoading);
        gamePanel = new GamePanel();
        add(gamePanel);
        this.addKeyListener(gamePanel);
    }

    public void startGame() {
        gamePanel.startGame();

    }

    public static void main(String args[]) {
        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true);
        gameFrame.startGame();
    }   

}
