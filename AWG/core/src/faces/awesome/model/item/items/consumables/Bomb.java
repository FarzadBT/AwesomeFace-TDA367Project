package faces.awesome.model.item.items.consumables;

import faces.awesome.model.Facing;
import faces.awesome.model.MapSegment;
import faces.awesome.model.Position;
import faces.awesome.model.item.BaseConsumable;
import faces.awesome.model.objects.object.BombObject;

/**
 * @author Farzad Besharati
 *
 * A consumable Bomb Item, when used, places a BombObject in front of you
 */

public class Bomb extends BaseConsumable {

    private BombObject bomb;

    public Bomb(int maxQuantity) {
        this.name = "Bomb";
        this.maxQuantity = maxQuantity;
        this.quantity = maxQuantity;
    }

    /**
     * place a bomb in front of the player and decrement the current quantity
     * @param pos
     * @param facing
     */
    @Override
    public void use(Position pos, Facing facing, MapSegment segment) {
        if(quantity > 0) {
            decrement();
            createObject(pos, facing, segment);
        }
    }

    /**
     * helper method, creates a GameObject when you use a bomb
     * @param pos
     * @param facing
     */
    private void createObject(Position pos, Facing facing, MapSegment segment) {
        switch (facing) {
            case NORTH:
                bomb = new BombObject(pos.movePos(0, 1), segment);
                break;
            case SOUTH:
                bomb = new BombObject(pos.movePos(0, -1), segment);
                break;
            case EAST:
                bomb = new BombObject(pos.movePos(1, 0), segment);
                break;
            case WEST:
                bomb = new BombObject(pos.movePos(-1, 0), segment);
                break;
        }
    }

}
