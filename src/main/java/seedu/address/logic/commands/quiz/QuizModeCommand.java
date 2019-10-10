package seedu.address.logic.commands.quiz;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DIFFICULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUMBER;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;

import seedu.address.model.Model;
import seedu.address.model.question.Difficulty;
import seedu.address.model.question.Subject;

/**
 * Enters the quiz mode of NUStudy.
 */
public class QuizModeCommand extends Command {
    public static final String COMMAND_WORD = "quiz";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Enters quiz mode where questions will be displayed "
            + " and answer input is required to check the accuracy.\n"
            + "Parameters: "
            + PREFIX_NUMBER + "number of questions (a positive integer)"
            + PREFIX_DIFFICULTY + "DIFFICULTY "
            + PREFIX_SUBJECT + "SUBJECT "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NUMBER + "8"
            + PREFIX_DIFFICULTY + "Easy "
            + PREFIX_SUBJECT + "Maths ";

    public static final String MESSAGE_SUCCESS = "You have successfully entered quiz mode!";
    public static final String MESSAGE_NOT_ENOUGH_QUESTIONS = "There are not enough questions to do";


    private final int numOfQuestions;
    private final Subject subject;
    private final Difficulty difficulty;

    /**
     * @param numOfQuestions total number of questions for user to do
     */
    public QuizModeCommand(int numOfQuestions, Subject subject, Difficulty difficulty) {
        this.numOfQuestions = numOfQuestions;
        this.subject = subject;
        this.difficulty = difficulty;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
