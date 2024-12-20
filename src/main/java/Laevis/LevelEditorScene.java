package Laevis;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class LevelEditorScene extends Scene {
    private boolean ChangingScene = false;
    private float TimeToChangeScene = 2.0f;

    public LevelEditorScene() {
        System.out.println("Level Editor Scene");
    }

    @Override
    public void SceneUpdate(float DeltaTime) {
        System.out.println((1.0f / DeltaTime) + "FPS");

        if (!ChangingScene && KeyListener.isKeyPressed(GLFW_KEY_SPACE)) {
            ChangingScene = true;
        }

        if (ChangingScene && TimeToChangeScene > 0) {
            TimeToChangeScene -= DeltaTime;
            Window.Get().r -= DeltaTime * 5.0f;
            Window.Get().g -= DeltaTime * 5.0f;
            Window.Get().b -= DeltaTime * 5.0f;
        } else if (ChangingScene) {
            Window.ChangeScene(1);
        }
    }
}
