package faces.awesome.model.objects.pickup;

import faces.awesome.model.GameObject;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.model.item.items.consumables.Bomb;

/**
 * Created by Mr Cornholio on 10/05/2018.
 */
public class SmallBomb extends BasePickup {

    public SmallBomb(Position pos) {
        super(pos, "smallBomb");
    }

    public void onPickup(PlayerCharacter playerCharacter) {
        playerCharacter.incrementConsumable("Bomb", 1);
    }
}
