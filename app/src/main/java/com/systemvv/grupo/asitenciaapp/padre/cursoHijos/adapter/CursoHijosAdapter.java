package com.systemvv.grupo.asitenciaapp.padre.cursoHijos.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.systemvv.grupo.asitenciaapp.R;

import com.systemvv.grupo.asitenciaapp.padre.cursoHijos.adapter.holder.CursoHijosHolder;
import com.systemvv.grupo.asitenciaapp.padre.cursoHijos.listener.CursoHijosListener;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;

import java.util.List;

public class CursoHijosAdapter extends RecyclerView.Adapter<CursoHijosHolder> {

    private List<Cursos> cursosList;
    private CursoHijosListener listener;

    public CursoHijosAdapter(List<Cursos> cursosList) {
        this.cursosList = cursosList;
      //  this.listener = listener;
    }

    @NonNull
    @Override
    public CursoHijosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cursos_hijos, parent, false);
        return new CursoHijosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoHijosHolder holder, int position) {
        Cursos cursos = cursosList.get(position);
        holder.bind(cursos);
    }

    @Override
    public int getItemCount() {
        if (cursosList == null) return 0;
        return cursosList.size();
    }
    public void mostrarLista(List<Cursos>cursosList) {
        this.cursosList = cursosList;
        notifyDataSetChanged();
    }
}
