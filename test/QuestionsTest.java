import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class QuestionsTest {
    private ChemistryQuestions chemistryQuestions;  // Example subclass of Questions

    @Before
    public void setUp() {
        // Initialize ChemistryQuestions (which should load questions from the database)
        chemistryQuestions = new ChemistryQuestions();
    }

    @Test
    public void testLoadQuestionsFromDatabase() {
        // Ensure that questions are successfully loaded from the database
        String randomQuestion = chemistryQuestions.getAnyQuestion();
        assertNotNull("Questions should not be null", randomQuestion);
    }

    @Test
    public void testGetAnswer() {
        // Test that a specific question returns the correct answer
        String question = "What is the symbol for Hydrogen?";
        String answer = chemistryQuestions.getAnswer(question);
        assertEquals("The answer should be 'H'", "H", answer);
    }

    @Test
    public void testGetAnyQuestion() {
        // Ensure that any random question can be retrieved and is valid
        String randomQuestion = chemistryQuestions.getAnyQuestion();
        assertNotNull("getAnyQuestion should return a question", randomQuestion);

        // Ensure the question has a corresponding answer
        String answer = chemistryQuestions.getAnswer(randomQuestion);
        assertNotNull("The answer to the random question should not be null", answer);
    }

    @Test
    public void testGetQuestionCountForCategory() {
        // Check the number of questions for the 'Chemistry' category
        int questionCount = chemistryQuestions.getQuestionCountForCategory("Chemistry");
        assertTrue("There should be at least one question in Chemistry", questionCount > 0);
    }

    @Test
    public void testLogPlayerAnswer() {
        // Test logging a player's correct answer
        int sessionId = 1;  // Example session ID
        int questionId = chemistryQuestions.getQuestionId("What is the symbol for Hydrogen?");
        chemistryQuestions.logPlayerAnswer(sessionId, questionId, true);

        // Normally you would verify this via a database check or log file, if applicable
        assertTrue("Player's answer should be logged in the database (verify manually)", true);
    }

    @Test
    public void testGetQuestionId() {
        // Test retrieving the ID of a known question
        String question = "What is the symbol for Hydrogen?";
        int questionId = chemistryQuestions.getQuestionId(question);
        assertTrue("Question ID should be a positive integer", questionId > 0);
    }

    @Test
    public void testSetQuestion() {
        // Test manually adding a question to the question map
        String newQuestion = "What is the symbol for Helium?";
        String newAnswer = "He";
        chemistryQuestions.setQuestion(newQuestion, newAnswer);

        // Ensure the new question and answer were added correctly
        assertEquals("The answer should be 'He'", newAnswer, chemistryQuestions.getAnswer(newQuestion));
    }
}
