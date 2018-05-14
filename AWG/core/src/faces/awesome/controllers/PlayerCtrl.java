package faces.awesome.controllers;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import faces.awesome.model.*;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.model.WorldMap;

public class PlayerCtrl {

    private final WorldMap world;
    private PlayerCharacter player;
    //private WorldMap world;

  
    public PlayerCtrl(PlayerCharacter player, WorldMap world) {
        this.player = player;
        this.world = world;
    }

  
    public void tryMove(int dx, int dy) {
        Position newPosition = player.getPos().movePos(dx, dy);

        boolean solid = world.isSolid(newPosition.getX(), newPosition.getY());

        if (!solid) {

            world.tryMovePosition(player.getPos(), newPosition);

        }

        Position worldPos = world.setNewMap(newPosition.getX(), newPosition.getY());

        if ( worldPos != null ) {

            world.setPosition(worldPos);

            player.move(worldPos.getX()-player.getPos().getX(), worldPos.getY()-player.getPos().getY(), false);

        } else {

            player.move(dx, dy, solid);

        }


    }

    public void setFacing(Facing dir) {
        player.setFacing(dir);
    }
}


