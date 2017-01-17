package net.Andrewcpu.client.entities.blocks;

import net.Andrewcpu.client.entities.Entity;
import net.Andrewcpu.client.entities.utils.Clickable;
import net.Andrewcpu.client.entities.utils.Colorable;
import net.Andrewcpu.client.entities.utils.EntityType;

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

    public BlockType getBlockType() {
        return blockType;
    }

    public void setBlockType(BlockType blockType) {
        this.blockType = blockType;
    }

    @Override
    public Color getColor() {
        return color;
    }
    public void breakBlock(){
        getLocation().getWorld().breakBlock(this);
    }
    @Override
    public void click() {
        breakBlock();
    }
}
