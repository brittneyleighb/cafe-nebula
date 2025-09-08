# Café Nebula Game Engine - Setup Guide

This guide walks you through setting up the Café Nebula game engine on different platforms and getting your first graphics window running.

## Prerequisites

### Required Software
- **Java 21+** (OpenJDK recommended)
- **Gradle 8+** (or use the included Gradle wrapper)
- **Git** for version control
- **Graphics drivers** supporting OpenGL 3.3+

### Platform-Specific Requirements

#### Windows with WSL
- Windows 10 Build 19044+ or Windows 11 (for WSLg support)
- OR X11 server software (VcXsrv, Xming)

#### Native Linux
- Mesa graphics drivers or proprietary GPU drivers
- X11 or Wayland display server

#### macOS
- macOS 10.14+ with OpenGL 3.3 support

## Installation Steps

### 1. Clone the Repository

```bash
git clone <your-repo-url>
cd cafe-nebula
```

### 2. Verify Java Installation

```bash
java --version
# Should show Java 21 or higher
```

If Java isn't installed:

**Ubuntu/Debian:**
```bash
sudo apt update
sudo apt install openjdk-21-jdk
```

**macOS (with Homebrew):**
```bash
brew install openjdk@21
```

### 3. Platform-Specific Graphics Setup

#### Option A: WSL with WSLg (Windows 11/Recent Windows 10)

Check if WSLg is available:
```bash
echo $DISPLAY
# Should show something like :0 or :0.0
```

If WSLg is available, install mesa utilities:
```bash
sudo apt update
sudo apt install mesa-utils x11-apps
```

Test graphics:
```bash
glxinfo | grep "OpenGL version"
# Should show OpenGL version info
```

#### Option B: WSL with X11 Server (Older Windows)

1. **Install VcXsrv on Windows:**
   - Download from: https://sourceforge.net/projects/vcxsrv/
   - Install with default settings

2. **Launch VcXsrv:**
   - Start VcXsrv
   - Choose "Multiple windows"
   - Display number: 0
   - Check "Disable access control"
   - Finish setup

3. **Configure WSL:**
```bash
export DISPLAY=:0.0
echo 'export DISPLAY=:0.0' >> ~/.bashrc
```

4. **Install graphics libraries:**
```bash
sudo apt update
sudo apt install mesa-utils x11-apps libgl1-mesa-glx
```

5. **Test the setup:**
```bash
xeyes
# Should show a window with eyes that follow your cursor
```

#### Option C: Native Linux

```bash
# Ubuntu/Debian
sudo apt install mesa-utils build-essential

# Fedora
sudo dnf install mesa-dri-drivers mesa-libGL-devel

# Test OpenGL
glxinfo | grep "OpenGL version"
```

#### Option D: macOS

OpenGL should work out of the box. You may need to install Xcode command line tools:
```bash
xcode-select --install
```

## Building and Running

### 1. Make Gradle Wrapper Executable

```bash
chmod +x gradlew
```

### 2. Clean Previous Builds

```bash
./gradlew clean
```

### 3. Build the Project

```bash
./gradlew build
```

### 4. Run the Sample Application

```bash
./gradlew :samples:run
```

### Expected Output

You should see:
1. Gradle downloading dependencies (first run only)
2. Compilation messages
3. A window titled "Café Nebula — Hello Window" (960x540 pixels)
4. Smooth color cycling background animation

## Project Structure Verification

Your project should look like this:

```
cafe-nebula/
├── build.gradle.kts              # Root build configuration
├── settings.gradle.kts           # Module definitions
├── gradlew                       # Gradle wrapper script
├── gradlew.bat                   # Windows Gradle wrapper
├── engine-core/
│   └── src/main/java/com/ashmidnight/cafenebula/engine/
│       ├── GameLoop.java
│       ├── GlfwInput.java        # (Abstract class)
│       ├── Key.java
│       └── Time.java
├── gfx-glfw-opengl/
│   └── src/main/java/com/ashmidnight/cafenebula/gfx/
│       └── Shader.java
└── samples/
    ├── build.gradle.kts          # Samples module configuration
    └── src/main/java/com/ashmidnight/cafenebula/
        └── SandboxMain.java      # Main demo application
```

## Troubleshooting

### Common Issues and Solutions

#### Issue: "Could not find or load main class"
**Cause:** Main class in wrong location or module
**Solution:**
```bash
# Check if file exists in correct location
ls -la samples/src/main/java/com/ashmidnight/cafenebula/SandboxMain.java

# Verify package declaration matches directory structure
head -n 1 samples/src/main/java/com/ashmidnight/cafenebula/SandboxMain.java
# Should show: package com.ashmidnight.cafenebula;
```

#### Issue: "EGL: Library not found"
**Cause:** WSL graphics not configured
**Solution:**
1. Follow WSL graphics setup (Option A or B above)
2. Verify display variable: `echo $DISPLAY`
3. Test with: `glxinfo | grep OpenGL`

#### Issue: "GlfwInput is abstract; cannot be instantiated"
**Cause:** Trying to create instance of abstract class
**Solution:** Comment out input-related code temporarily:
```java
// private GlfwInput input;
// input = new GlfwInput(window);
```

#### Issue: Build fails with dependency errors
**Cause:** Missing or incorrect module dependencies
**Solution:** Verify `samples/build.gradle.kts` exists with correct content:
```kotlin
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
    // ... LWJGL dependencies
}
```

#### Issue: Segmentation fault on program exit
**Cause:** Improper OpenGL cleanup sequence
**Status:** Known issue, program runs correctly but crashes on exit
**Workaround:** Close window normally, ignore terminal error message

### Verification Commands

Test your setup step by step:

```bash
# 1. Check Java
java --version

# 2. Check Gradle
./gradlew --version

# 3. Check graphics (Linux/WSL)
glxinfo | grep "OpenGL version"

# 4. Test simple graphics
xeyes  # Should show a window

# 5. Build project
./gradlew clean build

# 6. Run sample
./gradlew :samples:run
```

## Performance Notes

### First Run
- Gradle will download ~50MB of LWJGL dependencies
- Compilation may take 30-60 seconds
- Subsequent runs should be much faster

### Runtime Requirements
- **RAM:** 512MB minimum (JVM overhead + graphics)
- **Graphics:** OpenGL 3.3+ support required
- **CPU:** Any modern processor sufficient for 60fps rendering

## IDE Setup (Optional)

### IntelliJ IDEA
1. Open project by selecting the root `build.gradle.kts`
2. Wait for Gradle import to complete
3. Set Project SDK to Java 21+
4. Run configuration should auto-generate for `SandboxMain`

### VS Code
1. Install Java Extension Pack
2. Open project folder
3. VS Code should detect Gradle project automatically

## Next Steps

Once you have the basic window running:

1. **Explore the code:** Understand the HSV color cycling implementation
2. **Experiment:** Try changing color cycling speed or saturation values
3. **Add features:** Implement proper input handling, basic shapes, textures
4. **Fix issues:** Address the cleanup-related crash, improve resource management

## Getting Help

If you encounter issues not covered here:

1. Check the main README.md for current known issues
2. Review LEARNING.md for solutions to problems encountered during development
3. Verify your graphics drivers are up to date
4. Test with a simple OpenGL program to isolate engine vs. system issues

---

**Success Criteria:** You should see a smooth color-cycling window that responds to close commands. The program may crash on exit (known issue) but should run stably while active.
