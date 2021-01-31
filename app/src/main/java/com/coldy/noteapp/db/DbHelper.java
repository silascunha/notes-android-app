package com.coldy.noteapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final Integer VERSION = 1;
    public static final String DB_NAME = "db_anotacoes";
    public static final String TABLE_NAME = "notas";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " titulo VARCHAR, conteudo TEXT)";

        try {
            db.execSQL(sql);
            Log.i("INFO DB", "SUCESSO AO CRIAR A TABELA");
        }
        catch (SQLiteException e) {
            Log.i("INFO DB", "ERRO AO CRIAR A TABELA ///" + "CAUSA: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
