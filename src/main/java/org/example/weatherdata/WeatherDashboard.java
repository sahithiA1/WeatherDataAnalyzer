package org.example.weatherdata;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.time.Month;
import java.util.List;

/// Creates a colorful JavaFX dashboard for analyzing weather data.
///
/// This dashboard displays average temperature, days above a
/// temperature threshold, rainy days, and total weather records.
///
/// It uses Java interfaces, records, lambdas, streams, and CSS
/// without declaring an explicit application class.
///
/// Example:
/// ```java
/// WeatherDashboard.main(new String[0]);
/// ```
public interface WeatherDashboard {

    /// Starts the JavaFX dashboard.
    ///
    /// Example:
    /// ```java
    /// WeatherDashboard.main(new String[0]);
    /// ```
    ///
    /// @param args command-line arguments
    static void main(String[] args) {

        // Start JavaFX without an explicit Application class
        Platform.startup(() -> {
            try {
                showDashboard();
            } catch (IOException e) {
                e.printStackTrace();
                Platform.exit();
            }
        });
    }

    /// Creates and displays the weather dashboard.
    ///
    /// The dashboard loads weather records from the CSV file,
    /// displays statistics, and allows the user to analyze
    /// different months and temperature thresholds.
    ///
    /// Example:
    /// ```java
    /// // Called from the JavaFX Application Thread
    /// WeatherDashboard.showDashboard();
    /// ```
    ///
    /// @throws IOException if the CSV file cannot be read
    static void showDashboard() throws IOException {

        // Read weather data from the CSV file
        List<WeatherData> data =
                WeatherCSVReader.readWeatherData();

        // Create the application window
        Stage stage = new Stage();
        stage.setTitle("Weather Data Analyzer");

        // Main dashboard layout
        VBox root = new VBox(20);
        root.setPadding(new Insets(25));
        root.getStyleClass().add("dashboard");

        // Dashboard heading
        Label heading = new Label("☀ Weather Dashboard");
        heading.getStyleClass().add("heading");

        Label subtitle = new Label(
                "Explore your daily weather statistics");
        subtitle.getStyleClass().add("subtitle");

        // Statistic cards
        Label averageCard = new Label();
        Label hotCard = new Label();
        Label rainyCard = new Label();
        Label totalCard = new Label();

        // Apply colorful CSS styles
        averageCard.getStyleClass().addAll(
                "stat-card", "temperature-card");

        hotCard.getStyleClass().addAll(
                "stat-card", "hot-card");

        rainyCard.getStyleClass().addAll(
                "stat-card", "rain-card");

        totalCard.getStyleClass().addAll(
                "stat-card", "total-card");

        // Arrange cards horizontally
        HBox cards = new HBox(
                12,
                averageCard,
                hotCard,
                rainyCard,
                totalCard
        );

        // Month selection dropdown
        ComboBox<Month> monthSelector = new ComboBox<>();
        monthSelector.getItems().addAll(Month.values());
        monthSelector.setValue(Month.AUGUST);

        // Temperature threshold input
        TextField thresholdField = new TextField("30");
        thresholdField.setPromptText(
                "Enter temperature threshold");

        // Weather records table
        TableView<WeatherData> table = new TableView<>();

        // Date column
        TableColumn<WeatherData, String> dateColumn =
                new TableColumn<>("Date");

        dateColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(
                        cell.getValue().date().toString()
                )
        );

        // Temperature column
        TableColumn<WeatherData, String> tempColumn =
                new TableColumn<>("Temperature");

        tempColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(
                        cell.getValue().temperature() + "°C"
                )
        );

        // Weather category column
        TableColumn<WeatherData, String> categoryColumn =
                new TableColumn<>("Weather Category");

        categoryColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(
                        WeatherCategory.getCategory(
                                cell.getValue().temperature()
                        )
                )
        );

        // Add columns to the table
        table.getColumns().addAll(
                dateColumn,
                tempColumn,
                categoryColumn
        );

        // Resize table columns automatically
        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN
        );

        table.setPrefHeight(350);

        // Analyze button
        Button analyzeButton =
                new Button("Analyze Weather");

        analyzeButton.getStyleClass().add(
                "analyze-button");

        // Lambda expression to update the dashboard
        Runnable updateDashboard = () -> {

            try {

                // Selected month
                int month =
                        monthSelector.getValue().getValue();

                // User-entered temperature threshold
                double threshold = Double.parseDouble(
                        thresholdField.getText().trim()
                );

                // Use streams to filter records by month
                List<WeatherData> monthlyData =
                        data.stream()
                                .filter(day ->
                                        day.date().getMonthValue()
                                                == month)
                                .toList();

                // Calculate average monthly temperature
                double average =
                        WeatherAnalyzer.averageTemperature(
                                data, month);

                // Find days above the temperature threshold
                long hotDays =
                        WeatherAnalyzer.daysAboveTemperature(
                                monthlyData, threshold
                        ).size();

                // Count rainy days
                long rainyDays =
                        WeatherAnalyzer.countRainyDays(
                                monthlyData);

                // Update average temperature card
                averageCard.setText(
                        "🌡 Average Temp\n%.1f°C"
                                .formatted(average)
                );

                // Update threshold card
                hotCard.setText(
                        "☀ Above Threshold\n%d Days"
                                .formatted(hotDays)
                );

                // Update rainy days card
                rainyCard.setText(
                        "🌧 Rainy Days\n%d Days"
                                .formatted(rainyDays)
                );

                // Update total records card
                totalCard.setText(
                        "📅 Total Records\n%d"
                                .formatted(monthlyData.size())
                );

                // Display weather records for selected month
                table.getItems().setAll(monthlyData);

            } catch (NumberFormatException e) {

                // Show an error for invalid input
                Alert alert = new Alert(
                        Alert.AlertType.ERROR,
                        "Please enter a valid temperature."
                );

                alert.setHeaderText(
                        "Invalid Temperature");

                alert.showAndWait();
            }
        };

        // Run analysis when button is clicked
        analyzeButton.setOnAction(event ->
                updateDashboard.run()
        );

        // Show August statistics when application starts
        updateDashboard.run();

        // Arrange dropdown, input, and button
        HBox filters = new HBox(
                12,
                monthSelector,
                thresholdField,
                analyzeButton
        );

        // Add all components to the dashboard
        root.getChildren().addAll(
                heading,
                subtitle,
                cards,
                filters,
                table
        );

        // Create application scene
        Scene scene = new Scene(
                root, 850, 650);

        // Load external CSS stylesheet
        var stylesheet =
                WeatherDashboard.class.getResource(
                        "/style.css");

        if (stylesheet != null) {
            scene.getStylesheets().add(
                    stylesheet.toExternalForm());
        }

        // Exit JavaFX when the window is closed
        stage.setOnCloseRequest(
                event -> Platform.exit()
        );

        // Display dashboard
        stage.setScene(scene);
        stage.show();
    }
}
