package Pepse.world.trees;

import Pepse.world.Block;
import Pepse.world.Terrain;
import danogl.collisions.GameObjectCollection;
import java.util.Random;

/**
 * this class responsible for building the trees in the game
 */
public class BuildTrees {
    private final GameObjectCollection gameObjects;
    private final Terrain terrain;
    private final int seed;

    /**
     * the constructor of the class
     * @param gameObjects gameObjects
     * @param terrain terrain
     * @param seed the seed of the game
     */
    public BuildTrees(GameObjectCollection gameObjects,
                      Terrain terrain,int seed){
        this.gameObjects = gameObjects;
        this.terrain = terrain;
        this.seed = seed;

    }

    /**
     * this method create trees in the given range
     * @param minX minX
     * @param maxX maxX
     */
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
}
