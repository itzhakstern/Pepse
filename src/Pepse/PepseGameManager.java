package Pepse;
import Pepse.world.Sky;
import Pepse.world.Terrain;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.util.Vector2;

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
        GameObject sky = Sky.create(this.gameObjects(), windowController.getWindowDimensions(), Layer.BACKGROUND);

        Terrain terrain = new Terrain(this.gameObjects(), Layer.STATIC_OBJECTS, windowController.getWindowDimensions());
        terrain.createInRange(0,(int)windowController.getWindowDimensions().x());
    }
}
