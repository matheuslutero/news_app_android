import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")

if (localPropertiesFile.exists()) {
    localProperties.load(FileInputStream(localPropertiesFile))
}

val newsApiKey: String by localProperties
val newsApiBaseUrl: String by project
val newsAppName: String by project
val newsSources: String by project
val sportsAppName: String by project
val sportsSources: String by project

android {
    namespace = "com.matheuslutero.newsapp"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "API_KEY", "\"$newsApiKey\"")
        buildConfigField("String", "BASE_URL", "\"$newsApiBaseUrl\"")
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
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

    flavorDimensions += "version"

    productFlavors {
        create("news") {
            dimension = "version"
            applicationId = "com.matheuslutero.newsapp"
            resValue(type = "string", name = "app_name", value = newsAppName)
            buildConfigField(type = "String", name = "SOURCES", value = "\"$newsSources\"")
        }
        create("sports") {
            dimension = "version"
            applicationId = "com.matheuslutero.newsapp.sports"
            resValue(type = "string", name = "app_name", value = sportsAppName)
            buildConfigField(type = "String", name = "SOURCES", value = "\"$sportsSources\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Coil
    implementation(libs.coil.compose)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)

    // Dagger Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.android.compiler)

    // JUnit
    testImplementation(libs.junit)

    // Moshi
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.adapters)

    // Mockk
    testImplementation(libs.mockk)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.logging.interceptor)

    // Serialization
    implementation(libs.kotlinx.serialization.json)

    // Truth
    testImplementation(libs.truth)

    // Turbine
    testImplementation(libs.turbine)

    // Android
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.biometric)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.navigation.compose)
}
