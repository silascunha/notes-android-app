package com.coldy.noteapp.db;

import com.coldy.noteapp.model.Note;

import java.util.List;

public interface INoteDAO {

    boolean save(Note note);
    boolean update(Note note);
    boolean delete(Note note);
    Note findById(Long id);
    List<Note> findAll();

}
