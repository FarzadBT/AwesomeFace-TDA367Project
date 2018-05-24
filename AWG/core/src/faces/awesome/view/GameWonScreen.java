package faces.awesome.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import faces.awesome.GDXWrapper;
import faces.awesome.controllers.ScreenRepository;
import faces.awesome.controllers.ScreenSwitchListener;
import faces.awesome.controllers.ScreenSwitcher;
import faces.awesome.utils.AwesomeTimer;

/**
 * @author Therese Sturesson
 *
 */

public class GameWonScreen implements Screen, ScreenSwitchListener {

    private Stage stage;
    private final GDXWrapper gdxWrapper;

    private BitmapFont gameOverText;
    private SpriteBatch sprBatch;

    private AwesomeTimer timer;

    private float fadeElapsed = 0.f;

    private boolean calledOnce = false;

    public GameWonScreen(GDXWrapper game) {
        stage = new Stage(new ScreenViewport());
        gdxWrapper = game;
        sprBatch = new SpriteBatch();
        gameOverText = new BitmapFont();
        timer =  new AwesomeTimer();
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        fadeElapsed += delta;

        float fade = 1.0f;

        if (timer.secondsElapsed() >= 5) {
            fade = Interpolation.fade.apply(fadeElapsed / 1.0f);
            stage.draw();
        }

        gameOverText.setColor(1f, 1f, 1f, fade);
        gameOverText.getData().setScale(2.0f);
        sprBatch.begin();
        gameOverText.draw(sprBatch, "GAME WON", stage.getWidth() / 2 - 100, stage.getHeight() / 2 + 100);
        sprBatch.end();

    }


    public void initialize() {
        Gdx.input.setInputProcessor(stage);
        ScreenSwitcher.setListener(this);
    }

    @Override
    public void show() {

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("core/assets/shade/skin/uiskin.json"));

        TextButton mainMenu = new TextButton("Back To Main Menu", skin);
        table.add(mainMenu).height(75f).width(250f).fillX().uniformX();

        mainMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenRepository.setMainMenuScreen(gdxWrapper);
            }
        });

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void onScreenChange(ScreenSwitcher.ScreenType screen) {

    }
}

