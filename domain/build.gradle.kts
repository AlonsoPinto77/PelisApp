plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies{
    implementation ("com.android.support:multidex:1.0.3")

    //di
    implementation("io.insert-koin:koin-core:3.4.2")
}
