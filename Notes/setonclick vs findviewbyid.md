In Android Studio, `findViewById()` and `setOnClickListener()` have different jobs.

## 1. Difference in simple words

| `findViewById()`                         | `setOnClickListener()`                     |
| ---------------------------------------- | ------------------------------------------ |
| Finds a view, like a Button or TextView. | Performs an action when a view is clicked. |
| Connects your Java code to an XML view.  | Handles the user's click.                  |
| Example: Find the Roll button.           | Example: Roll the dice when clicked.       |

Easy trick to remember:

- `findViewById()` = Find the button
- `setOnClickListener()` = Tell the button what to do

## 2. Example in Android Studio

Suppose your XML layout has a button:

```
<Button
    android:id="@+id/rollButton"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Roll Dice" />
```

### Step 1: `findViewById()`

```
Button rollButton = findViewById(R.id.rollButton);
```

What happens?

Android finds the button with ID `rollButton` in the XML layout and connects it to the Java variable `rollButton`.

### Step 2: `setOnClickListener()`

```
rollButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(
            MainActivity.this,
            "Dice Rolled!",
            Toast.LENGTH_SHORT
        ).show();
    }
});
```

What happens?

When the user clicks the button, the message `"Dice Rolled!"` appears on the screen.

## 3. Complete example

Both methods work together:

```
Button rollButton = findViewById(R.id.rollButton);

rollButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(
            MainActivity.this,
            "Dice Rolled!",
            Toast.LENGTH_SHORT
        ).show();
    }
});
```

1\. `findViewById()`

Finds the Roll Dice button.

2\. `setOnClickListener()`

Waits for the user to click the button.

3\. Action happens

Displays the Dice Rolled! message.

## 4. Viva question

Q: What is the difference between `findViewById()` and `setOnClickListener()`?

Answer: `findViewById()` is used to find a view by its ID in the XML layout, whereas `setOnClickListener()` is used to perform an action when the user clicks that view.