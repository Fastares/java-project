package com.example.projectf;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class Monthlycalender extends Application {
    protected static LocalDate currentMonth = LocalDate.now();
    @Override
    public void start(Stage stage) throws IOException {
        Pane layout = new Pane();
        Scene scene = new Scene(layout, 650, 400);

        Label monthTitle = new Label(
                currentMonth.getMonth().toString() + " " + currentMonth.getYear()
        );
        Button prevMonth = new Button("<");
        Button nextMonth = new Button(">");
        HBox header = new HBox(10); // spacing = 10
        header.setAlignment(Pos.CENTER);
        header.setPrefWidth(480); // match your calendar width

        //no default highlighted button
        prevMonth.setFocusTraversable(false);
        nextMonth.setFocusTraversable(false);

        header.getChildren().addAll(prevMonth, monthTitle, nextMonth);

        // position the whole thing once
        header.setLayoutX(35);   // same as calendar X
        header.setLayoutY(10);
        layout.getChildren().add(header);

        monthTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        //layout.getChildren().addAll(prevMonth, monthTitle, nextMonth);
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
        Rectwboarder sidedish = new Rectwboarder(120, 350);
        sidedish.setLayoutX(492);
        sidedish.setLayoutY(40);

        Label sidebarTitle = new Label("Select a day");
        sidebarTitle.setLayoutX(505);
        sidebarTitle.setLayoutY(55);

        ListView<String> sidebarList = new ListView<>();
        sidebarList.setLayoutX(505);
        sidebarList.setLayoutY(85);
        sidebarList.setPrefSize(95, 290);
        sidebarList.setFocusTraversable(false);

        /*buttons for prev/next day in sidebar
        Button prevDay = new Button("<");
        Button nextDay = new Button(">");
        prevDay.setLayoutX(505);
        nextDay.setLayoutX(585); // right side of sidebar
        prevDay.setLayoutY(30);
        nextDay.setLayoutY(30);*/

        /*layout.getChildren().addAll(sidebarTitle, sidebarList);

        //add the sidebar and calendar to the side
        layout.getChildren().addAll(sidedish.getBoarder(), sidedish.getFillw());*/

        // add sidebar background first
        layout.getChildren().addAll(sidedish.getBoarder(), sidedish.getFillw());

        // then add sidebar content on top
        layout.getChildren().addAll(sidebarTitle, sidebarList);

        int daysInMonth = currentMonth.lengthOfMonth();
        int startDay = currentMonth.withDayOfMonth(1).getDayOfWeek().getValue();
        int sundayStart = startDay % 7;

        // (how many days, starting day, the pane, starting postion x, starting postion y)
        // starting day goes from sunday to saturday from 1-7 minus 1 for 0-6 in short terms
        // "Su", "M", "Tu", "W", "Th", "F", "Sa"
        Daysinmonth slide = new Daysinmonth(daysInMonth, sundayStart, layout, 40, 48, stage, sidebarTitle, sidebarList);

        stage.setScene(scene);
        stage.show();

        prevMonth.setOnAction(e -> {
            currentMonth = currentMonth.minusMonths(1);
            try {
                new Monthlycalender().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        nextMonth.setOnAction(e -> {
            currentMonth = currentMonth.plusMonths(1);
            try {
                new Monthlycalender().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
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

        layout.getChildren().addAll(Sunday , Monday, Tuesday, Wesnesday, Thursday, Friday, Saturday);
    }
}

