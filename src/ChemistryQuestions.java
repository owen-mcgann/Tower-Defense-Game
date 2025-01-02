import java.util.Map;

public class ChemistryQuestions extends Questions {

    // Constructor that loads chemistry questions from the database
    public ChemistryQuestions() {
        super("Chemistry");  // Pass "Chemistry" as the category to the parent class (Questions)
    }

    @Override
    public void setQuestion(String question, String answer) {
        questionMap.put(question, answer);  // Store the question and its corresponding answer
    }

    @Override
    public String getAnswer(String question) {
        return questionMap.get(question);  // Retrieve the answer for the given question
    }

    /**
     * Retrieves the question corresponding to a specific answer.
     * This method searches through the questionMap.
     * @param answer The answer text.
     * @return The question that corresponds to the given answer, or null if no match is found.
     */
    public String getQuestion(String answer) {
        for (Map.Entry<String, String> entry : questionMap.entrySet()) {
            if (entry.getValue().equals(answer)) {
                return entry.getKey();  // Return the question when the answer matches
            }
        }
        return null;  // Return null if no matching question is found
    }

    /**
     * Updates or sets the answer for a given question in the questionMap.
     * This method allows modifying the answer for an existing question.
     * @param question The text of the question.
     * @param answer The new answer to be set.
     */
    public void setAnswer(String question, String answer) {
        questionMap.put(question, answer);  // Update or set the answer for the given question
    }
}