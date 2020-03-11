
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    // apply from: 'versions.gradle'
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(deps.plugin.gradle)
        classpath(deps.kotlin.plugin)
        classpath(deps.navigation.safeArgsPlugin)
        classpath(deps.kotlin.allopen)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
    }
}
//
//task clean(type: Delete) {
//    delete rootProject.buildDir
//}
