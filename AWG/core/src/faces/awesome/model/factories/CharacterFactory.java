package faces.awesome.model.factories;

import com.squareup.otto.Bus;
import faces.awesome.model.MapSegment;
import faces.awesome.model.characters.PlayerCharacter;
import faces.awesome.model.Position;

/*
* @author Philip Nilsson
*
* A simple class that handles the creation of characters.
*/

public class CharacterFactory {

    //Creates a new PlayerCharacter at the specified coordinates.
    public static PlayerCharacter createPlayer(int x, int y, Bus bus, String name, MapSegment segment){
        return new PlayerCharacter(new Position(x, y), bus, name, segment);
    }


}
