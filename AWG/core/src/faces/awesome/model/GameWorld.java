package faces.awesome.model;

import com.badlogic.gdx.maps.Map;

public class GameWorld {

    private Map currentMap;

    public GameWorld(Map currentMap) {
        this.currentMap = currentMap;
    }

    public boolean isSolid(int x, int y) {

        return Tiles.isSolid(currentMap, x, y);

    }

}
