package faces.awesome.model.objects.pickup;

import faces.awesome.model.Position;
import faces.awesome.model.characters.PlayerCharacter;

/**
 * @author Farzad Besharati
 *
 * When picked up will restore the players health by a small amount
 */
public class SmallHeart extends BasePickup {

    public SmallHeart(Position pos) {
        super(pos, "smallHeart");
    }

    @Override
    public void onPickup(PlayerCharacter playerCharacter) {
        playerCharacter.increaseHealth(5);
    }
}
