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

    public void addToInventory(Item item, PlayerCharacter player) {
        if(item instanceof BaseInstant)
            ((BaseInstant)item).use(player);
        else if(!isInInventory(item.getName()))
            addNewToInventory(item);
        else if(item instanceof BaseConsumable)
            ((BaseConsumable)(inventory.get(item.getName()))).incrementN(((BaseConsumable) item).getQuantity());
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
     * @param name
     * @return true if item is in inventory, false if otherwise
     */
    public boolean isInInventory(String name) {
        return inventory.containsKey(name);
    }

    /**
     * Test function
     * @return the size of the hashmap
     */
    public int getSize() {
        return inventory.size();
    }

    public Item getItem(String name) {
        if(isInInventory(name))
            return inventory.get(name);
        return null;
    }
}
