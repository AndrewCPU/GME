package net.Andrewcpu.client.phys;

import net.Andrewcpu.client.utils.Location;

import java.awt.*;

/**
 * Created by stein on 1/7/2017.
 */
public class CollisionBox {
    private int w, h;

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public CollisionBox(int w, int h){
        this.w = w;
        this.h = h;
    }

    public Rectangle getCollisionBox(){
        return new Rectangle(getW(),getH());
    }
    public Rectangle getFullCollisionBox(Location location){
        return new Rectangle(location.getSolidX(), location.getSolidY(), getW(),getH());
    }
}
