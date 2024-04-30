plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.navigation.safe.args)
}

android {
    namespace = "com.ren.zarinacapital"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ren.zarinacapital"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "FOREX_BASE_URL", "\"http://16.170.211.115/api/v1/\"")
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
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.bundles.navigation)

    // Core
    implementation(project(":core:theme"))
    implementation(project(":core:presentation"))
    implementation(project(":core:di"))

    // Api
    implementation(project(":forex-api"))

    // Data
    implementation(project(":data:courses"))

    // Features
    implementation(project(":features:courses"))
    implementation(project(":features:menu"))
    implementation(project(":features:my-courses"))
    implementation(project(":features:auth"))
}

kapt {
    correctErrorTypes = true
}