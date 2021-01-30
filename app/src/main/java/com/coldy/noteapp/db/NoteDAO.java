package com.coldy.noteapp.db;

import android.database.sqlite.SQLiteDatabase;
import com.coldy.noteapp.model.Note;

import java.util.List;

public class NoteDAO implements INoteDAO {

    private SQLiteDatabase dbWrite;
    private SQLiteDatabase dbRead;

    public NoteDAO(DbHelper dbHelper) {
        this.dbWrite = dbHelper.getWritableDatabase();
        this.dbRead = dbHelper.getReadableDatabase();
    }

    @Override
    public boolean save(Note note) {
        return true;
    }

    @Override
    public boolean update(Note note) {
        return true;
    }

    @Override
    public boolean delete(Note note) {
        return true;
    }

    @Override
    public Note findById(Long id) {
        return null;
    }

    @Override
    public List<Note> findAll() {
        return null;
    }
}
