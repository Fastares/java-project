package com.example.projectf;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.time.LocalDate;

// this makes the sorting system of which day it is for example 1 beings up the events of one
class Day {
    private static Day selectedDay = null;
    private Label test;
    private Button side;
    private int pos = 0;
    private int month = 5;
    public Stage center;
    private LocalDate gain;


    public Day(int count, Pane hold, double X, double Y, Label sidebarTitle, ListView<String> sidebarList) {
        test = new Label("" + count);
        test.setPrefSize(50, 50);
        test.setLayoutX(X);
        test.setLayoutY(Y);
        //side = new Button("event: 0");
        //gain = LocalDate.of(LocalDate.now().getYear(), month, count);
        gain = LocalDate.of(
                Monthlycalender.currentMonth.getYear(),
                Monthlycalender.currentMonth.getMonthValue(),
                count
        );

        ArrayList<Event> events = DayView.calendarEvents.get(gain);
        int eventCount = (events == null) ? 0 : events.size();
        side = new Button("event: " + eventCount);
        side.setFocusTraversable(false);

        side.setOnAction(e -> {
            sidebarTitle.setText("Events for " + gain.getMonth() + " " + gain.getDayOfMonth());

            sidebarList.getItems().clear();

            ArrayList<Event> daysEvents = DayView.calendarEvents.get(gain);

            if (daysEvents == null || daysEvents.isEmpty()) {
                sidebarList.getItems().add("No events");
            } else {
                for (Event event : daysEvents) {
                    sidebarList.getItems().add(event.toString());
                }
            }

            if (selectedDay != null) {
                selectedDay.test.setStyle("");
                selectedDay.side.setStyle("");
            }

            selectedDay = this;

            //test.setStyle("-fx-background-color: lightblue; -fx-background-insets: 20 10 5 5;");
        });

        test.setOnMouseClicked(event -> {
            DayView.show(center, gain);
        });

        //side.setOnAction(event -> {
        //    DayView.show(center, gain);
        //});

        side.setLayoutX(X - 3);
        side.setLayoutY(Y + 45.5);
        side.setPrefSize(65, 25);
        pos = count;
        /*side.setOnMousePressed(event -> {
            System.out.println("on");
            DayView.show(center, gain);
            System.out.println("hit");
        });*/

        hold.getChildren().addAll(test, side);
    }

    public void setLayoutX(double X) {
        test.setLayoutX(X);
        side.setLayoutX(X - 3);
    }
    public void setLayoutY(double Y) {
        test.setLayoutY(Y);
        side.setLayoutY(Y + 45.5);
    }

    public Button getSide() { return side; }

    public void addthis(Pane layout) {
        layout.getChildren().addAll(test, side);
    }

    public int getPos() { return pos; }
    public void setPos(int pos) { this.pos = pos; }

}
