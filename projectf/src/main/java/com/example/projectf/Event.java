package com.example.projectf;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Event { private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private String category;
    private String notes;
    public Event(String name, LocalTime startTime, LocalTime endTime, String category, String notes) {
        this.name = name;
    }
    this.startTime = startTime;
    this.endTime = endTime;
    this.category = category;
    this.notes = notes;
}

public String getName() { return name; }
public LocalTime getStartTime() { return startTime; }
public LocalTime getEndTime() { return endTime; }
public String getCategory() { return category; }
public String getNotes() { return notes; }

@Override public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
    return startTime.format(formatter) + " - " + endTime.format(formatter) + ": " + name;
}

