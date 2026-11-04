# StudentFormApp

A simple Android student registration application built with **Java and XML in Android Studio**. The project demonstrates Android Activities, XML layouts, form validation, `Toast` messages, `SharedPreferences`, explicit `Intents`, Activity Lifecycle methods, responsive layouts, landscape-specific resources, internet permission, and a custom launcher icon.

> **Project approach:** Requirements A–K are implemented in the same `StudentFormApp` project. The later requirements (G–K) extend the original project; they do not require creating a second application.

## Table of Contents

- [Project Overview](#project-overview)
- [Requirements A–K](#requirements-ak)
- [Project Structure](#project-structure)
- [Technologies and Concepts](#technologies-and-concepts)
- [Setup and Run](#setup-and-run)
- [Application Flow](#application-flow)
- [Testing Checklist](#testing-checklist)
- [Important Notes](#important-notes)

## Project Overview

**Project name:** `StudentFormApp`  
**Language:** Java  
**UI:** XML  
**IDE:** Android Studio  
**Minimum SDK:** API 23 or above  
**Package name used in the examples:** `com.example.studentformapp`

The application has three Activities:

1. **SplashActivity** — displays a welcome/logo screen for 3 seconds.
2. **MainActivity** — displays a student registration form and a Welcome button.
3. **DetailActivity** — displays the information saved from the registration form.

When the user submits a valid form, the app saves the information in `SharedPreferences` and opens the detail screen using an explicit `Intent`. Previously saved form values are loaded when the main screen opens again.

If your Android project uses a different package name, use your actual package name in the Java files.

## Requirements A–K

### A. Splash Screen

- `SplashActivity` is the first screen shown when the application starts.
- The splash screen displays the app logo/text and a welcome message.
- A `Handler` with a 3000-millisecond delay keeps the splash screen visible for 3 seconds.
- After the delay, an explicit `Intent` opens `MainActivity`.
- `finish()` closes the splash Activity so it is not left in the back stack.

### B. Main Activity

The main screen is implemented in `MainActivity.java` with its XML layout, `activity_main.xml`.

It contains:

- A **Welcome** button.
- A student registration form.
- A **Submit** button.

The Welcome button uses `setOnClickListener()` to display a short welcome message using a `Toast`.

### C. Student Registration Form

The form contains the following controls:

| Field / Control               | Resource ID  |
| ----------------------------- | ------------ |
| Username                      | `etName`     |
| Email                         | `etEmail`    |
| Address                       | `etAddress`  |
| Password                      | `etPassword` |
| Terms and conditions checkbox | `checkBox`   |
| Welcome button                | `btnWelcome` |
| Submit button                 | `btnSubmit`  |

The Java code connects these XML controls using `findViewById()`.

Before saving, the app checks that:

- All text fields have been completed.
- The email address matches Android's email address pattern.
- The user has accepted the terms and conditions.

If a check fails, the app displays an error message or sets an error on the email field.

### D. Detail Screen

- `DetailActivity` displays the submitted username, email, address, password, and agreement status.
- Its layout is stored in `activity_detail.xml`.
- The Activity reads the saved values from `SharedPreferences`.
- The detail screen is opened from `MainActivity` after a successful form submission.

### E. SharedPreferences and Activity Lifecycle

#### SharedPreferences

`SharedPreferences` is used as local key-value storage for the form data.

The app saves these values in a preferences file named `UserData`:

| Key        | Data type |
| ---------- | --------- |
| `name`     | String    |
| `email`    | String    |
| `address`  | String    |
| `password` | String    |
| `agree`    | Boolean   |

The app uses `editor.apply()` to save changes and reads the saved values when the Activities need them. This allows the form data to remain available after the app is closed and reopened.

#### Activity Lifecycle

The project demonstrates these Activity Lifecycle methods:

| Method        | Purpose                                                         |
| ------------- | --------------------------------------------------------------- |
| `onCreate()`  | Creates the Activity and initializes its layout and views.      |
| `onStart()`   | Makes the Activity visible to the user.                         |
| `onResume()`  | Brings the Activity to the foreground for interaction.          |
| `onPause()`   | Called when the Activity is partially obscured or losing focus. |
| `onStop()`    | Called when the Activity is no longer visible.                  |
| `onDestroy()` | Called before the Activity is destroyed.                        |

In this project, `onCreate()` initializes the splash, main, and detail screens. `SplashActivity` also overrides `onDestroy()` to remove its pending Handler callback.

### F. Explicit Intents and Navigation

The project uses explicit Intents to navigate between its Activities:

1. `SplashActivity` → `MainActivity`
2. `MainActivity` → `DetailActivity`

An explicit Intent identifies the exact Activity that Android should open.

### G. ConstraintLayout

The portrait version of `activity_main.xml` uses `ConstraintLayout` as its outer layout.

- Add the ConstraintLayout dependency in the app module's Gradle file if it is not already present:

  ```gradle
  implementation 'androidx.constraintlayout:constraintlayout:2.2.1'
  ```

- The outer `ConstraintLayout` positions the screen content.
- A `ScrollView` allows the form to scroll on smaller screens.
- An inner vertical `LinearLayout` arranges the form controls.
- The original view IDs are retained, so the existing Java code does not need to change.

**Important:** The form controls are still arranged by the inner `LinearLayout`; the outer layout is the `ConstraintLayout`.

### H. Landscape Layout Support

A separate resource directory, `res/layout-land/`, provides layouts for horizontal device orientation.

Create the directory in Android Studio:

1. Right-click `res`.
2. Select **New → Android Resource Directory**.
3. Set **Resource type** to `layout`.
4. Add the **Orientation** qualifier.
5. Select **Landscape** and confirm.

Add these landscape layout files:

- `layout-land/activity_main.xml`
- `layout-land/activity_detail.xml`

The landscape main layout arranges the registration form in two side-by-side columns. The portrait layout remains in `res/layout/`.

For `activity_detail.xml`, create a horizontal arrangement suitable for the detail screen and retain all original TextView IDs:

- `tvName`
- `tvEmail`
- `tvAddress`
- `tvPassword`
- `tvAgreement`

Android automatically selects the matching layout resource based on the device orientation. The Java Activity code can continue using the same layout resource names and view IDs.

### I. Internet Permission

Add the following permission as a direct child of the `<manifest>` element, before the `<application>` element in `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

This permission allows the app to access the internet when it implements internet-based functionality. The current registration form and `SharedPreferences` features work offline; adding the permission does not itself create an internet feature.

### J. Custom App Icon

Replace the default launcher icon with a custom icon:

1. Right-click the `res` folder.
2. Select **New → Image Asset**.
3. Choose **Launcher Icons (Adaptive and Legacy)**.
4. Select an image for the foreground layer.
5. Choose a background color.
6. Keep the icon name as `ic_launcher`.
7. Select **Next → Finish**.

Set the icon on the `<application>` element in `AndroidManifest.xml`:

```xml
android:icon="@mipmap/ic_launcher"
```

The custom launcher icon will then be used for the application.

### K. Efficiency, User Friendliness, and Visual Consistency

The project applies these improvements:

| Improvement          | Implementation                                                                    |
| -------------------- | --------------------------------------------------------------------------------- |
| Consistent design    | Use the same blue (`#3F51B5`) and white background, with consistent text styling. |
| User friendly        | Provide clear hints in the form fields.                                           |
| Responsive interface | Use `ConstraintLayout` and `ScrollView` where appropriate.                        |
| Landscape support    | Provide alternative layouts in `res/layout-land/`.                                |
| Local data storage   | Use `SharedPreferences` for the small amount of form data.                        |
| Fast navigation      | Use explicit Intents between Activities.                                          |
| Input validation     | Check empty fields, email format, and terms acceptance.                           |
| Resource cleanup     | Remove pending Handler callbacks in `SplashActivity.onDestroy()`.                 |

## Project Structure

The main project files and resources are organized as follows:

```text
StudentFormApp/
└── app/
    └── src/
        └── main/
            ├── java/
            │   └── com/example/studentformapp/
            │       ├── SplashActivity.java
            │       ├── MainActivity.java
            │       └── DetailActivity.java
            │
            ├── res/
            │   ├── layout/
            │   │   ├── activity_splash.xml
            │   │   ├── activity_main.xml
            │   │   └── activity_detail.xml
            │   │
            │   ├── layout-land/
            │   │   ├── activity_main.xml
            │   │   └── activity_detail.xml
            │   │
            │   └── mipmap-*/
            │       └── ic_launcher
            │
            └── AndroidManifest.xml
```

The app module's Gradle file also contains the ConstraintLayout dependency when required. Android Studio may show resource folders and generated launcher icon files in a slightly different way depending on the project view and build configuration.

## Technologies and Concepts

- **Java:** application logic and Activity classes.
- **XML:** user interface layouts.
- **Android Activities:** separate application screens.
- **Handler and Runnable:** delayed transition from the splash screen.
- **Toast:** short on-screen feedback.
- **EditText, Button, CheckBox, TextView:** form and display controls.
- **`findViewById()`:** connects XML views to Java variables.
- **`setOnClickListener()`:** handles button clicks.
- **SharedPreferences:** stores small values locally as key-value pairs.
- **Explicit Intent:** opens a specific Activity.
- **ConstraintLayout:** flexible positioning of UI content.
- **ScrollView:** supports scrolling when content is larger than the screen.
- **Resource qualifiers:** select a separate layout for landscape orientation.
- **AndroidManifest.xml:** declares Activities, app settings, launcher Activity, icon, and permissions.

## Setup and Run

1. Open the project in Android Studio.
2. Confirm that the Java package name matches the package declarations in the Java files.
3. Confirm that all three Activity classes and their XML layouts are present.
4. Add the ConstraintLayout dependency to the app module if it is not already present.
5. Create the `layout-land` resource directory and add the landscape layouts.
6. Add the internet permission and custom launcher icon reference to `AndroidManifest.xml`.
7. Confirm that `SplashActivity` has the `MAIN` and `LAUNCHER` intent filter.
8. Allow Gradle Sync and the project build to finish.
9. Run the app on an emulator or Android device.

## Application Flow

```text
App Launch
    |
    v
SplashActivity
    |
    | Shows logo/welcome screen for 3 seconds
    v
MainActivity
    |
    | User enters registration details
    | App validates the form
    | App saves data in SharedPreferences
    v
DetailActivity
    |
    | Reads and displays saved information
    v
End
```

## Testing Checklist

Use this checklist to verify the project:

- [ ] App opens on the splash screen.
- [ ] Splash screen remains visible for approximately 3 seconds.
- [ ] Main Activity opens after the splash screen.
- [ ] Welcome button displays a Toast message.
- [ ] Empty form fields are rejected.
- [ ] Invalid email format is rejected.
- [ ] Submission is rejected if the terms checkbox is not selected.
- [ ] Valid form data is saved successfully.
- [ ] Detail Activity opens after successful submission.
- [ ] Detail Activity displays the saved information.
- [ ] Previously saved form values appear when the app is reopened.
- [ ] Rotating the device selects the landscape layout.
- [ ] Landscape main screen displays the form in two columns.
- [ ] Landscape detail layout retains the original TextView IDs.
- [ ] Internet permission is present in the manifest.
- [ ] Custom launcher icon appears for the app.

## Important Notes

1. **One project:** Requirements G–K are extensions to the original A–F implementation. Keep the existing Activities, Java logic, SharedPreferences keys, and Intent navigation.
2. **Keep IDs consistent:** If you provide portrait and landscape versions of the same screen, keep the view IDs used by the Java code identical in both layouts.
3. **Internet permission:** The permission is included for possible internet access, but the described registration and local-storage features do not require an internet connection.
4. **Password handling:** This assignment demonstration saves the password as plain text in `SharedPreferences` and displays it on the detail screen. This is not safe for a real application. A production app should not store or display passwords in plain text; use an appropriate secure authentication approach instead.
5. **Landscape detail layout:** The original instructions specify that this file should be created by adapting the existing detail screen horizontally while retaining its TextView IDs. The exact XML for that landscape detail screen is not specified here.

---

**Project:** StudentFormApp  
**Platform:** Android  
**Implementation:** Java + XML  
**Purpose:** Demonstration of Android UI, Activities, local storage, navigation, lifecycle, and responsive layouts.
