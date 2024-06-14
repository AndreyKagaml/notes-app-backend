package com.kahamlyk.task.controller;

import com.kahamlyk.task.dto.NoteDTO;
import com.kahamlyk.task.entity.Note;
import com.kahamlyk.task.service.interfaces.NoteService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "http://localhost:3000")
public class NoteController {


    private final NoteService noteService;
    private final ModelMapper modelMapper;

    @Autowired
    public NoteController(NoteService noteService, ModelMapper modelMapper) {
        this.noteService = noteService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<NoteDTO> getAllNotes() {
        return noteService.getAll().stream().map(this::convertToDTO).toList();
    }

    @GetMapping("/{id}")
    public NoteDTO getNoteById(@PathVariable Long id) {
        return convertToDTO(noteService.readById(id));
    }

    @PostMapping
    public Note createNote(@RequestBody @Valid NoteDTO noteDTO) {
        return noteService.create(convertToEntity(noteDTO));
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody @Valid NoteDTO noteDTO) {
        Note note = convertToEntity(noteDTO);
        return noteService.update(id, note);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteService.delete(id);
    }

    private Note convertToEntity(NoteDTO noteDTO) {
        return modelMapper.map(noteDTO, Note.class);
    }

    private NoteDTO convertToDTO(Note note) {
        return modelMapper.map(note, NoteDTO.class);
    }
}
