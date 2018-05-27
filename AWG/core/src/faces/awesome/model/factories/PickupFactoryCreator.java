package faces.awesome.model.factories;

import faces.awesome.model.objects.pickup.BasePickup;

/**
 * @author Farzad Besharati
 */
public interface PickupFactoryCreator<T extends BasePickup> {
    public T create();
}
