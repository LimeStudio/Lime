import org.gradle.api.Project
import java.io.FileInputStream
import java.util.*

class KeystoreUtils(project: Project) {
    private val keystorePropertiesFile = project.rootProject.file("keystore.properties")
    val keystoreProperties = Properties()
            .apply {
                load(FileInputStream(keystorePropertiesFile))
            }
    val keyStoreFile =
            if (project.file(keystoreProperties["storeFileLocal"]!!).exists()) {
                project.file(keystoreProperties["storeFileLocal"]!!)
            } else {
                project.file(keystoreProperties["storeFileCI"]!!)
            }

}