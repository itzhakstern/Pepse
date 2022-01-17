package Pepse.world;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.ImageReader;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import java.awt.event.KeyEvent;

/**
 * the class represent the avatar plyer in the game
 */
public class Avatar extends GameObject {
    public static final String STAND_PATH = "/Users/davidrotenberg/IdeaProjects/Pepse/asserts/avatar.png";
    private static final String RUN_PATH = "/Users/davidrotenberg/IdeaProjects/Pepse/asserts/ran_avatar.png";
    private static final String FLY_PATH = "/Users/davidrotenberg/IdeaProjects/Pepse/asserts/fly_avatar.png";
    private final UserInputListener inputListener;
    private float levelOfEnergy = 100;
    Renderable avatarRan;
    Renderable avatarStand;
    Renderable avatarFly;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     */
    public Avatar(Vector2 topLeftCorner, Vector2 dimensions,
                  Renderable renderable,
                  UserInputListener inputListener,
                  ImageReader imageReader) {

        super(topLeftCorner, dimensions, renderable);
        avatarRan = imageReader.readImage(Avatar.RUN_PATH, true);
        avatarStand = imageReader.readImage(Avatar.STAND_PATH, true);
        avatarFly = imageReader.readImage(Avatar.FLY_PATH, true);
        this.inputListener = inputListener;
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        transform().setAccelerationY(500);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if(other.getTag().equals("ground")){
            this.setVelocity(Vector2.ZERO);
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        this.renderer().setRenderable(this.avatarStand);
        float vel = 0;
        if(inputListener.isKeyPressed(KeyEvent.VK_RIGHT)){
            this.renderer().setRenderable(this.avatarRan);
            vel += 300;
            renderer().setIsFlippedHorizontally(false);
        }
        if(inputListener.isKeyPressed(KeyEvent.VK_LEFT)){
            this.renderer().setRenderable(this.avatarRan);
            vel -= 300;
            renderer().setIsFlippedHorizontally(true);
        }
        transform().setVelocityX(vel);
        if(inputListener.isKeyPressed(KeyEvent.VK_SPACE) && getVelocity().y() == 0){
            transform().setVelocityY(-300);
        }
        else if(inputListener.isKeyPressed(KeyEvent.VK_SHIFT) &&
                inputListener.isKeyPressed(KeyEvent.VK_SPACE) && levelOfEnergy > 0){
            this.renderer().setRenderable(this.avatarFly);
            levelOfEnergy -= 0.5f;
            transform().setVelocityY(-300);
        }
        if(transform().getVelocity().equals(Vector2.ZERO) && levelOfEnergy < 100){
            levelOfEnergy += 0.5f;
        }
    }

}
