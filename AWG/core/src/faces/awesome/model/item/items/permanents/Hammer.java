package faces.awesome.model.item.items.permanents;

import faces.awesome.model.Facing;
import faces.awesome.model.MapSegment;
import faces.awesome.model.Position;
import faces.awesome.model.item.BaseItem;

/**
 * Created by Mr Cornholio on 24/04/2018.
 */
public class Hammer extends BaseItem {

    public Hammer() {
        this.name = "Hammer";
    }

    @Override
    public void use(Position pos, Facing facing, MapSegment segment) {

    }
}
