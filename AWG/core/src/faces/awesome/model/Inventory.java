package faces.awesome.model;

import faces.awesome.model.item.BaseConsumable;
import faces.awesome.model.item.BaseInstant;
import faces.awesome.model.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mr Cornholio on 20/04/2018.
 */
public class Inventory {
    private HashMap<String, Item> inventory;

    public Inventory() {
        inventory = new HashMap<>();
    }

    public void addToInventory(Item item) {
        if(item instanceof BaseInstant)
            item.use();
        else if(!isInInventory(item))
            addNewToInventory(item);
        else if(item instanceof BaseConsumable)
            ((BaseConsumable) item).increment();
    }

    /**
     * Add a new item to the inventory
     * @param item
     */
    private void addNewToInventory(Item item) {
        inventory.put(item.getName(), item);
    }

    /**
     * Increment a consumable item in the inventory
     * @param item
     */
    public void incrementConsumable(BaseConsumable item) {
        item.increment();
    }

    /**
     * Decrement a consumable item in the inventory
     * @param item
     */
    public void decrementConsumable(BaseConsumable item) {
        item.decrement();
    }

    /**
     * Finds out if an item is currently in the inventory
     * @param item
     * @return true if item is in inventory, false if otherwise
     */
    public boolean isInInventory(Item item) {
        return inventory.containsKey(item.getName());
    }

    /**
     * Test function
     * @return the size of the hashmap
     */
    public int getSize() {
        return inventory.size();
    }
}
