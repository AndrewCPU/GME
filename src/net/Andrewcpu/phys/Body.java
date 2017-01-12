package net.Andrewcpu.phys;

/**
 * Created by stein on 1/7/2017.
 */
public class Body extends CollisionBox{
    private double yVel;

    public Body(int w, int h) {
        super(w,h);
    }

    public int getYVel() {
        return (int)yVel;
    }

    public void setYVel(int yVel) {
        this.yVel = yVel;
    }

    public void tick(){
        yVel+=0.25;
        if(yVel > 5)
            yVel = 5;
    }
}
