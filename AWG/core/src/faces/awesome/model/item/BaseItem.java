package faces.awesome.model.item;

import faces.awesome.model.Facing;
import faces.awesome.model.MapSegment;
import faces.awesome.model.Position;

/**
 * @author Farzad Besharati
 *
 * TODO skriva vad klassen gör
 */

public abstract class BaseItem implements Item {

    protected String name;

    public String getName() {
        return name;
    }

    public abstract void use(Position pos, Facing facing, MapSegment segment);

}
