package faces.awesome.model.item.items.permanents;

import faces.awesome.model.Facing;
import faces.awesome.model.MapSegment;
import faces.awesome.model.Position;
import faces.awesome.model.item.BaseItem;

/**
 * @author Farzad Besharati
 *
 * A Hammer permanent item, when used it will deal damage to enemies in front of you
 */

public class Hammer extends BaseItem {

    public Hammer() {
        this.name = "Hammer";
    }

    @Override
    public void use(Position pos, Facing facing, MapSegment segment) {

    }

}
