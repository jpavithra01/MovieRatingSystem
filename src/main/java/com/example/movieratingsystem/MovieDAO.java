package com.example.movieratingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieDAO {

    // ✅ Add new movie to database
    public void addMovie(String title, String genre, int releaseYear) {
        String sql = "INSERT INTO movies (title, genre, release_year) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, title);
            stmt.setString(2, genre);
            stmt.setInt(3, releaseYear);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Movie added successfully!");
            } else {
                System.out.println("⚠️ Failed to add movie.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Error adding movie: " + e.getMessage());
        }
    }

    // ✅ View all movies from database
    public void viewMovies() {
        String sql = "SELECT * FROM movies";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n🎬 Movie List:");
            boolean found = false;

            while (rs.next()) {
                found = true;
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                int year = rs.getInt("release_year");

                System.out.println(id + ". " + title + " (" + genre + ", " + year + ")");
            }

            if (!found) {
                System.out.println("⚠️ No movies found in the database!");
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching movies: " + e.getMessage());
        }
    }
}
