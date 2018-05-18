package faces.awesome.services;

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

    //Adds a map to storage
    public static void addMap(String name, TiledMap map){
        maps.put(name, map);
    }

    //Returns the TiledMaps corresponding to the key "name"
    public static TiledMap loadMap(String name){
        return maps.get(name);
    }

}
