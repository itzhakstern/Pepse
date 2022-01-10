package Pepse.world.trees;

import Pepse.util.ColorSupplier;
import Pepse.world.Block;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.components.Transition;
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

                new Transition<Float>(
                        leaf, //the game object being changed
                        (angel) -> leaf.renderer().setRenderableAngle(angel),  //the method to call
                        -10f,    //initial transition value
                        10f,   //final transition value
                        Transition.LINEAR_INTERPOLATOR_FLOAT,  //use a cubic interpolator
                        2,   //transtion fully over half a day
                        Transition.TransitionType.TRANSITION_BACK_AND_FORTH,
                        null);
//                leaf.renderer().setRenderableAngle(angle);
//                leaf.setDimensions(dimensionsAsVector2);
                gameObjects.addGameObject(leaf);
            }
        }
    }
}
