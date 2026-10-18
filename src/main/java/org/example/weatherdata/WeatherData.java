package org.example.weatherdata;

import java.time.LocalDate;

/**
 * Represents weather information for a single day.
 *
 * @param date the date of the weather observation
 * @param temperature temperature in Celsius
 * @param humidity humidity percentage
 * @param precipitation rainfall in millimeters
 */
public record WeatherData(
        LocalDate date,
        double temperature,
        int humidity,
        double precipitation
) {
}

