package faces.awesome.controllers;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import faces.awesome.model.GameWorld;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.model.WorldMap;

public class PlayerCtrl {

    private final GameWorld world;
    private PlayerCharacter player;
    //private WorldMap world;

  
    public PlayerCtrl(PlayerCharacter player, GameWorld world) {
        this.player = player;
        this.world = world;
    }

  
    public void tryMove(int dx, int dy) {

        Position newPosition = player.getPos().movePos(dx, dy);
        boolean solid = world.isSolid(newPosition.getX(), newPosition.getY());

        player.move(dx, dy, solid);
    }
}


