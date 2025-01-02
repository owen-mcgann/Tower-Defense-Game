import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChemistryQuestionsTest {

    private ChemistryQuestions chemistryQuestions;

    @Before
    public void setUp() {
        // Initialize the ChemistryQuestions class
        chemistryQuestions = new ChemistryQuestions();

        // Clear the question map to ensure tests start with an empty state
        chemistryQuestions.questionMap.clear();

        // Set some sample questions for testing (to simulate loading from the database)
        chemistryQuestions.setQuestion("What is the symbol for Hydrogen?", "H");
        chemistryQuestions.setQuestion("What is the symbol for Oxygen?", "O");
    }

    @Test
    public void testSetAndGetQuestion() {
        // Test setting a new question and retrieving the answer
        chemistryQuestions.setQuestion("What is the symbol for Nitrogen?", "N");
        String answer = chemistryQuestions.getAnswer("What is the symbol for Nitrogen?");
        assertEquals("The answer should be 'N'", "N", answer);
    }

    @Test
    public void testGetAnswer() {
        // Test retrieving an answer for a known question
        String answer = chemistryQuestions.getAnswer("What is the symbol for Hydrogen?");
        assertEquals("The answer should be 'H'", "H", answer);

        // Test retrieving an answer for a non-existent question
        String nonExistentAnswer = chemistryQuestions.getAnswer("What is the symbol for Carbon?");
        assertNull("Answer should be null for a question that doesn't exist", nonExistentAnswer);
    }

    @Test
    public void testGetQuestion() {
        // Test retrieving a question from a known answer
        String question = chemistryQuestions.getQuestion("O");
        assertEquals("The question should be 'What is the symbol for Oxygen?'", "What is the symbol for Oxygen?", question);

        // Test retrieving a question for a non-existent answer
        String nonExistentQuestion = chemistryQuestions.getQuestion("C");
        assertNull("Question should be null for an answer that doesn't exist", nonExistentQuestion);
    }

    @Test
    public void testSetAnswer() {
        // Test setting a new answer for an existing question
        chemistryQuestions.setAnswer("What is the symbol for Hydrogen?", "H2");
        String updatedAnswer = chemistryQuestions.getAnswer("What is the symbol for Hydrogen?");
        assertEquals("The updated answer should be 'H2'", "H2", updatedAnswer);
    }
}
