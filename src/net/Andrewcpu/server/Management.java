package net.Andrewcpu.server;

import net.Andrewcpu.client.engine.World;
import net.Andrewcpu.client.engine.generators.NormalGenerator;
import net.Andrewcpu.client.entities.Entity;
import net.Andrewcpu.packets.BlockBreakPacket;
import net.Andrewcpu.packets.BlockPlacePacket;
import net.Andrewcpu.packets.EntitySyncPacket;
import net.Andrewcpu.server.utils.ServerWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by stein on 1/15/2017.
 */
public class Management {
    private ServerWorld world;
    public static final int FPS = 60;
    public Management(){
        world = new ServerWorld("World", new NormalGenerator());
        Timer t = new Timer(1000 / FPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        t.start();
    }
    public void tick(){
        for(Entity e : world.getEntities()) {
            e.tick();
            EntitySyncPacket syncPacket = new EntitySyncPacket(e.getUUID().toString(),e.getLocation().getSolidX(),e.getLocation().getSolidY());
            broadcastSyncPacket(syncPacket);
        }

    }
    public void handleBreak(BlockBreakPacket blockBreakPacket){

    }
    public void handlePlace(BlockPlacePacket blockPlacePacket){

    }
    public void broadcastSyncPacket(EntitySyncPacket packet){

    }
}
