# Android SharedPreferences with Kotlin

## What Is SharedPreferences?

`SharedPreferences` is Android's traditional key-value storage mechanism
for small pieces of application data, such as:

-   Theme preference.
-   Whether onboarding is complete.
-   Small UI settings.
-   A simple counter or last-selected option.

It is not a replacement for a database designed for large datasets,
complex queries, or relational records.

## How It Works

``` text
User changes a setting
        |
        v
Android application
        |
        v
SharedPreferences
Key: "dark_mode"
Value: true
        |
        v
App starts again
        |
        v
Read the saved value and restore the setting
```

## Supported Value Types

Common types include `String`, `int`, `long`, `float`, `boolean`, and
`Set<String>`.

## Complete Kotlin Example: Persistent Counter

This example creates a counter with **Increase Counter** and **Reset
Counter** buttons. The value is saved and restored when the app is
reopened.

Create a Kotlin Android project configured with AndroidX AppCompat. Put
the code below in `MainActivity.kt`, adjusting the package name to match
your project.

``` kotlin
package com.example.sharedpreferencesdemo

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var counterText: TextView
    private lateinit var preferences:
        android.content.SharedPreferences

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Initialize SharedPreferences
        preferences = getSharedPreferences(
            "MyAppPreferences",
            MODE_PRIVATE
        )

        // 2. Load the saved value; use 0 if no value exists
        counter = preferences.getInt("counter", 0)

        // 3. Create a simple user interface
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 60, 40, 40)
        }

        counterText = TextView(this).apply {
            textSize = 24f
        }

        val increaseButton = Button(this).apply {
            text = "Increase Counter"
        }

        val resetButton = Button(this).apply {
            text = "Reset Counter"
        }

        layout.addView(counterText)
        layout.addView(increaseButton)
        layout.addView(resetButton)

        setContentView(layout)

        updateCounterText()

        // 4. Increase and save the counter
        increaseButton.setOnClickListener {
            counter++

            preferences.edit()
                .putInt("counter", counter)
                .apply()

            updateCounterText()
        }

        // 5. Reset and save the counter
        resetButton.setOnClickListener {
            counter = 0

            preferences.edit()
                .putInt("counter", counter)
                .apply()

            updateCounterText()
        }
    }

    private fun updateCounterText() {
        counterText.text = "Counter: $counter"
    }
}
```

## Step-by-Step Explanation

### 1. Open the preferences file

``` kotlin
preferences = getSharedPreferences(
    "MyAppPreferences",
    MODE_PRIVATE
)
```

-   `MyAppPreferences` is the preferences file name.
-   `MODE_PRIVATE` stores the preferences in app-private storage under
    normal Android sandbox rules.
-   Private storage does **not** mean that the contents are encrypted.

### 2. Read the saved value

``` kotlin
counter = preferences.getInt("counter", 0)
```

-   `"counter"` is the key.
-   `0` is the default if the key is missing.
-   If a saved integer exists, that value is returned.

### 3. Save the value

``` kotlin
preferences.edit()
    .putInt("counter", counter)
    .apply()
```

-   `edit()` creates an editor.
-   `putInt()` stages an integer value under a key.
-   `apply()` updates the in-memory preferences immediately and writes
    the changes to disk asynchronously.

### 4. Reset the value

``` kotlin
preferences.edit()
    .putInt("counter", 0)
    .apply()
```

This stores `0` under the counter key.

## How to Test

1.  Run the app on an emulator or Android device.
2.  Press **Increase Counter** several times.
3.  Close and reopen the app.
4.  Confirm the saved value is restored.
5.  Press **Reset Counter**.
6.  Reopen the app and confirm the value remains `0`.

Clearing app data or uninstalling the app generally removes local
preferences.

## SharedPreferences vs DataStore

Jetpack **DataStore** is often recommended for new Android projects. It
supports asynchronous operations and integrates with Kotlin coroutines
and Flow.

  -----------------------------------------------------------------------
  SharedPreferences                   Jetpack DataStore
  ----------------------------------- -----------------------------------
  Traditional key-value preferences   Modern data-storage solution
  API                                 

  Simple synchronous-style read API   Asynchronous Flow-based reads

  Uses an editor and `apply()`        Uses coroutine-based updates

  Useful for understanding existing   Often a good choice for new
  Android projects                    settings-storage code
  -----------------------------------------------------------------------

## Security Warning

Do not store passwords, authentication tokens, or sensitive personal
information as plain text in `SharedPreferences`. App-private storage is
not the same as encryption. Sensitive information needs an appropriate
security design and protected storage.

## Quick Revision

1.  What is SharedPreferences?
2.  What does `getInt("counter", 0)` do?
3.  What is the role of `putInt()`?
4.  What does `apply()` do?
5.  Does `MODE_PRIVATE` mean the preferences are encrypted?
6.  When might DataStore be preferable?
