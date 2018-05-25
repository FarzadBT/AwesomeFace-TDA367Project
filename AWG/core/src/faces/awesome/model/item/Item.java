package faces.awesome.model.item;

import faces.awesome.model.Facing;
import faces.awesome.model.MapSegment;
import faces.awesome.model.Position;

/**
 * @author Farzad Besharati
 *
 * An interface used for creating items, has all basic attributes of an item
 */

public interface Item {

    /**
     *
     * @return name of the item
     */
    String getName();

    /**
     * different effects depending on the item
     *
     * @param pos origin position of the player using the item
     * @param facing facing of the player using the itme
     * @param segment the current MapSegment that the player is in
     */
    void use(Position pos, Facing facing, MapSegment segment);

}
