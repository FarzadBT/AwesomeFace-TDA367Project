package faces.awesome.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import faces.awesome.model.Facing;

/**
 * @author Therese Sturesson
 * Updated by: Linus Wallman, Philip Nilsson
 *
 * A controller that takes in input from the keybord
 */

public class GameScreenCtrl implements InputProcessor {

    private PlayerCtrl playerCtrl;

    public GameScreenCtrl(PlayerCtrl playerCtrl) {
        this.playerCtrl = playerCtrl;
    }

    @Override
    public boolean keyDown(int keycode) {
        boolean isCtrlPressed = Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)
                || Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT);

        if(isCtrlPressed) {
            switch (keycode) {
                case Input.Keys.LEFT:
                    playerCtrl.changeFacing(Facing.WEST);
                    break;
                case Input.Keys.RIGHT:
                    playerCtrl.changeFacing(Facing.EAST);
                    break;
                case Input.Keys.UP:
                    playerCtrl.changeFacing(Facing.NORTH);
                    break;
                case Input.Keys.DOWN:
                    playerCtrl.changeFacing(Facing.SOUTH);
                    break;
            }
        } else {
            switch (keycode) {
                case Input.Keys.LEFT:
                    playerCtrl.tryMove(-1, 0, Facing.WEST);
                    break;
                case Input.Keys.RIGHT:
                    playerCtrl.tryMove(1, 0, Facing.EAST);
                    break;
                case Input.Keys.UP:
                    playerCtrl.tryMove(0, 1, Facing.NORTH);
                    break;
                case Input.Keys.DOWN:
                    playerCtrl.tryMove(0, -1, Facing.SOUTH);
                    break;
            }
        }

            /*if(keycode == Input.Keys.LEFT) {
            playerCtrl.tryMove(-1, 0, Facing.WEST);
        } else if(keycode == Input.Keys.RIGHT) {
            playerCtrl.tryMove(1, 0, Facing.EAST);
        } else if(keycode == Input.Keys.UP) {
            playerCtrl.tryMove(0, 1, Facing.NORTH);
        } else if (keycode == Input.Keys.DOWN) {
            playerCtrl.tryMove(0, -1, Facing.SOUTH);
        }*/

        switch (keycode) {
            case Input.Keys.A:
                playerCtrl.useItem1();
                break;
            case Input.Keys.S:
                playerCtrl.useItem2();
                break;
        }

        return true;
    }

    //Methods we have to implement but do not use (from the interface InputProcessor)
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
