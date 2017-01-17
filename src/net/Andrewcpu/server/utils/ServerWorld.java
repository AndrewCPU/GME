package net.Andrewcpu.server.utils;

import net.Andrewcpu.client.engine.World;
import net.Andrewcpu.client.engine.WorldGenerator;

/**
 * Created by stein on 1/15/2017.
 */
public class ServerWorld extends World {
    public ServerWorld(String name, WorldGenerator generator) {
        super(name, generator);
    }
}
