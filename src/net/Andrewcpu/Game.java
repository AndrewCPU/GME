package net.Andrewcpu;

import net.Andrewcpu.engine.World;
import net.Andrewcpu.entities.Player;
import net.Andrewcpu.render.Display;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stein on 1/5/2017.
 */
public class Game {
    private Display display;
    private World world;

    private static Game instance;

    private List<Integer> pressedKeys = new ArrayList<>();
    public static Game getInstance(){
        return instance;
    }
    public Player player;
    public Game(){
        instance = this;
        display = new Display();
        player = new Player();
        world = new World("World");
        world.createEntity(player);
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void pressKey(int i){
        if(!pressedKeys.contains(i))
            pressedKeys.add(i);
    }
    public void releaseKey(int i){
        while(pressedKeys.contains(i))
            pressedKeys.remove((Integer)i);
    }

    public boolean isPressing(int i){
        return pressedKeys.contains(i);
    }

    public void kill(){
        System.exit(0);
    }
}
