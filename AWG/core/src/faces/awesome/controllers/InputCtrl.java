package faces.awesome.controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputCtrl implements InputProcessor {

    PlayerCtrl playerCtrl;

    public InputCtrl(PlayerCtrl playerCtrl) {
        this.playerCtrl = playerCtrl;
    }

    @Override
    public boolean keyDown(int keycode) {

        if(keycode == Input.Keys.LEFT) {

            playerCtrl.tryMove(-1, 0);

        }

        if(keycode == Input.Keys.RIGHT) {

            playerCtrl.tryMove(1, 0);

        }

        if(keycode == Input.Keys.UP) {

            playerCtrl.tryMove(0, 1);

        }

        if(keycode == Input.Keys.DOWN) {

            playerCtrl.tryMove(0, -1);

        }

        return true;
    }



    //Methods we have to implements but do not use
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
