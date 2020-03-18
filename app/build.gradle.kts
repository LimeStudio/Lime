import com.android.build.gradle.internal.dsl.TestOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(GradlePluginId.ANDROID_APPLICATION)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_KAPT)
    id(GradlePluginId.KOTLIN_ANDROID_EXTENSIONS)
    id(GradlePluginId.ANDROIDX_NAVIGATION_SAFEARGS)
    id(GradlePluginId.TOOLS_JACOCO)
    id(GradlePluginId.KOTLIN_ALLOPEN)
}
jacoco {
    toolVersion = "0.8.2"
}
allOpen {
    annotations("com.lime.testing.OpenClass")
}
android {
    compileSdkVersion(AndroidVersion.COMPILE_VERSION)
    buildToolsVersion(AndroidVersion.BUILD_TOOLS)

    defaultConfig {
        applicationId = "com.moi.lime"
        minSdkVersion(AndroidVersion.MIN_SDK_VERSION)
        targetSdkVersion(AndroidVersion.TARGET_SDK_VERSION)
        versionCode = AndroidVersion.VERSION_CODE
        versionName = AndroidVersion.VERSION_NAME
        testInstrumentationRunner = "com.moi.lime.util.LimeTestRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    signingConfigs {
        val keystoreUtils = KeystoreUtils(project)
        create("default") {
            keyAlias = keystoreUtils.keystoreProperties["keyAlias"].toString()
            keyPassword = keystoreUtils.keystoreProperties["keyPwd"].toString()
            storeFile = keystoreUtils.keyStoreFile
            storePassword = keystoreUtils.keystoreProperties["storePwd"].toString()
        }
    }
    buildTypes {
        getByName("release") {
            multiDexEnabled = true
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
            signingConfig = signingConfigs.findByName("default")
            isTestCoverageEnabled = true
        }
        getByName("debug") {
            multiDexEnabled = true
            isTestCoverageEnabled = true
        }
    }
    sourceSets {
        getByName("test").java.srcDirs("src/test-common/java")
        getByName("androidTest").java.srcDirs("src/test-common/java")
    }
    dataBinding {
        isEnabled = true
    }
    testOptions {
        unitTests(delegateClosureOf<TestOptions.UnitTestOptions> {
            isIncludeAndroidResources = true
            all(KotlinClosure1<Any, Test>({
                //jvmArgs '-noverify', '-ea'
                (this as Test).also { testTask ->
                    testTask.extensions
                            .getByType(JacocoTaskExtension::class.java)
                            .isIncludeNoLocationClasses = true
                }
            }, this))
        })
    }
    androidExtensions {
        isExperimental = true
    }

}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //kotlin
    implementation(deps.kotlin.stdlib)
    //lifecycle
    implementation(deps.lifecycle.java8)
    implementation(deps.lifecycle.viewmodel)
    implementation(deps.lifecycle.livedata)
    implementation(deps.lifecycle.runtime)
    implementation(deps.lifecycle.extensions)
    kapt(deps.lifecycle.compiler)
    //room
    implementation(deps.room.runtime)
    implementation(deps.room.coroutines)
    kapt(deps.room.compiler)
    //paging
    implementation(deps.paging.runtime)
    //workManager
    implementation(deps.work.runtimeKtx)
    //navigation
    implementation(deps.navigation.fragmentKtx)
    implementation(deps.navigation.uiKtx)
    //constraintLayout
    implementation(deps.constraintlayout.constraintlayoutSolver)
    implementation(deps.constraintlayout.constraintlayout)
    //support
    implementation(deps.support.material)
    implementation(deps.support.appcompat)
    implementation(deps.support.recyclerview)
    implementation(deps.support.annotation)
    implementation(deps.support.legacySupportCoreUtils)
    implementation(deps.support.legacySupportV4)
    implementation(deps.support.ktx)
    implementation(deps.support.palette)
    //glide
    implementation(deps.glide.core)
    kapt(deps.glide.compiler)
    //coroutines_android
    implementation(deps.coroutines.android)
    implementation(deps.coroutines.core)
    //retrofit
    implementation(deps.retrofit.runtime)
    implementation(deps.retrofit.gson)
    implementation(deps.retrofit.mock)
    //dagger
    implementation(deps.dagger.runtime)
    implementation(deps.dagger.android)
    implementation(deps.dagger.androidSupport)
    kapt(deps.dagger.androidSupportCompiler)
    kapt(deps.dagger.compiler)
    //cookie
    implementation(deps.other.persistentCookieJar)

    //test
    testImplementation(deps.kotlin.test)
    testImplementation(deps.other.junit)
    testImplementation(deps.retrofit.mock)
    testImplementation(deps.other.mockWebServer)
    testImplementation(deps.mockito.core)
    androidTestImplementation(deps.mockito.core)
    androidTestImplementation(deps.mockito.android)
    androidTestImplementation(deps.kotlin.test)


    // Core library
    androidTestImplementation(deps.other.androidxTestCore)

    // AndroidJUnitRunner and JUnit Rules
    androidTestImplementation(deps.runner.runner)
    androidTestImplementation(deps.runner.rules)

    // Assertions
    androidTestImplementation(deps.androidJunit.junit)
    androidTestImplementation(deps.androidJunit.truth)

    // Espresso dependencies
    androidTestImplementation(deps.espresso.core)
    androidTestImplementation(deps.espresso.contrib)

    //JetPack test
    testImplementation(deps.arch_core.testing)
    androidTestImplementation(deps.arch_core.testing)

    //support
    androidTestImplementation(deps.support.appcompat)
    androidTestImplementation(deps.support.recyclerview)
    androidTestImplementation(deps.support.cardview)
    androidTestImplementation(deps.support.material)

    //coroutines test
    testImplementation(deps.coroutines.test)
    androidTestImplementation(deps.coroutines.test)

}
createJacocoUnitTestReportTask()

