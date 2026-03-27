# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
# Android
./gradlew :composeApp:assembleDebug

# Desktop (JVM)
./gradlew :composeApp:run

# Web (Kotlin/Wasm)
./gradlew :composeApp:wasmJsBrowserDevelopmentRun

# iOS — open iosApp/iosApp.xcodeproj in Xcode, or build the framework:
./gradlew :composeApp:linkDebugFrameworkIosArm64
./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64
```

No tests exist in the project currently.

## Architecture

Single-module Kotlin Multiplatform project (`composeApp`) targeting **Android, iOS, Desktop (JVM), and Web (Wasm)**. UI is built entirely with Compose Multiplatform.

### Navigation Flow

`App.kt` sets up a `NavHost` with two top-level destinations:
1. **Intro** — splash screen (1s delay, auto-navigates to Main)
2. **Main** — scaffold with bottom navigation (4 tabs: Home, Store, Laundry, My)

Routes are defined via `AppMainDestinations` constants and a `Route` sealed class in `App.kt`. Bottom nav tabs are configured in `MainApp.kt`.

### Expect/Actual Pattern

Platform-specific code uses Kotlin's `expect`/`actual` mechanism:
- `Platform.kt` — platform name/version detection
- `Color.kt` — platform-specific theme colors
- `WebView.kt` — native WebView per platform (Android: `android.webkit.WebView`, iOS: `WKWebView` via cinterop, Desktop: stub)

Source sets: `commonMain` → `androidMain`, `iosMain`, `desktopMain`, `wasmJsMain`.

### iOS Integration

The iOS app is a SwiftUI wrapper (`iosApp/`). `ContentView.swift` hosts the Compose UI via `ComposeView` (a `UIViewControllerRepresentable` bridging to `MainViewController.kt`).

## Key Dependencies

- **Kotlin 2.1.0**, **Compose Multiplatform 1.7.3**
- **Navigation Compose** (`2.7.0-alpha07`) — multiplatform navigation
- **AndroidX Lifecycle/ViewModel** (`2.8.4`) — multiplatform ViewModel support
- **Napier** (`2.7.1`) — multiplatform logging
- Version catalog: `gradle/libs.versions.toml`

## Android Config

- Package: `com.kkiruru.kmp.hello`
- Min SDK 24 / Target SDK 34 / Compile SDK 34
- JVM target: 19