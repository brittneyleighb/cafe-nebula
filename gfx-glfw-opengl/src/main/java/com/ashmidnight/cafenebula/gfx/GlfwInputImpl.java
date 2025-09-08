package com.ashmidnight.cafenebula.gfx;

import com.ashmidnight.cafenebula.engine.GlfwInput;
import com.ashmidnight.cafenebula.engine.Key;
// other imports...

public final class GlfwInputImpl implements GlfwInput {
    private long windowHandle;

    public GlfwInputImpl(long handle) {
        this.windowHandle = handle;
        // initialization code
    }

    @Override
    public void update() {
        // implementation
    }

    @Override
    public boolean isKeyDown(Key key) {
        // implementation
        return false;
    }

    @Override
    public boolean isKeyPressed(Key key) {
        // implementation
        return false;
    }

    @Override
    public boolean isKeyReleased(Key key) {
        // implementation
        return false;
    }
}
