package net.Andrewcpu.client.render;

import net.Andrewcpu.client.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by stein on 1/7/2017.
 */
public class Canvas extends JComponent{
    public Canvas()
    {
        repaint();
    }
    @Override
    public void paint(Graphics graphics){
        if(Game.getInstance()!= null && Game.getInstance().getDisplay() != null && Game.getInstance().getDisplay().getRender() != null)
            Game.getInstance().getDisplay().getRender().render(graphics);
//        grabFocus();
    }
}
