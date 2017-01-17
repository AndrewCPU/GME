package net.Andrewcpu.client.engine.generators;

import net.Andrewcpu.client.Game;
import net.Andrewcpu.client.engine.World;
import net.Andrewcpu.client.engine.WorldGenerator;
import net.Andrewcpu.client.entities.blocks.*;
import net.Andrewcpu.client.utils.Location;

import java.util.Random;

/**
 * Created by stein on 1/15/2017.
 @Override
 */
public class NormalGenerator extends WorldGenerator{
    public NormalGenerator() {
        setBoolean("PLACE",true);
        setBoolean("BREAK",true);
    }

    public void generate(World world) {
        Random random = new Random();
        for(int i = -50; i<=50; i++){
            for(int y = 0; y<= 100; y++){
                if(y == 0){
                    Grass block = new Grass();
                    block.setLocation(new Location(i * block.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight(), world));
                    block.spawn();
                    block.setDistance(1);
                    if(random.nextInt(15) == 2){
                        int h = random.nextInt(7 - 3) + 3;
                        for(int l = 1; l<=h; l++){
                            Log log = new Log();
                            log.setLocation(new Location(i * log.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() - (l * log.getH()), world));
                            log.spawn();
                            log.setDistance(1);
                        }
                        Leaves leaves = new Leaves();
                        leaves.setLocation(new Location(i * leaves.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() - ((h + 1) * leaves.getH()),world));
                        leaves.spawn();
                        leaves.setDistance(1);

                        leaves = new Leaves();
                        leaves.setLocation(new Location((i - 1) * leaves.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() - ((h) * leaves.getH()),world));
                        leaves.spawn();
                        leaves.setDistance(1);

                        leaves = new Leaves();
                        leaves.setLocation(new Location((i + 1) * leaves.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() - ((h) * leaves.getH()),world));
                        leaves.spawn();
                        leaves.setDistance(1);

                        leaves = new Leaves();
                        leaves.setLocation(new Location((i - 1) * leaves.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() - ((h - 1) * leaves.getH()),world));
                        leaves.spawn();
                        leaves.setDistance(1);

                        leaves = new Leaves();
                        leaves.setLocation(new Location((i + 1) * leaves.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() - ((h - 1) * leaves.getH()),world));
                        leaves.spawn();
                        leaves.setDistance(1);

                        leaves = new Leaves();
                        leaves.setLocation(new Location((i - 2) * leaves.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() - ((h - 1) * leaves.getH()),world));
                        leaves.spawn();
                        leaves.setDistance(1);

                        leaves = new Leaves();
                        leaves.setLocation(new Location((i + 2) * leaves.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() - ((h - 1) * leaves.getH()),world));
                        leaves.spawn();
                        leaves.setDistance(1);
                    }
                }
                else if(y <=3){
                    boolean type = random.nextBoolean();;
                    if(y == 3){
                        if(type){
                            Stone stone = new Stone();
                            stone.setLocation(new Location(i * stone.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() + (y * stone.getCollisionBox().height), world));
                            stone.spawn();
                            stone.setDistance(1);
                            continue;
                        }
                    }

                    Dirt dirt = new Dirt();
                    dirt.setLocation(new Location(i * dirt.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() + (y * dirt.getCollisionBox().height), world));
                    dirt.spawn();
                    dirt.setDistance(1);
                }
                else{
                    Stone stone = new Stone();
                    stone.setLocation(new Location(i * stone.getCollisionBox().width, Game.getInstance().getDisplay().getGameFrame().getHeight() + (y * stone.getCollisionBox().height), world));
                    stone.spawn();
                    stone.setDistance(1);
                }
            }

        }
    }
}
