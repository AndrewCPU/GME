package net.Andrewcpu.entities;

import net.Andrewcpu.entities.blocks.BlockType;
import net.Andrewcpu.entities.utils.Colorable;
import net.Andrewcpu.entities.utils.EntityType;

import java.awt.*;

/**
 * Created by stein on 1/8/2017.
 */
public class Item extends Entity implements Colorable{
    private BlockType type;
    private int amount = 1;
    private Color color;
    public Item(BlockType blockType, Color color) {
        super(EntityType.ITEM);
        setW(10);
        setH(10);
        setSpriteSheetID(1);
        setGravity(true);
        this.type = blockType;
        noCollideType.add(EntityType.ITEM);
        noCollideType.add(EntityType.PLAYER);
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

    @Override
    public void tick(){
        if(isOnGround())
            setYVel(-4);
        super.tick();
    }
}
