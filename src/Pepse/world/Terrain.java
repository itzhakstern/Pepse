package Pepse.world;

import Pepse.util.ColorSupplier;
import Pepse.util.PerlinNoise;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;

import java.awt.*;
import java.util.Random;

import static java.lang.Math.abs;
import static java.lang.Math.floor;

public class Terrain {
    private static final Color BASE_GROUND_COLOR = new Color(212, 123, 74);
    private static final int TERRAIN_DEPTH = 20;
    private GameObjectCollection gameObjects;
    private int groundLayer;
    private float groundHeightAtX0;
    private Vector2 windowDimensions;
    private PerlinNoise perlinNoise;
    public Terrain(GameObjectCollection gameObjects,
                   int groundLayer,
                   Vector2 windowDimensions){

        this.gameObjects = gameObjects;
        this.groundLayer = groundLayer;
        this.groundHeightAtX0 = windowDimensions.y() * (2f/3);
        this.windowDimensions = windowDimensions;
//        double seed = new Random().nextGaussian() * 255; // ??
        double seed = new Random().nextInt(150);
        perlinNoise = new PerlinNoise(seed);
    }

    public float groundHeightAt(float x) {
        float v = perlinNoise.noise(x / 35);
        return this.windowDimensions.y()* (1f/3) * v +  (this.windowDimensions.y()) * (2f/3);
    }

    public void createInRange(int minX, int maxX) {
        RectangleRenderable rectangleRenderablenew = new RectangleRenderable(ColorSupplier.
                approximateColor(BASE_GROUND_COLOR));
        for (int x = minX; x <= maxX; x += Block.SIZE) {
            float groundHeightAtX = groundHeightAt(x);
            double floor = Math.floor(groundHeightAtX / Block.SIZE) * Block.SIZE;
            while (floor <= this.windowDimensions.y()){
                GameObject block = new Block(new Vector2(x,(int)floor),rectangleRenderablenew);
                block.setTag("ground");
                this.gameObjects.addGameObject(block);
                floor += block.getDimensions().y();
            }
        }
    }
}
