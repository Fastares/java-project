package com.example.projectf;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

// this is for the event bar and calender area
public class Rectwboarder {
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
