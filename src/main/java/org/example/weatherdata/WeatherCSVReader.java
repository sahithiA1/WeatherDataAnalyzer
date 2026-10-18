package org.example.weatherdata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

/// Reads weather information from a CSV file.
///
/// This interface loads the weatherdata.csv file from the
/// resources folder and converts each row into a WeatherData record.
///
/// Example usage:
/// ```java
/// List<WeatherData> data = WeatherCSVReader.readWeatherData();
/// ```
public interface WeatherCSVReader {

    /// Reads and parses the weather data from the CSV file.
    ///
    /// @return a list of WeatherData records
    /// @throws IOException if the file cannot be found or read
    static List<WeatherData> readWeatherData() throws IOException {

        // Find the CSV file inside src/main/resources
        InputStream input = WeatherCSVReader.class
                .getResourceAsStream("/weatherdata.csv");

        // Check whether the file exists
        if (input == null) {
            throw new IOException("weatherdata.csv file not found.");
        }

        // Open the CSV file for reading
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(input, StandardCharsets.UTF_8))) {

            return reader.lines()
                    // Skip the first row containing column names
                    .skip(1)

                    // Ignore any empty rows
                    .filter(line -> !line.isBlank())

                    // Convert each CSV row into a WeatherData record
                    .map(WeatherCSVReader::parseWeatherData)

                    // Collect all weather records into a list
                    .toList();
        }
    }

    /// Converts one CSV row into a WeatherData record.
    ///
    /// @param line a comma-separated row of weather values
    /// @return a WeatherData record containing the parsed values
    static WeatherData parseWeatherData(String line) {

        // Split the row into individual values
        String[] values = line.split(",");

        // Convert each value into its correct Java data type
        LocalDate date = LocalDate.parse(values[0].trim());
        double temperature = Double.parseDouble(values[1].trim());
        int humidity = Integer.parseInt(values[2].trim());
        double precipitation = Double.parseDouble(values[3].trim());

        // Create and return a weather record
        return new WeatherData(
                date,
                temperature,
                humidity,
                precipitation
        );
    }
}

