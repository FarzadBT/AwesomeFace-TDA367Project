package faces.awesome.model.item;

/**
 * @author Farzad Besharati
 *
 * TODO skriva vad klassen gör
 */

public abstract class BaseConsumable implements ConsumableItem {

    protected String name;
    protected int quantity = 1, maxQuantity;

    @Override
    public String getName() {
        return name;
    }


    @Override
    public void increment() {
        if (quantity < maxQuantity)
            quantity++;
        else
            quantity = maxQuantity;
    }

    @Override
    public void decrement() {
        if (quantity > 0)
            quantity--;
        else
            quantity = 0;
    }

    @Override
    public void incrementN(int n) {
        if (quantity+n < maxQuantity)
            quantity += n;
        else
            quantity = maxQuantity;
    }

    @Override
    public void decrementN(int n) {
        if (quantity-n >= 0)
            quantity -= n;
        else
            quantity = 0;
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
