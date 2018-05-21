package faces.awesome;

//Author: Philip Nilsson

//This class takes care of the instantiation of model-obbjects such as PlayerCharacter and items.

import faces.awesome.model.CharacterFactory;
import faces.awesome.model.ItemFactory;
import faces.awesome.model.PlayerCharacter;

public class Game {

    private PlayerCharacter player;

public Game(){
    //player = CharacterFactory.createPlayer(10, 10);
    player.addNewToInventory(ItemFactory.CreateSword());
    player.addNewToInventory(ItemFactory.CreateHammer());
    player.setSlot1(player.getInventory().getItem("Sword"));
    player.setSlot1(player.getInventory().getItem("Hammer"));
}

public PlayerCharacter getPlayer(){
    return player;
}

}
