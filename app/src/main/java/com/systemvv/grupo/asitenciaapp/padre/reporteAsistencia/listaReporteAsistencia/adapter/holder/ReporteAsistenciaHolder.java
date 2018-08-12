package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteAsistencia.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Asistencia;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReporteAsistenciaHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textView7)
    TextView textViewConteo;
    @BindView(R.id.textView6)
    TextView textViewFecha;
    @BindView(R.id.textViewHoraInicio)
    TextView textViewInicioHora;
    @BindView(R.id.textViewHoraFin)
    TextView textViewFinHora;
    @BindView(R.id.textView8)
    ImageView imageView;


    public ReporteAsistenciaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Asistencia asistencia) {
        textViewConteo.setText(asistencia.getConteo() + "");
        textViewFecha.setText(asistencia.getFecha());
        textViewInicioHora.setText(asistencia.getInicioRegistroHora());
        textViewFinHora.setText(asistencia.getFinRegistroHora());
        validarTipoAsistencia(asistencia);
    }

    private void validarTipoAsistencia(Asistencia asistencia) {
        if(asistencia.getTipASistencia().equals("FALTO")){
            imageView.setBackground(itemView.getResources().getDrawable(R.drawable.reporte_falto));
        }else if (asistencia.getTipASistencia().equals("PUNTUAL")){
            imageView.setBackground(itemView.getResources().getDrawable(R.drawable.reporte_presente));
        }else if (asistencia.getTipASistencia().equals("TARDE")){
            imageView.setBackground(itemView.getResources().getDrawable(R.drawable.reporte_tarde));
        }
    }
}
