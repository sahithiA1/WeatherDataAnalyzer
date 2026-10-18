# Weather Data Analyzer

## Project Overview

Weather Data Analyzer is a Java application that reads weather information from a CSV file and analyzes temperature, humidity, and precipitation data.

The project demonstrates modern Java programming features, including records, enhanced switch expressions, text blocks, lambdas, streams, and Markdown Javadoc. It follows an interface-based design without explicitly declaring Java classes.

The application includes both a command-line analyzer and a colorful JavaFX graphical interface.

## Features

- Read and parse weather data from a CSV file.
- Calculate the average temperature for a selected month.
- Find days with temperatures above a specified threshold.
- Count rainy days based on precipitation values greater than zero.
- Categorize temperatures as Hot, Warm, or Cold.
- Filter weather records by month.
- Display weather statistics in an interactive JavaFX dashboard.

## Technologies Used

- Java 25
- Maven
- JavaFX
- CSS
- Java Records
- Enhanced Switch Expressions
- Java Text Blocks
- Lambda Expressions
- Java Stream API
- Markdown Javadoc

## Project Structure

```text
WeatherDataAnalyzer/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/
        │   └── org/example/weatherdata/
        │       ├── Main.java
        │       ├── WeatherData.java
        │       ├── WeatherCSVReader.java
        │       ├── WeatherAnalyzer.java
        │       ├── WeatherCategory.java
        │       └── WeatherDashboard.java
        └── resources/
            ├── weatherdata.csv
            └── style.css
```

## CSV Data Format

Weather information is stored in `src/main/resources/weatherdata.csv`.

Example:

```csv
Date,Temperature,Humidity,Precipitation
2023-08-01,32.5,65,0.0
2023-08-02,35.0,60,0.2
```

Each record includes a date, temperature in Celsius, humidity percentage, and precipitation amount.

A day is considered rainy when its precipitation is greater than zero.

## Modern Java Features

### Java Records

The `WeatherData` record stores individual weather observations without requiring a traditional Java class.

### Enhanced Switch Expressions

`WeatherCategory` uses an enhanced switch expression to classify temperatures:

- Hot: 30°C or above
- Warm: 20°C to below 30°C
- Cold: Below 20°C

### Java Text Blocks

`Main.java` uses Java text blocks to generate a formatted weather analysis report.

### Lambdas and Streams

The application uses lambda expressions and Java streams to filter weather records, calculate averages, and count rainy days.

### Modern Java Documentation

The source code uses Markdown documentation comments (`///`), supported in JDK 23 and later, to document interfaces and methods.

### Interface-Based Design

The application uses records, interfaces with static methods, and functional constructs without explicitly declaring traditional Java classes.

## How to Run the Application

### Requirements

- JDK 25
- Apache Maven
- IntelliJ IDEA or another Java-compatible IDE

### Run the JavaFX Dashboard

1. Open the project in IntelliJ IDEA.
2. Allow Maven to download and load the dependencies.
3. Open the Maven tool window.
4. Expand **Plugins