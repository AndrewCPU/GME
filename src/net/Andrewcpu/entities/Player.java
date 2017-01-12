package net.Andrewcpu.entities;

import net.Andrewcpu.entities.utils.Direction;
import net.Andrewcpu.entities.utils.Directionable;
import net.Andrewcpu.entities.utils.EntityType;
import net.Andrewcpu.entities.utils.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by stein on 1/3/2017.
 */
public class Player extends Entity implements Directionable {
    private Direction direction = Direction.LEFT;
    private int health = 20;
    private int maxHealth = 20;
    private Inventory inventory;
    public Player() {
        super(EntityType.PLAYER);
        Random random= new Random();
        setSpriteSheetID(1);
        setSpriteX(random.nextInt(9));
        setSpriteY(random.nextInt(6));
        setGravity(true);
        setW(20);
        setH(20);
        setDistance(0.5);
        noCollideType.add(EntityType.ITEM);
        inventory = new Inventory(this);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void pickup(Item item){
        item.getLocation().getWorld().killEntity(item);
        inventory.addItem(item);
        System.out.println(item.getType());
    }

    @Override
    public void tick(){
        for(Entity entity : getLocation().getWorld().getEntities()){
            if(entity instanceof Item){
                if(entity.distance(this) > 0 && entity.distance(this) <= 2 * getW()){
                    if(entity.getLocation().getSolidX() > getLocation().getSolidX())
                        entity.moveLeft();
                    else if(entity.getLocation().getSolidX() < getLocation().getSolidX())
                        entity.moveRight();
                }
                if(entity.collides(this)){
                    pickup((Item)entity);
                }
            }
        }
        super.tick();
    }
}
