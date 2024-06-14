package com.kahamlyk.task.service.implementation;

import com.kahamlyk.task.entity.Note;
import com.kahamlyk.task.exeption.EntityErrorException;
import com.kahamlyk.task.exeption.NoteNotFoundException;
import com.kahamlyk.task.repository.NoteRepository;
import com.kahamlyk.task.service.interfaces.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note create(Note note) {
        try {
            return noteRepository.save(note);
        }catch (Exception e){
            throw new EntityErrorException("Note with this title already exists");
        }
    }

    @Override
    public Note readById(long id) {
        return noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(String.format("Note with %d id doesn't exist", id)));
    }

    @Override
    public Note update(Long id, Note note) {
        note.setId(id);
        return noteRepository.save(note);
    }

    @Override
    public void delete(long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public List<Note> getAll() {
        return noteRepository.findAll();
    }
}
