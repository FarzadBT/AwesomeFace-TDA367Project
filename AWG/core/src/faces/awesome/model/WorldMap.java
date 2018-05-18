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

    //Current tilemap and mapposition
    private TiledMap currentMap;
    private Position mapPosition;


    //Constructor, takes a TiledMap
    public WorldMap (TiledMap map){
        this.currentMap = map;
        this.mapPosition = new Position(0, 0);
    }


    //Returns the current map
    public TiledMap getCurrentMap (){
        return currentMap;
    }


    //Sets the current map. For testing purposes only.
    public void setCurrentMap (TiledMap currentMap) {
        this.currentMap = currentMap;
        this.mapPosition = new Position(0, 0);
    }


    //Checks the border for the segment
    public void checkSegmentBorder (Position oldPos, Position newPos) {
        int xMin = this.mapPosition.getX() * 32;
        int yMin = this.mapPosition.getY() * 16;

        int xMax = (this.mapPosition.getX() + 1) * 32;
        int yMax = (this.mapPosition.getY() + 1) * 16;


        if (oldPos.getX() >= xMin && newPos.getX() < xMin) {
            this.mapPosition = this.mapPosition.movePos(-1, 0);
        } else if (oldPos.getY() >= yMin && newPos.getY() < yMin) {
            this.mapPosition = this.mapPosition.movePos(0, -1);
        } else if (oldPos.getX() < xMax && newPos.getX() >= xMax) {
            this.mapPosition = this.mapPosition.movePos(1, 0);
        } else if (oldPos.getY() < yMax && newPos.getY() >= yMax) {
            this.mapPosition = this.mapPosition.movePos(0, 1);
        }
    }


    //Sets a new map
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


    //Gets the map position
    public Position getMapPosition() {
        return mapPosition;
    }


    //Sets the players position on the map
    public void setPlayerPosOnMap (Position playerPos) {
        this.mapPosition = new Position(playerPos.getX()/32, playerPos.getY()/16);
    }


}
