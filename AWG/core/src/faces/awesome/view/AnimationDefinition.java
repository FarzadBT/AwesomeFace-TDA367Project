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

    AnimationDefinition() {
        TextureAtlas atlas = new TextureAtlas("core/assets/enemies_and_player.pack");

        characterStandingUp = new TextureRegion(atlas.findRegion("maskman"), 16, 0,  64, 64);
        characterStandingLeft = new TextureRegion(atlas.findRegion("maskman"), 16, 64, 64, 64);
        characterStandingDown = new TextureRegion(atlas.findRegion("maskman"), 16, 128, 64, 64);
        characterStandingRight = new TextureRegion(atlas.findRegion("maskman"), 16, 194, 64, 64);

        characterRunningUp = new Animation<>(0.3f, setupRunningFrames(atlas.findRegion("maskman"), 80, 0, 64, 64, 8));
        characterRunningLeft = new Animation<>(0.3f, setupRunningFrames(atlas.findRegion("maskman"), 80, 64, 64, 64, 8));
        characterRunningDown = new Animation<>(0.3f, setupRunningFrames(atlas.findRegion("maskman"), 80, 128, 64, 64, 8));
        characterRunningRight = new Animation<>(0.3f, setupRunningFrames(atlas.findRegion("maskman"), 80, 194, 64, 64, 8));

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
}
