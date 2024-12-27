package Laevis;

import imgui.*;
import imgui.callback.ImStrConsumer;
import imgui.callback.ImStrSupplier;
import imgui.flag.ImGuiBackendFlags;
import imgui.flag.ImGuiConfigFlags;
import imgui.flag.ImGuiKey;
import imgui.flag.ImGuiMouseCursor;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import static org.lwjgl.glfw.GLFW.*;


public class   ImGuiLayer {
    public ImGuiImplGlfw imguiglfw;
    public ImGuiImplGl3 implGl3;


    //mouse cursors provided by glfw
    private final long[] mousecursors=new long[ImGuiMouseCursor.COUNT];


    //flags for showing windows ,can be edited for any additions
    public boolean showText = false;
    public boolean showmainmenu=true ;
    public boolean show2dWin=false;
    public boolean show3dWin=false;
    // Constructor initializes the ImGui backend
    public ImGuiLayer() {
        initImGui();  // Ensure proper initialization when the object is created

    }

    // Initialization method for ImGui
    public void initImGui() {
        imguiglfw = new ImGuiImplGlfw();
        implGl3 = new ImGuiImplGl3();

        // Create ImGui context
        //essential for imgui to work
        ImGui.createContext();

        //init ImguiIo config
        final ImGuiIO io = ImGui.getIO();
        io.setConfigFlags(ImGuiConfigFlags.NavEnableKeyboard);
        io.setBackendFlags(ImGuiBackendFlags.HasMouseCursors);
        io.setBackendPlatformName("imgui_java_impl_glfw");//i have no idea what this does
        io.addConfigFlags(ImGuiConfigFlags.ViewportsEnable);

        io.setDisplaySize(1920,1080);
        io.getFonts().addFontDefault();
        io.getFonts().build();

        //keyboard mapping for imgui , used for io.keydown
        final int[] keyMap = new int[ImGuiKey.COUNT];
        keyMap[ImGuiKey.Tab] = GLFW_KEY_TAB;
        keyMap[ImGuiKey.LeftArrow] = GLFW_KEY_LEFT;
        keyMap[ImGuiKey.RightArrow] = GLFW_KEY_RIGHT;
        keyMap[ImGuiKey.UpArrow] = GLFW_KEY_UP;
        keyMap[ImGuiKey.DownArrow] = GLFW_KEY_DOWN;
        keyMap[ImGuiKey.PageUp] = GLFW_KEY_PAGE_UP;
        keyMap[ImGuiKey.PageDown] = GLFW_KEY_PAGE_DOWN;
        keyMap[ImGuiKey.Home] = GLFW_KEY_HOME;
        keyMap[ImGuiKey.End] = GLFW_KEY_END;
        keyMap[ImGuiKey.Insert] = GLFW_KEY_INSERT;
        keyMap[ImGuiKey.Delete] = GLFW_KEY_DELETE;
        keyMap[ImGuiKey.Backspace] = GLFW_KEY_BACKSPACE;
        keyMap[ImGuiKey.Space] = GLFW_KEY_SPACE;
        keyMap[ImGuiKey.Enter] = GLFW_KEY_ENTER;
        keyMap[ImGuiKey.Escape] = GLFW_KEY_ESCAPE;
        keyMap[ImGuiKey.KeyPadEnter] = GLFW_KEY_KP_ENTER;
        keyMap[ImGuiKey.A] = GLFW_KEY_A;
        keyMap[ImGuiKey.C] = GLFW_KEY_C;
        keyMap[ImGuiKey.V] = GLFW_KEY_V;
        keyMap[ImGuiKey.X] = GLFW_KEY_X;
        keyMap[ImGuiKey.Y] = GLFW_KEY_Y;
        keyMap[ImGuiKey.Z] = GLFW_KEY_Z;


/*
        glfwSetMouseButtonCallback(glfwWindow, (w, button, action, mods) -> {
            final boolean[] mouseDown = new boolean[5];

            mouseDown[0] = button == GLFW_MOUSE_BUTTON_1 && action != GLFW_RELEASE;
            mouseDown[1] = button == GLFW_MOUSE_BUTTON_2 && action != GLFW_RELEASE;
            mouseDown[2] = button == GLFW_MOUSE_BUTTON_3 && action != GLFW_RELEASE;
            mouseDown[3] = button == GLFW_MOUSE_BUTTON_4 && action != GLFW_RELEASE;
            mouseDown[4] = button == GLFW_MOUSE_BUTTON_5 && action != GLFW_RELEASE;

            io.setMouseDown(mouseDown);

            if (!io.getWantCaptureMouse() && mouseDown[1]) {
                ImGui.setWindowFocus(null);
            }
        });
        glfwSetScrollCallback(glfwWindow, (w, xOffset, yOffset) -> {
            io.setMouseWheelH(io.getMouseWheelH() + (float) xOffset);
            io.setMouseWheel(io.getMouseWheel() + (float) yOffset);
        });

        io.setSetClipboardTextFn(new ImStrConsumer() {
            @Override
            public void accept(final String s) {
                glfwSetClipboardString(glfwWindow, s);
            }

        });

        io.setGetClipboardTextFn(new ImStrSupplier() {
            @Override
            public String get() {
                final String clipboardString = glfwGetClipboardString(glfwWindow);
                if (clipboardString != null) {
                    return clipboardString;
                } else {
                    return "";
                }
            }
        });


 */
    }



    // Call this method to render ImGui elements
    public void ImGui() {


        if(showmainmenu) {
            // Set window size (width, height) and position (x, y)
            ImGui.setWindowSize(400, 300);  // Set the window size to 400x300
            ImGui.setWindowPos(100, 100);   // Set the window position to (100, 100)

            ImGui.begin("Main menu");

            ImGui.showDemoWindow();

            if (ImGui.button("2d", 500, 50)) {

                show2dWin = true;

            }
            if (ImGui.button("3d", 550, 50)) {
                show3dWin = true;
            }
            ImGui.end();
        }
        if(show2dWin){
            ImGui.begin("2d editor");
            ImGui.text("this is the 2d editor");
            showmainmenu=false;
            if(ImGui.button("return to main menu")){
                showmainmenu=true;
            }
            ImGui.end();
        }

        if(show3dWin){
            ImGui.begin("3d editor");
            ImGui.text("this is the 3d editor");

            ImGui.end();
        }
        if (showText) {
            ImGui.text("This is a cool message!");
            ImGui.sameLine();
        }


    }



    // Optionally, a cleanup method if necessary
    public void cleanup() {
        implGl3.destroyDeviceObjects();
        ImGui.destroyContext();
    }
}