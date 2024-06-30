import org.gradle.kotlin.dsl.libs
import org.jetbrains.kotlin.utils.addToStdlib.safeAs

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.navigation.safe.args)
}

android {
    namespace = "com.ren.courses"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        viewBinding = true
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.fragment.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.view.binding.property.delegate.noreflection)
    implementation(libs.bundles.navigation)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.glide)

    // adapterDelegate
    val adapterDelegateVer = ("4.3.2")
    implementation ("com.hannesdorfmann:adapterdelegates4-kotlin-dsl:$adapterDelegateVer")
    implementation ("com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:$adapterDelegateVer")

    // player view
    implementation ("com.google.android.exoplayer:exoplayer:2.19.1")

    implementation(project(":core:theme"))
    implementation(project(":core:data"))
    implementation(project(":core:presentation"))
    implementation(project(":forex-api"))
}