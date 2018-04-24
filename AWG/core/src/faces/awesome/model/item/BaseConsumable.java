package faces.awesome.model.item;

/**
 * Created by Mr Cornholio on 20/04/2018.
 */
public abstract class BaseConsumable implements ConsumableItem {
    protected String name;
    protected int quantity = 1, maxQuantity;

    /**
     *
     * @return name of the item
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Has different effects depending on the item
     */
    @Override
    public void use() {

    }

    @Override
    public void increment() {
        quantity++;
    }

    @Override
    public void decrement() {
        quantity--;
    }

    @Override
    public void increaseMax(int n) {
        maxQuantity += n;
    }

    @Override
    public void decreaseMax(int n) {
        maxQuantity -= n;
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
