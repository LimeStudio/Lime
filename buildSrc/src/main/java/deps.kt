object deps {
    private const val androidGradlePluginVersion = "3.6.0"
    private const val archCoreVersion = "2.1.0"
    private const val roomVersion = "2.2.0"
    private const val lifecycleVersion = "2.2.0-beta01"
    private const val pagingVersion = "2.1.0"
    private const val navigationVersion = "2.1.0"
    private const val workVersion = "2.2.0"
    private const val supportVersion = "1.0.0"
    private const val constraintlayoutVersion = "2.0.0-beta4"
    private const val glideVersion = "4.10.0"
    private const val rxjava2Version = "2.2.4"
    private const val retrofitVersion = "2.6.2"
    private const val rxAndroidVersion = "2.1.0"
    private const val kotlinVersion = "1.3.50"
    private const val daggerVersion = "2.24"
    private const val junitVersion = "4.12"
    private const val mockwebserverVersion = "4.2.1"
    private const val mockitoVersion = "2.26.0"
    private const val mockitoAllVersion = "2.26.0"
    private const val mockitoAndroidVersion = "2.26.0"
    private const val espressoVersion = "3.2.0"
    private const val runnerVersion = "1.2.0"
    private const val persistentCookieJarVersion = "v1.0.1"
    private const val androidJunitVersion = "1.1.0"
    private const val androidxTestCoreVersion = "1.2.0"
    private const val googleTruthVersion = "0.42"
    private const val coroutinesVersion = "1.3.2"


    object plugin {
        val gradle = "com.android.tools.build:gradle:$androidGradlePluginVersion"
    }

    object kotlin {
        val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
        val test = "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"
        val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        val allopen = "org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion"
    }

    object constraintlayout {
        val constraintlayoutSolver = "androidx.constraintlayout:constraintlayout-solver:$constraintlayoutVersion"
        val constraintlayout = "androidx.constraintlayout:constraintlayout:$constraintlayoutVersion"
    }

    object support {
        val material = "com.google.android.material:material:$supportVersion"
        val appcompat = "androidx.appcompat:appcompat:$supportVersion"
        val recyclerview = "androidx.recyclerview:recyclerview:$supportVersion"
        val annotation = "androidx.annotation:annotation:$supportVersion"
        val legacySupportCoreUtils = "androidx.legacy:legacy-support-core-utils:$supportVersion"
        val legacySupportV4 = "androidx.legacy:legacy-support-v4:$supportVersion"
        val cardview = "androidx.cardview:cardview:$supportVersion"
        val ktx = "androidx.core:core-ktx:$supportVersion"
        val palette = "androidx.palette:palette:$supportVersion"

    }

    object lifecycle {
        val java8 = "androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion"
        val compiler = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"
        val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
        val extensions = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
    }

    object room {
        val runtime = "androidx.room:room-runtime:$roomVersion"
        val compiler = "androidx.room:room-compiler:$roomVersion"
        val rxjava2 = "androidx.room:room-rxjava2:$roomVersion"
        val testing = "androidx.room:room-testing:$roomVersion"
        val coroutines = "androidx.room:room-ktx:$roomVersion"
    }

    object paging {
        val runtime = "androidx.paging:paging-runtime:$pagingVersion"
        val testing = "androidx.paging:paging-common:$pagingVersion"
        val rxjava2 = "androidx.paging:paging-rxjava2:$pagingVersion"
    }

    object arch_core {
        val testing = "androidx.arch.core:core-testing:$archCoreVersion"
    }

    object navigation {
        val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        val uiKtx = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
        val testingKtx = "androidx.navigation:navigation-testing-ktx:$navigationVersion"
        val safeArgsPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion"
    }

    object work {
        val runtimeKtx = "androidx.work:work-runtime-ktx:$workVersion"
        val testing = "androidx.work:work-testing:$workVersion"
    }

    object glide {
        val core = "com.github.bumptech.glide:glide:$glideVersion"
        val compiler = "com.github.bumptech.glide:compiler:$glideVersion"
    }

    object retrofit {
        val runtime = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        val gson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        val mock = "com.squareup.retrofit2:retrofit-mock:$retrofitVersion"
        val rxjava2 = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
    }

    object dagger {
        val runtime = "com.google.dagger:dagger:$daggerVersion"
        val android = "com.google.dagger:dagger-android:$daggerVersion"
        val androidSupport = "com.google.dagger:dagger-android-support:$daggerVersion"
        val compiler = "com.google.dagger:dagger-compiler:$daggerVersion"
        val androidSupportCompiler = "com.google.dagger:dagger-android-processor:$daggerVersion"
    }

    object mockito {
        val core = "org.mockito:mockito-core:$mockitoVersion"
        val all = "org.mockito:mockito-all:$mockitoAllVersion"
        val android = "org.mockito:mockito-android:$mockitoAndroidVersion"
    }

    object espresso {
        val core = "androidx.test.espresso:espresso-core:$espressoVersion"
        val contrib = "androidx.test.espresso:espresso-contrib:$espressoVersion"
        val intents = "androidx.test.espresso:espresso-intents:$espressoVersion"
    }

    object runner {
        val runner = "androidx.test:runner:$runnerVersion"
        val rules = "androidx.test:rules:$runnerVersion"
    }

    object androidJunit {
        val junit = "androidx.test.ext:junit:$androidJunitVersion"
        val truth = "androidx.test.ext:truth:$androidJunitVersion"
    }

    object coroutines {
        val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
        val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion"

    }

    object other {
        val androidxTestCore = "androidx.test:core:$androidxTestCoreVersion"
        val rxjava2 = "io.reactivex.rxjava2:rxjava:$rxjava2Version"
        val rxAndroid = "io.reactivex.rxjava2:rxandroid:$rxAndroidVersion"
        val junit = "junit:junit:$junitVersion"
        val mockWebServer = "com.squareup.okhttp3:mockwebserver:$mockwebserverVersion"
        val persistentCookieJar = "com.github.franmontiel:PersistentCookieJar:$persistentCookieJarVersion"
    }
}

