package net.Andrewcpu.entities;

import net.Andrewcpu.entities.utils.EntityType;
import net.Andrewcpu.phys.Body;
import net.Andrewcpu.utils.Location;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stein on 1/3/2017.
 */
public class Entity extends Body{
    private BufferedImage sprite = null;
    private EntityType entityType;
    private Location location;
    private boolean alive = false;
    private int spriteSheetID = 0;
    private int spriteX, spriteY;
    private double distance = 1;
    private boolean gravity = false;
    protected List<EntityType> noCollideType = new ArrayList<>();
    private boolean onGround = false;

    public Entity(EntityType entityType) {
        super(20,20);
        this.entityType = entityType;
    }


    public void moveLeft(){
        moveLeft(1);
    }
    public void moveRight(){
        moveRight(1);
    }
    public void collisionDetection(double d){
        while(isColliding()){
            setLocation(getLocation().add(-d ,0));
        }
    }
    private boolean isColliding(){
        for(Entity entity : location.getWorld().getEntities()){
            if(entity != this && !noCollideType.contains(entity.getEntityType())){
                if(entity.collides(this))
                    return true;
            }
        }
        return false;
    }
    public void moveLeft(double d){
        setLocation(getLocation().add(-d,0)); //todo collision detection
        collisionDetection(-d);
    }
    public void moveRight(double d){
        setLocation(getLocation().add(d,0));
        collisionDetection(d);
    }


    public List<EntityType> getNoCollideType() {
        return noCollideType;
    }

    public void setNoCollideType(List<EntityType> noCollideType) {
        this.noCollideType = noCollideType;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getSpriteSheetID() {
        return spriteSheetID;
    }

    public void setSpriteSheetID(int spriteSheetID) {
        this.spriteSheetID = spriteSheetID;
    }

    public int getSpriteX() {
        return spriteX;
    }

    public void setSpriteX(int spriteX) {
        this.spriteX = spriteX;
    }

    public int getSpriteY() {
        return spriteY;
    }

    public void setSpriteY(int spriteY) {
        this.spriteY = spriteY;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void spawn(){
        alive = true;
        location.getWorld().createEntity(this, getLocation());
    }

    public boolean hasGravity() {
        return gravity;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public void jump(){
        if(isOnGround())
            setYVel(-5);
    }

    public void tick(){
        if(hasGravity())
        {
            onGround = false;
            super.tick();
            int y = getLocation().getSolidY();
            setLocation(getLocation().add(0,getYVel()));
            for(Entity e : getLocation().getWorld().getEntities()){
                if(e != this && e.collides(this) && !noCollideType.contains(e.getEntityType())){
                    setLocation(getLocation().add(0,-getYVel() ));
                    setYVel(0);
                    if(e.getLocation().getSolidY() > getLocation().getSolidY())
                        onGround = true;
                }
            }
            if(getLocation().getY()>=1000)
                setLocation(new Location(getLocation().getX(), 0, getLocation().getWorld()));
        }
    }

    public boolean collides(Entity e){
        return e.getFullCollisionBox(e.getLocation()).intersects(getFullCollisionBox(getLocation()));
    }

    public double distance(Entity e){
        return distance(e.getLocation());
    }
    private double distance(Location location){
        return distance(new Point(location.getSolidX(),location.getSolidY()));
    }
    private double distance(Point p){
        return new Point(getLocation().getSolidX(),getLocation().getSolidY()).distance(p.x,p.y);
    }

}
