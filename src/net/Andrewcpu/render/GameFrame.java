package net.Andrewcpu.render;

import net.Andrewcpu.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * Created by stein on 1/5/2017.
 */
public class GameFrame extends JFrame implements KeyListener,MouseMotionListener,MouseListener,MouseWheelListener{
    private int width = 1000;
    private int height = 800;
    private java.util.List<Integer> pressedKeys = new ArrayList<>();

    public List<Integer> getPressedKeys() {
        return pressedKeys;
    }

    public void setPressedKeys(List<Integer> pressedKeys) {
        this.pressedKeys = pressedKeys;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getUnitsToScroll() > 0) {
            Game.getInstance().player.getInventory().setSelectedSlot(Game.getInstance().player.getInventory().getSelectedSlot() + 1);
        }
        else{
            Game.getInstance().player.getInventory().setSelectedSlot(Game.getInstance().player.getInventory().getSelectedSlot() - 1);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Game.getInstance().getDisplay().click(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(Game.getInstance().getDisplay() == null)
            return;
        Game.getInstance().getDisplay().mouseX = e.getX();
        Game.getInstance().getDisplay().mouseY = e.getY();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Game.getInstance().pressKey(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Game.getInstance().releaseKey(e.getKeyCode());
    }

    public GameFrame(){
        setBounds(0,0,width,height);
        setLayout(null);
        Canvas canvas = new Canvas();
        canvas.setBounds(0,0,getWidth(),getHeight());
        add(canvas);
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setIgnoreRepaint(true);
        addMouseWheelListener(this);
        System.setProperty("sun.awt.noerasebackground", "true");
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                canvas.setBounds(0,0,getWidth(),getHeight());
            }
        });
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Game.getInstance().kill();
            }
        });
    }

    public void setWidth(int width) {
        this.width = width;
        setBounds(getX(),getY(),width,height);
    }

    public void setHeight(int height) {
        this.height = height;
        setBounds(getX(),getY(),width,height);
    }
}
