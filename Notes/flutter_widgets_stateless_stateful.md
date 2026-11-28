# Flutter Widgets: StatelessWidget and StatefulWidget

## 1. What is a Widget?

A **Widget** is a building block of a Flutter application.

Everything you see on a Flutter app screen is made using widgets.

Examples:

-   `Text` -- displays text.
-   `Image` -- displays a picture.
-   `Icon` -- displays an icon.
-   `ElevatedButton` -- creates a button.
-   `Container` -- provides a box or layout area.
-   `TextField` -- allows the user to enter text.

**Easy example:** Think of building a house. Bricks are used to build
the house. In Flutter, widgets are used to build the app.

## 2. What is StatelessWidget?

A `StatelessWidget` is a widget that does not manage changing data
(state) of its own.

Its UI is based on the information it receives. If that information
changes, Flutter can create the widget again with the new information,
but the widget itself does not manage changing state.

### Example

``` dart
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Text("Hello World");
  }
}
```

This example displays `Hello World`.

Common examples:

-   App logo
-   Static text
-   App title
-   Simple icons

## 3. What is StatefulWidget?

A `StatefulWidget` is used when a part of the screen needs to change
over time.

It has a separate `State` object where changing data is stored.

### Example: Counter

``` dart
class MyCounter extends StatefulWidget {
  @override
  State<MyCounter> createState() {
    return _MyCounterState();
  }
}

class _MyCounterState extends State<MyCounter> {
  int count = 0;

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Text("Count: $count"),
        ElevatedButton(
          onPressed: () {
            setState(() {
              count++;
            });
          },
          child: Text("Increase"),
        ),
      ],
    );
  }
}
```

When the user presses the button:

-   First click: count becomes 1.
-   Second click: count becomes 2.
-   Third click: count becomes 3.

### What does setState() do?

`setState()` tells Flutter that the state has changed and the UI should
be rebuilt to show the updated value.

## 4. Difference Between StatelessWidget and StatefulWidget

  -----------------------------------------------------------------------
  StatelessWidget                     StatefulWidget
  ----------------------------------- -----------------------------------
  Does not manage changing state of   Manages changing state through a
  its own.                            separate State object.

  Suitable for UI that does not need  Suitable for UI that changes in
  to change by itself.                response to user actions or other
                                      events.

  Does not use `setState()` itself.   Its State object can use
                                      `setState()`.

  Example: a fixed heading.           Example: a counter.

  Simpler structure.                  Has a widget class and a separate
                                      State class.
  -----------------------------------------------------------------------

## 5. Quick Real-Life Examples

  Situation                                   Widget type
  ------------------------------------------- -----------------
  Displaying the app name                     StatelessWidget
  Showing a fixed logo                        StatelessWidget
  Increasing a counter                        StatefulWidget
  Like button that changes when tapped        StatefulWidget
  Checkbox that can be checked or unchecked   StatefulWidget
  Showing a fixed welcome message             StatelessWidget

## 6. Easy Way to Remember

-   **Widget:** A building block of the Flutter UI.
-   **StatelessWidget:** Does not manage changing state of its own.
-   **StatefulWidget:** Manages changing state using a separate State
    object.
-   **setState():** Tells Flutter to rebuild the UI after the state
    changes.

**Remember:** Use StatelessWidget when the widget does not need to
manage changing state. Use StatefulWidget when it does.
