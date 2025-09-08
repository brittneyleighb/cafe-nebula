package com.ashmidnight.cafenebula.gfx;

import com.ashmidnight.cafenebula.engine.GlfwInput;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public final class Window {
    private long handle;
    private GlfwInput glfwInput;

    public Window(int w, int h, String title) {
        if (!glfwInit()) throw new IllegalStateException("GLFW init failed");
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        if (org.lwjgl.system.Platform.get() == org.lwjgl.system.Platform.MACOSX)
            glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

        handle = glfwCreateWindow(w, h, title, NULL, NULL);
        if (handle == NULL) throw new RuntimeException("Window creation failed");

        glfwMakeContextCurrent(handle);
        glfwSwapInterval(1); // vsync
        GL.createCapabilities();
        glfwShowWindow(handle);

        glViewport(0, 0, w, h);

        glfwInput = new GlfwInputImpl(handle);
    }

    public long getHandle() { return handle; }
    public GlfwInput input() { return glfwInput; }

    public boolean shouldClose() { return glfwWindowShouldClose(handle); }
    public void poll() { glfwPollEvents(); glfwInput.update(); }
    public void swap() { glfwSwapBuffers(handle); }
    public void destroy() { glfwDestroyWindow(handle); glfwTerminate(); }
}

