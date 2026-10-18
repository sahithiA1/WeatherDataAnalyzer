package org.example.weatherdata;

/// Categorizes weather based on temperature.
///
/// This interface uses an enhanced switch expression
/// to classify temperatures as Hot, Warm, or Cold.
///
/// Example:
/// ```java
/// String category = WeatherCategory.getCategory(32.5);
/// // Result: Hot
/// ```
public interface WeatherCategory {

    /// Determines the weather category for a temperature.
    ///
    /// @param temperature temperature in Celsius
    /// @return the corresponding weather category
    static String getCategory(double temperature) {

        // Determine the temperature range
        int category = temperature >= 30 ? 1
                : temperature >= 20 ? 2 : 3;

        // Use an enhanced switch expression to return the category
        return switch (category) {
            case 1 -> "Hot";
            case 2 -> "Warm";
            case 3 -> "Cold";
            default -> "Unknown";
        };
    }
}

