import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GeographyQuestionsTest {

    private GeographyQuestions geographyQuestions;

    @Before
    public void setUp() {
        // Initialize the GeographyQuestions class
        geographyQuestions = new GeographyQuestions();

        // Clear the question map to ensure tests start with an empty state
        geographyQuestions.questionMap.clear();

        // Set some sample questions for testing
        geographyQuestions.setQuestion("What is the capital of France?", "Paris");
        geographyQuestions.setQuestion("What is the capital of Germany?", "Berlin");
    }

    @Test
    public void testSetAndGetQuestion() {
        // Test setting a new question and retrieving the answer
        geographyQuestions.setQuestion("What is the capital of Spain?", "Madrid");
        String answer = geographyQuestions.getAnswer("What is the capital of Spain?");
        assertEquals("The answer should be 'Madrid'", "Madrid", answer);
    }

    @Test
    public void testGetAnswer() {
        // Test retrieving an answer for a known question
        String answer = geographyQuestions.getAnswer("What is the capital of France?");
        assertEquals("The answer should be 'Paris'", "Paris", answer);

        // Test retrieving an answer for a non-existent question
        String nonExistentAnswer = geographyQuestions.getAnswer("What is the capital of Italy?");
        assertNull("Answer should be null for a question that doesn't exist", nonExistentAnswer);
    }

    @Test
    public void testGetQuestion() {
        // Test retrieving a question from a known answer
        String question = geographyQuestions.getQuestion("Berlin");
        assertEquals("The question should be 'What is the capital of Germany?'", "What is the capital of Germany?", question);

        // Test retrieving a question for a non-existent answer
        String nonExistentQuestion = geographyQuestions.getQuestion("Rome");
        assertNull("Question should be null for an answer that doesn't exist", nonExistentQuestion);
    }

    @Test
    public void testSetAnswer() {
        // Test setting a new answer for an existing question
        geographyQuestions.setAnswer("What is the capital of France?", "Marseille");
        String updatedAnswer = geographyQuestions.getAnswer("What is the capital of France?");
        assertEquals("The updated answer should be 'Marseille'", "Marseille", updatedAnswer);
    }
}
