plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.navigationSafeArgs)
}

android {
    namespace = "com.oguzhanozgokce.worldwords"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.oguzhanozgokce.worldwords"
        minSdk = 27
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.swiperefreshlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    //test
    testImplementation(libs.androidx.core.testing)
    implementation(libs.androidx.fragment.testing)
    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    //Gson
    implementation(libs.com.google.code.gson)
    //Glide
    implementation(libs.com.github.bumptech.glide)
    kapt(libs.com.github.bumptech.glide.compiler)
    //test
    testImplementation (libs.mockito.core)
    testImplementation (libs.mockito.inline)
    testImplementation (libs.kotlinx.coroutines.test)
    testImplementation (libs.androidx.core)
    testImplementation (libs.mockito.kotlin)



}