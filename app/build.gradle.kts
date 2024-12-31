plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs")
    id("com.google.devtools.ksp")
    id ("kotlin-parcelize")



}

android {
    namespace = "com.example.myapplicationfragmentsnav"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myapplicationfragmentsnav"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{
        viewBinding=true
    }
}

dependencies {
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui.ktx)



    implementation ("androidx.room:room-runtime:2.5.2")
    annotationProcessor ("androidx.room:room-compiler:2.5.2")
    ksp("androidx.room:room-compiler:2.5.0")
    //kapt("androidx.room:room-compiler:2.5.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
    implementation ("androidx.room:room-ktx:2.5.2")



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}