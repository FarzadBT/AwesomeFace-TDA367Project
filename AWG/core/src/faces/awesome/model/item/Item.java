package faces.awesome.model.item;

import faces.awesome.model.Facing;
import faces.awesome.model.MapSegment;
import faces.awesome.model.Position;

/**
 * @author Farzad Besharati
 *
 * TODO skriva vad klassen gör
 */

public interface Item {

    /**
     *
     * @return name of the item
     */
    String getName();

    /**
     * different effects depending on the item
     */
    void use(Position pos, Facing facing, MapSegment segment);

}
