package com.example.projectf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import java.util.ArrayList;

public class Event {

    private LocalDate eventDate;
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private String category;
    private String notes;

    public Event(LocalDate eventDate,
                 String name,
                 LocalTime startTime,
                 LocalTime endTime,
                 String category,
                 String notes) {

        this.eventDate = eventDate;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.notes = notes;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public String getName() {
        return name;
    }

    public LocalTime getStartTime() {
        return startTime; }

    public LocalTime getEndTime() {return endTime; }

    public String getCategory() {return category; }

    public String getNotes() { return notes; }

    // SAVE EVENT
    public void saveToDatabase() {

        String url = "jdbc:mysql://localhost:3306/javabook";
        String username = "scott";
        String password = "tiger";

        String sql = "INSERT INTO Events " +
                "(EventDate, Name, StartTime, EndTime, Category, Notes) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setDate(1, Date.valueOf(eventDate));
            pstmt.setString(2, name);
            pstmt.setTime(3, Time.valueOf(startTime));
            pstmt.setTime(4, Time.valueOf(endTime));
            pstmt.setString(5, category);
            pstmt.setString(6, notes);

            pstmt.executeUpdate();

            System.out.println("Event saved successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE EVENT
    public void deleteFromDatabase() {

        String url = "jdbc:mysql://localhost:3306/javabook";
        String username = "scott";
        String password = "tiger";

        String sql = "DELETE FROM Events " +
                "WHERE EventDate = ? " +
                "AND Name = ? " +
                "AND StartTime = ? " +
                "AND EndTime = ?";

        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setDate(1, Date.valueOf(eventDate));
            pstmt.setString(2, name);
            pstmt.setTime(3, Time.valueOf(startTime));
            pstmt.setTime(4, Time.valueOf(endTime));

            pstmt.executeUpdate();

            System.out.println("Event deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Event> loadEventsFromDatabase(LocalDate date) {

        ArrayList<Event> events = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/javabook";
        String username = "scott";
        String password = "tiger";

        String sql = "SELECT * FROM Events WHERE EventDate = ?";

        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setDate(1, Date.valueOf(date));

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Event event = new Event(
                        rs.getDate("EventDate").toLocalDate(),
                        rs.getString("Name"),
                        rs.getTime("StartTime").toLocalTime(),
                        rs.getTime("EndTime").toLocalTime(),
                        rs.getString("Category"),
                        rs.getString("Notes")
                );

                events.add(event);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }


    @Override
    public String toString() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("ha");

        return startTime.format(formatter)
                + " - "
                + endTime.format(formatter)
                + ": "
                + name;
    }
}