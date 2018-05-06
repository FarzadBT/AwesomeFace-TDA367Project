package faces.awesome.view;

import faces.awesome.model.Character;

public class UICharacter {

    public enum State {STANDING, RUNNING}

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
}
