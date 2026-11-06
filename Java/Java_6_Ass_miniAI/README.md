# Fuel Consumption Predictor

An Android application that uses Machine Learning and TensorFlow Lite to predict vehicle fuel efficiency in Miles Per Gallon (MPG).

## Project Overview

Fuel Consumption Predictor allows users to enter vehicle specifications and receive an estimated fuel efficiency prediction.

The application uses a neural network trained on the UCI Auto MPG dataset. The trained model is converted into TensorFlow Lite format and integrated into the Android application.

## Features

- Predicts vehicle fuel efficiency in MPG.
- Uses a trained Machine Learning model.
- Runs predictions directly inside the Android application.
- Simple and user-friendly interface.
- Input validation and error handling.

## Technologies Used

| Technology | Purpose |
|---|---|
| Python | Model training |
| TensorFlow | Neural network |
| TensorFlow Lite | Mobile model deployment |
| Java | Android application logic |
| XML | User interface |
| Android Studio | Android development |
| Pandas & NumPy | Dataset processing |

## Machine Learning Dataset

**Dataset:** UCI Auto MPG Dataset

The model uses four vehicle features:

1. Displacement (cubic inches)
2. Cylinders
3. Horsepower
4. Vehicle Weight (pounds)

**Target:** Fuel Efficiency (MPG)

## Project Structure

```text
FuelConsumptionPredictor/
│
├── app/
│   └── src/main/
│       ├── assets/
│       │   └── fuel_consumption.tflite
│       ├── java/
│       │   └── com/example/fuelconsumptionpredictor/
│       │       └── MainActivity.java
│       ├── res/
│       │   └── layout/
│       │       └── activity_main.xml
│       └── AndroidManifest.xml
│
├── ml/
│   ├── artifacts/
│   │   └── fuel_consumption.tflite
│   ├── train_model.py
│   └── requirements.txt
│
├── build.gradle
├── settings.gradle
└── README.md
```

## Model Training

Install the required Python libraries:

```bash
pip install -r ml/requirements.txt
```

Run the training script from the project root:

```bash
python ml/train_model.py
```

The script downloads the dataset, preprocesses the data, trains a neural network, evaluates the model, and converts it into TensorFlow Lite format.

The generated model is saved in:

```text
ml/artifacts/fuel_consumption.tflite
app/src/main/assets/fuel_consumption.tflite
```

## Running the Android Application

1. Open the project in Android Studio.
2. Wait for Gradle Sync to complete.
3. Select an Android emulator or connected device.
4. Run the application.
5. Enter the vehicle specifications.
6. Press the prediction button to get the estimated MPG.

## Testing

The application was tested successfully.

- Application launches successfully.
- TensorFlow Lite model loads correctly.
- Vehicle specifications are accepted.
- Fuel efficiency predictions are generated.
- Predictions produce reasonable MPG values.

## Expected Output

The application displays an estimated fuel efficiency value in MPG based on the entered vehicle specifications.

## Future Improvements

- Improve model accuracy through additional experiments.
- Add more vehicle specifications.
- Improve input validation.
- Add prediction history.
- Enhance the user interface.

## Author

Malik Saad Khawar

Computer Science Student

## Dataset Reference

UCI Machine Learning Repository: Auto MPG Dataset

https://archive.ics.uci.edu/dataset/9/auto+mpg