package faces.awesome.model;
import faces.awesome.AwesomeGame;
import faces.awesome.model.item.BaseConsumable;
import faces.awesome.model.item.Item;
import java.lang.*;

public class PlayerCharacter extends Character {

    private Inventory inventory;
	  private Item slot1, slot2;
  
    public PlayerCharacter(Position pos) {
        super(pos, 5, 15);
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

	public void addNewToInventory(Item item) {
		inventory.addNewToInventory(item, this);
	}

	public void incrementConsumable(String name, int n) {
    	inventory.incrementConsumable(name, n);
	}

	public void incrementMaxConsumable(String name, int n) {
    	inventory.incrementMaxConsumable(name, n);
	}



	//TODO lägga till facing
	public void move(int dx, int dy, boolean solid) {

		//Kolla först om den tile man ska till är solid eller inte (här kommer det även kollas
		//om man tile är t.ex en dörr eller liknande som man kan gå in genom)

		//TODO ska anropa metoden isSolid med dx och dy (ska inte vara =false)
		boolean isSolid = false;


		//Kollar sedan om den tile man ska till är occupied av en annan character (typ enemy)

		//TODO ska anropa metoden isOccupied med dx och dy (ska inte vara =false)
		boolean isOccupied = false;



		//Om båda är false (tilen är inte solid och inte occupied) så rör man sig dit

		if (!isSolid && !isOccupied) {

			//Moves the playerCharacter
			setPos(pos.movePos(dx, dy));

		}

	}
}