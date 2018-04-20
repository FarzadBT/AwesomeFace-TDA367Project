package faces.awesome.model.item;

/**
 * Created by Mr Cornholio on 20/04/2018.
 */
public abstract class BaseConsumable implements ConsumableItem {
    protected String name;
    protected int quantity = 0, maxQuantity;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void use() {

    }

    public void increment() {
        quantity++;
    }

    public void decrement() {
        quantity--;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public int getMax() {
        return maxQuantity;
    }
}
