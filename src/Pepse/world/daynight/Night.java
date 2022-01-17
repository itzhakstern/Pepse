package Pepse.world.daynight;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.components.CoordinateSpace;
import danogl.components.Transition;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;

import java.awt.*;

/**
 *this class represent the night in the game
 */
public class Night {
    private static final Color NIGHT = Color.BLACK;
    private static final Float MIDNIGHT_OPACITY = 0.5f;

    /**
     * the method create the night in the game
     * @param gameObjects gameObjects
     * @param windowDimensions windowDimensions
     * @param  cycleLength cycleLength
     * @param layer layer
     * @return GameObject(the night)
     */
    public static GameObject create(
            GameObjectCollection gameObjects,
            Vector2 windowDimensions,
            float cycleLength,
            int layer){

        GameObject night = new GameObject(Vector2.ZERO,windowDimensions,new RectangleRenderable(NIGHT));
        night.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        night.setTag("night");
        gameObjects.addGameObject(night, layer);

        new Transition<Float>(
                night, //the game object being changed
                night.renderer()::setOpaqueness,  //the method to call
                0f,    //initial transition value
                MIDNIGHT_OPACITY,   //final transition value
                Transition.CUBIC_INTERPOLATOR_FLOAT,  //use a cubic interpolator
                cycleLength/2,   //transtion fully over half a day
                Transition.TransitionType.TRANSITION_BACK_AND_FORTH,
                null);  //nothing further to execute upon reaching final value

        return night;
    }
}
