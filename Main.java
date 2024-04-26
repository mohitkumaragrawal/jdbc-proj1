import java.util.*;
import java.sql.*;

public class Main {
    static String JDBC_URL = "jdbc:mysql://localhost:3306/library";
    static String USERNAME = "root";
    static String PASSWORD = "root";

    static void insertBook(String title, String pub, String author, int copies) {
      try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
        String sql = "INSERT INTO books (title, publisher, authors, copies) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, title);
        statement.setString(2, pub);
        statement.setString(3, author);
        statement.setInt(4, copies);
        statement.executeUpdate();
      }  catch (SQLException e) {
        e.printStackTrace();
      }
    }

    static void printBooks() {
      try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
        System.out.println("Title\tPub\tAuthor\tCopies");
        while (resultSet.next()) {
          String title = resultSet.getString("title");
          String publisher = resultSet.getString("publisher");
          String author = resultSet.getString("authors");
          int copies = resultSet.getInt("copies");
          System.out.println(title + "\t" + publisher + "\t" + author + "\t" + copies);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);


      while (true) {
        System.out.println("1. Insert book");
        System.out.println("2. Show Books");

        int option = scanner.nextInt();

        if (option == 1) {
          String title = scanner.next();
          String pub = scanner.next();
          String author = scanner.next();
          int copies = scanner.nextInt();

          System.out.println(title + " | " + pub + " | " + author + " | " + copies);

          insertBook(title, pub, author, copies);
        } else if (option == 2) {
          printBooks();
        } else {
          break;
        }
      }
    }
}
