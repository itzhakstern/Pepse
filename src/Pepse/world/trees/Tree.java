package Pepse.world.trees;

import Pepse.util.ColorSupplier;
import Pepse.world.Block;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
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
        float t = y;
        while (t >= (y - (treeHeight*Block.SIZE))){
            GameObject block = new Block(new Vector2(x, t),rectangleRenderablenew);
            block.setTag("race");
            gameObjects.addGameObject(block,Layer.STATIC_OBJECTS);
            t -= Block.SIZE;
        }
        Vector2 center = new Vector2(x,t);
        int width = random.nextInt(3)+3;
        int height = random.nextInt(3)+4;
        int topLeftLeafsX = (int)(center.x() - (width / 2)*Block.SIZE);
        int topLeftLeafsY = (int)(center.y() - (height/ 2)*Block.SIZE);
        for(int i = topLeftLeafsX; i < topLeftLeafsX + (width+1) * Block.SIZE ;i+= Block.SIZE){
            for (int j = topLeftLeafsY; j <topLeftLeafsY + (height+1) * Block.SIZE ;j+=Block.SIZE){
                Color BASE_GROUND_COLOR = new Color(random.nextInt(10)+ 45
                        ,random.nextInt(10) + 195,random.nextInt( 10) + 45);
                RectangleRenderable rectangleRenderablenew = new
                        RectangleRenderable(ColorSupplier.
                        approximateColor(BASE_GROUND_COLOR));
                Leaf leaf = new Leaf(new Vector2(i ,j),rectangleRenderablenew);
                leaf.schedule();
                leaf.setTag("leaf");

                gameObjects.addGameObject(leaf, Layer.DEFAULT+8);
            }
        }
    }
}
