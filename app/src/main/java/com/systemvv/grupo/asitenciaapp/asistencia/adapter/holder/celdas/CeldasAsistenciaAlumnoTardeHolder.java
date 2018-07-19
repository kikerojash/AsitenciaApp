package com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.celdas;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CeldasAsistenciaAlumnoTardeHolder extends AbstractViewHolder {

    @BindView(R.id.textViewValorNumerico)
    TextView textViewDatos;
    @BindView(R.id.root)
    ConstraintLayout fondo;

    public CeldasAsistenciaAlumnoTardeHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(AsistenciaUi asistencia) {
        textViewDatos.setText("X");
        fondo.getLayoutParams().width = ConstraintLayout.LayoutParams.MATCH_PARENT;
       // validacionPintar(asistencia);
        if (asistencia.isPintar()) {
            fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_orange_500));
        } else {
            fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_white_1000));
        }
    }

    private void validacionPintar(AsistenciaUi asistencia) {
        switch (asistencia.getTipasistencia()) {
            case ASISTENCIA_TARDE:
                if (asistencia.isPintar()) {
                    fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_orange_500));
                } else {
                    fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_white_1000));
                }
                break;
            case ASISTENCIA_TARDE_JUSTIFICADA:
                if (asistencia.isPintar()) {
                    fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_yellow_500));
                } else {
                    fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_white_1000));
                }
                break;
            default:
                break;
        }
    }
}
