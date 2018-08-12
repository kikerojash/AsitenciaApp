package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Incidencias;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReporteIncidenciasHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.textView10)
    TextView textViewConteo;
    @BindView(R.id.textView9)
    TextView textViewDescripcionIncidencia;
    @BindView(R.id.textViewFechaHora)
    TextView textViewFechaHora;
    @BindView(R.id.btnSignin)
    Button button;

    public ReporteIncidenciasHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Incidencias incidencias) {
        textViewConteo.setText(incidencias.getConteo()+"");
        textViewDescripcionIncidencia.setText(incidencias.getNombreIncidencias());
        textViewFechaHora.setText("Fecha: " + incidencias.getFecha() + " Hora: " + incidencias.getHora());
        validarTipoIncidencia(incidencias);
    }

    private void validarTipoIncidencia(Incidencias incidencias) {
        switch (incidencias.getTipoIncidencia()) {
            case "Prioridad Alta"://ALTA
                button.setBackgroundColor(itemView.getResources().getColor(R.color.md_red_A400));
                button.setText("A");
                break;
            case "Prioridad Media"://MEDIA
                button.setBackgroundColor(itemView.getResources().getColor(R.color.md_orange_600));
                button.setText("M");
                break;
            case "Prioridad Baja"://BAJA
                button.setBackgroundColor(itemView.getResources().getColor(R.color.md_green_400));
                button.setText("B");
                break;
                default:
                    break;
        }
    }
}
