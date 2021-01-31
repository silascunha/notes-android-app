package com.coldy.noteapp.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.coldy.noteapp.R;
import com.coldy.noteapp.db.INoteDAO;
import com.coldy.noteapp.db.NoteDAO;
import com.coldy.noteapp.model.Note;

public class NoteActivity extends AppCompatActivity {

    private EditText editTitulo;
    private EditText editConteudo;

    private boolean isEditing;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTitulo = findViewById(R.id.editTitulo);
        editConteudo = findViewById(R.id.editConteudo);

        isEditing = getIntent().getBooleanExtra("isEditing", false);

        if (isEditing) {
            note = (Note) getIntent().getSerializableExtra("note");
            editTitulo.setText(note.getTitulo());
            editConteudo.setText(note.getConteudo());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notes, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menuId = item.getItemId();

        if (menuId == R.id.menuitem_salvar) {
            saveNote();
            this.finish();
            return true;
        }
        if (menuId == android.R.id.home) {
            String msg = (isEditing) ? "Alterações descartadas" : "Nota descartada";
            Toast.makeText(getApplicationContext(),
                    msg, Toast.LENGTH_SHORT).show();
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isEmpty(EditText edit) {
        String text = edit.getText().toString().trim();

        return text.length() == 0 || text.matches("");
    }

    private void saveNote() {
        INoteDAO noteDAO = new NoteDAO(getApplicationContext());

        boolean isAllEmpty = isEmpty(editTitulo) && isEmpty(editConteudo);

        if (isAllEmpty) {
            Toast.makeText(getApplicationContext(),
                    "Não é possível salvar pois nada foi preenchido.", Toast.LENGTH_SHORT).show();

            return;
        }

        if (isEditing) {
            note.setTitulo(editTitulo.getText().toString());
            note.setConteudo(editConteudo.getText().toString());
            noteDAO.update(note);

            Toast.makeText(this, "Nota atualizada!", Toast.LENGTH_SHORT).show();
            return;
        }
        note = new Note();
        note.setTitulo(editTitulo.getText().toString());
        note.setConteudo(editConteudo.getText().toString());

        noteDAO.save(note);

        Toast.makeText(this, "Nota salva com sucesso!", Toast.LENGTH_SHORT).show();
    }
}