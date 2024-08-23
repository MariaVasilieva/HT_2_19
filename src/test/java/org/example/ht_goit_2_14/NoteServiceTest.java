package org.example.ht_goit_2_14;

import org.example.ht_goit_2_14.notes.Note;
import org.example.ht_goit_2_14.notes.NoteService;
import org.example.ht_goit_2_14.notes.NoteServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class NoteServiceTest {
    private NoteService noteService;
    @BeforeEach
    void setUp() {
        noteService = new NoteServiceImp();
    }
    @Test
    void testAddAndListAll() {
        Note note = new Note();
        note.setTitle("First Note");
        note.setContent("This is the first note.");

        Note addedNote = noteService.add(note);
        List<Note> allNotes = noteService.listAll();

        assertEquals(1, allNotes.size());
        assertEquals(addedNote, allNotes.get(0));
    }

    @Test
    void testDeleteById() {
        Note note = new Note();
        note.setTitle("First Note");
        note.setContent("This is the first note.");

        Note addedNote = noteService.add(note);
        noteService.deleteById(addedNote.getId());

        List<Note> allNotes = noteService.listAll();
        assertTrue(allNotes.isEmpty());
    }

    @Test
    void testUpdate() {
        Note note = new Note();
        note.setTitle("First Note");
        note.setContent("This is the first note.");

        Note addedNote = noteService.add(note);
        addedNote.setTitle("Updated Note");
        addedNote.setContent("This is the updated content.");

        noteService.update(addedNote);
        Note updatedNote = noteService.getById(addedNote.getId());

        assertEquals("Updated Note", updatedNote.getTitle());
        assertEquals("This is the updated content.", updatedNote.getContent());
    }

    @Test
    void testGetById() {
        Note note = new Note();
        note.setTitle("First Note");
        note.setContent("This is the first note.");

        Note addedNote = noteService.add(note);
        Note foundNote = noteService.getById(addedNote.getId());

        assertNotNull(foundNote);
        assertEquals(addedNote, foundNote);
    }
}