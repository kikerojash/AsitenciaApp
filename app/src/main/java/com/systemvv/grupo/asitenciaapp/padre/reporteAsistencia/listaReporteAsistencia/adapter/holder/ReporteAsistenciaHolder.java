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
    @BindView(R.id.textView8)
    ImageView imageView;


    public ReporteAsistenciaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Asistencia asistencia) {
        textViewConteo.setText(asistencia.getConteo() + "");
        textViewFecha.setText(asistencia.getFecha());
        validarTipoAsistencia(asistencia);
    }

    private void validarTipoAsistencia(Asistencia asistencia) {
        switch (asistencia.getTipoAsistencia()) {
            case 1://Presente
                imageView.setBackground(itemView.getResources().getDrawable(R.drawable.presente));
                break;
            case 2://Tarde
                imageView.setBackground(itemView.getResources().getDrawable(R.drawable.falto));
                break;
            case 3://Falto
                imageView.setBackground(itemView.getResources().getDrawable(R.drawable.falto));
                break;

        }
    }
}
