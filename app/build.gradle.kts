plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
    id("org.jetbrains.kotlinx.kover") version "0.7.5"
}

android {
    namespace = "com.example.guthubprofiler"
    compileSdk = 34

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    defaultConfig {
        applicationId = "com.example.guthubprofiler"
        minSdk = 29
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
    koverReport {
        androidReports("debug") {
            verify {
                rule {
                    isEnabled = true
                    bound {
                        minValue = 80 // Minimum coverage percentage
                    }
                }
            }
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.13.9")
    testImplementation("androidx.test.ext:junit:1.1.5")
    testImplementation("org.robolectric:robolectric:4.11.1")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("org.robolectric:robolectric:4.11.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}
