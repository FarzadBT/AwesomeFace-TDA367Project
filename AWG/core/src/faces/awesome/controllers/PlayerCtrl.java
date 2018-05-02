package faces.awesome.controllers;

import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.model.WorldMap;

public class PlayerCtrl {

    private final WorldMap world;
    private PlayerCharacter player;
    //private WorldMap tiledMap;

  
    public PlayerCtrl(PlayerCharacter player, WorldMap world) {
        this.player = player;
        this.world = world;
    }

  
    public void tryMove(int dx, int dy) {

        Position newPosition = player.getPos().movePos(dx, dy);

        world.setNewMap(newPosition.getX(), newPosition.getY());

        boolean solid = world.isSolid(newPosition.getX(), newPosition.getY());

        player.move(dx, dy, solid);
    }
}


