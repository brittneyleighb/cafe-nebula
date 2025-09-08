package com.ashmidnight.cafenebula.gfx;

import com.ashmidnight.cafenebula.engine.GameLoop.Renderable;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public final class RendererGL implements Renderable {
    private final Window window;
    private final Shader shader;
    private final Mesh mesh;

    public RendererGL(Window window) {
        this.window = window;

        String vs = """
            #version 330 core
            layout(location=0) in vec3 aPos;
            void main(){ gl_Position = vec4(aPos, 1.0); }
            """;
        String fs = """
            #version 330 core
            out vec4 color;
            void main(){ color = vec4(0.95, 0.65, 0.15, 1.0); }
            """;

        this.shader = new Shader(vs, fs);
        this.mesh   = new Mesh(new float[]{
                -0.6f, -0.4f, 0f,
                0.6f, -0.4f, 0f,
                0.0f,  0.6f, 0f
        });
    }

    @Override public void render(double alpha) {
        glClearColor(0f, 0f, 0f, 1f);
        glClear(GL_COLOR_BUFFER_BIT);

        shader.bind();
        mesh.draw();

        window.swap();
    }

    @Override public boolean shouldClose() { return window.shouldClose(); }
    @Override public void pollInput() { window.poll(); }
}
