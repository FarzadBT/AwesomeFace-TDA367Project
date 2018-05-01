package faces.awesome.model;

import com.badlogic.gdx.maps.tiled.TiledMap;

import java.util.HashMap;

public class MapStorage {

    public MapStorage(){}

    //A class used to store TiledMaps, easier to access.

    public static HashMap<String, TiledMap> maps = new HashMap<>();

    //Adds a map to storage
    public static void AddMap(String name, TiledMap map){
        maps.put(name, map);
    }

    //Returns the HashMap containing all TiledMaps
    public static HashMap<String, TiledMap> getMaps() {
        return maps;
    }

    //Sets the HashMap
    public static void setMaps(HashMap<String, TiledMap> maps) {
        MapStorage.maps = maps;
    }

    //Returns the TiledMaps corresponding to the key "name"
    public static TiledMap LoadMap(String name){
        return maps.get(name);
    }
}
