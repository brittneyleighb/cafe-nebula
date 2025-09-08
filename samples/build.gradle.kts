plugins {
    application
    java
}

application {
    mainClass.set("com.ashmidnight.cafenebula.SandboxMain")
}

dependencies {
    implementation(project(":engine-core"))
    implementation(project(":gfx-glfw-opengl"))

    // LWJGL dependencies
    implementation(platform("org.lwjgl:lwjgl-bom:3.3.4"))
    implementation("org.lwjgl:lwjgl")
    implementation("org.lwjgl:lwjgl-glfw")
    implementation("org.lwjgl:lwjgl-opengl")

    val os = org.gradle.internal.os.OperatingSystem.current()
    val lwjglNatives = when {
        os.isWindows -> "natives-windows"
        os.isMacOsX   -> "natives-macos"
        else         -> "natives-linux"
    }

    runtimeOnly("org.lwjgl:lwjgl::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-glfw::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-opengl::$lwjglNatives")
}