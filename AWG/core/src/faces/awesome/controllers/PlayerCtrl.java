package faces.awesome.controllers;

import faces.awesome.model.*;
import faces.awesome.services.Tiles;
import faces.awesome.services.WorldMap;

/*
 * Author: Therese Sturesson
 * Updated by: Philip Nilsson, Farzad Besharati
 *
 * TODO skriva vad klassen gör
 */

public class PlayerCtrl {

    private final WorldMap world;
    private final PlayerCharacter player;
    private final MapSegment segment;


    public PlayerCtrl(PlayerCharacter player, WorldMap world, MapSegment segment) {
        this.player = player;
        this.world = world;
        this.segment = segment;
    }

    //TODO kolla om det går att använda segment istället för world

    public void tryMove(int dx, int dy, Facing facing) {

        Position newPosition = player.getPos().movePos(dx, dy);

        boolean solid = Tiles.isSolid(world.getCurrentMap(), newPosition.getX(), newPosition.getY());

        boolean occupied = segment.isOccupied(newPosition);

        if (!solid && !occupied) {

            player.setFacing(facing);

            segment.checkSegmentBorder(player.getPos(), newPosition);

        }

        Position worldPos = segment.setNewMap(newPosition);

        if ( worldPos != null ) {

            segment.setPlayerPosOnMap(worldPos);

            player.move(worldPos.getX()-player.getPos().getX(), worldPos.getY()-player.getPos().getY(), false, false);

        } else {

            player.move(dx, dy, solid, occupied);

        }


    }

    public void useItem1(){
        player.useSlot1();
    }

    public void useItem2(){
        player.useSlot2();
    }

}
