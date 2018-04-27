package faces.awesome.controllers;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;

public class PlayerCtrl {

    private PlayerCharacter playerCharacter;
    private TiledMap tiledMap;

    public PlayerCtrl(PlayerCharacter playerCharacter, TiledMap tiledMap) {
        this.playerCharacter = playerCharacter;
        this.tiledMap = tiledMap;
    }

    private boolean isSolid(int x, int y) {
        for (MapLayer layer :  tiledMap.getLayers()) {

            if (layer instanceof TiledMapTileLayer) {

                TiledMapTileLayer tiledLayer = (TiledMapTileLayer) layer;
                TiledMapTileLayer.Cell cell = tiledLayer.getCell(x, y);

                if (cell != null && cell.getTile() != null) {

                    boolean tileIsSolid = cell.getTile().getProperties().get("solid", false, null);

                    if ( tileIsSolid ) {
                        return true;
                    }

                }

            }
        }
        return false;
    }


    public void tryMove(int dx, int dy) {

        Position newPosition = playerCharacter.getPos().movePos(dx, dy);

        boolean solid = isSolid((int) newPosition.getX(), (int) newPosition.getY());

        //Position newPos = playerCharacter.getPos();

        if ( !solid ) {

            //TODO check if the playerCharacter will collide with an enemy here (if sats)






            playerCharacter.move(dx, dy);
        }

    }

    public boolean Collision(){  //Not sure if this should be boolean or not. Or a separate method at all. Probably better to follow SOC.
        //What follows in a very early prototype of collision detection.

        //Get characters current position
        Position current = playerCharacter.getPos();

        //Get characters position after move, do not move character
        Position next = playerCharacter.getPos().movePos(1,0);

        //Get map layer in which character is moving
        TiledMapTileLayer layer = (TiledMapTileLayer)tiledMap.getLayers().get(0);

        //Get the cell corresponding to the tile at the next position. We've defined position using floats,
        // but tiles are represented by an integer indexed matrix
        TiledMapTileLayer.Cell cell =  layer.getCell( Math.round(next.getX()), Math.round(next.getY()));

        if(cell.getTile().getProperties().get("Occupied").equals(false)){
            return false;
        }
        return true;
    }
    /*
    Might as well document this a little further...
    The tilemap's constituent parts, i.e, the tiles themselves, are arranged in accordance with an integer indexed
    matrix of dimension n x m. In our case n = 32, m = 16. Each tile has an associated cell which contains properties
    pertaining to the tile in question.

    We could thus simply add a new property to every tile contained in the layer in which a character is moving.
    A boolean called Occupied, for example. Properties can also be concurrently modified using cell.getTile().getProperties().put("key", property)
     */
}
