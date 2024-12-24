package com.example.notes.service;


import com.example.notes.exception.NoteAlreadyExistsException;
import com.example.notes.exception.NoteNotFoundException;
import com.example.notes.model.Note;
import com.example.notes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            return optionalNote.get();
        } else {
            throw new NoteNotFoundException("Note not found with id: " + id);
        }
    }

    public Note addNote(Note note) {
        Optional<Note> existingNote = noteRepository.findByTitle(note.getTitle());
        if (existingNote.isPresent()) {
            throw new NoteAlreadyExistsException("Note with title already exists: " + note.getTitle());
        }
        return noteRepository.save(note);
    }

    public Note updateNote(Long id, Note updatedNote) {
        Note existingNote = getNoteById(id);
        existingNote.setTitle(updatedNote.getTitle());
        existingNote.setContent(updatedNote.getContent());
        return noteRepository.save(existingNote);
    }

    public void deleteNoteById(Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            noteRepository.deleteById(id);
        } else {
           throw new NoteNotFoundException("Note not found with id: " + id);
        }
    }
}


