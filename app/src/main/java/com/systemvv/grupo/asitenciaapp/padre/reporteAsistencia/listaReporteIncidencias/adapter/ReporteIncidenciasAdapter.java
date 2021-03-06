package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Incidencias;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.adapter.holder.ReporteIncidenciasHolder;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.listener.IncidenciasListener;

import java.util.List;

public class ReporteIncidenciasAdapter extends RecyclerView.Adapter<ReporteIncidenciasHolder> {

    private List<Incidencias> incidenciasList;
    private IncidenciasListener listener;

    public ReporteIncidenciasAdapter(List<Incidencias> incidenciasList, IncidenciasListener listener) {
        this.incidenciasList = incidenciasList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ReporteIncidenciasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reporte_incidencias, parent, false);
        return new ReporteIncidenciasHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReporteIncidenciasHolder holder, int position) {
        Incidencias incidencias = incidenciasList.get(position);
        holder.bind(incidencias,listener);
    }

    @Override
    public int getItemCount() {
        if (incidenciasList == null) return 0;
        return incidenciasList.size();
    }

    public void mostrarLista(List<Incidencias> incidenciasList) {
        this.incidenciasList = incidenciasList;
        notifyDataSetChanged();
    }

    public void eliminarItem(Incidencias incidencias) {
        int position = this.incidenciasList.indexOf(incidencias);
        if (position != -1) {
            this.incidenciasList.remove(incidencias);
            notifyItemRemoved(position);
        }
    }
}
