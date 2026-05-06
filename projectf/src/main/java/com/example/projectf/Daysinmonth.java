package com.example.projectf;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// used for the calender so it can spam the number of days
public class Daysinmonth {
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
         switch (press) {
             case "Su":
                 return 1;
            case "M":
                return 2;
            case "Tu":
                return 3;
            case "W":
                return 4;
            case "Th":
                return 5;
            case "F":
                return 6;
            case "Sa":
                return 7;
        }
         return 1;
    }
}
