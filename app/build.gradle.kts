plugins {
    id("com.android.application")
}

android {
    namespace = "com.jccsisc.myroomdb"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.jccsisc.myroomdb"
        minSdk = 23
        targetSdk = 33
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Room
    implementation("androidx.room:room-runtime:2.5.2")
    implementation ("androidx.room:room-rxjava3:2.5.2")
    annotationProcessor("androidx.room:room-compiler:2.5.2")

    //RXJava
    implementation ("io.reactivex.rxjava3:rxjava:3.0.0")
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.0")

    //ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata:2.6.2")

    //Dagger
    implementation("com.google.dagger:dagger:2.38.1")
    annotationProcessor("com.google.dagger:dagger-compiler:2.38.1")

    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))//se agrega esta linea porque da problemas para generar dagger 2

    //Design
    // Circle Indicator (To fix the xml preview "Missing classes" error)
    implementation("me.relex:circleindicator:2.1.6")
    implementation("org.imaginativeworld.whynotimagecarousel:whynotimagecarousel:2.1.0")
}