package faces.awesome.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import faces.awesome.GDXWrapper;
import faces.awesome.view.ScreenRepository;

/**
 * @author Linus Wallman
 */
public class GameOverScreenCtrl implements InputProcessor {

    private final GDXWrapper gdxWrapper;

    public GameOverScreenCtrl(GDXWrapper g) {
        this.gdxWrapper = g;
    }

    @Override
    public boolean keyDown(int keycode) {

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            ScreenRepository.setCreditScreen(gdxWrapper);
        }

        return false;
    }

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
