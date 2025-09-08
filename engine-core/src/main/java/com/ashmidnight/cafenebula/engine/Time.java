package com.ashmidnight.cafenebula.engine;

public final class Time {
    private Time() {}
    public static double now() { return System.nanoTime() * 1e-9; }

    public static final class FpsCounter {
        private double acc = 0.0;
        private int frames = 0;
        private double fps = 0.0;
        public void tick(double dt) { acc += dt; frames++; if (acc >= 1.0) { fps = frames / acc; frames = 0; acc = 0.0; } }
        public double fps() { return fps; }
    }
}
