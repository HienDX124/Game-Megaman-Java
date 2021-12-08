package com.hien.userinterface;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics2D;

import com.hien.effect.Animation;
import com.hien.effect.CacheDataLoader;
import com.hien.effect.FrameImage;
import com.hien.gameobject.GameWorld;
import com.hien.gameobject.Megaman;
import com.hien.gameobject.PhysicalMap;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    
    Thread thread;
    boolean isRunning;
    InputManager inputManager;

    private BufferedImage bufImage;
    private Graphics2D buf2D;

    public GameWorld gameWorld;

    public GamePanel() {

        gameWorld = new GameWorld();
        inputManager = new InputManager(gameWorld);
        bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);

    }

    public void UpdateGame() {
        gameWorld.Update();
    }; 

    public void RenderGame() {
        if (bufImage == null) {
            bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT,BufferedImage.TYPE_INT_ARGB);
        }

        if (bufImage != null) {
            buf2D = (Graphics2D) bufImage.getGraphics();
        }

        if (buf2D != null) {
            buf2D.setColor(new Color(255,255,255));
            // buf2D.fillRect(0, 0,GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);

            gameWorld.Render(buf2D);

        }
    }

    @Override
    public void paint (Graphics g) {
        g.drawImage(bufImage, 0, 0, this);

    }

    @Override
    public void run() {
        long FPS = 80;
        long period = 1000* 1000000 / FPS;
        long beginTime;
        long sleepTime;

        beginTime = System.nanoTime();
        while(isRunning) {

            UpdateGame();
            RenderGame();

            repaint();

            long deltaTime = System.nanoTime() - beginTime;
            sleepTime = period - deltaTime;

            try {
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime/1000000);
                }
                else Thread.sleep(period/2000000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            beginTime = System.nanoTime();

        }

    }

    public void startGame() {
        if(thread == null) {
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputManager.setPressedButton(e.getKeyCode());
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputManager.setReleasedButton(e.getKeyCode());

    }

        
}
