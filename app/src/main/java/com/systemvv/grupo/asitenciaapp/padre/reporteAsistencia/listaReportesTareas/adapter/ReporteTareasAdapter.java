package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Tareas;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas.adapter.holder.ReporteTareasHolder;

import java.util.List;

public class ReporteTareasAdapter extends RecyclerView.Adapter<ReporteTareasHolder> {

    private List<Tareas> tareasList;

    public ReporteTareasAdapter(List<Tareas> tareasList) {
        this.tareasList = tareasList;
    }

    @NonNull
    @Override
    public ReporteTareasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reporte_tarea, parent, false);
        return new ReporteTareasHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReporteTareasHolder holder, int position) {
        Tareas tareas = tareasList.get(position);
        holder.bind(tareas);
    }

    @Override
    public int getItemCount() {
        if (tareasList == null) return 0;
        return tareasList.size();
    }

    public void mostrarLista(List<Tareas> tareasList) {
        this.tareasList = tareasList;
        notifyDataSetChanged();
    }

}
