package com.ashmidnight.cafenebula.gfx;

import static org.lwjgl.opengl.GL20.*;

final class Shader {
    private final int prog;

    Shader(String vsSrc, String fsSrc) {
        int vs = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vs, vsSrc);
        glCompileShader(vs);
        if (glGetShaderi(vs, GL_COMPILE_STATUS) == 0)
            throw new RuntimeException(glGetShaderInfoLog(vs));

        int fs = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fs, fsSrc);
        glCompileShader(fs);
        if (glGetShaderi(fs, GL_COMPILE_STATUS) == 0)
            throw new RuntimeException(glGetShaderInfoLog(fs));

        prog = glCreateProgram();
        glAttachShader(prog, vs);
        glAttachShader(prog, fs);
        glLinkProgram(prog);
        if (glGetProgrami(prog, GL_LINK_STATUS) == 0)
            throw new RuntimeException(glGetProgramInfoLog(prog));

        glDeleteShader(vs);
        glDeleteShader(fs);
    }

    void bind() { glUseProgram(prog); }
}
