package faces.awesome.model.objects.pickup;

import faces.awesome.model.GameObject;
import faces.awesome.model.characters.PlayerCharacter;
import faces.awesome.model.Position;

/**
 * @author Farzad Besharati
 *
 * TODO skriva vad klassen gör
 */

public class BombPickupSmall extends GameObject {

    public BombPickupSmall(Position pos) {
        super(pos, "bombPickupSmall");
    }

    public void onPickup(PlayerCharacter playerCharacter) {
        playerCharacter.incrementConsumable("Bomb", 1);
    }

}
