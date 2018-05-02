package faces.awesome.model;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Tiles {


    public static boolean isSolid(Map tiledMap, int x, int y) {
        boolean tileIsSolid = false;

        for (MapLayer layer :  tiledMap.getLayers()) {

            if (layer instanceof TiledMapTileLayer) {

                TiledMapTileLayer tiledLayer = (TiledMapTileLayer) layer;
                TiledMapTileLayer.Cell cell = tiledLayer.getCell(x, y);

                if (cell != null && cell.getTile() != null) {

                    tileIsSolid = cell.getTile().getProperties().get("solid", false, null);

                }
            }
        }
        return tileIsSolid;
    }


    public static String getWalkInId (Map tiledMap, int x, int y) {

        for (MapLayer layer :  tiledMap.getLayers()) {

            if (layer instanceof TiledMapTileLayer) {

                TiledMapTileLayer tiledLayer = (TiledMapTileLayer) layer;
                TiledMapTileLayer.Cell cell = tiledLayer.getCell(x, y);

                if (cell != null && cell.getTile() != null) {

                    String id = cell.getTile().getProperties().get("walkInId", null, null);

                    if ( id != null ) {

                        return id;

                    }
                }
            }
        }

        return null;
    }


}
