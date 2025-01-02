import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MathQuestionsTest {

    private MathQuestions mathQuestions;

    @Before
    public void setUp() {
        // Initialize the MathQuestions class
        mathQuestions = new MathQuestions();

        // Clear the question map to ensure tests start with an empty state
        mathQuestions.questionMap.clear();

        // Set some sample questions for testing
        mathQuestions.setQuestion("What is 2 + 2?", "4");
        mathQuestions.setQuestion("What is 5 * 5?", "25");
    }

    @Test
    public void testSetAndGetQuestion() {
        // Test setting a new question and retrieving the answer
        mathQuestions.setQuestion("What is 10 / 2?", "5");
        String answer = mathQuestions.getAnswer("What is 10 / 2?");
        assertEquals("The answer should be '5'", "5", answer);
    }

    @Test
    public void testGetAnswer() {
        // Test retrieving an answer for a known question
        String answer = mathQuestions.getAnswer("What is 2 + 2?");
        assertEquals("The answer should be '4'", "4", answer);

        // Test retrieving an answer for a non-existent question
        String nonExistentAnswer = mathQuestions.getAnswer("What is 3 + 3?");
        assertNull("Answer should be null for a question that doesn't exist", nonExistentAnswer);
    }

    @Test
    public void testGetQuestion() {
        // Test retrieving a question from a known answer
        String question = mathQuestions.getQuestion("25");
        assertEquals("The question should be 'What is 5 * 5?'", "What is 5 * 5?", question);

        // Test retrieving a question for a non-existent answer
        String nonExistentQuestion = mathQuestions.getQuestion("6");
        assertNull("Question should be null for an answer that doesn't exist", nonExistentQuestion);
    }

    @Test
    public void testSetAnswer() {
        // Test setting a new answer for an existing question
        mathQuestions.setAnswer("What is 2 + 2?", "Four");
        String updatedAnswer = mathQuestions.getAnswer("What is 2 + 2?");
        assertEquals("The updated answer should be 'Four'", "Four", updatedAnswer);
    }
}
