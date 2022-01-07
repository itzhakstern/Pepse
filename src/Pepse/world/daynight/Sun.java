package Pepse.world.daynight;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.components.Transition;
import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;
//import java.util.Math;
import java.awt.*;

public class Sun {
    private static Vector2 calcSunPosition(Vector2 windowDimensions, float angleInSky){
        Vector2 vector2 = windowDimensions.mult(0.5f);
        int r = 200;
        double x = r * Math.cos(angleInSky) + vector2.x();
        double y = r * Math.sin(angleInSky) + vector2.y();
        return new Vector2((float) x,(float) y);
    }

    private static final Color SUN = Color.YELLOW;
    private static final Float MIDNIGHT_OPACITY = 360f;
    public static GameObject create(
            Vector2 windowDimensions,
            float cycleLength,
            GameObjectCollection gameObjects,
            int layer){
        GameObject sun = new GameObject(new Vector2(windowDimensions.x() / 2, windowDimensions.y() / 2),
                Vector2.ONES.mult(75),new OvalRenderable(SUN));
        sun.setTag("sun");
        gameObjects.addGameObject(sun, layer);

        new Transition<Float>(
                sun, //the game object being changed
                (angel) -> sun.setCenter(calcSunPosition(windowDimensions,angel)),  //the method to call
                0f,    //initial transition value
                MIDNIGHT_OPACITY,   //final transition value
                Transition.LINEAR_INTERPOLATOR_FLOAT,  //use a cubic interpolator
                cycleLength/2,   //transtion fully over half a day
                Transition.TransitionType.TRANSITION_LOOP,
                null);  //nothing further to execute upon reaching final value
        return sun;
    }
}
