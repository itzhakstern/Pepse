package Pepse.world.trees;

import Pepse.world.Block;
import Pepse.world.Terrain;
import danogl.collisions.GameObjectCollection;
import danogl.util.Vector2;

import java.util.Random;

public class BuildTrees {
//    static Random random;
    private GameObjectCollection gameObjects;
    private Terrain terrain;
    private final int seed;

    public BuildTrees(GameObjectCollection gameObjects,
                      Terrain terrain,int seed){
        this.gameObjects = gameObjects;
        this.terrain = terrain;
        this.seed = seed;

    }

    public void creatInRange(int minX, int maxX){
        Random random = new Random(seed);
        for (int i = minX; i < maxX; i+= Block.SIZE*2) {
            int coin = random.nextInt(100);
            if(coin <=10){

                float ground = (float) Math.floor(
                        terrain.groundHeightAt(i)/ Block.SIZE) * Block.SIZE - Block.SIZE;
                Tree.create(gameObjects, i, ground);
            }
        }
    }
//    public static void create(GameObjectCollection gameObjects,
//                              Terrain terrain, Vector2 windowDimensions){
//
////        for (int i = 0; i < windowDimensions.x(); i+= Block.SIZE) {
////            int coin = random.nextInt(100);
////            if(coin <=10){
////
////                float ground = (float) Math.floor(
////                        terrain.groundHeightAt(i)/ Block.SIZE) * Block.SIZE - Block.SIZE;
////                Tree.create(gameObjects, i, ground);
////            }
////        }
//    }

}
