package org.example.weatherdata;

import java.io.IOException;
import java.util.List;

/// Main entry point for the Weather Data Analyzer.
///
/// This interface connects the CSV reader, weather analyzer,
/// and weather category features.
///
/// It uses a Java text block to display the results.
public interface Main {

    /// Starts the application and displays weather statistics.
    ///
    /// @param args command-line arguments
    /// @throws IOException if the CSV file cannot be read
    static void main(String[] args) throws IOException {

        // Read weather observations from the CSV file
        List<WeatherData> data = WeatherCSVReader.readWeatherData();

        // Calculate the average temperature for August
        double average = WeatherAnalyzer.averageTemperature(data, 8);

        // Find days with temperatures above 30 degrees Celsius
        List<WeatherData> hotDays =
                WeatherAnalyzer.daysAboveTemperature(data, 30);

        // Count the number of rainy days
        long rainyDays = WeatherAnalyzer.countRainyDays(data);

        // Display the results using a Java text block
        String report = """
                ==================================
                       WEATHER DATA ANALYZER
                ==================================
                Total Weather Records: %d
                Average August Temperature: %.2f°C
                Days Above 30°C: %d
                Total Rainy Days: %d
                ==================================
                """.formatted(
                data.size(),
                average,
                hotDays.size(),
                rainyDays
        );

        System.out.println(report);

        // Display the weather category for each observation
        System.out.println("Daily Weather Categories:");

        data.forEach(day -> System.out.printf(
                "%s | %.1f°C | %s%n",
                day.date(),
                day.temperature(),
                WeatherCategory.getCategory(day.temperature())
        ));
    }
}

