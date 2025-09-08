package com.ashmidnight.cafenebula.sandbox;

import com.ashmidnight.cafenebula.engine.GameLoop;
import com.ashmidnight.cafenebula.engine.Input;
import com.ashmidnight.cafenebula.engine.Log;
import com.ashmidnight.cafenebula.engine.Time;
import com.ashmidnight.cafenebula.gfx.RendererGL;
import com.ashmidnight.cafenebula.gfx.Window;

public final class SandboxMain {
    public static void main(String[] args) {
        Window win = new Window(960, 540, "Café Nebula — Core Test");
        RendererGL renderer = new RendererGL(win);
        Input input = win.input();
        Time.FpsCounter fps = new Time.FpsCounter();

        final double[] pos = {0.0, 0.0}; // simple demo state

        GameLoop loop = new GameLoop();
        loop.run(new GameLoop.Logic() {
            @Override public void fixedUpdate(double dt) {
                double speed = 200.0; // pixels/sec (conceptual)
                if (input.isKeyDown(Input.Key.W) || input.isKeyDown(Input.Key.UP))    pos[1] += speed * dt;
                if (input.isKeyDown(Input.Key.S) || input.isKeyDown(Input.Key.DOWN)) pos[1] -= speed * dt;
                if (input.isKeyDown(Input.Key.A) || input.isKeyDown(Input.Key.LEFT)) pos[0] -= speed * dt;
                if (input.isKeyDown(Input.Key.D) || input.isKeyDown(Input.Key.RIGHT))pos[0] += speed * dt;

                if (input.isKeyPressed(Input.Key.ESCAPE)) {
                    Log.info("Escape pressed — exiting.");
                    win.destroy();
                    // setting close flag:
                    // (GLFW doesn't need an explicit flag here since we'll tear down after loop ends)
                }
                fps.tick(dt);
            }

            @Override public void render(double alpha) {
                // For now we just clear; later you can draw a quad at pos[]
                renderer.render(alpha);
                // Occasionally log FPS (throttle to avoid spam)
                if (Math.random() < 0.01) Log.info("FPS ~ " + Math.round(fps.fps()));
            }

            @Override public boolean shouldClose() { return win.shouldClose(); }

            @Override public void poll() { win.poll(); }
        });

        win.destroy();
    }
}

