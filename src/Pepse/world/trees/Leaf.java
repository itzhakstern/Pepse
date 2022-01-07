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
    private static final RectangleRenderable rectangleRenderablenew = new RectangleRenderable(ColorSupplier.
            approximateColor(BASE_GROUND_COLOR));
    public static void create(GameObjectCollection gameObjects, Vector2 center){
        int w = random.nextInt(3)+1;
        int h = random.nextInt(3)+3;
        System.out.printf("w, h = %d, %d%n", w, h);
        int x_ = (int)(center.x() - (w / 2)*Block.SIZE);
        int y_ = (int)(center.y() - (h/ 2)*Block.SIZE);
        System.out.printf("x_, y_ = %d, %d%n", x_, y_);
        for(int i = x_; i < x_ + h* Block.SIZE   ;i+= Block.SIZE){
            for (int j = y_; j <y_ + w * Block.SIZE ;j+=Block.SIZE){
                System.out.printf("center = %f, %f%n", center.x(), center.y());
                System.out.printf("i, j = %d, %d%n", i, j);

                GameObject leaf = new Block(new Vector2(i,j),rectangleRenderablenew);
                gameObjects.addGameObject(leaf);
            }
        }
    }
}
