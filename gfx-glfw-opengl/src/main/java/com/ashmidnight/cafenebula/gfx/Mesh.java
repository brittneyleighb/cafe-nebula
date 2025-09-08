package com.ashmidnight.cafenebula.gfx;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

final class Mesh {
    private final int vao;
    private final int vbo;
    private final int count;

    Mesh(float[] verts) {
        count = verts.length / 3;
        vao = glGenVertexArrays();
        vbo = glGenBuffers();

        glBindVertexArray(vao);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, verts, GL_STATIC_DRAW);

        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * Float.BYTES, 0L);

        glBindVertexArray(0);
    }

    void draw() {
        glBindVertexArray(vao);
        glDrawArrays(GL_TRIANGLES, 0, count);
        glBindVertexArray(0);
    }
}

