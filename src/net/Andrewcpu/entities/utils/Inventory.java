package net.Andrewcpu.entities.utils;

import net.Andrewcpu.entities.Entity;
import net.Andrewcpu.entities.Item;
import net.Andrewcpu.entities.blocks.BlockType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stein on 1/8/2017.
 */
public class Inventory {
    private List<Item> items = new ArrayList<>();
    private Entity owner;
    private int selectedSlot = 0;
    public Inventory(Entity owner) {
        this.owner = owner;
    }

    public void addItem(Item item){
        boolean alreadyContains = false;
        for(Item item1 : items){
            System.out.println(item1.getType() == item.getType());
            if(item1.getType()==item.getType()){
                alreadyContains = true;
                item1.setAmount(item1.getAmount() + item.getAmount());
                break;
            }
        }
        if(!alreadyContains)
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Entity getOwner() {
        return owner;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    public int getSelectedSlot() {
        return selectedSlot;
    }

    public void setSelectedSlot(int selectedSlot) {
        if(selectedSlot > 8)
            this.selectedSlot = 0;
        else if(selectedSlot < 0)
            this.selectedSlot = 8;
        else
            this.selectedSlot = selectedSlot;
    }
}
