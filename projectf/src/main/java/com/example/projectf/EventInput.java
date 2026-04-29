package com.example.projectf;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventInput {

    private Event originalEvent;

    public EventInput(Event event) {
        this.originalEvent = event;
    }

    public Event showAndGetEvent() {
        Dialog<Event> dialog = new Dialog<>();
        dialog.setTitle(originalEvent == null ? "Add Event" : "Edit Event");

        ButtonType saveButton = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButton, ButtonType.CANCEL);

        TextField nameField = new TextField();
        TextField startField = new TextField();
        TextField endField = new TextField();

        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("School", "Activities", "Work", "Appointments", "Other");

        TextArea notesArea = new TextArea();

        startField.setPromptText("Example: 1:00 PM or 13:00");
        endField.setPromptText("Example: 2:30 PM or 14:30");

        if (originalEvent != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

            nameField.setText(originalEvent.getName());
            startField.setText(originalEvent.getStartTime().format(formatter));
            endField.setText(originalEvent.getEndTime().format(formatter));
            categoryBox.setValue(originalEvent.getCategory());
            notesArea.setText(originalEvent.getNotes());
        } else {
            categoryBox.setValue("Other");
        }

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);

        grid.add(new Label("Start time:"), 0, 1);
        grid.add(startField, 1, 1);

        grid.add(new Label("End time:"), 0, 2);
        grid.add(endField, 1, 2);

        grid.add(new Label("Category:"), 0, 3);
        grid.add(categoryBox, 1, 3);

        grid.add(new Label("Notes:"), 0, 4);
        grid.add(notesArea, 1, 4);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(button -> {
            if (button == saveButton) {
                try {
                    return new Event(
                            nameField.getText(),
                            parseTime(startField.getText()),
                            parseTime(endField.getText()),
                            categoryBox.getValue(),
                            notesArea.getText()
                    );
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText("Please check your event information.");
                    alert.setContentText("Time should look like 1:00 PM or 13:00.");
                    alert.showAndWait();
                    return null;
                }
            }
            return null;
        });

        return dialog.showAndWait().orElse(null);
    }

    private LocalTime parseTime(String text) {
        text = text.trim().toUpperCase();

        try {
            DateTimeFormatter amPmFormatter = DateTimeFormatter.ofPattern("h:mm a");
            return LocalTime.parse(text, amPmFormatter);
        } catch (Exception ignored) {
            return LocalTime.parse(text);
        }
    }
}
