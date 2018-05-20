package faces.awesome.model;

//Author: Philip Nilsson
//A simple class that handles the creation of characters.

public class CharacterFactory {

    //Creates a new PlayerCharacter at the specified coordinates.
    public static PlayerCharacter createPlayer(int x, int y){
        return new PlayerCharacter(new Position(x, y));
    }


}
