package com.ashmidnight.cafenebula.engine;
import org.tinylog.Logger;

public final class Log {
    private Log() {}
    public static void info(String msg) { Logger.info(msg); }
    public static void warn(String msg) { Logger.warn(msg); }
    public static void error(String msg, Throwable t) { Logger.error(t, msg); }
}
