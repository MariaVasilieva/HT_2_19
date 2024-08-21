package org.example.ht_goit_2_14.notes;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NoteServiceImp implements NoteService {
    private final List<Note> notes = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @Override
    public List<Note> listAll() {
        return notes;
    }

    @Override
    public Note add(Note note) {
        note.setId(counter.incrementAndGet());
        notes.add(note);
        return note;
    }

    @Override
    public void deleteById(long id) {
        Optional<Note> noteToDelete = notes.stream().filter(note -> note.getId() == id).findFirst();
        if (noteToDelete.isPresent()) {
            notes.remove(noteToDelete.get());
        }
        else {
            throw new RuntimeException("Note not found");
        }
    }

    @Override
    public void update(Note note) {
        Optional<Note> noteToUpdate = notes.stream().filter(n -> n.getId() == note.getId())
                .findFirst();
        if (noteToUpdate.isPresent()) {
            noteToUpdate.get().setTitle(note.getTitle());
            noteToUpdate.get().setContent(note.getContent());
        }
        else {
            throw new RuntimeException("Note not found");
        }
    }

    @Override
    public Note getById(long id) {
        return notes.stream().filter(note -> note.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }
}
