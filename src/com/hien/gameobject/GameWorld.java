package com.hien.gameobject;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics2D;
// import java.lang.Object;
// import javafx.scene.media.MediaPlayer;

import com.hien.effect.CacheDataLoader;
import com.hien.effect.FrameImage;
import com.hien.userinterface.GameFrame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
  
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
 
import javax.lang.model.util.ElementScanner6;

public class GameWorld {
   
    //  TEMP: START
    

    AudioInputStream audioInputStream;
    public Clip getBgMusic(String audioPath) throws LineUnavailableException {
        try {
            audioInputStream = 
                    AudioSystem.getAudioInputStream(new File(audioPath).getAbsoluteFile());
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Clip clip;
        clip = AudioSystem.getClip();
        try {
            clip.open(audioInputStream);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        return clip;
    }

    //  TEMP: END
    

    private BufferedImage bufferedImage;

    public ParticularObjectManager particularObjectManager;
    public BulletManager bulletManager;

    public Megaman megaman;

    public PhysicalMap physicalMap;
    public Camera camera;
    public BackgroundMap backgroundMap;

    public static final int finalBossX = 3600;

    public static final int PAUSEGAME = 0;
    public static final int TUTORIAL = 1;
    public static final int GAMEPLAY = 2;
    public static final int GAMEOVER = 3;
    public static final int GAMEWIN = 4;

    public static final int INTROGAME = 0;
    public static final int MEETFINALBOSS = 1;

    public int openIntroGameY = 0;
    public int state = PAUSEGAME;
    public int previousState = state;
    public int tutorialState = INTROGAME;
    
    public int storyTutorial = 0;
    public String[] textsl = new String[4];

    public String textTutorial;
    public int currentSize;

    private boolean finalbossTrigger = true;
    ParticularObject boss;

    FrameImage avatar = CacheDataLoader.getInstance().getFrameImage("avatar");

    private int numberOflife = 3;

    public AudioClip bgMusic;
    ParticularObject redeyd2;

    public GameWorld() {
        
        textsl[0] = "Hello anh Tiệp!!";
        textsl[1] = "Cuối cùng cũng xong.";
        textsl[2] = "Tiếp theo em sẽ bắt đầu làm Naruto solo.";
        textsl[3] = "Còn bây giờ, đi bắn quái vật thoiiiii!!!";
        textTutorial = textsl[0];
        currentSize = 0;

        bufferedImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        megaman = new Megaman(2000, 400, this);
        physicalMap = new PhysicalMap(0, 0, this);
        backgroundMap = new BackgroundMap(0, 0, this);
        camera = new Camera(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, this);
        bulletManager = new BulletManager(this);

        megaman.setTeamType(ParticularObject.LEAGUE_TEAM);

        particularObjectManager = new ParticularObjectManager(this);
        particularObjectManager.addObject(megaman);
        
        initEnemies();
        
        bgMusic = CacheDataLoader.getInstance().getSound("bgmusic");
    }

    private void initEnemies() {
        ParticularObject redeyd1 = new  RedEyeDevil(1150, 410, this);
        if (megaman.getPosX() > 1150)
            redeyd1.setDirection(ParticularObject.RIGHT_DIR);
        else redeyd1.setDirection(ParticularObject.LEFT_DIR);
        redeyd1.setTeamType(ParticularObject.ENERMY_TEAM);
        particularObjectManager.addObject(redeyd1);

        redeyd2 = new RedEyeDevil(2500, 500, this);
        redeyd2.setTeamType(ParticularObject.ENERMY_TEAM);
        particularObjectManager.addObject(redeyd2);

        // ParticularObject redeyd2_1 = new  RedEyeDevil(2600, 500, this);
        // redeyd2_1.setDirection(ParticularObject.LEFT_DIR);
        // redeyd2_1.setTeamType(ParticularObject.ENERMY_TEAM);
        // particularObjectManager.addObject(redeyd2_1);

        // ParticularObject redeyd2_2 = new  RedEyeDevil(2700, 500, this);
        // redeyd2_2.setDirection(ParticularObject.LEFT_DIR);
        // redeyd2_2.setTeamType(ParticularObject.ENERMY_TEAM);
        // particularObjectManager.addObject(redeyd2_2);

        // ParticularObject redeyd2_3 = new  RedEyeDevil(2800, 500, this);
        // redeyd2_3.setDirection(ParticularObject.LEFT_DIR);
        // redeyd2_3.setTeamType(ParticularObject.ENERMY_TEAM);
        // particularObjectManager.addObject(redeyd2_3);

        // ParticularObject redeyd2_4 = new  RedEyeDevil(2900, 500, this);
        // redeyd2_4.setDirection(ParticularObject.LEFT_DIR);
        // redeyd2_4.setTeamType(ParticularObject.ENERMY_TEAM);
        // particularObjectManager.addObject(redeyd2_4);

        // ParticularObject redeyd2_5 = new  RedEyeDevil(3000, 500, this);
        // redeyd2_5.setDirection(ParticularObject.LEFT_DIR);
        // redeyd2_5.setTeamType(ParticularObject.ENERMY_TEAM);
        // particularObjectManager.addObject(redeyd2_5);

        // ParticularObject redeyd2_6 = new  RedEyeDevil(3100, 500, this);
        // redeyd2_6.setDirection(ParticularObject.LEFT_DIR);
        // redeyd2_6.setTeamType(ParticularObject.ENERMY_TEAM);
        // particularObjectManager.addObject(redeyd2_6);

        
        ParticularObject redeyd4 = new  RedEyeDevil(500, 1190, this);
        redeyd4.setDirection(ParticularObject.RIGHT_DIR);
        redeyd4.setTeamType(ParticularObject.ENERMY_TEAM);
        particularObjectManager.addObject(redeyd4);

    }

    public void switchState(int state) {
        previousState = this.state;
        this.state = state;
    }

    public void TutorialUpdate() {
        switch(tutorialState) {
            case INTROGAME: {
                if (storyTutorial == 0) {
                    if (openIntroGameY < 450) {
                        openIntroGameY += 4;
                    } 
                    else storyTutorial ++;
                } 
                else {
                    if (currentSize < textTutorial.length()) currentSize ++;
                }
                break;
            }        
            
            case MEETFINALBOSS: {
                if (storyTutorial == 0) {
                    if (openIntroGameY >= 450) {
                        openIntroGameY -=1;
                    }

                    if (camera.getPosX() < finalBossX) {
                        camera.setPosX(camera.getPosX() + 2);
                    }

                    if (megaman.getPosX() < finalBossX + 150) {
                        megaman.setDirection(ParticularObject.RIGHT_DIR);
                        megaman.run();
                        megaman.Update();
                    } 
                    else {
                        megaman.stopRun();
                    }
                    if (openIntroGameY < 450 && camera.getPosX() >= finalBossX && megaman.getPosX() >= finalBossX + 150) {
                        camera.lock();
                        storyTutorial++;
                        megaman.stopRun();
                        physicalMap.phys_map[14][120] = 1;
                        physicalMap.phys_map[15][120] = 1;
                        physicalMap.phys_map[16][120] = 1;
                        physicalMap.phys_map[17][120] = 1;
                        
                        backgroundMap.map[14][120] = 17;
                        backgroundMap.map[15][120] = 17;
                        backgroundMap.map[16][120] = 17;
                        backgroundMap.map[17][120] = 17;
                        
                    }

                } else {
                    if (currentSize < textTutorial.length()) currentSize ++;
                }
                break;
            }
        }
    }

    public void drawString(Graphics2D g2, String text, int x, int y) {
        for(String str : text.split("\n"))
            g2.drawString(str, x, y+=g2.getFontMetrics().getHeight());
    }

    private void TutorialRender (Graphics2D g2) {
        switch(tutorialState) {
            case INTROGAME: {
                int yMid = GameFrame.SCREEN_HEIGHT/2 - 15;
                int y1 = yMid - GameFrame.SCREEN_HEIGHT/2 - openIntroGameY/2;
                int y2 = yMid + openIntroGameY/2;

                g2.setColor(Color.BLACK);
                g2.fillRect(0, y1, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT/2);
                g2.fillRect(0, y2, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT/2);

                if (storyTutorial >= 1) {
                    g2.drawImage(avatar.getImage(), 600, 350, null);
                    g2.setColor(Color.BLUE);
                    g2.fillRect(280, 450, 350, 80);
                    g2.setColor(Color.WHITE);
                    currentSize = textTutorial.length();
                    String text = textTutorial.substring(0, currentSize - 1);
                    drawString(g2, text, 290, 480);
                }
                break;
            }

            case MEETFINALBOSS: {
                int yMid = GameFrame.SCREEN_HEIGHT/2 - 15;
                int y1 = yMid - GameFrame.SCREEN_HEIGHT/2 - openIntroGameY/2;
                int y2 = yMid + openIntroGameY/2;

                g2.setColor(Color.BLACK);
                g2.fillRect(0, y1, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT/2);
                g2.fillRect(0, y2, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT/2);
                break;
            }
        }
    }

    public void Update() {


        // camera.Update();
        // bulletManager.UpdateObject();
        // particularObjectManager.UpdateObject();

        switch(state) {
            case PAUSEGAME: {
                break;
            }

            case TUTORIAL: {
                TutorialUpdate();
                break;
            }

            case GAMEPLAY: {
                particularObjectManager.UpdateObject();
                bulletManager.UpdateObject();
                physicalMap.Update();
                camera.Update();

                if (megaman.getPosX() > redeyd2.getPosX())
                    redeyd2.setDirection(ParticularObject.RIGHT_DIR);
                else redeyd2.setDirection(ParticularObject.LEFT_DIR);

                if (megaman.getPosX() > finalBossX && finalbossTrigger) {
                    finalbossTrigger = false;
                    switchState(TUTORIAL);
                    tutorialState = MEETFINALBOSS;
                    storyTutorial = 0;
                    openIntroGameY = 550;

                    boss = new FinalBoss(finalBossX + 700, 460, this);
                    boss.setTeamType(ParticularObject.ENERMY_TEAM);
                    boss.setDirection(ParticularObject.LEFT_DIR);
                    particularObjectManager.addObject(boss);
                }

                if (megaman.getState() == ParticularObject.DEATH) {
                    numberOflife --;
                    if (numberOflife >= 0) {
                        megaman.setBlood(100);
                        megaman.setPosY(megaman.getPosY() - 50);
                        megaman.setState(ParticularObject.NOBEHURT);
                        particularObjectManager.addObject(megaman);
                    }
                    else {
                        switchState(GAMEOVER);
                        bgMusic.stop();
                    }
                }

                if (!finalbossTrigger && boss.getState() == ParticularObject.DEATH) {
                    switchState(GAMEWIN);
                }
                break;
            }
            case GAMEOVER: {
                break;
            }


        }
    }
    
    public void Render(Graphics2D g2) {
        // physicalMap.draw(g2);
        // backgroundMap.draw(g2);
        // particularObjectManager.draw(g2);
        // bulletManager.draw(g2);

        // Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics();
        if (g2 != null) {
            switch(state) {
                case PAUSEGAME: {
                    g2.setColor(Color.BLACK);
                    g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
                    g2.setColor(Color.WHITE);
                    g2.drawString("PressEnter to continue", 400, 300);
                    break;
                }
                case TUTORIAL: {
                    backgroundMap.draw(g2);
                    // physicalMap.draw(g2);
                    if (tutorialState == MEETFINALBOSS) {
                        particularObjectManager.draw(g2);
                    }
                    TutorialRender(g2);
                    break;
                }
                case GAMEWIN:{
                    // g2.drawImage(CacheDataLoader.getInstance().getFrameImage("gamewin").getImage(), 300, 300, null);
                    // System.out.println("You winnnnnnnnnnn");
                    // break;
                }

                case GAMEPLAY: {
                    backgroundMap.draw(g2);
                    particularObjectManager.draw(g2);
                    bulletManager.draw(g2);

                    g2.setColor(Color.GRAY);
                    g2.fillRect(19, 59, 102, 22);
                    g2.setColor(Color.RED);
                    g2.fillRect(20, 60, megaman.getBlood(), 20);

                    for (int i = 0; i < numberOflife; i++) {
                        g2.drawImage(CacheDataLoader.getInstance().getFrameImage("hearth").getImage(), 20+i*40, 18, null);
                    }

                    if (state == GAMEWIN) {
                        g2.drawImage(CacheDataLoader.getInstance().getFrameImage("gamewin").getImage(), 300, 300, null);
                        // System.out.println("You winnnnnnnnnnn");
                    }

                    break;
                }
                case GAMEOVER: {
                    g2.setColor(Color.BLACK);
                    g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
                    g2.setColor(Color.WHITE);
                    g2.drawString("GAME OVER", 450, 300);;
                    break;
                }
            }
        }


    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

}
