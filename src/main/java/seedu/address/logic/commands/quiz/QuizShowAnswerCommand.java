package seedu.address.logic.commands.quiz;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.question.Answer;

/**
 * Shows an answer for the question in quiz.
 */
public class QuizShowAnswerCommand extends Command {
    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_SUCCESS = "The answer is: %1$s";
    public static final String INDEX_EXCEED_RANGE = "The index input is out of the range of quiz questions!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Answer answer = model.showQuizAnswer();

        return new CommandResult(String.format(MESSAGE_SUCCESS, answer.toString()), 4);
    }
}
