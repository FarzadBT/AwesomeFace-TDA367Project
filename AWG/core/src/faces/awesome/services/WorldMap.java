package faces.awesome.services;

import com.badlogic.gdx.maps.tiled.TiledMap;
import faces.awesome.model.Position;
import faces.awesome.model.WorldPosition;

import java.util.Observable;

/*
 * Author: Philip Nilsson
 * Updated by: Therese Sturesson
 *
 * A class that keeps track of which map that is current and sets a new map when needed.
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

        setCurrentMap(MapStorage.loadMap(worldPosition.getMap()));

        setChanged();

        notifyObservers();

        return new Position(worldPosition.getX(), worldPosition.getY());

    }


}
