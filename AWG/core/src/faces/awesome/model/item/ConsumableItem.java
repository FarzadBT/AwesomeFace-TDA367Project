package faces.awesome.model.item;

/**
 * Created by Mr Cornholio on 20/04/2018.
 */
public interface ConsumableItem extends Item {
    int getQuantity();

    int getMax();

    void increment();

    void decrement();

    void increaseMax(int n);

    void decreaseMax(int n);
}