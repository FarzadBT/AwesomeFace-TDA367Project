package faces.awesome.controllers;

import faces.awesome.AwesomeGame;
import faces.awesome.model.WorldMap;
import faces.awesome.view.GameScreen;

/**
 * This class is a repository for all the screens in the game.
 * It can also be used for changing current screen.
 */

public class ScreenRepository {
    //only one instance per screen needed.

    private static GameScreen gameScreen;
    /**
     * define more screens here, define other behavior as well.
     */

    public void setGameScreen(AwesomeGame g, WorldMap map) {
        if (gameScreen == null) {
            gameScreen = new GameScreen(g, map);
        }

        g.setScreen(gameScreen);

        // Here we initialize the model
    }
}