package Pepse;
import Pepse.world.Avatar;
import Pepse.world.Block;
import Pepse.world.Sky;
import Pepse.world.Terrain;
import Pepse.world.daynight.Night;
import Pepse.world.daynight.Sun;
import Pepse.world.daynight.SunHalo;
import Pepse.world.trees.BuildTrees;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.gui.rendering.Camera;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;


/**
 * the class represent the game
 */
public class PepseGameManager extends GameManager{
    private GameObject avatar;
    private Vector2 windowDimensions;
    private BuildTrees buildTrees;
    private Terrain terrain;
    private int minXCoord;
    private int maxXCoord;
    private HashMap<String, Integer> tagMap;

    /**
     * the main function
     * @param args args
     */
    public static void main(String[] args) {
        new PepseGameManager().run();
    }

    @Override
    public void initializeGame(ImageReader imageReader,
                               SoundReader soundReader,
                               UserInputListener inputListener,
                               WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        tagMap = new HashMap<String,Integer>();
        tagMap.put("ground",-100);
        tagMap.put("leaf",0);
        tagMap.put("race",-100);
        Random random = new Random();
        int seed = random.nextInt(100) + 10;
        windowDimensions = windowController.getWindowDimensions();
        minXCoord = (int) (Math.floor( -windowDimensions.x() / Block.SIZE) * Block.SIZE);
        maxXCoord = (int) (Math.floor((2 * windowDimensions.x()) / Block.SIZE) * Block.SIZE);
        Sky.create(this.gameObjects(),
                windowController.getWindowDimensions(), Layer.BACKGROUND);
        terrain = new Terrain(this.gameObjects(), Layer.STATIC_OBJECTS,
                windowController.getWindowDimensions(), seed);
        terrain.createInRange(minXCoord,maxXCoord);
        Night.create(gameObjects(),windowController.getWindowDimensions(),
                10,Layer.FOREGROUND);
        GameObject sun = Sun.create(windowController.getWindowDimensions(),
                1500,this.gameObjects(), Layer.BACKGROUND);
        SunHalo.create(this.gameObjects(),sun,
                new Color(255, 255, 0, 20),
                Layer.BACKGROUND + 10);

        buildTrees = new BuildTrees(gameObjects(),terrain, seed);
        buildTrees.creatInRange(minXCoord - (int)(windowDimensions.x()/10),maxXCoord + (int)(windowDimensions.x()/10));

        Renderable avatarImgToDisplay = imageReader.readImage(Avatar.STAND_PATH, true);
        float x = windowController.getWindowDimensions().x()* 1/2f  - 24;
        Vector2 avatrCenter = new Vector2(x,terrain.groundHeightAt(x)- 24).mult(0.8f);
        avatar = new Avatar(avatrCenter,Vector2.ONES.mult(50),avatarImgToDisplay, inputListener, imageReader);
        gameObjects().addGameObject(avatar, Layer.DEFAULT);
        setCamera(new Camera(avatar, windowDimensions.mult(0.5f).subtract(avatrCenter),
                windowController.getWindowDimensions(),
                windowController.getWindowDimensions()));
    }

    @Override
    public void update(float deltaTime) {
        conteuesWorld();
        removeObjects();
        super.update(deltaTime);
    }


    /**
     * the method responsible for building the infinite world
     */
    private void conteuesWorld() {
        int width = (int) windowDimensions.x();
        float centerMin = avatar.getCenter().x() - minXCoord;
        float centerMax = maxXCoord - avatar.getCenter().x();
        if (centerMin < width) {
            conteuesGround(minXCoord - width, minXCoord);
            maxXCoord -= width;
            minXCoord -= width;
        }
        if (centerMax < width) {
            conteuesGround(maxXCoord, maxXCoord + width);
            maxXCoord += width;
            minXCoord += width;
        }
    }

    /**
     * the method remove unnecessary objects in the game
     */
    private void removeObjects() {
        for(GameObject gameObject : gameObjects()){
            float index = gameObject.getTopLeftCorner().x();
            if(index > maxXCoord || index < minXCoord){
                if(tagMap.containsKey(gameObject.getTag())) {
                    gameObjects().removeGameObject(gameObject, tagMap.get(gameObject.getTag()));
                }
            }
        }
    }


    /**
     * the method continues the ground in the given range
     * @param minX minX
     * @param maxX maxX
     */
    private void conteuesGround(int minX, int maxX){
        buildTrees.creatInRange(minX,maxX);
        terrain.createInRange(minX,maxX);
    }
}
