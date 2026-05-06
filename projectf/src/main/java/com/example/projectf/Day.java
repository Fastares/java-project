package com.example.projectf;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.time.LocalDate;

// this makes the sorting system of which day it is for example 1 beings up the events of one
public class Day {
    private Label test;
    private Button side;
    private int pos = 0;
    private int relatedevents = 0;
    private int month = LocalDate.now().getMonthValue();
    public Stage center;
    private LocalDate gain;

    public Day(int count, Pane hold, double X, double Y) {
        test = new Label("" + count);
        relatedevents = 0;
        test.setPrefSize(50, 50);
        test.setLayoutX(X);
        test.setLayoutY(Y);
        side = new Button("event: " + relatedevents);
        side.setLayoutX(X - 3);
        side.setLayoutY(Y + 45.5);
        side.setPrefSize(65, 25);
        pos = count;
        gain = LocalDate.of(LocalDate.now().getYear(), month, count);
        side.setOnMousePressed(event -> {
            DayView.show(center, gain);
        });
        hold.getChildren().addAll(test, side);
    }
    //adds up and makes it
    public void proevents() {
        System.out.println("hit");
        relatedevents = relatedevents + 1;
        System.out.println("" + relatedevents);
        side.setText("event: " + relatedevents);
    }
    //subtracts and makes it
    public void conevents() {
        if (relatedevents != 0) {
            System.out.println("cat");
            relatedevents = relatedevents - 1;
            System.out.println("" + relatedevents);
            side.setText("event: " + relatedevents);
        }
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
