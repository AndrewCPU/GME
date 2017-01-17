package net.Andrewcpu.client.engine;

import net.Andrewcpu.client.engine.generators.FunRunGenerator;
import net.Andrewcpu.client.entities.Item;
import net.Andrewcpu.client.entities.Player;
import net.Andrewcpu.client.entities.blocks.*;
import net.Andrewcpu.client.entities.Entity;
import net.Andrewcpu.client.utils.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stein on 1/3/___.getCollisionBox().width17.
 */
public class World {
    private String name;
    private Location spawnPoint = new Location(0,500,this);
    private WorldGenerator generator;
    public World(String name, WorldGenerator generator) {
        this.name = name;
        this.generator = generator;
        generator.generate(this);
    }

    public WorldGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(WorldGenerator generator) {
        this.generator = generator;
    }

    private List<Entity> entities = new ArrayList<>();
    public void createEntity(Entity entity){
        addBuffer.add(entity);
        entity.setLocation(spawnPoint);
    }
    public void createEntity(Entity entity, Location location){
        addBuffer.add(entity);
        entity.setLocation(location);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getSpawnPoint() {
        return spawnPoint;
    }

    public void setSpawnPoint(Location spawnPoint) {
        this.spawnPoint = spawnPoint;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
    private List<Entity> removeBuffer = new ArrayList<>();
    private List<Entity> addBuffer = new ArrayList<>();
    public void killEntity(Entity entity){
        removeBuffer.add(entity);
    }
    public void vSync(){
        entities.removeAll(removeBuffer);
        removeBuffer.clear();
        entities.addAll(addBuffer);
        addBuffer.clear();
        if(getGenerator() instanceof FunRunGenerator) {
            for (Entity e : getEntities()) {
                if (e instanceof Player)
                    e.moveRight(0.25);
            }
        }
    }

    public Entity highestBlockAt(int x){
        Entity e = null;
        for(Entity entity : getEntities()){
            if(entity instanceof Block){
                if(e == null) e = entity;
                if(entity.getLocation().getY() < e.getLocation().getY()) e = entity;
            }
        }
        return e;
    }
    private Block createBlock(BlockType type){
        switch (type){
            case DIRT:
                return new Dirt();
            case GRASS:
                return new Grass();
            case LEAVES:
                return new Leaves();
            case LOG:
                return new Log();
            case STONE:
                return new Stone();
        }
        return new Block();
    }
    public boolean isOccupied(Location location){
        for(Entity entity1 : getEntities()){
            if(entity1.getFullCollisionBox(entity1.getLocation()).contains(new Point(location.getSolidX(), location.getSolidY())))
                return true;
        }
        return false;
    }
    public void placeBlock(Player player, Entity entity){
        if(!getGenerator().getBoolean("PLACE"))
            return;
        Item item = player.getInventory().getItems().get(player.getInventory().getSelectedSlot());
        if(item == null) return;
        Block block = createBlock(item.getType());
        boolean empty = false;
        if(player.getLocation().getY() < entity.getLocation().getY() && !isOccupied(entity.getLocation().add(0,-20))){
            //up
            block.setLocation(entity.getLocation().add(0,-20));
            empty = true;
        }
        else if(player.getLocation().getY() > entity.getLocation().getY()&& !isOccupied(entity.getLocation().add(0,20))){
            //down
            block.setLocation(entity.getLocation().add(0,20));
            empty = true;
        }
        else if(player.getLocation().getX() > entity.getLocation().getX() && !isOccupied(entity.getLocation().add(20,0))){
            //right
            block.setLocation(entity.getLocation().add(20,0));
            empty = true;
        }
        else if(player.getLocation().getX() < entity.getLocation().getX()&& !isOccupied(entity.getLocation().add(-20,0))) {
            //left
            block.setLocation(entity.getLocation().add(-20, 0));
            empty = true;
        }

        if(empty){
            item.setAmount( item.getAmount() - 1 );
            if(item.getAmount() <= 0)
                item = null;
            player.getInventory().setItem(player.getInventory().getSelectedSlot(), item);
            block.spawn();
        }
    }
    public void breakBlock(Block block){
        if(!getGenerator().getBoolean("BREAK"))
            return;
        Item item = new Item(block.getBlockType(), block.getColor());
        item.setLocation(block.getLocation().add(0,1));
        item.spawn();
        killEntity(block);
    }
}
