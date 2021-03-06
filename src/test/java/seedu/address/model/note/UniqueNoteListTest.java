package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTENT_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppData.ALICE;
import static seedu.address.testutil.TypicalAppData.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.note.exceptions.DuplicateTitleException;
import seedu.address.model.note.exceptions.NoteNotFoundException;
import seedu.address.testutil.NoteBuilder;

class UniqueNoteListTest {

    private final UniqueNoteList uniqueNoteList = new UniqueNoteList();

    @Test
    void contains_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueNoteList.contains(null));
    }

    @Test
    void contains_noteNotInList_returnsFalse() {
        assertFalse(uniqueNoteList.contains(ALICE));
    }

    @Test
    void contains_noteInList_returnsTrue() {
        uniqueNoteList.add(ALICE);
        assertTrue(uniqueNoteList.contains(ALICE));
    }

    @Test
    void contains_noteWithSameIdentityFieldsInList_returnsTrue() {
        uniqueNoteList.add(ALICE);
        Note editedAlice = new NoteBuilder(ALICE).withContent(VALID_CONTENT_BOB).build();
        assertTrue(uniqueNoteList.contains(editedAlice));
    }

    @Test
    void add_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueNoteList.add(null));
    }

    @Test
    void add_duplicateNote_throwsDuplicateNoteException() {
        uniqueNoteList.add(ALICE);
        assertThrows(DuplicateTitleException.class, () -> uniqueNoteList.add(ALICE));
    }

    @Test
    void setNote_nullTargetNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueNoteList.setNote(null, ALICE));
    }

    @Test
    void setNote_nullEditedNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueNoteList.setNote(ALICE, null));
    }

    @Test
    void setNote_targetNoteNotInList_throwsNoteNotFoundException() {
        assertThrows(NoteNotFoundException.class, () -> uniqueNoteList.setNote(ALICE, ALICE));
    }

    @Test
    void setNote_editedNoteIsSameNote_success() {
        uniqueNoteList.add(ALICE);
        uniqueNoteList.setNote(ALICE, ALICE);
        UniqueNoteList expectedUniqueNoteList = new UniqueNoteList();
        expectedUniqueNoteList.add(ALICE);
        assertEquals(expectedUniqueNoteList, uniqueNoteList);
    }

    @Test
    void setNote_editedNoteHasSameIdentity_success() {
        uniqueNoteList.add(ALICE);
        Note editedAlice = new NoteBuilder(ALICE).withContent(VALID_CONTENT_BOB).build();
        uniqueNoteList.setNote(ALICE, editedAlice);
        UniqueNoteList expectedUniqueNoteList = new UniqueNoteList();
        expectedUniqueNoteList.add(editedAlice);
        assertEquals(expectedUniqueNoteList, uniqueNoteList);
    }

    @Test
    void setNote_editedNoteHasDifferentIdentity_success() {
        uniqueNoteList.add(ALICE);
        uniqueNoteList.setNote(ALICE, BOB);
        UniqueNoteList expectedUniqueNoteList = new UniqueNoteList();
        expectedUniqueNoteList.add(BOB);
        assertEquals(expectedUniqueNoteList, uniqueNoteList);
    }

    @Test
    void setNote_editedNoteHasNonUniqueIdentity_throwsDuplicateNoteException() {
        uniqueNoteList.add(ALICE);
        uniqueNoteList.add(BOB);
        assertThrows(DuplicateTitleException.class, () -> uniqueNoteList.setNote(ALICE, BOB));
    }

    @Test
    void remove_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueNoteList.remove(null));
    }

    @Test
    void remove_noteDoesNotExist_throwsNoteNotFoundException() {
        assertThrows(NoteNotFoundException.class, () -> uniqueNoteList.remove(ALICE));
    }

    @Test
    void remove_existingNote_removesNote() {
        uniqueNoteList.add(ALICE);
        uniqueNoteList.remove(ALICE);
        UniqueNoteList expectedUniqueNoteList = new UniqueNoteList();
        assertEquals(expectedUniqueNoteList, uniqueNoteList);
    }

    @Test
    void setNotes_nullUniqueNoteList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueNoteList.setNotes((UniqueNoteList) null));
    }

    @Test
    void setNotes_uniqueNoteList_replacesOwnListWithProvidedUniqueNoteList() {
        uniqueNoteList.add(ALICE);
        UniqueNoteList expectedUniqueNoteList = new UniqueNoteList();
        expectedUniqueNoteList.add(BOB);
        uniqueNoteList.setNotes(expectedUniqueNoteList);
        assertEquals(expectedUniqueNoteList, uniqueNoteList);
    }

    @Test
    void setNotes_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueNoteList.setNotes((List<Note>) null));
    }

    @Test
    void setNotes_list_replacesOwnListWithProvidedList() {
        uniqueNoteList.add(ALICE);
        List<Note> noteList = Collections.singletonList(BOB);
        uniqueNoteList.setNotes(noteList);
        UniqueNoteList expectedUniqueNoteList = new UniqueNoteList();
        expectedUniqueNoteList.add(BOB);
        assertEquals(expectedUniqueNoteList, uniqueNoteList);
    }

    @Test
    void setNotes_listWithDuplicateNotes_throwsDuplicateNoteException() {
        List<Note> listWithDuplicateNotes = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateTitleException.class, () -> uniqueNoteList.setNotes(listWithDuplicateNotes));
    }

    @Test
    void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueNoteList.asUnmodifiableObservableList().remove(0));
    }
}
