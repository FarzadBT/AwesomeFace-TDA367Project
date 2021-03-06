package faces.awesome.controllers;

import faces.awesome.model.*;
import faces.awesome.model.characters.PlayerCharacter;
import faces.awesome.services.Tiles;
import faces.awesome.services.WorldMap;

/**
 * @author Therese Sturesson
 * Updated by: Philip Nilsson
 *
 * A controller layer between the view and the model. It does some checks before the player can
 * move and delegates the use of items methods.
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

    /**
     * Change player's facing
     * @param facing facing to change to
     */
    public void changeFacing(Facing facing) {
        player.setFacing(facing);
    }


    // Checks if the playercharacter can move to the position the player wants
    public void tryMove(int dx, int dy, Facing facing) {

        Position newPosition = player.getPos().movePos(dx, dy);

        boolean solid = Tiles.isSolid(world.getCurrentMap(), newPosition.getX(), newPosition.getY());

        boolean occupied = segment.isOccupied(newPosition);

        player.setFacing(facing);

        if ( !solid && !occupied ) {

            segment.checkSegmentBorder(player.getPos(), newPosition);

        }

        Position worldPos = segment.setNewMap(newPosition);

        if ( worldPos != null ) {

            segment.setPlayerPosOnMap(worldPos);

            player.move(worldPos.getX()-player.getPos().getX(), worldPos.getY()-player.getPos().getY(), false, false);

        } else {

            player.move(dx, dy, solid, occupied);

        }

        segment.getPickupsInSegment().forEach(pickup -> {
            if (pickup.getPos().getX() == player.getPos().getX() &&
                    pickup.getPos().getY() == player.getPos().getY()) {
                pickup.onPickup(player);
                segment.removeFromPickups(pickup);
            }
        });

    }

    // Delegates the use of item 1
    public void useItem1(){
        player.useSlot1();
    }

    // Delegates the use of item 2
    public void useItem2(){
        player.useSlot2();
    }

}
