plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.plugin)
    kotlin("kapt")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.atm.olympuscourierapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.atm.olympuscourierapp"
        minSdk = 29
        targetSdk = 35
        versionCode = 5
        versionName = "2.1.2"

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Navegaciones
    implementation("androidx.navigation:navigation-compose:2.9.0")

    // Iconos
    implementation("androidx.compose.material:material-icons-extended:1.7.8")

    // Coil
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Sweet Alert
    implementation("com.github.f0ris.sweetalert:library:1.6.2")

    //Compressor
    implementation("id.zelory:compressor:3.0.1")

    //Permisos
    implementation("com.google.accompanist:accompanist-permissions:0.28.0")

    // Hilt
    implementation(libs.hilt)
    implementation(libs.hilt.navigation)
    kapt(libs.hilt.compiler)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)

    // OkHttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.interceptor)

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.6.0"))

    //Firebase Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.8.0")

    //Firebase Storage
    implementation("com.google.firebase:firebase-storage-ktx")

    implementation("com.github.kenglxn.QRGen:android:2.6.0")
}