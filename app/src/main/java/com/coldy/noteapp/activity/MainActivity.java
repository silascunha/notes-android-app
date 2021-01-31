package com.coldy.noteapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.coldy.noteapp.R;
import com.coldy.noteapp.adapter.MyAdapter;
import com.coldy.noteapp.db.INoteDAO;
import com.coldy.noteapp.db.NoteDAO;
import com.coldy.noteapp.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnCardClickListener {

    private List<Note> listaNotas = new ArrayList<>();
    private RecyclerView recyclerView;
    private INoteDAO noteDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);

        noteDAO = new NoteDAO(getApplicationContext());

        recyclerView = findViewById(R.id.recyclerView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
                intent.putExtra("isEditing", false);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        atualizarLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        initializeSearchView(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast toast = Toast.makeText(this, "Configurações clicado", Toast.LENGTH_SHORT);
            toast.show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void atualizarLista() {
        listaNotas = noteDAO.findAll();

        MyAdapter adapter = new MyAdapter(listaNotas, this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);
    }

    private void initializeSearchView(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.menuitem_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        String hint = (String) this.getText(R.string.menuitem_search);
        searchView.setQueryHint(hint);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onCardClick(int position) {
        Note note = listaNotas.get(position);
        Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
        intent.putExtra("note", note);
        intent.putExtra("isEditing", true);
        startActivity(intent);
    }

    @Override
    public void onCardLongClick(int position) {

    }
}