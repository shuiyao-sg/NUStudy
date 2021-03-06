package seedu.address.model.quiz;

import static java.time.LocalDate.parse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.address.model.question.Answer;
import seedu.address.model.question.Difficulty;
import seedu.address.model.question.QuestionBody;
import seedu.address.model.question.Subject;

/**
 * Represents a quiz result. Its answer, questionBody, subject,
 * difficulty, quizTime and result are guaranteed non-null.
 */
public class QuizResult {
    private final Answer answer;
    private final QuestionBody questionBody;
    private final Subject subject;
    private final Difficulty difficulty;
    private final String quizTime;
    private final boolean result;

    public QuizResult(Answer answer, QuestionBody questionBody, Subject subject, Difficulty difficulty,
                      String quizTime, boolean result) {
        this.answer = answer;
        this.questionBody = questionBody;
        this.subject = subject;
        this.difficulty = difficulty;
        this.quizTime = quizTime;
        this.result = result;
    }

    public Answer getAnswer() {
        return answer;
    }

    public QuestionBody getQuestionBody() {
        return questionBody;
    }

    public Subject getSubject() {
        return subject;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getQuizTime() {
        return quizTime;
    }

    public boolean getResult() {
        return result;
    }

    public String getResultToString() {
        return result ? "Correct" : "Incorrect";
    }

    /**
     * Returns true if the quizResult is stored within the given time period and false otherwise.
     * @param start The starting date.
     * @param end The ending date.
     * @return A boolean.
     */
    public boolean isWithinDate(LocalDate start, LocalDate end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = parse(quizTime.split(" ")[0], formatter);
        return !(date.isBefore(start) || date.isAfter(end));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof QuizResult)) {
            return false;
        }

        QuizResult otherQuizResult = (QuizResult) other;
        return otherQuizResult.getQuizTime().equals(getQuizTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer, questionBody, subject, difficulty, quizTime, result);
    }

    @Override
    public String toString() {
        return questionBody + "\n   " + answer + "\n";
    }
}
