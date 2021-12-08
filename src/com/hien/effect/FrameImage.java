package com.hien.effect;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class FrameImage {
    
    private String name;
    private BufferedImage image;
    
    public FrameImage(String name, BufferedImage image) {
        this.name = name;
        this.image = image;
    }

    public FrameImage(FrameImage frameImage){
        image = new BufferedImage(frameImage.getImageWidth(), 
                                    frameImage.getImageHeight(), 
                                    frameImage.getImage().getType());
        Graphics g = image.getGraphics();
        g.drawImage(frameImage.getImage(), 0, 0, null);
        name = frameImage.getName();
    }

    public FrameImage() {
        this.name = null;
        image = null;
    }


    public void draw(int x, int y,Graphics2D g2) {
        g2.drawImage(image, x - image.getWidth()/2, y - image.getHeight()/2, null);
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getImageWidth() {
        return image.getWidth();
    }

    public int getImageHeight() {
        return image.getHeight();
    }

}
