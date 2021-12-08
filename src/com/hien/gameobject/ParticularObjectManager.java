package com.hien.gameobject;

import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;



public class ParticularObjectManager {
    
    protected List<ParticularObject> particularObjects;

    private GameWorld gameWorld;
    
    public ParticularObjectManager(GameWorld gameWorld) {
        particularObjects = Collections.synchronizedList(new LinkedList<ParticularObject>());
        this.gameWorld = gameWorld;

    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public void addObject(ParticularObject particularObject){
        synchronized(particularObjects) {
            particularObjects.add(particularObject);
        }
    }

    public void removeObject(ParticularObject particularObject) {
        synchronized(particularObjects) {
            for (int id = 0; id < particularObjects.size(); id++) {
                ParticularObject object = particularObjects.get(id);
                if (object == particularObject) {
                    particularObjects.remove(id);
                }
            }
        }
    } 

    public ParticularObject getCollisionWithEnemyObject(ParticularObject object) {
        synchronized(particularObjects) {
            for (int id = 0; id < particularObjects.size(); id++){
                ParticularObject objectInList = particularObjects.get(id);
                if (object.getTeamType() != objectInList.getTeamType() &&
                    object.getBoundForCollisionWithEnemy().intersects(objectInList.getBoundForCollisionWithEnemy())) {
                        return objectInList;
                    }
            }
        }
        return null;
    }

    public void UpdateObject() {
        synchronized(particularObjects) {
            for (int id = 0; id < particularObjects.size(); id ++) {
                ParticularObject object = particularObjects.get(id);
                if (!object.isObjectOutOfCameraView()) object.Update();

                if (object.getState() == ParticularObject.DEATH) {
                    particularObjects.remove(id);
                }
            }
        }
    }

    public void draw(Graphics2D g2){
        synchronized(particularObjects){
            for(ParticularObject object: particularObjects)
                if(!object.isObjectOutOfCameraView()) object.draw(g2);
        }
    }
	

}
