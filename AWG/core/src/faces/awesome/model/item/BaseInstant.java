package faces.awesome.model.item;

import faces.awesome.model.Facing;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;

/**
 * Created by Mr Cornholio on 20/04/2018.
 */
public abstract class BaseInstant implements Item{
    protected String name;

    public String getName() {
        return name;
    }

    public abstract void use(PlayerCharacter player);
}
