package faces.awesome.model.objects.pickup;

import faces.awesome.model.GameObject;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;

/**
 * Created by Mr Cornholio on 25/05/2018.
 */
public abstract class BasePickup extends GameObject {

    public BasePickup(Position pos, String name) {
        super(pos, name);
    }

    public abstract void onPickup(PlayerCharacter playerCharacter);
}
