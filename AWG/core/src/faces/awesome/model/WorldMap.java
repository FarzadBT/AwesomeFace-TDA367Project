package faces.awesome.model;

import com.badlogic.gdx.Gdx;
import java.nio.file.Paths;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.util.ArrayList;
import java.util.HashMap;

public class WorldMap {


    //Current tilemap
    public TiledMap CurrentMap;

    //Constructor, takes a TiledMap
    public WorldMap(TiledMap map){
        this.CurrentMap = map;
    }

    //Returns the current map
    public TiledMap getCurrent(){
        return CurrentMap;
    }

    //Sets the current map. For testing purposes only.
    public void setCurrentMap(TiledMap currentMap) {
        CurrentMap = currentMap;
    }

}
