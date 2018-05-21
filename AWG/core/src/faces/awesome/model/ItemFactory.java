package faces.awesome.model;

//Author: Philip Nilsson

//A simple class that handles the creation of items.


import faces.awesome.model.item.Item;
import faces.awesome.model.item.items.permanents.Hammer;
import faces.awesome.model.item.items.permanents.Sword;

public class ItemFactory {

    public static Item CreateSword(){
        return new Sword();
    }

    public static Item CreateHammer(){
        return new Hammer();
    }

}
