package faces.awesome.model.item;

import faces.awesome.model.Facing;
import faces.awesome.model.Position;

/**
 * Created by Mr Cornholio on 20/04/2018.
 */
public interface Item {
    /**
     *
     * @return name of the item
     */
    String getName();

    /**
     * Different effects depending on the item
     */
    void use(Position pos, Facing facing);


}
