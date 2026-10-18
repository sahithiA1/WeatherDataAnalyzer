package org.example.weatherdata;

import java.time.LocalDate;

/// Represents weather information for a single day.
///
/// Stores the date, temperature, humidity, and precipitation
/// using a Java record instead of an explicit class.
///
/// Example:
/// ```java
/// WeatherData day = new WeatherData(
///     LocalDate.of(2023, 8, 1),
///     32.5,
///     65,
///     0.0
/// );
/// ```
///
/// @param date the date of the weather observation
/// @param temperature temperature in Celsius
/// @param humidity humidity percentage
/// @param precipitation rainfall in millimeters
public record WeatherData(
        LocalDate date,
        double temperature,
        int humidity,
        double precipitation
) {
}


