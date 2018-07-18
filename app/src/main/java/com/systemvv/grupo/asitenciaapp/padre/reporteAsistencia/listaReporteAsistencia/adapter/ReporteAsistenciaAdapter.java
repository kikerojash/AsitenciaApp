package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteAsistencia.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteAsistencia.adapter.holder.ReporteAsistenciaHolder;

import java.util.List;

public class ReporteAsistenciaAdapter extends RecyclerView.Adapter<ReporteAsistenciaHolder> {

    private List<Asistencia> asistenciaList;

    public ReporteAsistenciaAdapter(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
    }

    @NonNull
    @Override
    public ReporteAsistenciaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reporte_asistencia, parent, false);
        return new ReporteAsistenciaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReporteAsistenciaHolder holder, int position) {
        Asistencia asistencia = asistenciaList.get(position);
        holder.bind(asistencia);

    }

    @Override
    public int getItemCount() {
        if (asistenciaList == null) return 0;
        return asistenciaList.size();
    }

    public void mostrarLista(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
        notifyDataSetChanged();
    }
}
