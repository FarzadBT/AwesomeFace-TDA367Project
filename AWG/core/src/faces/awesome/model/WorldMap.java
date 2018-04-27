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

    public WorldMap(String path){
        System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
        this.CurrentMap = new TmxMapLoader().load("core/assets/theMap.tmx");
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

}
