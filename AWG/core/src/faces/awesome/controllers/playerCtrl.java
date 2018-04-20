package faces.awesome.controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class playerCtrl implements InputProcessor {



    @Override
    public boolean keyDown(int keycode) {

        if(keycode == Input.Keys.LEFT) {
            //pos.movePos(-32,0);
            //player.setPos(player.getPos().movePos(-32, 0));
        }

        if(keycode == Input.Keys.RIGHT) {
            //pos.movePos(32,0);
            //player.setPos(player.getPos().movePos(32, 0));
        }

        if(keycode == Input.Keys.UP) {
            //pos.movePos(0,32);
            //player.setPos(player.getPos().movePos(0, 32));
        }

        if(keycode == Input.Keys.DOWN) {
            //pos.movePos(0,-32);
            //player.setPos(player.getPos().movePos(0, -32));
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
