package seedu.address.logic.parser.questionparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.questioncommands.FindQuestionCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.question.QuestionContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindQuestionCommand object
 */
public class FindQuestionCommandParser implements Parser<FindQuestionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindQuestionCommand
     * and returns a FindQuestionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindQuestionCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindQuestionCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindQuestionCommand(new QuestionContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
