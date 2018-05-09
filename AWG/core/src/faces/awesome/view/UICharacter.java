package faces.awesome.view;

import faces.awesome.model.Character;
import faces.awesome.model.Position;

public class UICharacter {

    public enum State { STANDING, RUNNING }

    private AnimationDefinition animDefs;

    private Character character;

    private State currentState = State.STANDING;

    UICharacter(Character character) {
        this.character = character;
    }

    public State getState() {
        return currentState;
    }

    public void setState(State state) {
        currentState = state;
    }

    public Character getCharacter() {
        return character;
    }

    public void drawSelf() {
        Position pos = new Position(character.getPos().getX(), character.getPos().getY());


    }
}
