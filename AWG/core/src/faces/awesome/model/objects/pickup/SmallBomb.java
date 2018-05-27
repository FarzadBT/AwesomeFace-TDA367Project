package faces.awesome.model.objects.pickup;

import faces.awesome.model.Position;
import faces.awesome.model.characters.PlayerCharacter;

/**
 * @author Farzad Besharati
 *
 * When picked up will increase the quantity of bombs in the players inventory
 * by a small amount
 */

public class SmallBomb extends BasePickup {

    public SmallBomb(Position pos) {
        super(pos, "smallBomb");
    }

    public void onPickup(PlayerCharacter playerCharacter) {
        playerCharacter.incrementConsumable("Bomb", 1);
    }

}
