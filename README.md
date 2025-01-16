This is a Compose Multiplatform project template targeting Android, iOS and Desktop.

## Project Structure
* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `src/commonMain` is for code that’s common for all targets.
    - `kotlin` - Common Kotlin native code (no JVM code or Java libraries allowed here)
    - `composeResources` - Common resources for all platforms such as vector icons and strings;
  - All other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    - `src/iosMain` - iOS platform customization and access to iOS api through Kotlin/Native interop;
    - `src/androidMain` - Android platform customization and access to full Android SDK;
    - `src/desktopMain` - Desktop JVM platform and access to all Java libraries;


* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project if you need or want. 

## Project Architecture
- TBD

## Library Dependencies
* [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform) for UI framework;
* [Room Multiplatform](https://developer.android.com/kotlin/multiplatform/room) for SQLite database storage;
* [Koin](https://github.com/InsertKoinIO/koin) for dependency injection;
* [Ktor Client](https://github.com/ktorio/ktor) for networking layer;
* [KotlinX Serialization](https://github.com/Kotlin/kotlinx.serialization) for serialization;
* [KotlinX DateTime](https://github.com/Kotlin/kotlinx-datetime) for date/time;
* [Napier](https://github.com/AAkira/Napier) for logging;
* [Calf Adaptive UI](https://github.com/MohamedRejeb/Calf) for native adaptive UI and accessing platform specific API;

## Useful links
Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html).