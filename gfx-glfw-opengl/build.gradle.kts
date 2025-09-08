val lwjglVersion = "3.3.4"
val os = org.gradle.internal.os.OperatingSystem.current()
val lwjglNatives = when {
    os.isWindows -> "natives-windows"
    os.isMacOsX  -> "natives-macos"
    else         -> "natives-linux"
}

dependencies {
    implementation(project(":engine-core"))
    implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))
    implementation("org.lwjgl:lwjgl")
    implementation("org.lwjgl:lwjgl-glfw")
    implementation("org.lwjgl:lwjgl-opengl")
    runtimeOnly("org.lwjgl:lwjgl::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-glfw::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-opengl::$lwjglNatives")
}
