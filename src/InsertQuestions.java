import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class handles inserting questions and their associated answers into a database,
 * linking them to predefined categories such as Math, Geography, and Chemistry.
 * It reads question data from text files and inserts it into a database.
 */
public class InsertQuestions {

    /** The database connection object. */
    static Connection conn = null;

    /** The prepared statement for executing SQL queries. */
    static PreparedStatement statement = null;

    /**
     * The main method that serves as the entry point for the application. It connects to the
     * database, inserts categories, and reads question data from text files to insert into the database.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        try {
            // Make a connection to the database
            makeDBConnection();

            // Define relative path
            String relativePath = "../team_boza/";

            // Insert categories
            insertCategories();

            // Insert data from the text files (linking questions to categories)
            insertDataFromFile(relativePath + "questions/MathTimesTables.txt", getCategoryId("Math"));
            insertDataFromFile(relativePath + "questions/GeographyStateCap.txt", getCategoryId("Geography"));
            insertDataFromFile(relativePath + "questions/ChemistryPeriodicTable.txt", getCategoryId("Chemistry"));

            // Close the statement and connection after insertion
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Establishes a connection to the MySQL database using the JDBC driver.
     */
    public static void makeDBConnection() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            // Establish the database connection (modify the connection string with your database details)
            conn = DriverManager.getConnection("jdbc:mysql://localhost:1521/OwenDB?user=o_mcgann&password=Changeme_00");
            if (conn != null) {
                System.out.println("Database connection successful");
            } else {
                System.out.println("Database connection failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts predefined categories such as Math, Geography, and Chemistry into the Categories table.
     * Checks if the category already exists before inserting it.
     *
     * @throws SQLException If a database access error occurs.
     */
    public static void insertCategories() throws SQLException {
        String checkCategoryQuery = "SELECT COUNT(*) FROM Categories WHERE name = ?";
        String insertCategoryQuery = "INSERT INTO Categories (name) VALUES (?)";

        PreparedStatement checkStmt = conn.prepareStatement(checkCategoryQuery);

        // List of categories to insert
        String[] categories = {"Math", "Geography", "Chemistry"};

        for (String category : categories) {
            // Check if the category already exists
            checkStmt.setString(1, category);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            // If the category does not exist, insert it
            if (count == 0) {
                PreparedStatement insertStmt = conn.prepareStatement(insertCategoryQuery);
                insertStmt.setString(1, category);
                insertStmt.executeUpdate();
                System.out.println(category + " category inserted successfully!");
            } else {
                System.out.println(category + " category already exists, skipping insertion.");
            }
        }
    }

    /**
     * Retrieves the category ID from the Categories table based on the category name.
     *
     * @param categoryName The name of the category.
     * @return The ID of the category.
     * @throws SQLException If a database access error occurs or if the category is not found.
     */
    public static int getCategoryId(String categoryName) throws SQLException {
        String query = "SELECT id FROM Categories WHERE name = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, categoryName);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        } else {
            throw new SQLException("Category not found: " + categoryName);
        }
    }

    /**
     * Reads questions and answers from a text file and inserts them into the Questions and Answers tables.
     * The file should contain data in the format: question | correct answer.
     *
     * @param relativePath The path to the text file containing the questions and answers.
     * @param categoryId   The ID of the category to link the questions to.
     */
    public static void insertDataFromFile(String relativePath, int categoryId) {
        try (BufferedReader br = new BufferedReader(new FileReader(relativePath))) {
            String line;
            String insertQuestionQuery = "INSERT INTO Questions (question, category_id) VALUES (?, ?)";
            String insertAnswerQuery = "INSERT INTO Answers (question_id, answer, is_correct) VALUES (?, ?, ?)";
            statement = conn.prepareStatement(insertQuestionQuery);

            // Read each line from the file and insert into the database
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String question = parts[1];
                    String correctAnswer = parts[2];

                    // Insert the question and get the generated question ID
                    statement = conn.prepareStatement(insertQuestionQuery, PreparedStatement.RETURN_GENERATED_KEYS);
                    statement.setString(1, question);
                    statement.setInt(2, categoryId);
                    statement.executeUpdate();

                    ResultSet rs = statement.getGeneratedKeys();
                    if (rs.next()) {
                        int questionId = rs.getInt(1);

                        // Insert the correct answer
                        statement = conn.prepareStatement(insertAnswerQuery);
                        statement.setInt(1, questionId);
                        statement.setString(2, correctAnswer);
                        statement.setBoolean(3, true);  // Mark the answer as correct
                        statement.executeUpdate();
                    }
                }
            }
            System.out.println("Data from " + relativePath + " inserted successfully!");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
