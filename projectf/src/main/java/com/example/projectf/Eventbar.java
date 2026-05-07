package com.example.projectf;

// event bar wip but it takes the nearest hopefully 6 events and makes them into a list from clost to far 
public class Eventbar extends Rectwboarder {
    private int eventcount = 6;
    private int current = 0;

    public Eventbar(double width, double height) {
        super(width, height);
    }

}
