package com.example.projectf;

import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new Monthlycalender().start(stage);
    }
}