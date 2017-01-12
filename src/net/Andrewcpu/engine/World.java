package net.Andrewcpu.engine;

import net.Andrewcpu.Game;
import net.Andrewcpu.entities.blocks.*;
import net.Andrewcpu.entities.Entity;
import net.Andrewcpu.utils.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by stein on 1/3/___.getCollisionBox().width17.
 */
public class World {
    private String name;
    private Location spawnPoint = new Location(500,500,this);
    public World(String name) {
        this.name = name;
        int lastTree= -10;
        Random random = new Random();
        for(int i = -100; i<=100; i++){
            for(int y = 0; y<= 10; y++){
                if(y == 0){
                    Grass block = new Grass();
                    block.setLocation(new Location(i * block.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight(), this));
                    block.spawn();
                    block.setDistance(1);
                    if(random.nextInt(15) == 2){
                        int h = random.nextInt(7 - 3) + 3;
                        for(int l = 1; l<=h; l++){
                            Log log = new Log();
                            log.setLocation(new Location(i * log.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() - (l * log.getH()), this));
                            log.spawn();
                            log.setDistance(1);
                        }
                        Leaves leaves = new Leaves();
                        leaves.setLocation(new Location(i * leaves.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() - ((h + 1) * leaves.getH()),this));
                        leaves.spawn();
                        leaves.setDistance(1);

                        leaves = new Leaves();
                        leaves.setLocation(new Location((i - 1) * leaves.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() - ((h) * leaves.getH()),this));
                        leaves.spawn();
                        leaves.setDistance(1);

                        leaves = new Leaves();
                        leaves.setLocation(new Location((i + 1) * leaves.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() - ((h) * leaves.getH()),this));
                        leaves.spawn();
                        leaves.setDistance(1);

                        leaves = new Leaves();
                        leaves.setLocation(new Location((i - 1) * leaves.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() - ((h - 1) * leaves.getH()),this));
                        leaves.spawn();
                        leaves.setDistance(1);

                        leaves = new Leaves();
                        leaves.setLocation(new Location((i + 1) * leaves.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() - ((h - 1) * leaves.getH()),this));
                        leaves.spawn();
                        leaves.setDistance(1);

                        leaves = new Leaves();
                        leaves.setLocation(new Location((i - 2) * leaves.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() - ((h - 1) * leaves.getH()),this));
                        leaves.spawn();
                        leaves.setDistance(1);

                        leaves = new Leaves();
                        leaves.setLocation(new Location((i + 2) * leaves.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() - ((h - 1) * leaves.getH()),this));
                        leaves.spawn();
                        leaves.setDistance(1);
                    }
                }
                else if(y <=3){
                    boolean type = random.nextBoolean();;
                    if(y == 3){
                        if(type){
                            Stone stone = new Stone();
                            stone.setLocation(new Location(i * stone.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() + (y * stone.getCollisionBox().height), this));
                            stone.spawn();
                            stone.setDistance(1);
                            continue;
                        }
                    }

                    Dirt dirt = new Dirt();
                    dirt.setLocation(new Location(i * dirt.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() + (y * dirt.getCollisionBox().height), this));
                    dirt.spawn();
                    dirt.setDistance(1);
                }
                else{
                    Stone stone = new Stone();
                    stone.setLocation(new Location(i * stone.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() + (y * stone.getCollisionBox().height), this));
                    stone.spawn();
                    stone.setDistance(1); 
                }
            }
            
        }
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
    }
}
