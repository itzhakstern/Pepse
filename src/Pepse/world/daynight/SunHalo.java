package Pepse.world.daynight;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;

import java.awt.*;

public class SunHalo {
    public static GameObject create(
            GameObjectCollection gameObjects,
            GameObject sun,
            Color color,
            int layer){
        GameObject sunHalo = new GameObject(sun.getTopLeftCorner().add(new Vector2(-30,-30)),
                Vector2.ONES.mult(125),new OvalRenderable(color));
        gameObjects.addGameObject(sunHalo,layer);
        return sunHalo;
    }
}
