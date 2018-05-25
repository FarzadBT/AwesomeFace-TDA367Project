package faces.awesome;

//Author: Philip Nilsson

//This class takes care of the instantiation of model-obbjects such as PlayerCharacter and items.

import faces.awesome.model.ItemFactory;
import faces.awesome.model.MapSegment;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.services.GdxWrapperService;

public class AwesomeGame {

    private PlayerCharacter player;
    private GdxWrapperService wrapper;
    private MapSegment segment;

    public AwesomeGame(GdxWrapperService wrapper, MapSegment segment, PlayerCharacter player){
        //player = CharacterFactory.createPlayer(10, 10);
        //player.addNewToInventory(ItemFactory.CreateSword());
        //player.addNewToInventory(ItemFactory.CreateHammer());
        //player.setSlot1(player.getInventory().getItem("Sword"));
        //player.setSlot1(player.getInventory().getItem("Hammer"));

        this.wrapper = wrapper;
        this.segment = segment;
        this.player = player;

    }

    public PlayerCharacter getPlayer(){
        return player;
    }

}
