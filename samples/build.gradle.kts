plugins { application }

dependencies {
    implementation(project(":engine-core"))
    implementation(project(":gfx-glfw-opengl"))
}
application {
    mainClass.set("com.ashmidnight.cafenebula.sandbox.SandboxMain")
}
