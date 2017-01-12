package net.Andrewcpu.render;

import net.Andrewcpu.Game;
import net.Andrewcpu.engine.World;
import net.Andrewcpu.entities.Item;
import net.Andrewcpu.entities.utils.Clickable;
import net.Andrewcpu.entities.utils.Colorable;
import net.Andrewcpu.entities.utils.Direction;
import net.Andrewcpu.entities.utils.Directionable;
import net.Andrewcpu.entities.Entity;
import net.Andrewcpu.entities.Player;
import net.Andrewcpu.utils.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by stein on 1/5/2017.
 */
public class Render {
    private List<Point> clicks = new ArrayList<>();
    private List<Point> clickBuffer = new ArrayList<>();
    private boolean DEBUG = false;
    public List<SpriteSheet> spriteSheets = new ArrayList<>();
    private GameFrame frame;
    private BufferedImage background;
    private BufferedImage mountains;
    private int blockSize = 20;
    private int xOffset = 0, yOffset = 0;
    public boolean characterPicker = false;
    private long characterTime = 0;
    private Entity selectedBlock;
    public void toggleCharacterPicker()
    {
        if(characterTime + 1000 < System.currentTimeMillis()) {
            characterPicker = !characterPicker;
            characterTime = System.currentTimeMillis() + 100;
        }
    }
    public void addClick(Point point){
        Point nP = new Point(point.x - 5, point.y - 5);
        clickBuffer.add(nP);
    }
    public Render(GameFrame frame){

        spriteSheets.add(new SpriteSheet(9,6,SpriteSheet.load("/images/characterMap.png")));
        spriteSheets.add(new SpriteSheet(3,4,SpriteSheet.load("/images/newTrees.png")));
        spriteSheets.add(new SpriteSheet(1,1,SpriteSheet.load("/images/grass.png")));
        background = SpriteSheet.load("/images/background.png");
        mountains = SpriteSheet.load("/images/mountains.png");
        this.frame = frame;
    }

    public void render(Graphics g){
        selectedBlock = null;
        Point m = new Point(Game.getInstance().getDisplay().mouseX, Game.getInstance().getDisplay().mouseY);
        if(!frame.isFocused() && Game.getInstance().getWorld() == null)
            return;

        Player player = Game.getInstance().player;
        if(player!=null)
        xOffset = -player.getLocation().getSolidX() + frame.getWidth() / 2;
        yOffset = -player.getLocation().getSolidY() + (frame.getHeight() * 2)/ 3;
//        System.out.println(yOffset + "/ " + frame.getHeight());
//        Graphics g = frame.getGraphics();
        g.drawImage(background, xOffset / 4,0,frame.getWidth(),frame.getHeight() + 200,null);
        g.drawImage(background, (xOffset < 0 ? frame.getWidth() : -frame.getWidth()) + xOffset / 4,0,frame.getWidth(),frame.getHeight() + 200,null);

        g.drawImage(mountains, xOffset / 2,frame.getHeight()-(-yOffset / 10) - frame.getHeight() / 2,frame.getWidth(), 400,null);
        g.drawImage(mountains, (xOffset < 0 ? frame.getWidth() : -frame.getWidth()) + xOffset / 2,frame.getHeight()-(-yOffset / 10) - frame.getHeight() / 2 ,frame.getWidth(),400,null);
//        g.clearRect(0,0,frame.getWidth(),frame.getHeight());
        World world = Game.getInstance().getWorld();
        world.vSync();
        clicks.addAll(clickBuffer);
        clickBuffer.clear();
        for(Entity entity : world.getEntities()){
            BufferedImage image = spriteSheets.get(entity.getSpriteSheetID() - 1).getSprite(Direction.LEFT, entity.getSpriteX(),entity.getSpriteY());
            if(entity instanceof Directionable){
                image = spriteSheets.get(entity.getSpriteSheetID() - 1).getSprite(((Directionable) entity).getDirection(), entity.getSpriteX(),entity.getSpriteY());
                g.drawImage(image, entity.getLocation().getSolidX() + xOffset,entity.getLocation().getSolidY() + yOffset, entity.getCollisionBox().width,entity.getCollisionBox().height, null);
            }
            else if(entity instanceof Colorable){
                g.setColor(((Colorable) entity).getColor());
                g.fillRect(entity.getLocation().getSolidX() + xOffset, entity.getLocation().getSolidY() + yOffset, (int)entity.getW(), (int)entity.getH());
                g.setColor(Color.BLACK);
                if(entity.getFullCollisionBox(entity.getLocation().add(xOffset + entity.getCollisionBox().width / 2,yOffset +  entity.getCollisionBox().height + entity.getCollisionBox().height/2)).contains(m)) {
                    g.setColor(new Color(0,0,0,75));
                    g.fillRect(entity.getLocation().getSolidX() + xOffset, entity.getLocation().getSolidY() + yOffset, (int)entity.getW(), (int)entity.getH());
                    selectedBlock = entity;
                }
                g.setColor(Color.BLACK);
                    g.drawRect(entity.getLocation().getSolidX() + xOffset, entity.getLocation().getSolidY() + yOffset, (int)entity.getW(), (int)entity.getH());
            }
            else {
                g.drawImage(image, entity.getLocation().getSolidX() + xOffset,entity.getLocation().getSolidY() + yOffset, (int)entity.getCollisionBox().getWidth(), (int)entity.getCollisionBox().getHeight(), null);
            }
            if(entity instanceof Clickable){
                if(clicks.size() >= 1){
                    if(selectedBlock != null) {
                        if(selectedBlock instanceof Clickable) {
                            ((Clickable) selectedBlock).click();
                            clicks.clear();
                        }
                    }
                }
            }
            if(DEBUG) {
                g.setColor(Color.CYAN);
                g.fillRect(entity.getLocation().getSolidX(), entity.getLocation().getSolidY(), entity.getCollisionBox().width, entity.getCollisionBox().height);
            }
        }
        world.vSync();

        g.setColor(new Color(0,0,0,90));
        g.fillRect(0,0,100,20);
        g.setColor(new Color(255,0,0));
        g.fillRect(0,0,100 * (Game.getInstance().player.getHealth() / Game.getInstance().player.getMaxHealth()), 20);

        int item = 0;
        for(;item<9; item++){
            int s = 50;

            int x = frame.getWidth() - s - 20;
            int y = frame.getHeight() / 4  + (item * s);
            int padding = 5;
            if(player.getInventory().getSelectedSlot()!=item)
                g.setColor(new Color(0,0,0,75));
            else
                g.setColor(new Color(5, 2, 158,75));
            g.fillRect(x,y,s,s);
            if(player.getInventory().getItems().size() - 1 < item)
                continue;
            Item itm = player.getInventory().getItems().get(item);
            g.setColor(itm.getColor());
            g.fillRect(x + padding, y + padding, s - (padding * 2), s - (padding * 2));
            g.setColor(Color.BLACK);
            g.drawString(itm.getAmount() + "", x + (padding / 2) - 10, y + s - s / 2);

        }















        //todo leave at ottom
        if(characterPicker){
            g.setColor(new Color(0,0,0,99));
            g.fillRect(0,0,frame.getWidth(),frame.getHeight());
            int x = 100;
            int y = 100;
//            HashMap<Point,BufferedImage> map = new HashMap<>();
            HashMap<Point,Rectangle> mapRect = new HashMap<>();
            BufferedImage[][] imgs = spriteSheets.get(0).getSprites().get(Direction.LEFT);
            for(int r = 0; r<imgs.length; r++){
                for(int c = 0; c<imgs[0].length; c++){
                    BufferedImage image = imgs[r][c];
                    g.drawImage(image, x, y, 50, 50, null);
                    mapRect.put(new Point(r,c),new Rectangle(x,y + 50,50,50));
                    x+=50;
                    if(x>=frame.getWidth() - 100)
                    {
                        x = 100;
                        y += 50;
                    }
                }
            }
            g.drawImage(spriteSheets.get(0).getSprite(Direction.LEFT,Game.getInstance().player.getSpriteX(),Game.getInstance().player.getSpriteY()),frame.getWidth() / 2 - 100, frame.getHeight() - 400, 200, 200,null);
            Point p = new Point(Game.getInstance().getDisplay().mouseX, Game.getInstance().getDisplay().mouseY);
            for(Point point : mapRect.keySet()){
                Rectangle r = mapRect.get(point);
                if(r.contains(p)){
                    Game.getInstance().player.setSpriteX(point.x);
                    Game.getInstance().player.setSpriteY(point.y);
                }
            }
            return;
        }
        frame.repaint();
        clicks.clear();
        clicks.addAll(clickBuffer);
        clickBuffer.clear();
    }
}
