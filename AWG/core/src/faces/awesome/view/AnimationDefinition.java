package faces.awesome.view;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimationDefinition {

    // Look into flipping the texture region instead. this is here for now

    private TextureRegion characterStandingUp;
    private TextureRegion characterStandingLeft;
    private TextureRegion characterStandingDown;
    private TextureRegion characterStandingRight;

    private Animation<TextureRegion> characterRunningUp;
    private Animation<TextureRegion> characterRunningLeft;
    private Animation<TextureRegion> characterRunningDown;
    private Animation<TextureRegion> characterRunningRight;

    public float stateTime;

    AnimationDefinition(/*TextureAtlas atlas,*/ String regionName, int width, int height, int frameCount) {
        TextureAtlas atlas = new TextureAtlas("core/assets/enemies_and_player.pack");

        characterStandingUp = new TextureRegion(atlas.findRegion(regionName), 16, 0,  width, height);
        characterStandingLeft = new TextureRegion(atlas.findRegion(regionName), 16, 64, width, height);
        characterStandingDown = new TextureRegion(atlas.findRegion(regionName), 16, 128, width, height);
        characterStandingRight = new TextureRegion(atlas.findRegion(regionName), 16, 194, width, height);

        // Sort out a smoother way of finding regions (coordinates)
        characterRunningUp = new Animation<>(0.3f, setupRunningFrames(atlas.findRegion(regionName), 80, 0, width, height, frameCount));
        characterRunningLeft = new Animation<>(0.3f, setupRunningFrames(atlas.findRegion(regionName), 80, 64, width, height, frameCount));
        characterRunningDown = new Animation<>(0.3f, setupRunningFrames(atlas.findRegion(regionName), 80, 128, width, height, frameCount));
        characterRunningRight = new Animation<>(0.3f, setupRunningFrames(atlas.findRegion(regionName), 80, 194, width, height, frameCount));

        stateTime = 0.f;
    }

    private Array<TextureRegion> setupRunningFrames(AtlasRegion region, int x, int y, int width, int height, int frameCount) {
        Array<TextureRegion> frames = new Array<>();
        for (int i = 1; i < frameCount; i++) {
            TextureRegion tr = new TextureRegion(region, i * x, y, width, height);
            frames.add(tr);
        }

        return frames;
    }

    public TextureRegion getCharacterStandingUp() {
        return characterStandingUp;
    }

    public TextureRegion getCharacterStandingLeft() {
        return characterStandingLeft;
    }

    public TextureRegion getCharacterStandingDown() {
        return characterStandingDown;
    }

    public TextureRegion getCharacterStandingRight() {
        return characterStandingRight;
    }

    public Animation<TextureRegion> getCharacterRunningUp() {
        stateTime = 0.f;
        return characterRunningUp;
    }

    public Animation<TextureRegion> getCharacterRunningLeft() {
        stateTime = 0.f;
        return characterRunningLeft;

    }

    public Animation<TextureRegion> getCharacterRunningDown() {
        stateTime = 0.f;
        return characterRunningDown;
    }

    public Animation<TextureRegion> getCharacterRunningRight() {
        stateTime = 0.f;
        return characterRunningRight;
    }
}
