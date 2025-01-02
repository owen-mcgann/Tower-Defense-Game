import java.util.Map;

public class GeographyQuestions extends Questions {

    // Constructor that loads geography questions from the database
    public GeographyQuestions() {
        super("Geography");  // Pass "Geography" as the category to the parent class
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

    public void setAnswer(String question, String answer) {
        questionMap.put(question, answer);  // Update or set the answer for the given question
    }
}