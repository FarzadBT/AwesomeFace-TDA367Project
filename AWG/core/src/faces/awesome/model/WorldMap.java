package faces.awesome.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import java.util.Observable;

/*
 * Author: Philip Nilsson
 * Updated by: Therese Sturesson
 *
 * TODO skriva vad klassen gör
 */

public class WorldMap extends Observable {

    //Current tilemap
    private TiledMap currentMap;


    //Constructor, takes a TiledMap
    public WorldMap (TiledMap map){
        this.currentMap = map;
    }


    //Returns the current map
    public TiledMap getCurrentMap (){
        return currentMap;
    }


    //Sets the current map. For testing purposes only.
    public void setCurrentMap (TiledMap currentMap) {
        this.currentMap = currentMap;
    }


    //Sets a new map    TODO kolla på worldposition
    public Position setNewMap (int x, int y) {

        WorldPosition worldPosition = Tiles.getWorldPosition(currentMap, x, y);

        if (worldPosition == null) {
            return null;
        }

        setCurrentMap(MapStorage.LoadMap(worldPosition.getMap()));

        setChanged();

        notifyObservers();

        return new Position(worldPosition.getX(), worldPosition.getY());

    }


}
