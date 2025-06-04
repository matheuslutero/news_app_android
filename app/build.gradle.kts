import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
    alias(libs.plugins.navigation.safeargs)
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
    implementation(libs.coil)
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

    // Material
    implementation(libs.material)

    // Moshi
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.adapters)

    // Mockito
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.logging.interceptor)

    // Truth
    testImplementation(libs.truth)

    // Android
    implementation(libs.androidx.activity.compose)
    implementation(libs.activity.ktx)
    implementation(libs.appcompat)
    implementation(libs.biometric)
    implementation(libs.biometric.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.swiperefreshlayout)
    testImplementation(libs.androidx.core.testing)
}
