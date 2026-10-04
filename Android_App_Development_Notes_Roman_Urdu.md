# Android App Development — Play Store Publishing Notes
## Roman Urdu Study Notes

> Ye notes Mobile App Development assignment ke theoretical portion ke liye hain. Is mein Q1 se Q10 tak tamam concepts simple Roman Urdu mein explain kiye gaye hain.

---

# Assignment Overview

Is assignment ka main purpose sirf Android app banana nahi hai. Humein Android app ke release process ko samajhna hai:

1. App ki versioning samajhna
2. Signed APK/AAB banana
3. Keystore ko samajhna aur secure rakhna
4. Google Play Console ka publishing workflow samajhna
5. In concepts ko LaTeX technical report mein explain karna

Practical part mein ek basic Android app, hatta ke **Hello World** app bhi acceptable hai.

---

# Part 1 — APK Versioning

## Q1. What are `versionCode` and `versionName`, and why are they important?

### Simple Explanation

Android app ke har release ko identify karne ke liye `versionCode` aur `versionName` use hote hain.

### `versionCode`

`versionCode` ek **numeric value** hoti hai jo Android aur Google Play ko batati hai ke app ka kaunsa release newer hai.

Example:

```text
versionCode = 1
```

Next update:

```text
versionCode = 2
```

Jab app ka new release upload karte hain to normally `versionCode` previous release se higher hona chahiye.

### `versionName`

`versionName` user-friendly version number hota hai jo humans ke liye hota hai.

Examples:

```text
versionName = "1.0"
versionName = "1.1"
versionName = "2.0"
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

Yahan:

- `versionCode` → system ke liye
- `versionName` → user ko version samajhne ke liye

### Important Point

Dono ka purpose different hai. Sirf `versionName` change karna enough nahi hota; new release ke liye appropriate higher `versionCode` bhi chahiye.

---

## Q2. What happens if you don't increase the `versionCode` when updating your app?

### Simple Explanation

Suppose first release:

```text
versionCode = 1
versionName = "1.0"
```

Ab hum app update karte hain. Agar new release mein bhi:

```text
versionCode = 1
```

rakhein, to Google Play usay previous release se newer release nahi samjhega.

Is wajah se new release upload nahi ho sakta.

Correct example:

```text
Old:
versionCode = 1
versionName = "1.0"

New:
versionCode = 2
versionName = "1.1"
```

### Important Point

New release ke liye `versionCode` ko increase karna zaroori hai.

---

## Q3. Where can you find and edit `versionCode` and `versionName` in Android Studio?

### Simple Explanation

Android Studio mein ye values normally **app module ki Gradle configuration** mein hoti hain.

File usually:

```text
app/build.gradle
```

ya:

```text
app/build.gradle.kts
```

Ho sakti hai.

Gradle configuration mein ye values `defaultConfig` ke andar mil sakti hain.

Example:

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

Kotlin DSL project mein syntax different ho sakti hai:

```kotlin
android {
    defaultConfig {
        versionCode = 1
        versionName = "1.0"
    }
}
```

### Simple Rule

Android Studio → `app` → Gradle configuration → `defaultConfig`

Yahan version information check/edit ki ja sakti hai.

> Note: New Android Studio projects mein exact Gradle structure different ho sakta hai, lekin basic concept app ki Gradle configuration mein version information manage karna hai.

---

# Part 2 — Generating a Signed Build

## Q4. What is a `.jks` (keystore) file, and why is it critical?

### Simple Explanation

`.jks` ka matlab **Java KeyStore** hai.

Ye ek secure file hoti hai jisme app ko digitally sign karne ke liye cryptographic keys aur certificates store hote hain.

Simple words mein:

> **Keystore app ki digital identity/security container hai.**

Jab hum signed APK ya AAB banate hain, Android Studio keystore ki key ko use karke app ko sign karta hai.

### Ye important kyun hai?

App ki signing identity ko future releases mein bhi maintain karna important hota hai. Isliye keystore aur required credentials ko lose nahi karna chahiye.

### Example

Agar tumhari app ka release keystore se sign hua hai, to future updates ke signing setup ko bhi properly maintain karna hoga.

### Security

- `.jks` ko safe rakho
- Password safe rakho
- Public GitHub repository mein upload na karo
- Secure backup rakho
- Unauthorized logon ke saath share na karo

---

## Q5. What's the difference between `.apk` and `.aab` files?

### APK

`.apk` ka matlab **Android Package** hai.

APK ek installable Android application package hota hai.

Example:

```text
myapp-release.apk
```

Isay compatible Android device par install kiya ja sakta hai.

### AAB

`.aab` ka matlab **Android App Bundle** hai.

Ye mainly Google Play par app distribute karne ke liye publishing format hai.

Example:

```text
myapp-release.aab
```

Google Play AAB ko use karke different devices ke liye suitable APKs generate/deliver kar sakta hai.

### Simple Comparison

| APK | AAB |
|---|---|
| Installable application package | Publishing/distribution bundle |
| Device par directly install ho sakta hai | Normally direct installation ke liye nahi |
| Sideloading ke liye useful | Google Play publishing ke liye preferred |
| Complete APK package | Google Play optimized APKs generate karta hai |

### Yaad rakhne ka simple tareeqa

> **APK = Install**  
> **AAB = Publish**

---

## Q6. Explain step-by-step how to generate a signed release file in Android Studio.

### Simple Explanation

Signed AAB generate karne ka general process:

### Step 1 — Android project open karo

Android Studio mein apna project open karo.

### Step 2 — Build menu

Top menu se:

```text
Build
    ↓
Generate Signed Bundle / APK
```

### Step 3 — Android App Bundle select karo

Select:

```text
Android App Bundle
```

Phir Next karo.

### Step 4 — Keystore select ya create karo

Agar existing keystore hai to select karo.

Agar first time hai to new keystore create kar sakte ho.

### Step 5 — Keystore information

Required information provide karo:

- Keystore path
- Keystore password
- Key alias
- Key password

### Step 6 — Release variant

Normally:

```text
release
```

select karo.

### Step 7 — Generate

Configuration check karne ke baad Finish/Generate karo.

Android Studio signed AAB create karega.

AAB commonly is directory mein mil sakti hai:

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

### Simple Explanation

Keystore sensitive hota hai, isliye isay carefully protect karna chahiye.

### Important precautions

#### 1. Keystore secure rakho

`.jks` file ko safe location par rakho.

#### 2. GitHub par upload na karo

Keystore ko public repository mein commit nahi karna chahiye.

#### 3. Password secure rakho

Keystore aur key passwords ko source code mein directly hard-code nahi karna chahiye.

#### 4. Backup rakho

Secure backup zaroor rakho.

#### 5. Access control

Sirf authorized people ko access do.

#### 6. Credentials share na karo

Passwords ko public chats, screenshots, social media, etc. par share na karo.

### Important Rule

> **Keystore + passwords = sensitive release credentials**

---

# Part 3 — Publishing to Google Play

## Q8. What is required to open a Google Play Developer account?

### Simple Explanation

Google Play par app publish karne ke liye pehle **Google Play Developer account** chahiye.

Generally required cheezen:

- Google Account
- Developer registration
- Required developer/contact information
- Applicable identity verification
- Required account verification
- Applicable registration fee
- Google Play policies aur terms ko follow karna

### Important

Google Account hona alone enough nahi hai.

Developer ko Play Console ka registration process complete karna hota hai.

---

## Q9. List the major steps involved in publishing an app on the Google Play Console.

### Simple Publishing Flow

```text
Google Account / Developer Account
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

### Step-by-step

#### 1. Developer Account

Google Play Developer account create/setup karo.

#### 2. Create App

Play Console mein new app create karo.

#### 3. App Information

Basic information provide karo.

#### 4. Store Listing

Information add karo, for example:

- App name
- Short description
- Full description
- App icon
- Screenshots
- Category

#### 5. App Content

Required declarations aur information complete karo.

#### 6. Prepare AAB

Android Studio se signed AAB generate karo.

#### 7. Upload AAB

AAB ko Play Console ke suitable testing/release track mein upload karo.

#### 8. Testing

Required testing complete karo aur issues resolve karo.

#### 9. Production Release

Final production release prepare karo.

#### 10. Review and Publish

Google review ke baad, agar app requirements aur policies fulfill karti hai, to app Google Play par available ho sakti hai.

---

## Q10. What common errors or rejections might occur during app submission, and how can they be avoided?

### Common Problems

#### 1. Incorrect `versionCode`

Agar new release ka `versionCode` previous release se higher nahi hai, to upload issue aa sakta hai.

**Avoid:** New release mein appropriate higher `versionCode` use karo.

---

#### 2. Signing Problems

Wrong signing configuration ya keystore issue ho sakta hai.

**Avoid:** Correct keystore aur signing configuration use karo.

---

#### 3. Missing Store Information

Required description, screenshots, icon ya other information missing ho sakti hai.

**Avoid:** Play Console ki required fields complete karo.

---

#### 4. Policy Violations

App Google Play policies violate kar sakti hai.

Examples:

- Misleading content
- Restricted content
- Privacy issues
- Improper permissions/data use

**Avoid:** Relevant Google Play policies ko submission se pehle check karo.

---

#### 5. Incorrect Privacy/Data Information

Agar app user data collect/use karti hai aur information incorrectly declare ki gayi hai to issue ho sakta hai.

**Avoid:** App ke actual data collection aur use ko accurately declare karo.

---

#### 6. App Crashes

Agar app testing mein crash karti hai ya important feature work nahi karta to problem ho sakti hai.

**Avoid:** Submission se pehle app ko properly test karo.

---

#### 7. Technical Requirements

Google Play Android apps ke liye current technical requirements rakhta hai, including supported Android versions aur target API requirements.

**Avoid:** Release se pehle current technical requirements check karo.

---

# Quick Revision

## `versionCode`

System ke liye numeric release number.

```text
1 → 2 → 3 → 4
```

## `versionName`

User-friendly version.

```text
1.0 → 1.1 → 2.0
```

## `.jks`

App signing keys/certificates ko store karne wali secure keystore file.

## APK

Installable Android package.

> APK = Install

## AAB

Google Play publishing/distribution bundle.

> AAB = Publish

## Signed Build

App ko release ke liye cryptographic key se sign kiya jata hai.

## Google Play Console

Android apps ko Google Play par manage, test, review aur publish karne ka platform.

---

# Assignment ka Practical Task

Theory ke baad practical mein humein:

1. Basic Android application create karni hai.
2. `versionCode` assign karna hai.
3. `versionName` assign karna hai.
4. Signed release AAB generate karni hai.
5. Optional screenshots lene hain:
   - Gradle version settings
   - Generate Signed Bundle window
   - Generated `.aab` file
6. Final LaTeX report mein practical process explain karna hai.

> Google Play par actual publishing required nahi hai. Sirf publishing process ko samajhna aur demonstrate karna hai.

---

# Final Mental Model

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

**Sabse important cheez:** pehle concepts ko samjho, phir practical mein exactly yehi flow Android Studio mein perform karenge.
