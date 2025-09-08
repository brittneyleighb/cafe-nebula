// Input.java
package com.ashmidnight.cafenebula.engine;

public interface GlfwInput {
    void update();
    boolean isKeyDown(Key key);
    boolean isKeyPressed(Key key);
    boolean isKeyReleased(Key key);
}
