import java.net.URI

allprojects {
    repositories {
        mavenCentral()
        maven { url = URI("https://jitpack.io") }
    }
}
