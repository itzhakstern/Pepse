package Pepse.world;

import danogl.collisions.GameObjectCollection;
import danogl.util.Vector2;

public class Terrain {
    private GameObjectCollection gameObjects;
    private int groundLayer;
    private float groundHeightAtX0;
    public Terrain(GameObjectCollection gameObjects,
                   int groundLayer,
                   Vector2 windowDimensions){

        this.gameObjects = gameObjects;
        this.groundLayer = groundLayer;
        this.groundHeightAtX0 = windowDimensions.y() * (2f/3);
    }

    public float groundHeightAt(float x) {
        return groundHeightAtX0;
    }
}
