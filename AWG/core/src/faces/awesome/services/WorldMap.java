package faces.awesome.services;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.squareup.otto.Bus;
import faces.awesome.events.MapChangedEvent;
import faces.awesome.model.Position;
import faces.awesome.model.WorldPosition;

/*
 * Author: Philip Nilsson
 * Updated by: Therese Sturesson
 *
 * A class that keeps track of which map that is current and sets a new map when needed.
 */

public class WorldMap {

    //Current tilemap
    private TiledMap currentMap;
    private Bus bus;


    //Constructor, takes a TiledMap
    public WorldMap(TiledMap map, Bus bus){
        this.currentMap = map;
        this.bus = bus;
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
    public Position setNewMap (Position position) {

        WorldPosition worldPosition = Tiles.getWorldPosition(position, currentMap);

        if (worldPosition == null) {
            return null;
        }

        setCurrentMap(MapStorage.loadMap(worldPosition.getMap()));

        bus.post(new MapChangedEvent());

        return new Position(worldPosition.getPosition().getX(), worldPosition.getPosition().getY());

    }


}
