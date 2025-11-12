package com.example.movieratingsystem;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            System.out.println("❌ Failed to connect to the database.");
            return;
        }

        MovieDAO dao = new MovieDAO();
        Scanner sc = new Scanner(System.in);

        System.out.println("✅ Database connected successfully!");

        while (true) {
            System.out.println("\n🎬 Movie Rating System");
            System.out.println("1. View all movies");
            System.out.println("2. Add a new movie");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> dao.viewMovies();
                case 2 -> {
                    System.out.print("Enter movie title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter genre: ");
                    String genre = sc.nextLine();
                    System.out.print("Enter release year: ");
                    int year = sc.nextInt();
                    dao.addMovie(title, genre, year);
                }
                case 3 -> {
                    System.out.println("👋 Exiting... Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("⚠️ Invalid choice! Try again.");
            }
        }
    }
}
