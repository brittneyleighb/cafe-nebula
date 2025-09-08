# Café Nebula Game Engine

A custom game engine built in Java using LWJGL (Lightweight Java Game Library) for OpenGL graphics rendering.

## Why Java for Game Engine Development?

### Educational Value
As a computer science master's student, building a game engine in Java provides unique learning opportunities:

- **Memory Management Understanding**: Java's garbage collector teaches automatic memory management principles while still requiring awareness of object lifecycle and performance implications
- **Object-Oriented Design**: Java's strong OOP features make architectural patterns (Entity-Component-System, Observer, Factory) more explicit and easier to understand
- **Cross-Platform Development**: Write once, run anywhere philosophy demonstrates platform abstraction concepts
- **Mature Tooling**: Excellent debugging, profiling, and IDE support accelerates learning and development

### Technical Advantages

**Compared to C++:**
- **Memory Safety**: Eliminates entire classes of bugs (buffer overflows, dangling pointers, memory leaks)
- **Faster Development**: Less time debugging segfaults, more time learning graphics concepts
- **Rich Standard Library**: Built-in collections, threading, networking reduce boilerplate code
- **Reflection and Dynamic Features**: Enable powerful debugging tools and flexible architectures

**Compared to C#:**
- **True Cross-Platform**: No dependency on Microsoft runtime or licensing concerns
- **Open Source Ecosystem**: Complete control over the development stack
- **JVM Performance**: Decades of optimization in HotSpot JVM
- **LWJGL Integration**: Mature, well-maintained OpenGL bindings

**Compared to Rust:**
- **Learning Curve**: Focus on engine concepts rather than fighting the borrow checker
- **Rapid Prototyping**: Faster iteration cycles during experimentation phase
- **Ecosystem Maturity**: More extensive libraries and documentation
- **Memory Model Simplicity**: Understand graphics programming before tackling manual memory management

### Real-World Context

**Performance Reality Check:**
- Modern JVMs achieve 80-95% of native C++ performance for most workloads
- Graphics performance limited by GPU, not CPU language choice
- JIT compilation can optimize hot code paths better than static compilation
- Examples: Minecraft (Java), Path of Exile (partial Java backend), numerous mobile games

**Industry Usage:**
- **Mobile Games**: Android development primarily Java/Kotlin
- **Backend Services**: Game servers often use JVM languages for scalability
- **Tools and Editors**: Many game development tools built with Java (IntelliJ, NetBeans-based editors)
- **Middleware**: Libraries like LWJGL power production games

### Development Philosophy

This project prioritizes **learning and understanding** over maximum performance. Java enables:

- **Rapid Experimentation**: Quick iteration on graphics algorithms and engine architecture
- **Clear Code Structure**: Focus on design patterns and clean architecture
- **Debugging Productivity**: Excellent stack traces and debugging tools
- **Platform Independence**: Same codebase runs on Windows, Linux, macOS without modification

### Honest Limitations

**Where Java Falls Short:**
- **Garbage Collection Pauses**: Can cause frame timing issues (mitigated with modern low-latency GCs)
- **Memory Overhead**: Higher baseline memory usage than native languages
- **JNI Complexity**: Interfacing with native libraries requires additional complexity
- **Industry Perception**: Some performance-critical studios prefer C++ for shipping products

**When You Might Choose Alternatives:**
- **AAA Game Development**: C++ still dominates for maximum performance and memory control
- **Console Development**: Platform-specific optimizations easier with native code
- **Real-Time Requirements**: Hard real-time systems benefit from deterministic memory management
- **Existing Codebase**: Building on existing C++ engines (Unreal, custom engines)

## Current Status

**Working:** Basic graphics window with OpenGL context and color cycling demonstration.

![Current Progress](https://img.shields.io/badge/Status-Basic%20Window%20Working-green)

## What I'm Learning

### Graphics Programming Fundamentals
- **OpenGL Context Creation**: Setting up LWJGL with GLFW for window management
- **Color Space Mathematics**: Implementing HSV to RGB conversion for smooth color transitions
- **Game Loop Architecture**: 60fps rendering loop with proper frame timing
- **Resource Management**: Managing OpenGL shaders and context cleanup

### Build System & Project Structure
- **Multi-Module Gradle Projects**: Organizing engine components into separate modules
- **Kotlin DSL**: Using `build.gradle.kts` for build configuration
- **Dependency Management**: Handling native libraries and cross-platform builds
- **Module Dependencies**: Setting up proper inter-module relationships

### Development Environment
- **WSL Graphics Setup**: Configuring graphics rendering in Windows Subsystem for Linux
- **Native Library Integration**: Working with platform-specific LWJGL natives
- **IDE Configuration**: Setting up IntelliJ IDEA for multi-module Java projects

## Project Structure

```
cafe-nebula/
├── engine-core/           # Core engine classes (GameLoop, Input abstractions)
├── gfx-glfw-opengl/      # Graphics implementation using GLFW/OpenGL
├── samples/              # Example applications and testing
│   ├── build.gradle.kts  # Module-specific build configuration
│   └── src/main/java/com/ashmidnight/cafenebula/
│       └── SandboxMain.java  # Current demo: color-cycling window
└── build.gradle.kts      # Root project configuration
```

## Current Demo Features

- **Window Creation**: 960x540 OpenGL window with proper context
- **Color Cycling**: Smooth HSV color space transitions at 60fps
- **Cross-Platform**: Runs on Windows (via WSL), Linux, and macOS

## Technical Challenges Solved

### 1. Module Structure Issues
**Problem**: Classes in wrong modules causing compilation failures  
**Solution**: Reorganized project structure with proper Maven/Gradle conventions

### 2. Abstract Class Instantiation
**Problem**: Trying to instantiate abstract `GlfwInput` class  
**Solution**: Temporarily removed input handling to focus on graphics foundation

### 3. WSL Graphics Configuration
**Problem**: `EGL: Library not found` preventing window creation  
**Solution**: Configured X11 forwarding and graphics libraries for WSL environment

### 4. Native Library Loading
**Problem**: LWJGL natives not loading correctly  
**Solution**: Proper Gradle configuration for platform-specific native dependencies

## Next Steps

- [ ] Implement proper input handling system
- [ ] Fix cleanup-related segmentation fault on program exit
- [ ] Add basic 2D rendering capabilities
- [ ] Implement texture loading and management
- [ ] Create basic entity/component system
- [ ] Add audio system integration

## Running the Project

### Prerequisites
- Java 21+ (OpenJDK)
- Gradle 8+
- Graphics drivers supporting OpenGL 3.3+
- For WSL: X11 server (VcXsrv) or WSLg support

### Build and Run
```bash
./gradlew clean
./gradlew :samples:build
./gradlew :samples:run
```

### Expected Output
A window titled "Café Nebula — Hello Window" displaying a smooth color-cycling background.

## Learning Resources

- [LWJGL Documentation](https://www.lwjgl.org/guide)
- [OpenGL Tutorial](https://learnopengl.com/)
- [Game Programming Patterns](https://gameprogrammingpatterns.com/)
- [Gradle Multi-Project Builds](https://docs.gradle.org/current/userguide/multi_project_builds.html)

## Known Issues

- Program crashes with SIGSEGV on exit (cleanup sequence needs improvement)
- Input system not yet implemented
- Limited to basic color rendering currently

---

**Learning Focus**: Understanding the foundational systems that make game engines work, from low-level graphics APIs to high-level architecture patterns.
