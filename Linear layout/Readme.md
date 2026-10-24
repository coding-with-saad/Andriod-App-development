Android Studio mein LinearLayout Horizontal aur LinearLayout Vertical ka difference sirf itna hai ke ye apne andar mojood views (Button, TextView, ImageView, etc.) ko kis direction mein arrange karte hain.

## 1. LinearLayout Horizontal vs Vertical

### Horizontal

[Linear Layout Tutorial Android Studio Linearlayout Android Examples List Tutlane Will Example Instances Arranged Horizontal Row Child Only So - Tutorial Android](https://images.openai.com/static-rsc-4/t78ciC9nXNHaEEd5pUmIzLMbqmT6lmQ2frtuI7ZE7PDoFlX-wT6Wom1DgFFiwcRZGmwdYGx38XbfdJ-QEBT_m_83zhG4gQh8z5z2xBl6SUnfHsEztwBC-Rz_uwtfy0zFN9fhBUZWaRVjdEDWrpN3lTdPsdiJyS-BomP8rN42ivI?purpose=inline)

Left → Right

Views aik hi row mein side by side aate hain.

```
android:orientation="horizontal"
```

### Vertical

[Android Programming In Kotlin: Layouts](https://images.openai.com/static-rsc-4/ypXEXAsH7ligzYQHJXNhBdhaR13uO9AQ3p8m7ENuxdKvj0h2DV-93VjLQegs5iiKTSc-ujktcaag4UQgkaDmzcCiWv089CInP1VhUW9jqWm4FEJ4gzPExAkdh2ULBLe1jnkTHw031nZv8mkmMF1u-YkM6GgxOm4VC61fH5sB4R8?purpose=inline)

Top → Bottom

Views aik ke neeche aik aate hain.

```
android:orientation="vertical"
```

## 2. XML code example

Horizontal LinearLayout

```
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal">

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="One" />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Two" />

</LinearLayout>
```

Result: `[ One ] [ Two ]`

Vertical LinearLayout

```
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="One" />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Two" />

</LinearLayout>
```

Result:

```
[ One ]

[ Two ]
```

## 3. Important difference

| Feature    | Horizontal                         | Vertical                       |
| ---------- | ---------------------------------- | ------------------------------ |
| Direction  | Left to right                      | Top to bottom                  |
| Main axis  | Horizontal                         | Vertical                       |
| Width      | Views side by side space lete hain | Views alag rows mein hote hain |
| Common use | Buttons in one row                 | Login forms, profile screens   |

Yaad rakhne ka easy trick: Horizontal = side by side; Vertical = one below another.

Ek aur important baat: tum LinearLayout ke andar doosra LinearLayout bhi use kar sakte ho. Is tarah tum complex UI bana sakte ho, jaise login screen mein vertical layout aur uske andar buttons ke liye horizontal layout.