package faces.awesome.model.item.items.consumables;

import faces.awesome.model.Facing;
import faces.awesome.model.Position;
import faces.awesome.model.item.BaseConsumable;

/**
 * Created by Mr Cornholio on 20/04/2018.
 */
public class Bomb extends BaseConsumable {

    public Bomb(int maxQuantity) {
        this.name = "Bomb";
        this.maxQuantity = maxQuantity;
    }

    /**
     * place a bomb in front of the player and decrement the current quantity
     * @param pos
     * @param facing
     */
    @Override
    public void use(Position pos, Facing facing) {
        decrement();
    }
}
