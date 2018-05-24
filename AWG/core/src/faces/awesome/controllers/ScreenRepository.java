package faces.awesome.controllers;

import faces.awesome.GDXWrapper;
import faces.awesome.services.WorldMap;
import faces.awesome.view.GameOverScreen;
import faces.awesome.view.GameScreen;
import faces.awesome.view.GameWonScreen;
import faces.awesome.view.MainMenuScreen;

/**
 * @author Linus Wallman
 * Updated by: Therese Sturesson
 * This class is a repository for all the screens in the game.
 * It can also be used for changing current screen.
 */

public class ScreenRepository {
    //only one instance per screen needed.

    private static GameScreen gameScreen;
    private static GameOverScreen gameOverScreen;
    private static MainMenuScreen mainMenuScreen;
    private static GameWonScreen gameWonScreen;
    /**
     * define more screens here, define other behavior as well.
     */

    public static void setGameScreen(GDXWrapper g, WorldMap map) {
        if (gameScreen == null) {
            gameScreen = new GameScreen(g, map);
        }

        gameScreen.initialize();
        g.setScreen(gameScreen);
    }

    public static void setGameOverScreen(GDXWrapper g) {
        if (gameOverScreen == null) {
            gameOverScreen = new GameOverScreen(g);
        }

        gameOverScreen.initialize();
        g.setScreen(gameOverScreen);
    }

    public static void setMainMenuScreen(GDXWrapper g) {
        if (mainMenuScreen == null) {
            mainMenuScreen = new MainMenuScreen(g);
        }

        mainMenuScreen.initialize();

        g.setScreen(mainMenuScreen);
    }


    public static void setGameWonScreen(GDXWrapper g) {
        if (gameWonScreen == null) {
            gameWonScreen = new GameWonScreen(g);
        }

        gameWonScreen.initialize();
        g.setScreen(gameWonScreen);
    }


}