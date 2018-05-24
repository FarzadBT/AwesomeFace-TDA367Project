package faces.awesome.services;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.squareup.otto.Bus;
import faces.awesome.model.BossEnemy;
import faces.awesome.model.Enemy;
import faces.awesome.model.Position;
import faces.awesome.model.WorldPosition;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Therese Sturesson
 * Updated by:
 *
 * TODO skriva vad klassen gör
 */

public class Tiles {


    public static boolean isSolid(Map tiledMap, int x, int y) {
        boolean tileIsSolid = false;

        for (MapLayer layer : tiledMap.getLayers()) {

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


    public static WorldPosition getWorldPosition(Position position, Map tiledMap) {

        for (MapLayer layer : tiledMap.getLayers()) {

            if (layer instanceof TiledMapTileLayer) {

                TiledMapTileLayer tiledLayer = (TiledMapTileLayer) layer;
                TiledMapTileLayer.Cell cell = tiledLayer.getCell(position.getX(), position.getY());

                if (cell != null && cell.getTile() != null) {

                    String id = cell.getTile().getProperties().get("walkInId", null, null);


                    if (id != null) {

                        int newX = cell.getTile().getProperties().get("x", 0, null);

                        int newY = cell.getTile().getProperties().get("y", 0, null);


                        return new WorldPosition(new Position(newX, newY), id);

                    }
                }
            }
        }
        return null;
    }


    public static List<Enemy> populateWorldWithEnemies(Map currentMap, Bus bus) {

        List<Enemy> enemiesInWorld = new ArrayList<>();

        for (MapLayer layer : currentMap.getLayers()) {

            Array<RectangleMapObject> enemyArray = layer.getObjects().getByType(RectangleMapObject.class);

            for (RectangleMapObject recObject : enemyArray) {

                Position recPos = getCenterOfRecPos(recObject.getRectangle());

                Enemy enemy;

                if (recObject.getName().equals("boss")) {

                    enemy = new BossEnemy(recPos, bus, "boss");

                } else {

                    enemy = new Enemy(recPos, bus, "enemy");

                }

                enemiesInWorld.add(enemy);

            }
        }

        return enemiesInWorld;

    }

    private static Position getCenterOfRecPos(Rectangle rectangle) {
        return new Position((int) ((rectangle.x + (rectangle.width / 2)) / 32), (int) ((rectangle.y + (rectangle.height / 2)) / 32));
    }


}
