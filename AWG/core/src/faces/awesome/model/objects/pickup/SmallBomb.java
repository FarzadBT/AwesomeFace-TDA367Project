package faces.awesome.model.objects.pickup;

import faces.awesome.model.GameObject;
import faces.awesome.model.characters.PlayerCharacter;
import faces.awesome.model.Position;

/**
 * @author Farzad Besharati
 *
 * TODO skriva vad klassen gör
 */

public class SmallBomb extends BasePickup {

    public SmallBomb(Position pos) {
        super(pos, "smallBomb");
    }

    public void onPickup(PlayerCharacter playerCharacter) {
        playerCharacter.incrementConsumable("Bomb", 1);
    }

}
