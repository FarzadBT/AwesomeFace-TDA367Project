package faces.awesome.model;

import com.badlogic.gdx.Gdx;
import java.nio.file.Paths;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;


public class WorldMap {

    //Current tilemap
    private Map currentMap;

    //Constructor, takes a TiledMap
    public WorldMap(Map map){
        this.currentMap = map;
    }

    //Returns the current map
    public Map getCurrent(){
        return currentMap;
    }

    //Sets the current map. For testing purposes only.
    public void setCurrentMap(Map currentMap) {
        currentMap = currentMap;
    }


    public boolean isSolid(int x, int y) {

        return Tiles.isSolid(currentMap, x, y);

    }


    public void setNewMap (int x, int y) {

        String walkInId = Tiles.getWalkInId(currentMap, x, y);

        if (walkInId == null) {
            return;
        }

        System.out.println(1);

        setCurrentMap(MapStorage.LoadMap(walkInId));


    }



}
