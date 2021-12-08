package com.hien.userinterface;

import java.awt.event.KeyEvent;

import javax.sound.sampled.LineUnavailableException;

import java.applet.AudioClip;


import com.hien.gameobject.GameWorld;

public class InputManager {

    private GameWorld gameWorld;

    public InputManager(GameWorld gameWorld){
        this.gameWorld = gameWorld;
    }
    public void setPressedButton(int code) {
        
        switch (code) {

            case KeyEvent.VK_I: {
                gameWorld.bgMusic.play();
                break;
            }
            case KeyEvent.VK_UP: {

                break;
            }
            case KeyEvent.VK_DOWN: {
                gameWorld.megaman.dick();
                break;
            }

            case KeyEvent.VK_LEFT:{
                gameWorld.megaman.setDirection(gameWorld.megaman.LEFT_DIR);
                gameWorld.megaman.run();

                break;
            }
    
            case KeyEvent.VK_RIGHT: {
                gameWorld.megaman.setDirection(gameWorld.megaman.RIGHT_DIR);
                gameWorld.megaman.run();                

                break;
            }
        
            case KeyEvent.VK_ENTER: {
                if (gameWorld.state == GameWorld.PAUSEGAME) {
                    if (gameWorld.previousState == GameWorld.GAMEPLAY)
                        gameWorld.switchState(GameWorld.GAMEPLAY);
                    else gameWorld.switchState(GameWorld.TUTORIAL);

                    try {
                        gameWorld.getBgMusic("data/bgmusic.wav").start();
                    } catch (LineUnavailableException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if (gameWorld.state == GameWorld.TUTORIAL && gameWorld.storyTutorial >= 1) {
                    if (gameWorld.storyTutorial <= 3) {
                        gameWorld.storyTutorial ++;
                        gameWorld.currentSize = 1;
                        gameWorld.textTutorial = gameWorld.textsl[gameWorld.storyTutorial-1];
                    } 
                    else {
                        gameWorld.switchState(GameWorld.GAMEPLAY);
                    }
                }
            }
                break;
            case KeyEvent.VK_A:
                gameWorld.megaman.attack();

                break;

            case KeyEvent.VK_SPACE: {
                    gameWorld.megaman.jump();
            }
                break;

            default:
                break;
        }

    }

    public void setReleasedButton(int code) {

        switch (code) {
            case KeyEvent.VK_UP:
                break;
            
            case KeyEvent.VK_DOWN:
                gameWorld.megaman.standUp();
                break;
    

                case KeyEvent.VK_RIGHT:{
                    if (gameWorld.megaman.getSpeedX() > 0) {
                        gameWorld.megaman.stopRun();
                    }
                    break;
                }
                
                case KeyEvent.VK_LEFT:{
                    if (gameWorld.megaman.getSpeedX() <   0) {
                        gameWorld.megaman.stopRun();
                    }
                    break;
                }
    


        
            case KeyEvent.VK_ENTER:
                
                break;
        
            case KeyEvent.VK_A:
                
                break;

            case KeyEvent.VK_SPACE:

                break;
        
            default:
                break;
        }

    }

}
