package Pepse;
import Pepse.world.Avatar;
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
import danogl.gui.rendering.ImageRenderable;
import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.*;

public class PepseGameManager extends GameManager{
    public static void main(String[] args) {
        new PepseGameManager().run();
    }

    @Override
    public void initializeGame(ImageReader imageReader,
                               SoundReader soundReader,
                               UserInputListener inputListener,
                               WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        GameObject sky = Sky.create(this.gameObjects(),
                windowController.getWindowDimensions(), Layer.BACKGROUND);

        Terrain terrain = new Terrain(this.gameObjects(), Layer.STATIC_OBJECTS,
                windowController.getWindowDimensions());
        terrain.createInRange(0,(int)windowController.getWindowDimensions().x());
        GameObject night = Night.create(gameObjects(),windowController.getWindowDimensions(),
                10,Layer.FOREGROUND);
        GameObject sun = Sun.create(windowController.getWindowDimensions(),
                1500,this.gameObjects(), Layer.BACKGROUND);
        GameObject sunHalo = SunHalo.create(this.gameObjects(),sun,
                new Color(255, 255, 0, 20),
                Layer.BACKGROUND + 10);
        BuildTrees.create(gameObjects(),terrain,windowController.getWindowDimensions());






        ImageRenderable standAvatarImage = imageReader.readImage(Avatar.STAND_PATH, true);
        Renderable avatarImgToDisplay = standAvatarImage;
//        Vector2 avatrCenter = Vector2.ONES.mult(250);
        Vector2 avatrCenter = windowController.getWindowDimensions().mult(0.5f);
        GameObject avatar = new Avatar(avatrCenter,Vector2.ONES.mult(50),avatarImgToDisplay, inputListener);
        gameObjects().addGameObject(avatar, Layer.DEFAULT);

        setCamera(new Camera(avatar, windowController.getWindowDimensions().mult(0.5f).add(avatrCenter.mult(-1)),
                windowController.getWindowDimensions(),
                windowController.getWindowDimensions()));

    }
}
