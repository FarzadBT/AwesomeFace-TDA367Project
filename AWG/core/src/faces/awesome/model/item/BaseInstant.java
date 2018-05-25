package faces.awesome.model.item;

import faces.awesome.model.characters.PlayerCharacter;

/**
 * @author Farzad Besharati
 *
 * TODO skriva vad klassen gör
 */

public abstract class BaseInstant implements Item{

    protected String name;

    public String getName() {
        return name;
    }

    public abstract void use(PlayerCharacter player);

}
