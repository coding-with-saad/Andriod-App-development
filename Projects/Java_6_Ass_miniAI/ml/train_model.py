
import os
from pathlib import Path

import numpy as np
import pandas as pd
import tensorflow as tf


# Reproducibility
np.random.seed(42)
tf.random.set_seed(42)


# Dataset URL
DATASET_URL = (
    "https://archive.ics.uci.edu/"
    "ml/machine-learning-databases/auto-mpg/auto-mpg.data"
)

# Project paths
PROJECT_ROOT = Path(__file__).resolve().parent.parent

ARTIFACTS_DIR = PROJECT_ROOT / "ml" / "artifacts"
ANDROID_ASSETS_DIR = (
    PROJECT_ROOT / "app" / "src" / "main" / "assets"
)

MODEL_NAME = "fuel_consumption.tflite"


def load_dataset():

    print("Downloading Auto MPG dataset...")

    column_names = [
        "mpg",
        "cylinders",
        "displacement",
        "horsepower",
        "weight",
        "acceleration",
        "model_year",
        "origin",
        "car_name"
    ]

    data = pd.read_csv(
        DATASET_URL,
        sep=r"\s+",
        header=None,
        names=column_names,
        na_values="?",
        quotechar='"',
        engine="c"
    )

    # Convert required columns to numeric
    features = [
        "displacement",
        "cylinders",
        "horsepower",
        "weight"
    ]

    for column in features + ["mpg"]:
        data[column] = pd.to_numeric(
            data[column],
            errors="coerce"
        )

    # Remove missing values
    data = data.dropna(subset=features + ["mpg"])

    # Input features in fixed order
    X = data[features].to_numpy(dtype=np.float32)

    # Target: Miles Per Gallon
    y = data["mpg"].to_numpy(dtype=np.float32)

    print(f"Dataset loaded successfully: {len(data)} records")

    return X, y


def build_model(X_train):

    # Normalization layer
    normalizer = tf.keras.layers.Normalization()

    normalizer.adapt(X_train)

    # Neural network
    model = tf.keras.Sequential([
        tf.keras.layers.Input(shape=(4,)),
        normalizer,
        tf.keras.layers.Dense(32, activation="relu"),
        tf.keras.layers.Dense(16, activation="relu"),
        tf.keras.layers.Dense(1)
    ])

    model.compile(
        optimizer="adam",
        loss="mean_squared_error",
        metrics=["mean_absolute_error"]
    )

    return model


def train_model(X, y):

    print("\nPreparing training and testing data...")

    # Shuffle dataset
    indices = np.random.permutation(len(X))

    X = X[indices]
    y = y[indices]

    # 80% training, 20% testing
    split_index = int(len(X) * 0.8)

    X_train = X[:split_index]
    X_test = X[split_index:]

    y_train = y[:split_index]
    y_test = y[split_index:]

    print(f"Training records: {len(X_train)}")
    print(f"Testing records: {len(X_test)}")

    print("\nBuilding model...")

    model = build_model(X_train)

    print("\nTraining model...")

    model.fit(
        X_train,
        y_train,
        epochs=100,
        batch_size=16,
        validation_split=0.2,
        verbose=1
    )

    print("\nEvaluating model...")

    loss, mae = model.evaluate(
        X_test,
        y_test,
        verbose=0
    )

    print(f"Test MAE: {mae:.2f} MPG")

    return model


def convert_to_tflite(model):

    print("\nConverting model to TensorFlow Lite...")

    converter = tf.lite.TFLiteConverter.from_keras_model(model)

    tflite_model = converter.convert()

    # Create directories
    ARTIFACTS_DIR.mkdir(parents=True, exist_ok=True)
    ANDROID_ASSETS_DIR.mkdir(parents=True, exist_ok=True)

    # Save TFLite model
    artifacts_path = ARTIFACTS_DIR / MODEL_NAME
    android_path = ANDROID_ASSETS_DIR / MODEL_NAME

    with open(artifacts_path, "wb") as file:
        file.write(tflite_model)

    with open(android_path, "wb") as file:
        file.write(tflite_model)

    print("\nModel saved successfully!")
    print(f"Training model: {artifacts_path}")
    print(f"Android model: {android_path}")
    print(f"Model size: {len(tflite_model) / 1024:.2f} KB")


def main():

    print("================================")
    print("   Fuel Consumption Predictor")
    print("================================")

    X, y = load_dataset()

    model = train_model(X, y)

    convert_to_tflite(model)

    print("\nTraining and conversion completed!")


if __name__ == "__main__":
    main()
