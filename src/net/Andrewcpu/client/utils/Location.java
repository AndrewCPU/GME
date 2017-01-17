package net.Andrewcpu.client.utils;

import net.Andrewcpu.client.engine.World;

/**
 * Created by stein on 1/3/2017.
 */
public class Location {
    private double x, y;
    private World world;

    public Location(double x, double y, World world) {
        this.x = x;
        this.y = y;
        this.world = world;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public World getWorld() {
        return world;
    }

    public int getSolidX(){
        return (int)getX();
    }
    public int getSolidY(){
        return (int)getY();
    }

    public void setWorld(World world) {
        this.world = world;
    }
    public Location add(double x, double y){
        return new Location(x + getX(), y + getY(), getWorld());
    }

    @Override
    public String toString(){
        return getX() + ", " + getY() + ", " + getWorld().getName();
    }
}
