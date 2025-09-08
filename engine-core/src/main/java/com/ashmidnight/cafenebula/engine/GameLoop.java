package com.ashmidnight.cafenebula.engine;

public final class GameLoop {
    private static final double DT = 1.0 / 60.0;
    private double acc = 0.0, last = Time.now();

    public interface Logic {
        void fixedUpdate(double dt);    // fixed step (physics/logic)
        void render(double alpha);      // alpha = interpolation factor [0..1]
        boolean shouldClose();
        void poll();                    // poll platform input/events
    }

    public interface Renderable {
        void render(double alpha);
        boolean shouldClose();
        void pollInput();
    }

    public void run(Logic logic) {
        while (!logic.shouldClose()) {
            double now = Time.now();
            acc += now - last; last = now;

            logic.poll();

            while (acc >= DT) {
                logic.fixedUpdate(DT);
                acc -= DT;
            }
            logic.render(acc / DT);
        }
    }
}
