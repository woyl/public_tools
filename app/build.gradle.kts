import org.gradle.kotlin.dsl.implementation

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
//    kotlin("android") version "2.0.0" // 确保使用 Kotlin 2.0+

    alias(libs.plugins.kotlinCompose)
}

android {
    namespace = "com.woyl.my_"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.woyl.my_"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
//    implementation fileTree(dir: 'libs', include: ['*.jar'])
//    implementation"org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.6.20"
//    implementation 'androidx.appcompat:appcompat:1.6.1'
//    implementation 'androidx.core:core-ktx:1.12.0'
//    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
//    testImplementation 'junit:junit:4.13.2'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


//    implementation(libs.woyl.tool)
//    implementation(libs.androidx.startup)
    implementation (libs.androidx.startup.runtime)
    implementation (libs.okio)

    // Compose依赖
//    implementation("androidx.compose.ui:ui:1.5.4")
//    implementation("androidx.compose.material:material:1.5.4")
//    implementation("androidx.compose.ui:ui-tooling-preview:1.5.3")
//    debugImplementation("androidx.compose.ui:ui-tooling:1.5.3")

//    implementation 'com.github.woyl:public_tools:1.1.0'
    androidTestImplementation(project(":lt_woyl"))
    implementation (project (":lt_woyl")) // 引用工具库模块
}
