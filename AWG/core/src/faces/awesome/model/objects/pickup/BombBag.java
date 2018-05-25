package faces.awesome.model.objects.pickup;

import faces.awesome.model.GameObject;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.model.item.items.consumables.Bomb;

/**
 * Created by Mr Cornholio on 14/05/2018.
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
