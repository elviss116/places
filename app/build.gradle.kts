plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    signingConfigs {
        getByName("debug") {
            storeFile = file("place.pfx")
            storePassword = "123456"
            keyPassword = "123456"
            keyAlias = "ELVIS"
        }
    }
    namespace = "com.example.places"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.places"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

    flavorDimensions += "myPlaces"
    productFlavors {

        create("pinkStyle"){
            dimension = "myPlaces"
            applicationIdSuffix = ".pink"
            versionNameSuffix = "-pink"
        }

        create("normalStyle"){
            dimension = "myPlaces"
            applicationIdSuffix = ".normal"
            versionNameSuffix = "-normal"
        }

    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.daggerHilt)
    implementation(libs.navigation.ui)
    implementation(libs.navigation.fragment)
    implementation(libs.glide)
    implementation(libs.glideCompiler)
    implementation(libs.lottie)
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    ksp(libs.hiltCompiler)

}