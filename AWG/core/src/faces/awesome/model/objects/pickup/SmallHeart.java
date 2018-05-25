package faces.awesome.model.objects.pickup;

import faces.awesome.model.GameObject;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;

/**
 * Created by Mr Cornholio on 25/05/2018.
 */
public class SmallHeart extends BasePickup {

    public SmallHeart(Position pos) {
        super(pos, "smallHeart");
    }

    @Override
    public void onPickup(PlayerCharacter playerCharacter) {
        playerCharacter.increaseHealth(1);
    }
}
