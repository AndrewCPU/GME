package net.Andrewcpu.entities.blocks;

import net.Andrewcpu.entities.Entity;
import net.Andrewcpu.entities.Item;
import net.Andrewcpu.entities.utils.Clickable;
import net.Andrewcpu.entities.utils.Colorable;
import net.Andrewcpu.entities.utils.EntityType;

import java.awt.*;

/**
 * Created by stein on 1/7/2017.
 */
public class Block extends Entity implements Colorable, Clickable {
    protected Color color;
    protected BlockType blockType;
    public Block() {
        super(EntityType.BLOCK);
        setSpriteSheetID(3);
        setSpriteX(0);
        setSpriteY(0);
        setW(20);
        setH(20);
        setGravity(false);
    }

    @Override
    public Color getColor() {
        return color;
    }
    public void breakBlock(){
        Item item = new Item(blockType, getColor());
        item.setLocation(getLocation().add(0,1));
        item.spawn();
        getLocation().getWorld().killEntity(this);
    }
    @Override
    public void click() {
        breakBlock();
    }
}
