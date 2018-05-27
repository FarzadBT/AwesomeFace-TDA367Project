package faces.awesome.model.item;

/**
 * @author Farzad Besharati
 *
 * A more advanced Item interface, has all the attributes for a consumable
 * item (an item that has a quantity)
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