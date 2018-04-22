package faces.awesome.controllers;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import faces.awesome.model.Player;
import faces.awesome.model.Position;

public class PlayerCtrl {

    private Player player;
    private Map tiledMap;

    public PlayerCtrl(Player player, Map tiledMap) {
        this.player = player;
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

        Position newPosition = player.getPos().movePos(dx, dy);

        boolean solid = isSolid((int) newPosition.getX(), (int) newPosition.getY());

        if ( !solid ) {

            //TODO check if the player will collide with an enemy here (if sats)

            player.move(dx, dy);
        }

    }
}
