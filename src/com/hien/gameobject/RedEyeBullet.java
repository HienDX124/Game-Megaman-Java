package com.hien.gameobject;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.hien.effect.Animation;
import com.hien.effect.CacheDataLoader;

public class RedEyeBullet extends Bullet {
    private Animation forwardBulletAnim, backBulletAnim;

    public RedEyeBullet(float x, float y, GameWorld gameWorld) {
        super(x ,y , 30, 30, 1.0f, 10, gameWorld);
        forwardBulletAnim = CacheDataLoader.getInstance().getAnimation("redeyebullet");
        backBulletAnim =  CacheDataLoader.getInstance().getAnimation("redeyebullet");
        backBulletAnim.flipAllImage();
    }

    @Override
    public void draw(Graphics2D g2) {

        if (getSpeedX() > 0) {
            forwardBulletAnim.Update(System.nanoTime());
            forwardBulletAnim.draw( (int) (getPosX() - getGameWorld().camera.getPosX()),
                                    (int) getPosY() - (int) getGameWorld().camera.getPosY(),
                                    g2);
        }
        else {
            backBulletAnim.Update(System.nanoTime());
            backBulletAnim.draw( (int) (getPosX() - getGameWorld().camera.getPosX()),
                                    (int) getPosY() - (int) getGameWorld().camera.getPosY(),
                                    g2);
        }
    }

    @Override
    public void Update() {
        // TODO Auto-generated method stub
        super.Update();
    }

    @Override
    public void attack() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithMap();
    }

}
