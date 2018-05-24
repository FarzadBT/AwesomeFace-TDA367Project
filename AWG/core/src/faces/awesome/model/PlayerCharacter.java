package faces.awesome.model;

import com.squareup.otto.Bus;
import faces.awesome.events.PlayerCharacterDiedEvent;
import faces.awesome.model.item.Item;

/*
 * Author: Philip Nilsson
 * Updated by: Therese Sturesson, Farzad Besharati
 *
 * The class for the player character
 */


public class PlayerCharacter extends Character {

	// The variables in the class
    private Inventory inventory;
    private Item slot1, slot2;
	private Bus bus;

	public PlayerCharacter(Position pos, Bus bus, String name) {
        super(pos, 5, 100, name);
		this.bus = bus;
		inventory = new Inventory();
    }

    //Gets the items in slot 1 and 2
	public Item getSlot1() {
		return slot1;
	}

	public Item getSlot2() {
		return slot2;
	}

	//Sets the items in slot 1 and 2
	public void setSlot1(Item item) {
		slot1 = item;
	}

	public void setSlot2(Item item) {
		slot2 = item;
	}

	//Uses the slot 1 and 2
	public void useSlot1() {
		slot1.use(pos, facing);
	}

	public void useSlot2() {
		slot2.use(pos, facing);
	}

	//Gets the inventory för the player
	public Inventory getInventory() {
		return inventory;
	}

	//Adds new items to the inventory
	public void addNewToInventory(Item item) {
		inventory.addNewToInventory(item, this);
	}

	//Increment consumable items in the inventory
	public void incrementConsumable(String name, int n) {
    	inventory.incrementConsumable(name, n);
	}

	//Increment the max of consumable items in the inventory
	public void incrementMaxConsumable(String name, int n) {
    	inventory.incrementMaxConsumable(name, n);
	}


	//Moves the player
	public void move(int dx, int dy, boolean solid, boolean occupied ) {

		if (!solid && !occupied) {

			setPos(pos.movePos(dx, dy));

		}

	}

	//If you (the player) dies you lost the game
	@Override
	public void death() {

		bus.post(new PlayerCharacterDiedEvent());

	}

}