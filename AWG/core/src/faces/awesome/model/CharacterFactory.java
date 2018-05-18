package faces.awesome.model;

//Author: Amloudhi

public class CharacterFactory {

    public static PlayerCharacter createPlayer(int x, int y){
        return new PlayerCharacter(new Position(x, y));
    }


}
