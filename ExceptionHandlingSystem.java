import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Scanner;

// Class: User
class User {
    private int id;
    private String name;
    private String email;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        setEmail(email); // Demonstrates IllegalArgumentException
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.email = email;
    }
}

// Class: UserDatabase
class UserDatabase {
    private Map<Integer, User> users;

    public UserDatabase() {
        users = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public User getUser(int userId) {
        User user = users.get(userId);
        if (user == null) {
            throw new NullPointerException("User not found."); // Demonstrates NullPointerException
        }
        return user;
    }

    public void saveToFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (User user : users.values()) {
                writer.write(user.getId() + "," + user.getName() + "," + user.getEmail() + "\n");
            }
        } catch (IOException e) {
            System.out.println("IOException: Unable to write to file.");
        }
    }

    public void loadFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    throw new EOFException("Invalid file format."); // Demonstrates EOFException
                }
                addUser(new User(Integer.parseInt(parts[0]), parts[1], parts[2]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: File not found.");
        } catch (IOException e) {
            System.out.println("IOException: Error reading file.");
        }
    }

    public void connectToDatabase(String url) {
        try {
            Connection conn = DriverManager.getConnection(url, "user", "password");
        } catch (SQLException e) {
            System.out.println("SQLException: Failed to connect to the database."); // Demonstrates SQLException
        }
    }
}

// Main Class for Testing
public class ExceptionHandlingSystem {
    public static void main(String[] args) {
        UserDatabase database = new UserDatabase();
        Scanner scanner = new Scanner(System.in);

        // Adding users via input
        try {
            System.out.print("Enter User ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            System.out.print("Enter User Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter User Email: ");
            String email = scanner.nextLine();
            
            database.addUser(new User(id, name, email));

        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("InputMismatchException: Invalid input type.");
        }

        // Fetching a user
        try {
            System.out.print("Enter User ID to fetch: ");
            int userId = scanner.nextInt();
            User user = database.getUser(userId);
            System.out.println("Fetched User: " + user.getName() + ", " + user.getEmail());
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("InputMismatchException: Invalid input type.");
        }

        // File operations
        database.saveToFile("users.txt");
        database.loadFromFile("missingfile.txt"); // This will throw FileNotFoundException

        // Database connection
        database.connectToDatabase("jdbc:invalid:url"); // This will throw SQLException

        System.out.println("System executed with exception handling.");
    }
}