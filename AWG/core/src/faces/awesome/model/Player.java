package faces.awesome.model;

import faces.awesome.model.characters.PlayerCharacter;
import faces.awesome.model.item.BaseConsumable;
import faces.awesome.model.item.Item;
import faces.awesome.model.Position;

public class Player {
	private Inventory inventory;
	private Item slot1, slot2;
	private PlayerCharacter playerCharacter;

	public Player(Position pos) {
		inventory = new Inventory();
		playerCharacter = new PlayerCharacter(pos);
	}

	public Item getSlot1() {
		return slot1;
	}

	public void setSlot1(Item item) {
		slot1 = item;
	}

	public Item getSlot2() {
		return slot2;
	}

	public void setSlot2(Item item) {
		slot2 = item;
	}

	public void addToInventory(Item item) {
		if(!inventory.isInInventory(item))
			inventory.addNewToInventory(item);
		else if(item instanceof BaseConsumable)
			inventory.incrementConsumable();
	}
}