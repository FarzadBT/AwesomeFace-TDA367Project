package faces.awesome.model;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Tiles {

    private PlayerCharacter player;
    private Map tiledMap;

    public Tiles (PlayerCharacter player, Map tiledMap) {
        this.player = player;
        this.tiledMap = tiledMap;
    }


    public boolean isSolid(int x, int y) {
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



}
