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
    private OnCardClickListener mListener;

    public MyAdapter(List<Note> lista, OnCardClickListener mListener) {
        this.lista = lista;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_note, parent, false);

        return new MyViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note = lista.get(position);

        if(note.getTitulo() == null || note.getTitulo().equals("")) {
            holder.textTitulo.setVisibility(View.GONE);
        }
        else {
            holder.textTitulo.setText(note.getTitulo());
        }

        if (note.getConteudo() == null || note.getConteudo().equals("")) {
            holder.textConteudo.setVisibility(View.GONE);
        } else {
            holder.textConteudo.setText(note.getConteudo());
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    protected static class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        TextView textTitulo;
        TextView textConteudo;
        OnCardClickListener onCardClickListener;

        public MyViewHolder(@NonNull View itemView, OnCardClickListener onCardClickListener) {
            super(itemView);

            textTitulo = itemView.findViewById(R.id.textCardTitulo);
            textConteudo = itemView.findViewById(R.id.textCardConteudo);
            this.onCardClickListener = onCardClickListener;

            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            onCardClickListener.onCardClick(position);
        }

        @Override
        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            onCardClickListener.onCardLongClick(position);
            return true;
        }
    }

    public interface OnCardClickListener {
        void onCardClick(int position);
        void onCardLongClick(int position);
    }

}
