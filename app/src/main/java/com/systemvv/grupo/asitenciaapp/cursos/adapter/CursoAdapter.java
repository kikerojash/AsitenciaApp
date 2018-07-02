package com.systemvv.grupo.asitenciaapp.cursos.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.cursos.adapter.holder.CursoHolder;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.cursos.listener.CursoListener;

import java.util.List;

public class CursoAdapter extends RecyclerView.Adapter<CursoHolder> {

    private List<CursoUi> cursoUiList;
    private CursoListener listener;

    public CursoAdapter(List<CursoUi> cursoUiList, CursoListener listener) {
        this.cursoUiList = cursoUiList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CursoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_curso, parent, false);
        return new CursoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoHolder holder, int position) {
        CursoUi cursoUi = cursoUiList.get(position);
        holder.bind(cursoUi,listener);
    }

    @Override
    public int getItemCount() {
        if (cursoUiList == null) return 0;
        return cursoUiList.size();
    }

    public void mostrarLista(List<CursoUi> cursoUiList) {
        this.cursoUiList = cursoUiList;
        notifyDataSetChanged();
    }

}
