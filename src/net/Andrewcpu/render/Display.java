package net.Andrewcpu.render;


import net.Andrewcpu.Game;
import net.Andrewcpu.entities.utils.Direction;
import net.Andrewcpu.entities.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by stein on 1/5/2017.
 */
public class Display {
    private GameFrame gameFrame ;
    public static final int FPS = 60;
    private boolean paused = true;
    private Render render;
    public int mouseX = 0, mouseY = 0;
    public Display(){
        start();
    }
    public void start(){
        gameFrame = new GameFrame();
        render = new Render(gameFrame);
        Timer t = new Timer(1000 / FPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameFrame!= null && gameFrame.getComponent(0) != null && gameFrame.isVisible()) tick();
            }
        });
        t.start();
    }
    public void pause(){
        paused = true;
    }
    public void resume(){
        paused = false;
    }
    public double parallaxDist(double dist){
        return (double) (1.0 / dist);
    }
    public void tick(){


        if(Game.getInstance().isPressing(KeyEvent.VK_D)){
            Game.getInstance().player.setDirection(Direction.RIGHT);
//            System.out.println("RIGHT");
            Game.getInstance().player.moveRight(parallaxDist(Game.getInstance().player.getDistance()));
        }
        else if( Game.getInstance().isPressing(KeyEvent.VK_A)) {
            Game.getInstance().player.setDirection(Direction.LEFT);
            Game.getInstance().player.moveLeft(parallaxDist(Game.getInstance().player.getDistance()));
        }
        if(Game.getInstance().isPressing(KeyEvent.VK_SPACE)){
            Game.getInstance().player.jump();
        }
        if(Game.getInstance().isPressing(KeyEvent.VK_C)){
            getRender().toggleCharacterPicker();
        }
        gameFrame.getComponent(0).repaint();

        for(Entity entity : Game.getInstance().player.getLocation().getWorld().getEntities())
            entity.tick();
    }



    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public Render getRender() {
        return render;
    }

    public void setRender(Render render) {
        this.render = render;
    }

    public void click(Point point){
        getRender().addClick(point);
    }
}
