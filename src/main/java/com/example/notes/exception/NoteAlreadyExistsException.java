package com.example.notes.exception;

// Custom exception for "Note Already Exists" scenarios
public class NoteAlreadyExistsException extends RuntimeException {
    public NoteAlreadyExistsException(String message) {
        super(message);
    }
}
