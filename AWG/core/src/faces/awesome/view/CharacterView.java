package faces.awesome.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import faces.awesome.AwesomeGame;
import faces.awesome.model.Character;
import faces.awesome.model.Facing;


public class CharacterView {

    public enum State { STANDING, RUNNING }

    private AnimationDefinition animDefs;

    private final Character character;

    private float movementTime;

    private State currentState = State.STANDING;

    public float elapsedTimeSinceMovement = 0.f;

    private TextureRegion currentTexture;

    private Animation<TextureRegion> currentAnimation;

    CharacterView(Character c) {
        this.character = c;
    }


    public void draw(SpriteBatch sprBatch) {

        switch (currentState) {
            case STANDING: {
                TextureRegion region;
                if (character.getFacing() == Facing.NORTH) {
                    region = animDefs.getCharacterStandingUp();
                } else if (character.getFacing() == Facing.WEST) {
                    region = animDefs.getCharacterStandingLeft();
                } else if (character.getFacing() == Facing.SOUTH) {
                    region = animDefs.getCharacterStandingDown();
                } else if (character.getFacing() == Facing.EAST) {
                    region = animDefs.getCharacterStandingRight();
                } else {
                    // throw some exception perhaps. However, this case should never happen. It's just here to keep the interpreter from going insane
                    region = null;
                }
                sprBatch.draw(region, character.getPos().getX() * AwesomeGame.TILE_SIZE, character.getPos().getY() * AwesomeGame.TILE_SIZE);
                break;
            }

            case RUNNING: {
                if (elapsedTimeSinceMovement >= 0.0625f) {
                    Animation<TextureRegion> region;
                    if (character.getFacing() == Facing.NORTH) {
                        region = animDefs.getCharacterRunningUp();
                    } else if (character.getFacing() == Facing.WEST) {
                        region = animDefs.getCharacterRunningLeft();
                    } else if (character.getFacing() == Facing.SOUTH) {
                        region = animDefs.getCharacterRunningDown();
                    } else if (character.getFacing() == Facing.EAST) {
                        region = animDefs.getCharacterRunningRight();
                    } else {
                        region = null;
                    }
                    // TextureRegion currentFrame = region.getKeyFrame();
                    // sprBatch.draw(currentFrame, )
                }
                break;
            }

            default:
                break;
        }

    }
}
