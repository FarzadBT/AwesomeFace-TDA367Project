package faces.awesome.model;

import faces.awesome.model.characters.PlayerCharacter;
import faces.awesome.model.item.BaseConsumable;
import faces.awesome.model.item.BaseInstant;
import faces.awesome.model.item.Item;
import java.util.HashMap;

/*
 * Author: Farzad Besharati
 * Updated by:
 *
 * TODO skriva vad klassen gör
 */

public class Inventory {
    private HashMap<String, Item> inventory;

    public Inventory() {
        inventory = new HashMap<>();
    }

    public void addNewToInventory(Item item, PlayerCharacter player) {
        if(item instanceof BaseInstant)
            ((BaseInstant)item).use(player);
        else if(!isInInventory(item.getName()))
            inventory.put(item.getName(), item);
    }

    public void incrementConsumable(String name, int n) {
        if(isInInventory(name))
            getConsumable(name).incrementN(n);
    }

    public void incrementMaxConsumable(String name, int n) {
        if(isInInventory(name))
            getConsumable(name).increaseMax(n);
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

    public BaseConsumable getConsumable(String name) {
        if (isInInventory(name))
            return (BaseConsumable) inventory.get(name);
        return null;
    }
}
