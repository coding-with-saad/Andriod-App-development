# Android App Development — Play Store Publishing
## Theoretical Assignment Notes

> These notes cover the complete theoretical portion of the assignment from Question 1 to Question 10. The language is intentionally simple so the concepts can also be used for future revision.

---

# 1. Assignment Overview

The main purpose of this assignment is to understand the Android application release and publishing process.

The assignment covers:

1. Android app versioning
2. `versionCode` and `versionName`
3. Signed APK/AAB files
4. Java KeyStore (`.jks`)
5. Google Play Developer account
6. Google Play Console publishing workflow
7. Common submission errors and how to avoid them
8. A practical task to generate a signed AAB

The practical application can be very simple. A basic **Hello World** Android app is acceptable.

---

# Part 1 — APK Versioning

## Q1. What are `versionCode` and `versionName`, and why are they important?

`versionCode` and `versionName` are used to identify different releases of an Android application.

### `versionCode`

`versionCode` is a numeric value used by Android and Google Play to compare app releases.

For example:

```text
versionCode = 1
```

For the next release:

```text
versionCode = 2
```

The new release should normally have a higher `versionCode` than the previous release.

### `versionName`

`versionName` is a human-readable version number. It helps users understand which version of the app they are using.

Examples:

```text
"1.0"
"1.1"
"2.0"
```

### Example

First release:

```text
versionCode = 1
versionName = "1.0"
```

Next release:

```text
versionCode = 2
versionName = "1.1"
```

### Simple Difference

- `versionCode` → mainly for Android/Google Play
- `versionName` → mainly for people/users

Both are important because they help identify and manage different app releases.

---

## Q2. What happens if you don't increase the `versionCode` when updating your app?

When an Android application is updated, its new release should have a higher `versionCode`.

For example, suppose the current release is:

```text
versionCode = 1
versionName = "1.0"
```

If the developer creates a new release but keeps:

```text
versionCode = 1
```

Google Play will not treat it as a newer release compared with the existing release. The new release therefore cannot be uploaded as a valid newer version.

A correct update could be:

```text
Old release:
versionCode = 1
versionName = "1.0"

New release:
versionCode = 2
versionName = "1.1"
```

Therefore, the `versionCode` should be increased for a new release.

---

## Q3. Where can you find and edit these values in Android Studio?

The `versionCode` and `versionName` are normally managed in the Android app module's Gradle configuration.

The file may be:

```text
app/build.gradle
```

or:

```text
app/build.gradle.kts
```

They may appear inside the `defaultConfig` section.

Example using Gradle:

```gradle
android {
    defaultConfig {
        applicationId "com.example.myapp"
        minSdk 24
        targetSdk 35

        versionCode 1
        versionName "1.0"
    }
}
```

A Kotlin DSL project can use:

```kotlin
android {
    defaultConfig {
        versionCode = 1
        versionName = "1.0"
    }
}
```

The exact project structure can vary between Android Studio versions and project templates, but the main idea is that the app's Gradle configuration manages the version information.

---

# Part 2 — Generating a Signed Build

## Q4. What is a `.jks` (keystore) file, and why is it critical?

A `.jks` file is a **Java KeyStore**. It is a secure container used to store cryptographic keys and certificates used for signing an Android application.

In simple words:

> A keystore helps provide the digital identity used to sign the app.

When a developer creates a signed APK or AAB, the signing key from the keystore is used to digitally sign the application.

### Why is it important?

Application signing is an important part of Android release management. The signing identity must be properly maintained when releasing future updates.

### Security

The developer should:

- Keep the `.jks` file secure.
- Protect its passwords.
- Keep a secure backup.
- Avoid uploading it to a public repository.
- Give access only to authorized people.

---

## Q5. What's the difference between `.apk` and `.aab` files?

### APK

APK stands for **Android Package**.

An APK is an installable Android application package. It contains the files required to install the application on a compatible Android device.

Example:

```text
myapp-release.apk
```

### AAB

AAB stands for **Android App Bundle**.

An AAB is mainly a publishing and distribution format for Google Play. Google Play uses the bundle to generate and deliver suitable APKs for different devices and configurations.

Example:

```text
myapp-release.aab
```

### Comparison

| APK | AAB |
|---|---|
| Installable Android package | Publishing/distribution bundle |
| Can be installed directly on a compatible device | Not normally installed directly by the user |
| Useful for direct installation and testing | Preferred format for modern Google Play publishing |
| Contains the application package for installation | Allows Google Play to generate optimized APKs |

### Easy way to remember

> **APK = Install**  
> **AAB = Publish**

---

## Q6. Explain step-by-step how to generate a signed release file in Android Studio.

A signed release AAB can generally be generated using the following process.

### Step 1 — Open the project

Open the Android project in Android Studio.

### Step 2 — Open the Build menu

Go to:

```text
Build
    ↓
Generate Signed Bundle / APK
```

### Step 3 — Select Android App Bundle

Select:

```text
Android App Bundle
```

Then continue to the next step.

### Step 4 — Select or create a keystore

Choose an existing keystore or create a new one.

If it is the first release, a new keystore can be created.

### Step 5 — Enter keystore information

Provide the required information, such as:

- Keystore path
- Keystore password
- Key alias
- Key password

### Step 6 — Select the release build

Select the:

```text
release
```

build variant.

### Step 7 — Generate the AAB

Review the configuration and click the appropriate **Finish/Generate** option.

Android Studio will build and sign the application.

The AAB can usually be found in a directory similar to:

```text
app/build/outputs/bundle/release/
```

Example:

```text
app-release.aab
```

### Complete Flow

```text
Android Studio
      ↓
Build
      ↓
Generate Signed Bundle / APK
      ↓
Android App Bundle
      ↓
Create/Select Keystore
      ↓
Release
      ↓
Generate
      ↓
app-release.aab
```

---

## Q7. What precautions should you take with your keystore file and passwords?

The keystore contains sensitive signing information, so it should be protected carefully.

### Important precautions

1. **Keep the keystore secure.**  
   Store the `.jks` file in a secure location.

2. **Do not upload it to a public GitHub repository.**  
   Private signing keys should never be exposed publicly.

3. **Protect the passwords.**  
   Keystore and key passwords should not be placed directly in public source code.

4. **Keep a secure backup.**  
   A backup can help prevent accidental loss.

5. **Limit access.**  
   Only authorized developers should have access.

6. **Do not share credentials publicly.**  
   Passwords should not be posted in chats, screenshots, social media, or other public places.

### Important Rule

> Treat the keystore and its passwords as sensitive release credentials.

---

# Part 3 — Publishing to Google Play

## Q8. What is required to open a Google Play Developer account?

To publish an Android application on Google Play, a developer first needs a Google Play Developer account.

Generally, the developer needs:

- A Google Account
- Developer registration
- Required developer and contact information
- Applicable identity verification
- Required account verification
- Applicable registration fee
- Agreement to Google Play terms and policies

Having a Google Account alone is not enough. The developer must complete the Play Console registration and verification process.

The developer must also follow Google Play Developer Program Policies when using the platform.

---

## Q9. List the major steps involved in publishing an app on the Google Play Console.

The overall publishing process can be understood as:

```text
Developer Account
       ↓
Create App
       ↓
App Information
       ↓
Store Listing
       ↓
App Content
       ↓
Upload AAB
       ↓
Testing
       ↓
Production Release
       ↓
Google Review
       ↓
Published
```

### Step 1 — Create the developer account

Set up the Google Play Developer account and complete the required verification.

### Step 2 — Create the application

Create a new app in the Google Play Console.

### Step 3 — Provide app information

Enter the required basic information about the application.

### Step 4 — Complete the store listing

Provide information such as:

- App name
- Short description
- Full description
- App icon
- Screenshots
- Category

### Step 5 — Complete app content information

Provide the declarations and information required by Google Play for the application.

### Step 6 — Prepare the signed AAB

Generate a signed Android App Bundle using Android Studio.

### Step 7 — Upload the AAB

Upload the AAB to the appropriate testing or release track.

### Step 8 — Testing

Complete the required testing and resolve any problems.

### Step 9 — Create a production release

Prepare the final production release.

### Step 10 — Review and publishing

Submit the application for review. If it meets the required technical and policy requirements, it can become available on Google Play.

---

## Q10. What common errors or rejections might occur during app submission, and how can they be avoided?

Several technical and policy-related problems can happen during app submission.

### 1. Incorrect `versionCode`

If the new release does not have a higher `versionCode`, the upload can fail.

**How to avoid it:** Increase the `versionCode` for every new release.

---

### 2. Signing problems

Incorrect signing configuration or keystore problems can cause release issues.

**How to avoid it:** Use the correct keystore and signing configuration.

---

### 3. Missing store information

Required information such as descriptions, screenshots, icons, or other listing details may be missing.

**How to avoid it:** Complete all required Play Console fields before submission.

---

### 4. Policy violations

An app can be rejected if it violates Google Play policies.

Examples can include:

- Misleading content
- Restricted content
- Privacy problems
- Improper use of permissions or user data

**How to avoid it:** Review the relevant Google Play policies before submission.

---

### 5. Incorrect privacy or data declarations

If an app collects or uses user data and the developer provides incorrect or incomplete information, the submission may face problems.

**How to avoid it:** Accurately describe how the application collects, uses, and handles user data.

---

### 6. App crashes or technical problems

An app that crashes or does not work correctly can create problems during testing or review.

**How to avoid it:** Test the application properly before submission.

---

### 7. Failure to meet technical requirements

Google Play has technical requirements for Android applications, including requirements related to Android versions and target API levels.

**How to avoid it:** Check the current Google Play technical requirements before preparing the final release.

---

# Quick Revision

## `versionCode`

A numeric release number mainly used by Android and Google Play.

```text
1 → 2 → 3 → 4
```

## `versionName`

A human-readable version.

```text
1.0 → 1.1 → 2.0
```

## `.jks`

A Java KeyStore that stores cryptographic keys and certificates used for app signing.

## APK

An installable Android application package.

> **APK = Install**

## AAB

An Android App Bundle mainly used for publishing and distribution through Google Play.

> **AAB = Publish**

## Signed Build

A release build that has been digitally signed using a signing key.

## Google Play Console

The platform used by developers to manage, test, review, and publish Android applications on Google Play.

---

# Practical Task

After completing the theory, the practical task is to:

1. Create a small Android application.
2. Set a `versionCode`.
3. Set a `versionName`.
4. Generate a signed AAB.
5. Take screenshots if required:
   - Gradle version settings
   - Generate Signed Bundle window
   - Generated `.aab` file
6. Explain the practical work in the LaTeX report.

A basic **Hello World** application is enough.

The assignment does **not** require actual publication on Google Play. The goal is to demonstrate understanding of the publishing process.

---

# Final Mental Model

The complete process can be remembered like this:

```text
Android App
     ↓
Versioning
     ↓
versionCode + versionName
     ↓
Release Build
     ↓
Keystore (.jks)
     ↓
Signed AAB
     ↓
Google Play Console
     ↓
Store Listing + App Content
     ↓
Testing
     ↓
Review
     ↓
Publication
```

The most important idea is:

> **First understand the release process, then perform the same process practically in Android Studio.**
