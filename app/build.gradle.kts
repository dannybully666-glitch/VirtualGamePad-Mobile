plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.example.gamepadoverlay"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.gamepadoverlay"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
}
