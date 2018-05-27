package faces.awesome.controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import faces.awesome.GDXWrapper;
import faces.awesome.utils.navigator.*;
import faces.awesome.view.ScreenRepository;

import java.util.Map;

/**
 * @author Linus Wallman
 */
public class MainMenuScreenCtrl implements InputProcessor {
    private final GDXWrapper gdxWrapper;
    private TreeNavigator<Button> navigator;
    private TreeNavigator.Node currentNode;

    public MainMenuScreenCtrl(TreeNavigator<Button> navigator, GDXWrapper gdxWrapper) {
        this.navigator = navigator;
        this.gdxWrapper = gdxWrapper;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.DOWN:
                if (navigator.getRoot().getDown() != null) {
                    currentNode = navigator.getRoot().getDown();
                }
            case Input.Keys.UP:
                if (navigator.getRoot().getUp() != null) {
                    currentNode = navigator.getRoot().getUp();
                }
            case Input.Keys.SPACE:
                int value = currentNode.getValue();
                if (value == 0) {
                    ScreenRepository.setMainMenuScreen(gdxWrapper);
                    return false;
                }

                System.exit(0);
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
