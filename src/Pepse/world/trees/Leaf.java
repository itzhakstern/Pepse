package Pepse.world.trees;

import Pepse.util.ColorSupplier;
import Pepse.world.Block;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;

import java.awt.*;
import java.util.Random;

public class Leaf {
    static Random random = new Random();
    private static final Color BASE_GROUND_COLOR = new Color(50, 200, 30);
//    private static final RectangleRenderable rectangleRenderablenew = new
//            RectangleRenderable(ColorSupplier.
//            approximateColor(BASE_GROUND_COLOR));

    public static void create(GameObjectCollection gameObjects, Vector2 center){
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
                GameObject leaf = new Block(new Vector2(i ,j),rectangleRenderablenew);
                gameObjects.addGameObject(leaf);
            }
        }
    }
}
