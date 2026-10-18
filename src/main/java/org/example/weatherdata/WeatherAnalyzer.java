package org.example.weatherdata;

import java.util.List;

/// Provides methods for analyzing weather data.
///
/// This interface uses Java Streams and lambda expressions
/// to calculate weather statistics such as average temperature,
/// days above a temperature threshold, and rainy days.
///
/// Example usage:
/// ```java
/// double average = WeatherAnalyzer.averageTemperature(data, 8);
/// ```
public interface WeatherAnalyzer {

    /// Calculates the average temperature for a given month.
    ///
    /// @param data list of weather records
    /// @param month month number (1-12)
    /// @return average temperature in Celsius, or 0.0 if no data exists
    static double averageTemperature(List<WeatherData> data, int month) {

        return data.stream()
                // Select only records from the requested month
                .filter(day -> day.date().getMonthValue() == month)

                // Extract the temperature from each matching record
                .mapToDouble(WeatherData::temperature)

                // Calculate the average of the temperatures
                .average()

                // Return 0.0 if no records match the month
                .orElse(0.0);
    }

    /// Finds all days with temperatures above a given threshold.
    ///
    /// @param data list of weather records
    /// @param threshold minimum temperature to compare against
    /// @return list of records with temperatures above the threshold
    static List<WeatherData> daysAboveTemperature(
            List<WeatherData> data, double threshold) {

        return data.stream()
                // Keep only days hotter than the given threshold
                .filter(day -> day.temperature() > threshold)

                // Collect matching records into a list
                .toList();
    }

    /// Counts the number of days that recorded rainfall.
    ///
    /// A day is considered rainy if precipitation is greater than 0.
    ///
    /// @param data list of weather records
    /// @return total number of rainy days
    static long countRainyDays(List<WeatherData> data) {

        return data.stream()
                // Keep only days with precipitation greater than zero
                .filter(day -> day.precipitation() > 0)

                // Count the number of matching days
                .count();
    }
}

