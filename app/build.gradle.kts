plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    alias(libs.plugins.paparazzi)
}


android {
    namespace = "com.android.battleoffavorites"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.android.battleoffavorites"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
        viewBinding = true
        compose = true
    }
    hilt {
        enableAggregatingTask = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(libs.bundles.androidxCore)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.composeAdditional)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.hilt)
    implementation(libs.bundles.otherLibraries)
    implementation(libs.bundles.retrofit)
    implementation(libs.androidx.media3.session)
    implementation(libs.firebase.auth.ktx)
    kapt(libs.bundles.hiltKapt)
    annotationProcessor(libs.hiltCompiler)
    debugImplementation(libs.composeUiTooling)
    debugImplementation(libs.androidx.compose.ui.testManifest)
    testImplementation(libs.bundles.testing)
    androidTestImplementation(libs.bundles.uiTesting)
    implementation(libs.firebase.bom)
    implementation(libs.firebase.analytics)
    implementation (libs.firebase.firestore.ktx)

}

apply(plugin = "com.google.gms.google-services")

