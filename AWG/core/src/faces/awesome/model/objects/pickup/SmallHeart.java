package faces.awesome.model.objects.pickup;

import faces.awesome.model.GameObject;
import faces.awesome.model.Position;
import faces.awesome.model.characters.PlayerCharacter;

/**
 * Created by Mr Cornholio on 25/05/2018.
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
