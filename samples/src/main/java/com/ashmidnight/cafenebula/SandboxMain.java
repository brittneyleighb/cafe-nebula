package com.ashmidnight.cafenebula;

import com.ashmidnight.cafenebula.engine.GlfwInput;
import com.ashmidnight.cafenebula.engine.Key;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public final class SandboxMain {
    private long window;
    // private GlfwInput input;

    public static void main(String[] args) { new SandboxMain().run(); }

    private void run() {
        init();
        loop();
        glfwDestroyWindow(window);
        glfwTerminate();
        var cb = glfwSetErrorCallback(null);
        if (cb != null) cb.free();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        if (org.lwjgl.system.Platform.get() == org.lwjgl.system.Platform.MACOSX)
            glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

        int w = 960, h = 540;
        window = glfwCreateWindow(w, h, "Café Nebula — Hello Window", NULL, NULL);
        if (window == NULL) throw new RuntimeException("Failed to create window");

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1); // vsync
        GL.createCapabilities();
        // input = new GlfwInput(window);  // Initialize the input handler
        glfwShowWindow(window);
    }

    private void loop() {
        double last = System.nanoTime() * 1e-9;
        double acc = 0, DT = 1.0 / 60.0;
        float hue = 0f;

        while (!glfwWindowShouldClose(window)) {
            double now = System.nanoTime() * 1e-9;
            acc += now - last; last = now;

            glfwPollEvents();

            // Now you can add input checking here, like:
            // if (input.isKeyDown(Key.ESCAPE)) {
            //     glfwSetWindowShouldClose(window, true);
            // }

            while (acc >= DT) { hue = (hue + 0.2f * (float)DT) % 1f; acc -= DT; }

            float[] rgb = hsv(hue, 0.6f, 0.9f);
            glClearColor(rgb[0], rgb[1], rgb[2], 1f);
            glClear(GL_COLOR_BUFFER_BIT);
            glfwSwapBuffers(window);
        }
    }

    private static float[] hsv(float h, float s, float v) {
        float i = (float)Math.floor(h*6f), f = h*6f - i;
        float p=v*(1f-s), q=v*(1f-f*s), t=v*(1f-(1f-f)*s);
        return switch (((int)i)%6) {
            case 0 -> new float[]{v,t,p};
            case 1 -> new float[]{q,v,p};
            case 2 -> new float[]{p,v,t};
            case 3 -> new float[]{p,q,v};
            case 4 -> new float[]{t,p,v};
            default-> new float[]{v,p,q};
        };
    }
}
