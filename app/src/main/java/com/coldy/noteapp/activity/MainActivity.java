package com.coldy.noteapp.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.coldy.noteapp.R;
import com.coldy.noteapp.adapter.MyAdapter;
import com.coldy.noteapp.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Note> lista = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
                startActivity(intent);
            }
        });

        iniciaLista();

        recyclerView = findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter(lista);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);
    }

    private void iniciaLista() {
        Note note = new Note();
        note.setId(1L);
        note.setTitulo("Titulo 1");
        note.setConteudo("Conteudo 1 teste");
        lista.add(note);
        note = new Note();
        note.setId(1L);
        note.setTitulo("Titulo 2");
        note.setConteudo("Conteudo 2 teste teste teste teste fasf df asdfsdfasdf sdfa sdfsadfsafasdfasfd dasdasdsad asdasdasdas ddasdasdasdasd asdasdasdasdasdasfasfasd");
        lista.add(note);
        note = new Note();
        note.setId(1L);
        note.setTitulo("Titulo 3");
        note.setConteudo("Conteudo 3 teste");
        lista.add(note);
        note = new Note();
        note.setId(1L);
        note.setTitulo("Titulo 4");
        note.setConteudo("Conteudo 4 teste");
        lista.add(note);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}