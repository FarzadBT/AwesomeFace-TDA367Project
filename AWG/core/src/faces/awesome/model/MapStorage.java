package faces.awesome.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import java.util.HashMap;
import java.util.Map;

/*
* Author: Philip Nilsson
* Updated by: Therese Sturesson
*
* A class used to store TiledMaps, easier to access.
*/

public class MapStorage {

    private static Map<String, TiledMap> maps = new HashMap<>();

    public MapStorage(){}

    //Adds a map to storage
    public static void addMap(String name, TiledMap map){
        maps.put(name, map);
    }

    //Returns the HashMap containing all TiledMaps      TODO används inte, spara?
    public static Map<String, TiledMap> getMaps() {
        return maps;
    }

    //Sets the HashMap                                  TODO används inte, spara?
    public static void setMaps(Map<String, TiledMap> maps) {
        MapStorage.maps = maps;
    }

    //Returns the TiledMaps corresponding to the key "name"
    public static TiledMap LoadMap(String name){
        return maps.get(name);
    }
}
