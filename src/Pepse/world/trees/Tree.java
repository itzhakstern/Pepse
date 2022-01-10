package Pepse.world.trees;

import Pepse.util.ColorSupplier;
import Pepse.world.Block;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;

import java.awt.*;
import java.util.Random;



public class Tree{
    static Random random = new Random();
    private static final Color BASE_GROUND_COLOR = new Color(100, 50, 20);
    private static final RectangleRenderable rectangleRenderablenew = new
            RectangleRenderable(ColorSupplier.
            approximateColor(BASE_GROUND_COLOR));
    public static final float SIZE = 10;


    public static void create(GameObjectCollection gameObjects, float x, float y){
        int treeHeight = random.nextInt(4) + 5;
        float i = y;
        while (i >= (y - (treeHeight*Block.SIZE))){
            GameObject block = new Block(new Vector2(x, i),rectangleRenderablenew);
            gameObjects.addGameObject(block);
            i -= Block.SIZE;
        }
        Leaf.create(gameObjects,new Vector2(x,i));
    }
}
