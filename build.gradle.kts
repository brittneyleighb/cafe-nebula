plugins {
    java
}

group = "com.ashmidnight.cafenebula"
version = "1.0-SNAPSHOT"

repositories { mavenCentral() }

java {
    // Use your installed JDK 24; switch to 21 later if you prefer LTS.
    toolchain { languageVersion.set(JavaLanguageVersion.of(21)) }
}

subprojects {
    apply(plugin = "java")
    repositories { mavenCentral() }
    java { toolchain { languageVersion.set(JavaLanguageVersion.of(21)) } }
}

val lwjglVersion = "3.3.4"
val os = org.gradle.internal.os.OperatingSystem.current()
val lwjglNatives = when {
    os.isWindows -> "natives-windows"
    os.isMacOsX   -> "natives-macos"
    else         -> "natives-linux"
}

dependencies {
    implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))
    implementation("org.lwjgl:lwjgl")
    implementation("org.lwjgl:lwjgl-glfw")
    implementation("org.lwjgl:lwjgl-opengl")

    runtimeOnly("org.lwjgl:lwjgl::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-glfw::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-opengl::$lwjglNatives")
}
