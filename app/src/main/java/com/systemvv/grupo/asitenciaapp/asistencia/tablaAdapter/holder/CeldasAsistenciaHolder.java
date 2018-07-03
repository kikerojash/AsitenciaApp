package com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder.abstracto.CeldasViewHolder;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CeldasAsistenciaHolder extends CeldasViewHolder<AsistenciaUi> {
    @BindView(R.id.textViewValorNumerico)
    TextView textViewDatos;
    @BindView(R.id.root)
    ConstraintLayout fondo;
    AsistenciaUi asistenciaUi;

    public CeldasAsistenciaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(AsistenciaUi cell) {
        this.asistenciaUi = cell;
        textViewDatos.setText(cell.getJustificacion() + "");
        //validarCamposColor(cell);
        if (cell.isPintar()) {
            validarCamposColor(asistenciaUi);
        } else {
            disSelectColor();
        }
    }

    private void validarCamposColor(AsistenciaUi cell) {
        switch (cell.getTipasistencia()) {
            case ASISTENCIA_PRESENTE:
                fondo.setBackgroundColor(itemView.getResources().getColor(R.color.colorPrimary));
                break;
            case ASISTENCIA_TARDE:
                fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_yellow_700));
                break;
            case ASISTENCIA_FALTO:
                fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_red_A100));
                break;
            default:
                break;
        }
    }


    public AsistenciaUi obtenerAsistenciaUi() {
        return asistenciaUi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CeldasAsistenciaHolder that = (CeldasAsistenciaHolder) o;
        return asistenciaUi != null ? asistenciaUi.equals(that.asistenciaUi) : that.asistenciaUi == null;
    }

    @Override
    public int hashCode() {
        return asistenciaUi != null ? asistenciaUi.hashCode() : 0;
    }
    public void selectColor(){
        validarCamposColor(asistenciaUi);
    }
    public void disSelectColor(){
        colorOcultar();
    }

    private void colorOcultar(){
        fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_white_1000));
    }



    public void deselecionColor() {
        disSelectColor();
       // asistenciaUi.setSelecionCeldas(null);
    }
    public void selecionColor() {
        validarCamposColor(asistenciaUi);
       // asistenciaUi.setSelecionCeldas(null);
      //  asistenciaUi.setSelecionCeldas(this);
    }
}
