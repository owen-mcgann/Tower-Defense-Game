import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Abstract class representing a collection of questions for a specific category.
 * This class handles loading questions from a database, retrieving questions and answers,
 * and logging player responses.
 */
public abstract class Questions {
    /** A map storing questions and their corresponding answers. */
    protected HashMap<String, String> questionMap;

    /** Random object used for selecting questions randomly. */
    private Random random = new Random();

    /** Database URL for the connection. */
    private static final String DB_URL = "jdbc:mysql://localhost:1521/OwenDB";

    /** Database username for the connection. */
    private static final String USER = "o_mcgann";

    /** Database password for the connection. */
    private static final String PASS = "Changeme_00";

    /**
     * Constructs a Questions object for a specific category.
     * Initializes the question map and loads questions from the database based on the provided category.
     *
     * @param category The category of questions to load (e.g., Chemistry, Math, Geography).
     */
    public Questions(String category) {
        questionMap = new HashMap<>();
        loadQuestionsFromDatabase(category);
    }

    /**
     * Loads questions from the database for the specified category.
     * Populates the questionMap with question-answer pairs.
     *
     * @param category The category for which to load questions.
     */
    protected void loadQuestionsFromDatabase(String category) {
        String query = "SELECT q.id, q.question, a.answer " +
                "FROM Questions q " +
                "JOIN Categories c ON q.category_id = c.id " +
                "JOIN Answers a ON q.id = a.question_id " +
                "WHERE c.name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, category);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String question = rs.getString("question");
                String answer = rs.getString("answer");
                questionMap.put(question, answer);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a question and its corresponding answer to the question map.
     *
     * @param question The question to be added.
     * @param answer The answer to the question.
     */
    public void setQuestion(String question, String answer) {
        questionMap.put(question, answer);
    }

    /**
     * Retrieves the answer for a given question.
     *
     * @param question The question for which the answer is to be retrieved.
     * @return The answer to the question, or null if the question does not exist.
     */
    public String getAnswer(String question) {
        return questionMap.get(question);
    }

    /**
     * Randomly selects and returns a question from the question map.
     *
     * @return A randomly selected question, or null if no questions are available.
     */
    public String getAnyQuestion() {
        if (questionMap.isEmpty()) {
            return null;
        }
        List<String> keys = new ArrayList<>(questionMap.keySet());
        return keys.get(random.nextInt(keys.size()));
    }

    /**
     * Gets the count of questions available for a specific category.
     *
     * @param category The category for which to count questions.
     * @return The number of questions available in the specified category.
     */
    public int getQuestionCountForCategory(String category) {
        int count = 0;
        String query = "SELECT COUNT(q.id) AS question_count " +
                "FROM Questions q " +
                "JOIN Categories c ON q.category_id = c.id " +
                "WHERE c.name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, category);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("question_count");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    /**
     * Logs the player's answer to the database.
     *
     * @param sessionId The ID of the current session.
     * @param questionId The ID of the question being answered.
     * @param isCorrect True if the answer was correct, false otherwise.
     */
    public void logPlayerAnswer(int sessionId, int questionId, boolean isCorrect) {
        String query = "INSERT INTO PlayerAnswers (session_id, question_id, is_correct) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, sessionId);
            stmt.setInt(2, questionId);
            stmt.setBoolean(3, isCorrect);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the ID of a question based on its text.
     *
     * @param question The text of the question whose ID is to be retrieved.
     * @return The ID of the question, or -1 if the question does not exist.
     */
    public int getQuestionId(String question) {
        int questionId = -1;
        String query = "SELECT id FROM Questions WHERE question = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, question);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                questionId = rs.getInt("id");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questionId;
    }
}
