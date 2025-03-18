plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    id("org.jetbrains.kotlin.kapt") // Habilitar Kapt
}

android {
    namespace = "com.rpla.marvelherosrepo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.rpla.marvelherosrepo"
        minSdk = 31
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    kapt {
        correctErrorTypes = true
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
        compose = true
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
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.constraintlayout.compose)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.navigation.compose)
    implementation(libs.paging.runtime) // Para usar con ViewModel y LiveData
    implementation(libs.paging.compose)
    implementation(libs.material.icons.core) // Conjunto básico de iconos
    implementation(libs.material.icons.extended) // Conjunto extendido de iconos
    implementation(libs.androidx.splashscreen)
    implementation(libs.coroutines.core) // Corrutinas estándar
    implementation(libs.coroutines.android) // Soporte para Android
    implementation(libs.retrofit) // Core de Retrofit
    implementation(libs.retrofit.converter.gson) // Conversor de JSON a objetos Kotlin
    implementation(libs.okhttp) // Cliente HTTP
    implementation(libs.okhttp.logging) // Interceptor para logs de red
    implementation(libs.hilt.android) // Core de Hilt
    implementation(libs.androidx.annotation) // Para soporte de anotaciones
    //implementation(libs.javapoet)
    kapt(libs.hilt.compiler)
//    {
//        exclude(group = "com.squareup", module = "javapoet")
//    }
    implementation(libs.hilt.navigation.compose) // Integración con Jetpack Compose

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}