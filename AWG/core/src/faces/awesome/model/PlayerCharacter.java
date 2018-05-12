package faces.awesome.model;
import faces.awesome.AwesomeGame;
import faces.awesome.model.item.Item;
import java.lang.*;

public class PlayerCharacter extends Character {

    private Inventory inventory;
    private Item slot1, slot2;
  
    public PlayerCharacter(Position pos) {
        super(pos, 5, 100);
        inventory = new Inventory();
    }

	public Item getSlot1() {
		return slot1;
	}

	public void setSlot1(Item item) {
		slot1 = item;
	}

	public void useSlot1() {
		slot1.use(pos, facing);
	}

	public Item getSlot2() {
		return slot2;
	}

	public void setSlot2(Item item) {
		slot2 = item;
	}

	public void useSlot2() {
		slot2.use(pos, facing);
	}


	public Inventory getInventory() {
		return inventory;
	}

	public void addToInventory(Item item) {
		inventory.addToInventory(item, this);
	}



	//TODO lägga till facing
	public void move(int dx, int dy, boolean solid, boolean occupied ) {

		if (!solid && !occupied) {

			setPos(pos.movePos(dx, dy));

		}

	}
}