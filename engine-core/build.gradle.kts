plugins {
    java
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

dependencies {
    implementation("org.tinylog:tinylog-api:2.7.0")
    runtimeOnly("org.tinylog:tinylog-impl:2.7.0")
}
