plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.woyl.lt_woyl"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {
//    implementation fileTree(dir: 'libs', include: ['*.jar'])
//    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.6.20"
//    implementation 'androidx.appcompat:appcompat:1.6.1'
//    implementation 'androidx.core:core-ktx:1.12.0'
//    testImplementation 'junit:junit:4.13.2'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
//    //noinspection GradleCompatible
////    implementation 'com.android.support:recyclerview-v7:28.0.0'
//    //noinspection GradleCompatible
////    implementation 'com.android.support:appcompat-v7:28.0.0'
//    //noinspection GradleCompatible
////    implementation 'com.android.support:cardview-v7:28.0.0'
//
//    implementation 'com.github.bumptech.glide:glide:4.11.0'
//    annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'
//    implementation 'com.google.android.material:material:1.2.1'
//    implementation 'androidx.recyclerview:recyclerview:1.1.0'

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.media3.common)
    implementation(libs.androidx.startup.runtime)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
