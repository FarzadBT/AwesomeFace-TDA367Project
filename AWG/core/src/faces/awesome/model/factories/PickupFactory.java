package faces.awesome.model.factories;

import faces.awesome.model.objects.pickup.BasePickup;

/**
 * @author Farzad Besharati
 *
 * Handles the creation of Pickups
 */
public class PickupFactory<T extends BasePickup> {
    PickupFactoryCreator factoryCreator;

    public PickupFactory(PickupFactoryCreator<T> creator) {
        factoryCreator = creator;
    }

    public T create() {
        return (T) factoryCreator.create();
    }
}
