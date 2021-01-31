package com.coldy.noteapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import com.coldy.noteapp.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteDAO implements INoteDAO {

    private SQLiteDatabase dbWrite;
    private SQLiteDatabase dbRead;

    public NoteDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        this.dbWrite = dbHelper.getWritableDatabase();
        this.dbRead = dbHelper.getReadableDatabase();
    }

    @Override
    public boolean save(Note note) {

        if (note == null || note.getTitulo() == null || note.getConteudo() == null) {
            return false;
        }

        ContentValues cv = new ContentValues();
        cv.put("titulo", note.getTitulo());
        cv.put("conteudo", note.getConteudo());

        Long id = dbWrite.insert(DbHelper.TABLE_NAME, null, cv);

        if (id == -1) {
            Log.e("INFO", "Erro ao salvar a nota");
            return false;
        }

        note.setId(id);
        Log.i("INFO", "Nota salva com sucesso");

        return true;
    }

    @Override
    public boolean update(Note note) {
        ContentValues cv = new ContentValues();
        cv.put("titulo", note.getTitulo());
        cv.put("conteudo", note.getConteudo());

        try {
            int rowsAffected = dbWrite.update(
                    DbHelper.TABLE_NAME, cv, "id = ?",
                    new String[]{note.getId().toString()}
                );

            Log.i("INFO", "Linhas atualizadas: " + rowsAffected);

        } catch (SQLiteException e) {
            Log.e("INFO", "Erro ao atualizar a nota: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Note note) {

        try {
            dbWrite.delete(DbHelper.TABLE_NAME, "id = ?", new String[]{note.getId().toString()});
            Log.i("INFO", "Linhas excluida");

        } catch (SQLiteException e) {
            Log.e("INFO", "Erro ao excluir: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Note findById(Long id) {
        return null;
    }

    @Override
    public List<Note> filter(String regex) {
        List<Note> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABLE_NAME +
                " WHERE titulo LIKE ? OR conteudo LIKE ?";

        regex = "%" + regex + "%";

        Cursor cursor = dbRead.rawQuery(sql, new String[]{regex, regex});

        while (cursor.moveToNext()) {
            Note note = new Note();
            note.setId(cursor.getLong(cursor.getColumnIndex("id")));
            note.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            note.setConteudo(cursor.getString(cursor.getColumnIndex("conteudo")));

            lista.add(note);
        }
        cursor.close();
        return lista;
    }

    @Override
    public List<Note> findAll() {
        List<Note> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABLE_NAME;

        Cursor cursor = dbRead.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            Note note = new Note();
            note.setId(cursor.getLong(cursor.getColumnIndex("id")));
            note.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            note.setConteudo(cursor.getString(cursor.getColumnIndex("conteudo")));

            lista.add(note);
        }

        cursor.close();
        return lista;
    }
}
