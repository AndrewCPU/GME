package net.Andrewcpu.client.engine.generators;

import net.Andrewcpu.client.Game;
import net.Andrewcpu.client.engine.World;
import net.Andrewcpu.client.engine.WorldGenerator;
import net.Andrewcpu.client.entities.blocks.Log;
import net.Andrewcpu.client.utils.Location;

import java.util.Random;

/**
 * Created by stein on 1/15/2017.
 */
public class FunRunGenerator extends WorldGenerator {
    public FunRunGenerator() {
        setBoolean("BREAK",false);
        setBoolean("PLACE",false);
    }

    @Override
    public void generate(World world) {
        int length = 1000;
        int y = 10;
        Random random = new Random();
        for(int i = 0; i<length; i++){
            if(random.nextInt(10) == 2){
                y+= random.nextBoolean() ? -1 : 1;
            }
            Log log = new Log();
            log.setLocation(new Location(i * 20, Game.getInstance().getDisplay().getGameFrame().getHeight() - (y * 20), world));
            log.spawn();
        }
    }
}
