package faces.awesome.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import faces.awesome.model.Facing;

public class GameCtrl implements InputProcessor {

    PlayerCtrl playerCtrl;
    OrthographicCamera camera;


    public GameCtrl(PlayerCtrl playerCtrl, OrthographicCamera camera) {
        this.playerCtrl = playerCtrl;
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.LEFT) {
            playerCtrl.tryMove(-1, 0);
            playerCtrl.setFacing(Facing.WEST);
        }

        if (keycode == Input.Keys.RIGHT) {
            playerCtrl.tryMove(1, 0);
            playerCtrl.setFacing(Facing.EAST);
        }

        if (keycode == Input.Keys.UP) {
            playerCtrl.tryMove(0, 1);
            playerCtrl.setFacing(Facing.NORTH);
        }

        if (keycode == Input.Keys.DOWN) {
            playerCtrl.tryMove(0, -1);
            playerCtrl.setFacing(Facing.SOUTH);
        }

        boolean isCtrlPressed = false;

        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {
            isCtrlPressed = true;
        }

        if (isCtrlPressed && keycode == Input.Keys.LEFT) {
            playerCtrl.setFacing(Facing.WEST);
        } else if (isCtrlPressed && keycode == Input.Keys.RIGHT) {
            playerCtrl.setFacing(Facing.EAST);
        } else if (isCtrlPressed && keycode == Input.Keys.UP) {
            playerCtrl.setFacing((Facing.NORTH));
        } else if (isCtrlPressed && keycode == Input.Keys.DOWN) {
            playerCtrl.setFacing(Facing.SOUTH);
        }

        return true;
    }



    //Methods we have to implement but do not use
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
