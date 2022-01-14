package Pepse.world;

import Pepse.util.ColorSupplier;
import Pepse.util.PerlinNoise;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;
import static java.lang.Math.floor;

public class Terrain {
    private static final Color BASE_GROUND_COLOR = new Color(212, 123, 74);
    private static final int TERRAIN_DEPTH = 20;
    private final GameObjectCollection gameObjects;
    private float groundHeightAtX0;
    private final Vector2 windowDimensions;
    private final PerlinNoise perlinNoise;
    private final ArrayList<Double> heightVector;
    private final Random random;

    public Terrain(GameObjectCollection gameObjects,
                   int groundLayer,
                   Vector2 windowDimensions){
        this.gameObjects = gameObjects;
        this.groundHeightAtX0 = windowDimensions.y() * (2f/3);
        this.windowDimensions = windowDimensions;
        double seed = new Random().nextInt(150);
        heightVector = new ArrayList<>();
        perlinNoise = new PerlinNoise(seed);
        random = new Random();
    }

    public float groundHeightAt(float x) {
        float v = perlinNoise.noise(x / 200);
        return (this.windowDimensions.y() * (v+1)) * (2f/3);
    }

    public void createInRange(int minX, int maxX) {
        for (int x = minX; x <= maxX; x += Block.SIZE) {
            float groundHeightAtX = groundHeightAt(x);
            double floor = Math.floor(groundHeightAtX / Block.SIZE) * Block.SIZE;
            heightVector.add(floor);
            while (floor <= this.windowDimensions.y()){
                Color BASE_GROUND_COLOR = new Color(random.nextInt( 10) + 210,
                        random.nextInt( 10) +120, random.nextInt( 10) +75);
                RectangleRenderable rectangleRenderablenew = new RectangleRenderable(ColorSupplier.
                        approximateColor(BASE_GROUND_COLOR));
                GameObject block = new Block(new Vector2(x,(int)floor),rectangleRenderablenew);
                block.setTag("ground");
                this.gameObjects.addGameObject(block);
                floor += block.getDimensions().y();
            }
        }
    }
}
