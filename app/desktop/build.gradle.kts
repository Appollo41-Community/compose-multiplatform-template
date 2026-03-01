import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.conveyor)
}

// Conveyor reads `project.version` to set the app version in generated packages.
version = "1.0.0"

// JVM toolchain — JetBrains Runtime is recommended for Compose Desktop.
// The foojay-resolver plugin in settings.gradle.kts auto-downloads it.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
        vendor.set(JvmVendorSpec.JETBRAINS)
    }
}

dependencies {
    implementation(project(":composeApp"))
    implementation(compose.desktop.currentOs)
    implementation(libs.kotlinx.coroutines.swing)
    implementation(libs.napier)

    // ---------------------------------------------------------------------------
    // Conveyor: platform-specific Compose Desktop artifacts for cross-platform
    // packaging. Conveyor only uses these when building installers for
    // platforms other than the current one. They do NOT affect local development.
    // ---------------------------------------------------------------------------
    linuxAmd64("org.jetbrains.compose.desktop:desktop-jvm-linux-x64:${libs.versions.compose.plugin.get()}")
    macAmd64("org.jetbrains.compose.desktop:desktop-jvm-macos-x64:${libs.versions.compose.plugin.get()}")
    macAarch64("org.jetbrains.compose.desktop:desktop-jvm-macos-arm64:${libs.versions.compose.plugin.get()}")
    windowsAmd64("org.jetbrains.compose.desktop:desktop-jvm-windows-x64:${libs.versions.compose.plugin.get()}")
}

// Needed when mixing compose.desktop.currentOs with the platform-specific deps above.
// Without this, Gradle may fail to resolve the correct Compose UI variant.
configurations.all {
    attributes {
        attribute(Attribute.of("ui", String::class.java), "awt")
    }
}

// =============================================================================
// This app has TWO distribution methods:
//
// 1) jpackage (built-in Compose Desktop) — produces basic native installers
//    via Gradle tasks like `packageDmg`, `packageMsi`, `packageDeb`.
//    No auto-update support.
//
// 2) Conveyor (Hydraulic) — produces auto-updating native packages.
//    Run `conveyor make site` from the project root. Config is in conveyor.conf.
//    Supports Sparkle (macOS), MSIX (Windows), apt/yum repos (Linux).
// =============================================================================

compose.desktop {
    application {
        mainClass = "com.appollo41.app.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.appollo41.app"
            packageVersion = "1.0.0"
        }
    }
}
