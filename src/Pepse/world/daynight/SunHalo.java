package Pepse.world.daynight;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.components.CoordinateSpace;
import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;

import java.awt.*;

public class SunHalo {
    public static GameObject create(
            GameObjectCollection gameObjects,
            GameObject sun,
            Color color,
            int layer){
        GameObject sunHalo = new GameObject(sun.getTopLeftCorner(),
                Vector2.ONES.mult(500),new OvalRenderable(color));
        sunHalo.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        gameObjects.addGameObject(sunHalo,layer);
        sunHalo.addComponent((deltaTime)->sunHalo.setCenter(sun.getCenter()));
        return sunHalo;
    }
}
