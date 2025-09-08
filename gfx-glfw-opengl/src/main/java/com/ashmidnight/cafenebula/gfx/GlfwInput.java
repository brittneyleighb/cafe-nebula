// GlfwInput.java in the gfx package
package com.ashmidnight.cafenebula.gfx;

import com.ashmidnight.cafenebula.engine.Key;
import java.util.Map;
import java.util.EnumMap;
import static org.lwjgl.glfw.GLFW.*;

public final class GlfwInput implements com.ashmidnight.cafenebula.engine.GlfwInput {
    private final Map<Key, Integer> map = new EnumMap<>(Key.class);
    private final boolean[] prev = new boolean[Key.values().length];
    private final boolean[] curr = new boolean[Key.values().length];

    public GlfwInput() {
        map.put(Key.UP, GLFW_KEY_UP);
        map.put(Key.DOWN, GLFW_KEY_DOWN);
        // ... rest of your mappings
    }

    @Override
    public void update() {
        // Implementation
    }

    @Override
    public boolean isKeyDown(Key key) {
        return curr[key.ordinal()];
    }

    @Override
    public boolean isKeyPressed(Key key) {
        int i = key.ordinal();
        return curr[i] && !prev[i];
    }

    @Override
    public boolean isKeyReleased(Key key) {
        int i = key.ordinal();
        return !curr[i] && prev[i];
    }
}