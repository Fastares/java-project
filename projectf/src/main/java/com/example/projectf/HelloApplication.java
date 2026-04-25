package com.example.projectf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane layout = new Pane();
        Scene scene = new Scene(layout, 650, 400);
        // calender layout
        Rectwboarder plate = new Rectwboarder(480, 350);
        plate.SetFillarea(Color.WHITE);
        plate.SetFillbound(Color.BLACK);
        plate.setLayoutY(40);
        plate.setLayoutX(35);
        layout.getChildren().addAll(plate.getBoarder(), plate.getFillw());

        // the dividers
        double spacing = 53;
        Line dayline = new Line(37, 65,500, 65);
        Line lower1 = new Line(37, dayline.getStartY() + spacing,500, dayline.getEndY() + spacing);
        Line lower2 = new Line(37, lower1.getStartY() + spacing,500, lower1.getEndY() + spacing);
        Line lower3 = new Line(37, lower2.getStartY() + spacing,500, lower2.getEndY() + spacing);
        Line lower4 = new Line(37, lower3.getStartY() + spacing,500, lower3.getEndY() + spacing);
        Line lower5 = new Line(37, lower4.getStartY() + spacing,500, lower4.getEndY() + spacing);
        layout.getChildren().addAll(dayline, lower1, lower2, lower3, lower4, lower5);

        //days of the month for layout
        Label Sunday = new Label("Su");
        Sunday.setMaxSize(500, 500);
        Sunday.setLayoutX(64);
        Sunday.setLayoutY(45);

        int divider = 65;

        Line StoM = new Line((70 + ((double) divider / 2)), 42, (70 + ((double) divider / 2)), 388) ;

        Label Monday = new Label("M");
        Monday.setMaxSize(500, 500);
        Monday.setLayoutX(Sunday.getLayoutX() + divider);
        Monday.setLayoutY(45);

        Line MtoTu = new Line(StoM.getStartX() + divider, 42, StoM.getStartX() + divider, 388);

        Label Tuesday = new Label("Tu");
        Tuesday.setMaxSize(500, 500);
        Tuesday.setLayoutX(Monday.getLayoutX() + divider);
        Tuesday.setLayoutY(45);

        Line TutoW = new Line(MtoTu.getStartX() + divider, 42, MtoTu.getStartX() + divider, 388);

        Label Wesnesday = new Label("W");
        Wesnesday.setMaxSize(500, 500);
        Wesnesday.setLayoutX(Tuesday.getLayoutX() + divider);
        Wesnesday.setLayoutY(45);

        Line WtoTh = new Line(TutoW.getStartX() + divider, 42, TutoW.getStartX() + divider, 388);

        Label Thursday = new Label("Th");
        Thursday.setMaxSize(500, 500);
        Thursday.setLayoutX(Wesnesday.getLayoutX() + divider);
        Thursday.setLayoutY(45);

        Line ThtoF = new Line(WtoTh.getStartX() + divider, 42, WtoTh.getStartX() + divider, 388);

        Label Friday = new Label("F");
        Friday.setMaxSize(500, 500);
        Friday.setLayoutX(Thursday.getLayoutX() + divider);
        Friday.setLayoutY(45);

        Line FtoSa = new Line(ThtoF.getStartX() + divider, 42, ThtoF.getStartX() + divider, 388);

        Label Saturday = new Label("Sa");
        Saturday.setMaxSize(500, 500);
        Saturday.setLayoutX(Friday.getLayoutX() + divider);
        Saturday.setLayoutY(45);


        layout.getChildren().addAll(Sunday , Monday, Tuesday, Wesnesday, Thursday, Friday, Saturday);
        layout.getChildren().addAll(StoM, MtoTu, TutoW, WtoTh, ThtoF, FtoSa);


        //upandcoming layout
        Rectwboarder sidedish = new Rectwboarder(120, 350);
        sidedish.SetFillarea(Color.WHITE);
        sidedish.SetFillbound(Color.BLACK);
        sidedish.setLayoutX(500);
        sidedish.setLayoutY(40);

        //add the sidebar and calender to the side
        layout.getChildren().addAll(sidedish.getBoarder(), sidedish.getFillw());


        stage.setScene(scene);
        stage.show();
    }
}

class Rectwboarder {
    private Rectangle boarder;
    private Rectangle fillw;
    // constructor
    public Rectwboarder(double width, double height) {
        boarder = new Rectangle(width, height);
        fillw = new Rectangle(boarder.getWidth() - 4 , boarder.getHeight() - 4);

        fillw.setLayoutX(2);
        fillw.setLayoutY(2);

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

class Eventbar extends Rectwboarder {
    private int eventcount = 6;
    private int current = 0;
    public Eventbar(double width, double height) {
        super(width, height);
    }
}


