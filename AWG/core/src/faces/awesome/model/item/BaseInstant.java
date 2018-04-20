package faces.awesome.model.item;

/**
 * Created by Mr Cornholio on 20/04/2018.
 */
public abstract class BaseInstant implements Item {
    protected String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void use() {

    }
}
