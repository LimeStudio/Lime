import org.gradle.api.Project
import org.gradle.kotlin.dsl.task
import org.gradle.testing.jacoco.tasks.JacocoReport

fun Project.createJacocoUnitTestReportTask() {
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
}