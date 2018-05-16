package faces.awesome.model.objects.pickup;

import faces.awesome.model.GameObject;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;

/**
 * Created by Mr Cornholio on 14/05/2018.
 */
public abstract class PickupItem extends GameObject {
    public PickupItem(Position pos) {
        super(pos);
    }

    public abstract void onPickup(PlayerCharacter playerCharacter);
}
