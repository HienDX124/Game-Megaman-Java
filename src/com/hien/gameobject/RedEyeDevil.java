package com.hien.gameobject;

import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.hien.effect.Animation;
import com.hien.effect.CacheDataLoader;

public class RedEyeDevil extends ParticularObject{

    private Animation forwardAnim, backAnim;
    private long startTimeToShoot;
    private AudioClip shooting;


    public RedEyeDevil(float x, float y, GameWorld gameWorld) {
        super(x, y, 127, 89, 0, 100, gameWorld);
        backAnim = CacheDataLoader.getInstance().getAnimation("redeye");
        forwardAnim = CacheDataLoader.getInstance().getAnimation("redeye");
        forwardAnim.flipAllImage();

        startTimeToShoot = 0;
        setDamage(10);
        setTimeForNoBeHurt(300*1000000);
        shooting = CacheDataLoader.getInstance().getSound("redeyeshooting");

    }

    @Override
    public void attack() {
        shooting.play();
        Bullet bullet = new RedEyeBullet(getPosX(), getPosY(), getGameWorld())        ;
        if (getDirection() == LEFT_DIR) bullet.setSpeedX(-5);
        else bullet.setSpeedX(5);
        bullet.setTeamType(getTeamType());
        getGameWorld().bulletManager.addObject(bullet);
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();
        rect.x += 20;
        rect.width -= 40;
        return rect;
    }

    @Override
    public void Update() {
        super.Update();
        if (System.nanoTime() - startTimeToShoot > 1000*1000000 )  {
            attack();

            startTimeToShoot = System.nanoTime();
        }
    }


    @Override
    public void draw(Graphics2D g2) {

        if (!isObjectOutOfCameraView()) {
            if(getState() == NOBEHURT && (System.nanoTime()/10000000 % 2 != 1)) {

            }
            else {
                if (getDirection() == LEFT_DIR) {
                    backAnim.Update(System.nanoTime());
                    backAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
                                  (int) getPosY() - (int) getGameWorld().camera.getPosY(), 
                                  g2);
                }
                else {
                    forwardAnim.Update(System.nanoTime());
                    forwardAnim.draw( (int) (getPosX() - getGameWorld().camera.getPosX()),
                                      (int) getPosY() - (int)getGameWorld().camera.getPosY(),
                                      g2);
                }
            }
        }   
        // drawBoundForCollisionWithEnemy(g2);   
    }
}
