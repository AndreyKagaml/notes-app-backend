package com.kahamlyk.task.service.interfaces;

import com.kahamlyk.task.entity.Note;
import org.hibernate.service.Service;

import java.util.List;

public interface NoteService  {
    Note create(Note note);
    Note readById(long id);
    Note update(Long id, Note note);
    void delete(long id);
    List<Note> getAll();
}
