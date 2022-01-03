package Pepse.world;

import Pepse.util.ColorSupplier;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;

import java.awt.*;

public class Terrain {
    private static final Color BASE_GROUND_COLOR = new Color(212, 123, 74);
    private static final int TERRAIN_DEPTH = 20;
    private GameObjectCollection gameObjects;
    private int groundLayer;
    private float groundHeightAtX0;
    private Vector2 windowDimensions;
    public Terrain(GameObjectCollection gameObjects,
                   int groundLayer,
                   Vector2 windowDimensions){

        this.gameObjects = gameObjects;
        this.groundLayer = groundLayer;
        this.groundHeightAtX0 = windowDimensions.y() * (2f/3);
        this.windowDimensions = windowDimensions;
    }

    public float groundHeightAt(float x) {
        return groundHeightAtX0;
    }

    public void createInRange(int minX, int maxX) {
        RectangleRenderable rectangleRenderablenew = new RectangleRenderable(ColorSupplier.
                approximateColor(BASE_GROUND_COLOR));
        float block_size = Block.SIZE;
        for (int x = minX; x <= maxX; x+=block_size) {
            float groundHeightAtX = groundHeightAt(x);
            double floor = Math.floor(groundHeightAt(groundHeightAtX) / Block.SIZE) * Block.SIZE;
            while (floor <= this.windowDimensions.y()){
                GameObject block = new Block(new Vector2(x,(int)floor),rectangleRenderablenew);
                block.setTag("ground");
                this.gameObjects.addGameObject(block);
                floor += block.getDimensions().y();
            }

        }
    }
}
