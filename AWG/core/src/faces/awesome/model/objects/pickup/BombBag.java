package faces.awesome.model.objects.pickup;

import faces.awesome.model.characters.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.model.item.items.consumables.Bomb;

/**
 * @author Farzad Besharati
 *
 * TODO skriva vad klassen gör
 */

public class BombBag extends PickupItem {

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
