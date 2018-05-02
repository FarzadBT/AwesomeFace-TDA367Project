package faces.awesome.model;

import com.badlogic.gdx.Gdx;
import java.nio.file.Paths;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.util.ArrayList;
import java.util.HashMap;

public class WorldMap {


    //Current tilemap
    public Map CurrentMap;

    //Constructor, takes a TiledMap
    public WorldMap(Map map){
        this.CurrentMap = map;
    }

    //Returns the current map
    public Map getCurrent(){
        return CurrentMap;
    }

    //Sets the current map. For testing purposes only.
    public void setCurrentMap(Map currentMap) {
        CurrentMap = currentMap;
    }

}
