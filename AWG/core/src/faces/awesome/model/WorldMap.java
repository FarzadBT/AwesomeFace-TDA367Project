package faces.awesome.model;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class WorldMap {


    //Current tilemap
    private TiledMap currentMap;

    //Constructor, takes a TiledMap
    public WorldMap(TiledMap map){
        this.currentMap = map;
    }

    //Returns the current map
    public TiledMap getCurrent(){
        return currentMap;
    }

    //Sets the current map. For testing purposes only.
    public void setCurrentMap(TiledMap currentMap) {
        this.currentMap = currentMap;
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
