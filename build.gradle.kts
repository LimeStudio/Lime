
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

task("jacocoUnitTestReport", JacocoReport::class) {
    dependsOn("testDebugUnitTest")
    reports {
        xml.isEnabled = true
        html.isEnabled = true
    }
    val fileFilter = arrayOf("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*", "**/*Test*.*", "android/**/*.*")
    val debugTree = listOf(
            fileTree("$buildDir/intermediates/classes/debug").apply {
                exclude(*fileFilter)
            },
            fileTree("$buildDir/tmp/kotlin-classes/debug").apply {
                exclude(*fileFilter)
            }
    )
    val mainSrc = "$projectDir/src/main/java"
    sourceDirectories.from(mainSrc)
    classDirectories.from(debugTree)
    executionData.from(fileTree(projectDir).apply {
        include("build/jacoco/testDebugUnitTest.exec")
    })


}

//
//task clean(type: Delete) {
//    delete rootProject.buildDir
//}
