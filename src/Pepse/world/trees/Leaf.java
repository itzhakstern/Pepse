package Pepse.world.trees;

import Pepse.world.Block;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.components.ScheduledTask;
import danogl.components.Transition;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import java.util.Random;


/**
 * this class represent a leaf in the game
 */
public class Leaf extends GameObject {
    Random random = new Random();
    Vector2 topLeft;
    private Object status;
    private Transition<Float> horizontal;

    /**
     * the constructor of the class
     * @param topLeftCorner topLeftCorner
     * @param renderable renderable
     */
    public Leaf(Vector2 topLeftCorner, Renderable renderable) {
        super(topLeftCorner,Vector2.ONES.mult(Block.SIZE),renderable);
        status = LeafStatus.ON_TREE;
        topLeft = topLeftCorner;
    }


    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if(other.getTag().equals("ground")){
            removeComponent(horizontal);
            new ScheduledTask(this,0,false,() -> setVelocity(Vector2.ZERO));
            status = LeafStatus.ON_GROUND;
        }
    }

    /**
     * this method called as a ScheduledTask
     */
    private void helperSchedule() {
        if (status == LeafStatus.ON_GROUND) {
            renderer().setOpaqueness(1f);
            setTopLeftCorner(topLeft);
            status = LeafStatus.ON_TREE;
        } else if (status == LeafStatus.ON_TREE &&
                random.nextFloat() <= 0.1) {
            status = LeafStatus.FALLING;
            transform().setVelocityY(55);
            horizontal = setHorizontalV();
            renderer().fadeOut(8);
        }
    }


    /**
     * schedule method
     */
    public void schedule() {
        setAngle();
        setDimensions();
        new ScheduledTask(this, random.nextFloat() * 15,
                true, this::helperSchedule);
    }


    /**
     * Transition
     */
    void setAngle() {
        new Transition<Float>(
                this,
                (angel) -> this.renderer().setRenderableAngle(angel),
                2f,
                -2f,
                Transition.CUBIC_INTERPOLATOR_FLOAT,
                1f,
                Transition.TransitionType.TRANSITION_BACK_AND_FORTH,
                null);
    }

    /**
     * Transition
     */
    void setDimensions() {
        new Transition<>(
                this,
                this::setDimensions,
                new Vector2(Block.SIZE, Block.SIZE),
                new Vector2(Block.SIZE - (1 / 5f) * Block.SIZE, Block.SIZE),
                Transition.CUBIC_INTERPOLATOR_VECTOR,
                1f,
                Transition.TransitionType.TRANSITION_BACK_AND_FORTH,
                null);
    }

    /**
     * Transition
     * @return Transition
     */
    private Transition<Float> setHorizontalV() {
        return new Transition<>(
                this,
                horizontal -> transform().setVelocityX(horizontal),
                -100f,
                100f,
                Transition.CUBIC_INTERPOLATOR_FLOAT,
                1f,
                Transition.TransitionType.TRANSITION_BACK_AND_FORTH,
                null);
    }
}