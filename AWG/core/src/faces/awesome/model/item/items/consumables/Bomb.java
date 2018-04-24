package faces.awesome.model.item.items.consumables;

import faces.awesome.model.item.BaseConsumable;

/**
 * Created by Mr Cornholio on 20/04/2018.
 */
public class Bomb extends BaseConsumable {

    public Bomb(int maxQuantity) {
        this.name = "Bomb";
        this.maxQuantity = maxQuantity;
    }

    @Override
    public void use() {
        decrement();
    }
}
