package faces.awesome.controllers;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;

public class PlayerCtrl {

    private PlayerCharacter playerCharacter;
    private Map tiledMap;

    public PlayerCtrl(PlayerCharacter playerCharacter, Map tiledMap) {
        this.playerCharacter = playerCharacter;
        this.tiledMap = tiledMap;
    }

    private boolean isSolid(int x, int y) {
        boolean isSolid = false;
        boolean walkIn = false;

        for (MapLayer layer :  tiledMap.getLayers()) {

            if (layer instanceof TiledMapTileLayer) {

                TiledMapTileLayer tiledLayer = (TiledMapTileLayer) layer;
                TiledMapTileLayer.Cell cell = tiledLayer.getCell(x, y);

                if (cell != null && cell.getTile() != null) {

                    boolean tileIsSolid = cell.getTile().getProperties().get("solid", false, null);

                    boolean canWalkIn = cell.getTile().getProperties().get("walkIn", false, null);

                    isSolid = tileIsSolid;

                    if ( canWalkIn ) {
                        //anropa metoden som byter till en ny karta

                    }
                }
            }
        }
        return isSolid;
    }


    public void tryMove(int dx, int dy) {

        Position newPosition = playerCharacter.getPos().movePos(dx, dy);

        boolean solid = isSolid((int) newPosition.getX(), (int) newPosition.getY());

        //Position newPos = playerCharacter.getPos();

        if ( !solid ) {

            playerCharacter.move(dx, dy);
        }

    }
}
