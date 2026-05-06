package com.example.projectf;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;

public class Monthlycalender extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane layout = new Pane();
        Scene scene = new Scene(layout, 650, 400);

        // calender layout
        Rectwboarder plate = new Rectwboarder(480, 350);
        plate.setLayoutY(40);
        plate.setLayoutX(35);
        layout.getChildren().addAll(plate.getBoarder(), plate.getFillw());

        // the dividers that give spaces
        Yaxisgrid(37, 65,500, 65, 55, 6, layout);
        Xaxisgrid(102.5, 42, 102.5, 388, 65, 7, layout);

        //days of the month for layout
        daysinweek(64, 45, 65, layout);

        //upandcoming layout
        Eventbar sidedish = new Eventbar(120, 350);
        sidedish.setLayoutX(493);
        sidedish.setLayoutY(40);

        //add the sidebar and calender to the side
        layout.getChildren().addAll(sidedish.getBoarder(), sidedish.getFillw());

        // (how many days, starting day, the pane, starting postion x, starting postion y)
        // starting day goes from sunday to saturday from 1-7 minus 1 for 0-6 in short terms
        // "Su", "M", "Tu", "W", "Th", "F", "Sa"
        Daysinmonth slide = new Daysinmonth(29, "F", layout, 40, 48, stage);


        stage.setScene(scene);
        stage.show();
    }

    void Yaxisgrid(double startX, double startY, double endX, double endY, double space, int num, Pane layout) {
        Line[] startpl = new Line[num];
        int i = 0;
        while (i < num) {
            if (i == 0) {
                startpl[i] = new Line(startX, startY, endX, endY);
                layout.getChildren().add(startpl[i]);
                i++;
                continue;
            }
            startpl[i] = new Line(startX, startpl[i - 1].getStartY() + space, endX, startpl[i - 1].getEndY() + space);
            layout.getChildren().add(startpl[i]);
            i++;
        }
    }
    void Xaxisgrid(double startX, double startY, double endX, double endY, double space, int num, Pane layout) {
        Line[] endpl = new Line[num];
        int i = 0;
        while (i < num) {
            if (i == 0) {
                endpl[i] = new Line(startX, startY, endX, endY);
                layout.getChildren().add(endpl[i]);
                i++;
                continue;
            }
            endpl[i] = new Line(endpl[i - 1].getStartX() + space, startY, endpl[i - 1].getEndX() + space, endY);
            layout.getChildren().add(endpl[i]);
            i++;
        }
    }
    void daysinweek(double X, double Y, double divider, Pane layout) {
        Label Sunday = new Label("Su");
        Sunday.setLayoutX(X);
        Sunday.setLayoutY(Y);

        Label Monday = new Label("M");
        Monday.setLayoutX(Sunday.getLayoutX() + divider);
        Monday.setLayoutY(Y);

        Label Tuesday = new Label("Tu");
        Tuesday.setLayoutX(Monday.getLayoutX() + divider);
        Tuesday.setLayoutY(Y);

        Label Wesnesday = new Label("W");
        Wesnesday.setLayoutX(Tuesday.getLayoutX() + divider);
        Wesnesday.setLayoutY(Y);


        Label Thursday = new Label("Th");
        Thursday.setLayoutX(Wesnesday.getLayoutX() + divider);
        Thursday.setLayoutY(Y);

        Label Friday = new Label("F");
        Friday.setLayoutX(Thursday.getLayoutX() + divider);
        Friday.setLayoutY(Y);


        Label Saturday = new Label("Sa");
        Saturday.setLayoutX(Friday.getLayoutX() + divider);
        Saturday.setLayoutY(Y);

        Label substube = new Label(Monthname(5));
        substube.setLayoutX(280);
        substube.setLayoutY(3);
        substube.setMaxSize(300, 300);
        substube.setPrefSize(100, 50);
        substube.setViewOrder(-2.0);

        Rectwboarder bubstube = new Rectwboarder(50, 22);
        bubstube.setLayoutX(substube.getLayoutX() - 13);
        bubstube.setLayoutY(substube.getLayoutY() + 14);


        layout.getChildren().addAll(bubstube.getBoarder(), bubstube.getFillw(), substube, Sunday , Monday, Tuesday, Wesnesday, Thursday, Friday, Saturday);
    }
    public String Monthname(int month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";

        }
        return "";
    }
}

