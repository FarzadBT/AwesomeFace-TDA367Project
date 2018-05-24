package faces.awesome.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import faces.awesome.GDXWrapper;
import faces.awesome.controllers.ScreenRepository;
import faces.awesome.controllers.ScreenSwitchListener;
import faces.awesome.controllers.ScreenSwitcher;
import faces.awesome.controllers.ScreenSwitcher.ScreenType;

public class MainMenuScreen implements Screen, ScreenSwitchListener {

    private Stage stage;
    private final GDXWrapper gdxWrapper;

    public MainMenuScreen(GDXWrapper game) {
        stage = new Stage();
        gdxWrapper = game;
    }



    @Override
    public void show() {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        //Skin skin = new Skin(GDXWrapper.assets.getFileHandle("mainUi"));
        Skin skin = new Skin(Gdx.files.internal("core/assets/shade/skin/uiskin.json"));

        TextButton newGame = new TextButton("New Game", skin);
        TextButton exit = new TextButton("Exit", skin);

        table.add(newGame).height(75f).width(250).fillX().uniformX();
        table.row().pad(40, 0, 40, 0);
        table.add(exit).height(75f).width(250).fillX().uniformX();

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenRepository.setGameScreen(gdxWrapper, gdxWrapper.world);
            }
        });

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.exit(0);
            }
        });


    }

    public void initialize() {
        Gdx.input.setInputProcessor(stage);
        ScreenSwitcher.setListener(this);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }

    @Override
    public void onScreenChange(ScreenType screen) {

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }
}
