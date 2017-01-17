package net.Andrewcpu.client.engine;

import java.util.HashMap;

/**
 * Created by stein on 1/15/2017.
 */
public class WorldGenerator {
    private HashMap<String,Object> conditions = new HashMap<>();
    public void generate(World world){

    }
    public void setBoolean(String s, boolean t){
        conditions.put(s,t);
    }
    public void setInteger(String s, int i){
        conditions.put(s,i);
    }
    public void setDouble(String s, double d){
        conditions.put(s,d);
    }
    public void setString(String s, String t){
        conditions.put(s,t);
    }
    public boolean getBoolean(String s){
        if(conditions.containsKey(s))
            return (boolean)conditions.get(s);
        return false;
    }
    public int getInt(String s){
        if(conditions.containsKey(s))
            return (int)conditions.get(s);
        return 0;
    }
    public double getDouble(String s){
        if(conditions.containsKey(s))
            return (double)conditions.get(s);
        return 0;
    }
    public String getString(String s){
        if(conditions.containsKey(s))
            return (String)conditions.get(s);
        return "";
    }
}
