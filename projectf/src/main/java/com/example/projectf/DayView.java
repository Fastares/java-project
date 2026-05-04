package com.example.projectf;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class DayView {

    public static HashMap<LocalDate, ArrayList<Event>> calendarEvents = new HashMap<>();

    private static LocalDate currentDate;
    private static Event selectedEvent;

    private static ListView<String> scheduleList;
    private static TextArea detailsArea;

    private static DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("h:mm a");

    public static void show(Stage stage, LocalDate date) {
        System.out.println("yes");
        currentDate = date;

        calendarEvents.putIfAbsent(currentDate, new ArrayList<>());

        BorderPane root = new BorderPane();

        Button backButton = new Button("← Month");
        Label title = new Label("Day: " + currentDate);

        Button prev = new Button("<");
        Button next = new Button(">");

        HBox top = new HBox(15, backButton, prev, title, next);
        top.setStyle("-fx-padding: 15; -fx-font-size: 18;");

        scheduleList = new ListView<>();
        scheduleList.setPrefHeight(350);

        detailsArea = new TextArea();
        detailsArea.setEditable(false);
        detailsArea.setPrefHeight(120);

        Button addButton = new Button("Add Event");
        Button editButton = new Button("Edit Event");
        Button deleteButton = new Button("Delete Event");

        HBox buttons = new HBox(10, addButton, editButton, deleteButton);

        VBox center = new VBox(10,
                new Label("Schedule:"),
                scheduleList,
                new Label("Selected Event:"),
                detailsArea,
                buttons
        );

        center.setStyle("-fx-padding: 15;");

        root.setTop(top);
        root.setCenter(center);

        backButton.setOnAction(e -> {
            try {
                new Monthlycalender().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        prev.setOnAction(e -> {
            currentDate = currentDate.minusDays(1);
            show(stage, currentDate); // reload view
        });

        next.setOnAction(e -> {
            currentDate = currentDate.plusDays(1);
            show(stage, currentDate);
        });

        scheduleList.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            selectedEvent = getSelectedEventFromRow(newValue);
            showEventDetails();
        });

        addButton.setOnAction(e -> addEvent());
        editButton.setOnAction(e -> editEvent());
        deleteButton.setOnAction(e -> deleteEvent());

        refreshSchedule();

        Scene scene = new Scene(root, 600, 600);
        stage.setScene(scene);
        stage.setTitle("Day View");
        stage.show();
    }

    private static ArrayList<Event> getEventsForCurrentDate() {
        return calendarEvents.get(currentDate);
    }

    private static void refreshSchedule() {
        scheduleList.getItems().clear();

        for (int hour = 8; hour <= 23; hour++) {
            LocalTime time = LocalTime.of(hour, 0);
            String row = time.format(displayFormatter) + "  |  ";

            ArrayList<Event> eventsAtHour = new ArrayList<>();

            for (Event event : getEventsForCurrentDate()) {
                if (event.getStartTime().getHour() == hour) {
                    eventsAtHour.add(event);
                }
            }

            if (eventsAtHour.isEmpty()) {
                row += "";
            } else {
                for (Event event : eventsAtHour) {
                    row += event.getName() + " ";
                }
            }

            scheduleList.getItems().add(row);
        }

        detailsArea.clear();
    }

    private static Event getSelectedEventFromRow(String row) {
        if (row == null) {
            return null;
        }

        for (Event event : getEventsForCurrentDate()) {
            if (row.contains(event.getName())) {
                return event;
            }
        }

        return null;
    }

    private static void showEventDetails() {
        if (selectedEvent == null) {
            detailsArea.clear();
            return;
        }

        detailsArea.setText(
                "Name: " + selectedEvent.getName() + "\n" +
                        "Category: " + selectedEvent.getCategory() + "\n" +
                        "Time: " + selectedEvent.getStartTime().format(displayFormatter)
                        + " - " + selectedEvent.getEndTime().format(displayFormatter) + "\n" +
                        "Notes: " + selectedEvent.getNotes()
        );
    }

    private static void addEvent() {
        EventInput input = new EventInput(null);
        Event newEvent = input.showAndGetEvent();

        if (newEvent != null) {
            getEventsForCurrentDate().add(newEvent);
            refreshSchedule();
        }
    }

    private static void editEvent() {
        if (selectedEvent == null) {
            return;
        }

        EventInput input = new EventInput(selectedEvent);
        Event editedEvent = input.showAndGetEvent();

        if (editedEvent != null) {
            int index = getEventsForCurrentDate().indexOf(selectedEvent);
            getEventsForCurrentDate().set(index, editedEvent);

            refreshSchedule();

            selectedEvent = editedEvent;
            showEventDetails();
        }
    }

    private static void deleteEvent() {
        if (selectedEvent != null) {
            getEventsForCurrentDate().remove(selectedEvent);
            selectedEvent = null;
            refreshSchedule();
        }
    }
}
