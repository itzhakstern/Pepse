package Pepse.world.trees;

import Pepse.world.Block;
import Pepse.world.Terrain;
import danogl.collisions.GameObjectCollection;
import danogl.util.Vector2;

import java.util.Random;

public class BuildTrees {
    static Random random = new Random();
    public static void create(GameObjectCollection gameObjects,
                              Terrain terrain, Vector2 windowDimensions){
        for (int i = 0; i < windowDimensions.x(); i+= Block.SIZE) {
            int coin = random.nextInt(100);
            if(coin <=10){

                float ground = (float) Math.floor(
                        terrain.groundHeightAt(i)/ Block.SIZE) * Block.SIZE - Block.SIZE;
                Tree.create(gameObjects, i, ground);
            }
        }
    }
}
