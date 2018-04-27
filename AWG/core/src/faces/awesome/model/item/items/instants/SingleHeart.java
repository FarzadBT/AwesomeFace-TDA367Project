package faces.awesome.model.item.items.instants;

import faces.awesome.model.Facing;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.model.item.BaseInstant;

/**
 * Created by Mr Cornholio on 24/04/2018.
 */
public class SingleHeart extends BaseInstant {
    public SingleHeart() {
        this.name = "Single Heart";
    }

    @Override
    public void use(PlayerCharacter player) {
        player.increaseHealth(1);
    }

    // Is not really used
    @Override
    public void use(Position pos, Facing facing) {

    }
}
