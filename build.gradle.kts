// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins{
    id("com.google.devtools.ksp" ) version "1.9.0-1.0.12" apply false
}

buildscript{
    val kotlinVersion by extra {"1.8.0"}
    repositories{
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

tasks.create<Delete>("clean"){
    delete(rootProject.buildDir)
}