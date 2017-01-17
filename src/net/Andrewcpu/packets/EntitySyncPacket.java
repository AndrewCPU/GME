package net.Andrewcpu.packets;

/**
 * Created by stein on 1/15/2017.
 */
public class EntitySyncPacket {
    private String uuid;
    private int x, y;

    public EntitySyncPacket(){

    }

    public EntitySyncPacket(String uuid, int x, int y) {
        this.uuid = uuid;
        this.x = x;
        this.y = y;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
