Android Studio mein `match_parent` aur `wrap_content` ka difference yeh hai ke view apne parent ki available space fill karega ya sirf apne content jitni space lega.

## 1. `match_parent` vs `wrap_content`

[java - Qual é a função do wrap_content? - Stack Overflow em Português](https://images.openai.com/static-rsc-4/btIZcGl3vVQED5QGGqJ1WDZAPEd9YTZIpBzq4QDApzgF2_UrgBMJPPn8ObN8-fYZB3l0xxZP_hOobUkduCnfsm0t9xhbwDnPM571HAx5aIhkR_8Sk1A5QJ2D6w8NXdq5AYAN-m0420uoBvcasOONUwPhqOSwsLhhTqVcKS7OMB8?purpose=inline)

`match_parent`

Parent ki available width ya height ko fill karta hai.

[android - How to use weight in a Button to align Text on the left and Icon on the right - Stack Overflow](https://images.openai.com/static-rsc-4/Yy_ZS3S3vEfQ3N18YMm1EKUS0SGQCql8P5R1YAmxP48JfHd7j1S7Ph3-3kbFtCnuTkftXZqc_sNnHlThChjF3Z4lXf_GznIa7hEabramFjJpV6DrlEflPuz6QDli4daYyTDLFdg1jcDmC8d8yv-RpDjRF-l_mjki0zte9kY7PCU?purpose=inline)

`wrap_content`

Apne content ke mutabiq sirf required space leta hai.

## 2. XML code example

A. `match_parent`

```
<Button
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Login" />
```

Result: Button parent ki available poori width le lega, lekin height content ke mutabiq hogi.

B. `wrap_content`

```
<Button
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Login" />
```

Result: Button ki width aur height uske text aur required padding ke mutabiq hogi.

## 3. Comparison table

| Property | `match_parent`             | `wrap_content`                  |
| -------- | -------------------------- | ------------------------------- |
| Width    | Parent ki available width  | Content ke mutabiq              |
| Height   | Parent ki available height | Content ke mutabiq              |
| Example  | Full-width Login button    | Chhota Login button             |
| Use      | Jab space fill karni ho    | Jab content jitni space chahiye |

## 4. Viva ke liye short definitions

- Match Parent: The view occupies all the available space provided by its parent in the specified dimension.
- Wrap Content: The view occupies only the space required to display its content, including necessary padding.

Important: `match_parent` ka matlab poori screen nahi hota; iska matlab parent ki available space hota hai. Aur width aur height dono separately set ki ja sakti hain.

مثال: `layout_width="match_parent"` aur `layout_height="wrap_content"` ka matlab hai full available width, lekin content ke mutabiq height.