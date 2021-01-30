package com.coldy.noteapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.coldy.noteapp.R;
import com.coldy.noteapp.model.Note;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Note> lista;

    public MyAdapter(List<Note> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_note, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note = lista.get(position);

        holder.textTitulo.setText(note.getTitulo());
        holder.textConteudo.setText(note.getConteudo());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    protected static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textTitulo;
        TextView textConteudo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitulo = itemView.findViewById(R.id.textCardTitulo);
            textConteudo = itemView.findViewById(R.id.textCardConteudo);
        }
    }
}
