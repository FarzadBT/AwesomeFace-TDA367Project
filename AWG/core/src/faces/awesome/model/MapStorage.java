package faces.awesome.model;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;

import java.util.HashMap;


public class MapStorage {

    public MapStorage(){}

    //A class used to store TiledMaps, easier to access.


    public static java.util.Map<String, Map> maps = new HashMap<>();


    //Adds a map to storage
    public static void AddMap(String name, Map map){
        maps.put(name, map);
    }

    //Returns the HashMap containing all TiledMaps
    public static java.util.Map<String, Map> getMaps() {
        return maps;
    }

    //Sets the HashMap
    public static void setMaps(java.util.Map<String, Map> maps) {
        MapStorage.maps = maps;
    }

    //Returns the TiledMaps corresponding to the key "name"
    public static Map LoadMap(String name){
        return maps.get(name);
    }
}
