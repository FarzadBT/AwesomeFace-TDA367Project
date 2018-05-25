package faces.awesome.model.objects.pickup;

import faces.awesome.model.characters.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.model.item.items.consumables.Bomb;

/**
 * @author Farzad Besharati
 *
 * When picked up, will either add a Bomb item to the players inventory or increase
 * max capacity of that Bomb item
 */

public class BombBag extends BasePickup {

    public BombBag(Position pos) {
        super(pos, "bombBag");
    }

    @Override
    public void onPickup(PlayerCharacter playerCharacter) {
        if (playerCharacter.getInventory().isInInventory("Bomb")) {
            playerCharacter.incrementMaxConsumable("Bomb", 10);
            playerCharacter.incrementConsumable("Bomb", 10);
        }
        else
            playerCharacter.addNewToInventory(new Bomb(10));
    }

}
