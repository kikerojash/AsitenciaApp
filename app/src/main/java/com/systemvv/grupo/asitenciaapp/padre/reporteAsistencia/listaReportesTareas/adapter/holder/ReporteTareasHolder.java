package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Tareas;
import com.systemvv.grupo.asitenciaapp.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReporteTareasHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textView10)
    TextView textViewConteo;
    @BindView(R.id.textView9)
    TextView textViewDescripcionTarea;
    @BindView(R.id.textViewFechaHora)
    TextView textViewFechaHora;



    public ReporteTareasHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Tareas tareas) {
        textViewConteo.setText(tareas.getConteo() + "");
        textViewDescripcionTarea.setText(Utils.capitalize(tareas.getDescripcionTarea()));
        textViewFechaHora.setText("Fecha: " + tareas.getFecha() + " Hora: " + tareas.getHora());
    }
}
