package faces.awesome.model.item;

import faces.awesome.model.Facing;
import faces.awesome.model.Position;

/**
 * Created by Mr Cornholio on 20/04/2018.
 */
public interface ConsumableItem extends Item {

    /**
     * Increase the quantity by 1
     */
    void increment();

    /**
     * Decrease the quantity by 1
     */
    void decrement();

    /**
     * Increase the quantity by n
     * @param n amount to increase
     */
    void incrementN(int n);

    /**
     * Decrease the quantity by n
     * @param n amount to decrease
     */
    void decrementN(int n);

    /**
     * Increase maxQuantity by n
     * @param n amount to increase by
     */
    void increaseMax(int n);

    /**
     * Decrease maxQuantity by n
     * @param n amount to decrease by
     */
    void decreaseMax(int n);

    /**
     *
     * @return the current quantity
     */
    int getQuantity();

    /**
     *
     * @return the current max quantity
     */
    int getMax();
}