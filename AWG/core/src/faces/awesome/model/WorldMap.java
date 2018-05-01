package faces.awesome.model;

import com.badlogic.gdx.Gdx;
import java.nio.file.Paths;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.util.ArrayList;
import java.util.HashMap;

public class WorldMap {

    //public TiledMap map;
    //public ArrayList<TiledMap> maps = new ArrayList<>();

    public TiledMap CurrentMap;

    public HashMap<String, TiledMap> maps = new HashMap<>();

    public WorldMap(TiledMap map){
        this.CurrentMap = map;
    }

    public void AddMap(String name ,TiledMap map){
        maps.put(name, map);
    }

    public TiledMap getMap(String name){
        return maps.get(name);
    }

    public TiledMap getCurrent(){
        return CurrentMap;
    }

    public TiledMap LoadMap(String map){
        CurrentMap = new TmxMapLoader().load(map);
        return CurrentMap;
    }

    public TiledMap getCurrentMap() {
        return CurrentMap;
    }

    public void setCurrentMap(TiledMap currentMap) {
        CurrentMap = currentMap;
    }

    public HashMap<String, TiledMap> getMaps() {
        return maps;
    }

    public void setMaps(HashMap<String, TiledMap> maps) {
        this.maps = maps;
    }
}
