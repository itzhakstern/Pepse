package Pepse.world;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;

public class Avatar extends GameObject {
    public static final String STAND_PATH = "/Users/davidrotenberg/IdeaProjects/Pepse/src/asserts/avatar.png";
    private static final String RUN_PATH = "";
    private final UserInputListener inputListener;
    private float levelOfEnergy = 100;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     */
    public Avatar(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,UserInputListener inputListener) {
        super(topLeftCorner, dimensions, renderable);
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
        float vel = 0;
        if(inputListener.isKeyPressed(KeyEvent.VK_RIGHT)){
            vel += 300;
            renderer().setIsFlippedHorizontally(false);
        }
        if(inputListener.isKeyPressed(KeyEvent.VK_LEFT)){
            vel -= 300;
            renderer().setIsFlippedHorizontally(true);
        }
        transform().setVelocityX(vel);
        if(inputListener.isKeyPressed(KeyEvent.VK_SPACE) && getVelocity().y() == 0){
            transform().setVelocityY(-300);
        }
        else if(inputListener.isKeyPressed(KeyEvent.VK_SHIFT) &&
                inputListener.isKeyPressed(KeyEvent.VK_SPACE) && levelOfEnergy > 0){
            levelOfEnergy -= 0.5f;
            transform().setVelocityY(-300);
        }
        if(transform().getVelocity().equals(Vector2.ZERO) && levelOfEnergy < 100){
            levelOfEnergy += 0.5f;
        }
    }

}
