package faces.awesome.model;

import com.badlogic.gdx.Gdx;
import java.nio.file.Paths;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.HashMap;

public class WorldMap {


    //Current tilemap
    private Map CurrentMap;
    private Position mapPosition;

    int xMin = this.mapPosition.getX() * 32;
    int yMin = this.mapPosition.getY() * 16;

    int xMax = (this.mapPosition.getX() + 1) * 32;
    int yMax = (this.mapPosition.getY() + 1) * 16;

    //Constructor, takes a TiledMap
    public WorldMap(Map map){
        this.CurrentMap = map;
        this.mapPosition = new Position(0,0);
    }

    public void tryMovePosition(Position oldPos, Position newPos) {
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

    public int getxMin(){
        return xMin;
    }

    public int getyMin(){
        return yMin;
    }

    //Returns the current map
    public Map getCurrent(){
        return CurrentMap;
    }

    //Sets the current map. For testing purposes only.
    public void setCurrentMap(Map currentMap) {
        CurrentMap = currentMap;
    }

    public Position getMapPosition() {
        return mapPosition;
    }

    public void setMapPosition(Position mapPosition) {
        this.mapPosition = mapPosition;
    }
}
