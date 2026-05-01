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
import java.time.Month;
import java.util.Date;

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
        Rectwboarder sidedish = new Rectwboarder(120, 350);
        sidedish.setLayoutX(500);
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

        layout.getChildren().addAll(Sunday , Monday, Tuesday, Wesnesday, Thursday, Friday, Saturday);
    }
}

// this is for the event bar and calender area
class Rectwboarder {
    private Rectangle boarder;
    private Rectangle fillw;
    // constructor
    public Rectwboarder(double width, double height) {
        boarder = new Rectangle(width, height);
        fillw = new Rectangle(boarder.getWidth() - 4 , boarder.getHeight() - 4);
        fillw.setLayoutX(2);
        fillw.setLayoutY(2);

        SetFillarea(Color.WHITE);
        SetFillbound(Color.BLACK);

    }
    // rectangle grab (just incase)
    public Rectangle getBoarder() { return boarder; }
    public Rectangle getFillw() { return fillw; }
    // color
    public void SetFillarea (Paint value) {fillw.setFill(value);}
    public void SetFillbound (Paint value) {boarder.setFill(value);}
    public void SetFill(Paint value) {
        fillw.setFill(value);
        boarder.setFill(value);
    }
    // x pos
    public void setLayoutX(double Xmark) {
        boarder.setLayoutX(Xmark);
        fillw.setLayoutX((Xmark + (boarder.getWidth() - fillw.getWidth()) / 2));
    }
    public double getLayoutX() { return boarder.getLayoutX(); }
    // y pos
    public void setLayoutY (double Ymark) {
        boarder.setLayoutY(Ymark);
        fillw.setLayoutY(Ymark + (boarder.getHeight() - fillw.getHeight()) / 2);
    }
    public double getLayoutY() {return boarder.getLayoutY();}

}
// event bar wip but it takes the nearest hopefully 6 events and makes them into a list from clost to far
class Eventbar extends Rectwboarder {
    private int eventcount = 6;
    private int current = 0;
    public Eventbar(double width, double height) {
        super(width, height);
    }
}
// this makes the sorting system of which day it is for example 1 beings up the events of one
class Day {
    private Label test;
    private Button side;
    private int pos = 0;
    private int month = 5;
    public Stage center;
    private LocalDate gain;


    public Day(int count, Pane hold, double X, double Y) {
        test = new Label("" + count);
        test.setPrefSize(50, 50);
        test.setLayoutX(X);
        test.setLayoutY(Y);
        side = new Button("event: 0");
        side.setLayoutX(X - 3);
        side.setLayoutY(Y + 45.5);
        side.setPrefSize(65, 25);
        pos = count;
        gain = LocalDate.of(LocalDate.now().getYear(), month, count);
        side.setOnMousePressed(event -> {
            System.out.println("on");
            DayView.show(center, gain);
            System.out.println("hit");
        });

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
// used for the calender so it can spam the number of days
class Daysinmonth {
    private Day[] days;
    private int Sx = 65;
    private int Sy = 55;
    // day is total amount of days, press is the starting day, layout is the pane needed, px is starting x and py is starting y
    public Daysinmonth(int day, String press, Pane layout, double pX, double pY, Stage stage) {
        days = new Day[day];
        int i = 0;
        int counter = 0;
        int start = montonum(press) - 1;
        double hori = pX;
        double veri = pY;
        while (i != start) {
            hori = hori + Sx;
            counter++;
            i++;
        }
        i = 0;
        while (i != day) {
            if (counter < 7) {
                days[i] = new Day(i + 1, layout, hori, veri);
                days[i].center = stage;
                hori = hori + Sx;
            } else {
                counter = 0;
                veri = veri + Sy;
                hori = pX;
                continue;
            }
            counter++;
            i++;
        }
    }

    private int montonum(String press) {
        switch (press){
            case "Su": return 1;
            case "M": return 2;
            case "Tu": return 3;
            case "W" : return 4;
            case "Th": return 5;
            case "F" : return 6;
            case "Sa": return 7;
        }
        return 0;
    }
}
