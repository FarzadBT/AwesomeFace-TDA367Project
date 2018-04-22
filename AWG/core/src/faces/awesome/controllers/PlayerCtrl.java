package faces.awesome.controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import faces.awesome.model.Facing;
import faces.awesome.model.Player;

import java.awt.*;

public class PlayerCtrl implements InputProcessor {

    private Player player;
    private Facing facing;

    public PlayerCtrl(Player player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {

        if(keycode == Input.Keys.LEFT) {

            player.move(-1, 0);

        }

        if(keycode == Input.Keys.RIGHT) {

            player.move(1, 0);

        }

        if(keycode == Input.Keys.UP) {

            player.move(0, 1);

        }

        if(keycode == Input.Keys.DOWN) {

            player.move(0, -1);
            
        }

        return false;
    }

    
    //Methods we have to implement but we do not use
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    
}
