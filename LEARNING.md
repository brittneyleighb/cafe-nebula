# Learning Log - September 8, 2025

## Daily Goal
Get the Café Nebula game engine project building and displaying a basic graphics window.

## What I Learned Today

### 1. Java Project Structure & Modules
**Problem**: Compilation errors due to misorganized file structure
- Learned about Maven/Gradle directory conventions (`src/main/java/...`)
- Understanding multi-module projects and how they interact
- Fixed package declarations matching directory structure
- Moved files to correct module locations (`samples/` vs root)

**Key Insight**: Project structure isn't just organization—it directly affects how the build system finds and compiles code.

### 2. Abstract Classes vs Concrete Implementation
**Problem**: `GlfwInput is abstract; cannot be instantiated`
- Learned that abstract classes define interfaces but can't be created directly
- Understanding when to comment out problematic code vs. fixing implementation
- Prioritizing getting basic functionality working before adding complexity

**Key Insight**: Sometimes the fastest path to learning is temporarily removing advanced features to focus on fundamentals.

### 3. Gradle Build Configuration
**Problem**: Main class not found, module dependencies missing
- Created `build.gradle.kts` files for individual modules
- Learned Kotlin DSL syntax for Gradle configuration
- Understanding how `application` plugin works
- Configured LWJGL native library dependencies for different platforms

**Key Insight**: Build tools like Gradle are like recipes—every ingredient (dependency) and step (task) needs to be explicitly defined.

### 4. WSL Graphics Setup
**Problem**: `EGL: Library not found` preventing OpenGL window creation
- Learned that WSL doesn't have built-in graphics capabilities
- Successfully configured X11 forwarding for graphics display
- Understanding the relationship between Linux subsystems and Windows display

**Key Insight**: Development environments can introduce platform-specific challenges that aren't obvious from code alone.

### 5. OpenGL & Graphics Programming Concepts
**Achievement**: Successfully created a color-cycling OpenGL window
- Learned about OpenGL context creation through GLFW
- Understanding HSV color space and conversion to RGB
- Implemented a 60fps game loop with proper timing
- Saw how shader compilation and linking works

**Key Insight**: Graphics programming involves coordinating multiple systems (windowing, OpenGL context, shaders, render loop).

### 6. Git Workflow for Learning Projects
- Learned about descriptive commit messages
- Understanding how to document progress in README files
- Setting up repository structure for ongoing learning

## Challenges Overcome

1. **File Organization**: Multiple files with same name in different locations
2. **Build Configuration**: Creating proper module structure and dependencies
3. **Platform Issues**: Getting graphics working in WSL environment
4. **Error Diagnosis**: Reading compiler errors and understanding root causes

## Code That Works Now

```java
// Successfully creating an OpenGL window with color cycling
private void loop() {
    double last = System.nanoTime() * 1e-9;
    double acc = 0, DT = 1.0 / 60.0;
    float hue = 0f;

    while (!glfwWindowShouldClose(window)) {
        double now = System.nanoTime() * 1e-9;
        acc += now - last; last = now;

        glfwPollEvents();
        while (acc >= DT) { 
            hue = (hue + 0.2f * (float)DT) % 1f; 
            acc -= DT; 
        }

        float[] rgb = hsv(hue, 0.6f, 0.9f);
        glClearColor(rgb[0], rgb[1], rgb[2], 1f);
        glClear(GL_COLOR_BUFFER_BIT);
        glfwSwapBuffers(window);
    }
}
```

## Remaining Issues

- **Segmentation fault on program exit**: Cleanup sequence needs improvement
- **Input system temporarily disabled**: Need to understand proper GlfwInput implementation
- **Limited graphics capabilities**: Currently only clearing screen with colors

## Tomorrow's Goals

1. Investigate and fix the cleanup-related crash
2. Research proper input handling implementation
3. Add basic 2D rendering (draw a triangle or rectangle)
4. Learn about OpenGL shaders and how they work

## Analogies That Helped

- **Project structure**: Like organizing a kitchen—ingredients (classes) need to be in the right drawers (packages) for the chef (compiler) to find them
- **Build tools**: Like a recipe that explicitly lists every ingredient and step
- **Graphics pipeline**: Like a factory assembly line where each station (shader, buffer, context) has a specific job
- **Abstract classes**: Like blueprints that show what to build but aren't the actual building

## Key Commands Learned

```bash
./gradlew clean                    # Clear all compiled files
./gradlew :samples:compileJava     # Compile specific module
./gradlew :samples:run            # Run application
find /path -name "*.java"         # Locate files
export DISPLAY=:0.0               # Set graphics display for WSL
```

## Resources Used

- LWJGL documentation for OpenGL setup
- Gradle multi-project build guides
- WSL graphics configuration tutorials
- Java package structure conventions

---

**Overall Assessment**: Solid foundation established. The project now has working graphics output, proper build configuration, and a clear path forward for adding features.

**Confidence Level**: Comfortable with basic setup, ready to tackle more advanced graphics concepts.
