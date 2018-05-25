package faces.awesome.model.factories;

import faces.awesome.model.item.Item;
import faces.awesome.model.item.items.permanents.Hammer;
import faces.awesome.model.item.items.permanents.Sword;

/*
* @author Philip Nilsson
*
* A simple class that handles the creation of items.
*/

public class ItemFactory {

    public static Item CreateSword(){
        return new Sword();
    }

    public static Item CreateHammer(){
        return new Hammer();
    }

}
