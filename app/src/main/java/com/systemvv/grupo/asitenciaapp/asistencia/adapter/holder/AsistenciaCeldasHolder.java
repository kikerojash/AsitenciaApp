package com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder;


public class AsistenciaCeldasHolder {
        /*extends CeldasViewHolder<Asistencia> {
    @BindView(R.id.textViewValorNumerico)
    TextView textViewDatos;
    @BindView(R.id.root)
    ConstraintLayout fondo;
    Asistencia asistenciaUi;


    public AsistenciaCeldasHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Asistencia cell) {
        this.asistenciaUi = cell;
        textViewDatos.setText("X");
       // validarCamposColor(cell);
        if (!cell.isaBoolean()) {
           //
            //validarCamposColor(asistenciaUi);
            disSelectColor();
        } else {
            //disSelectColor();
         //   validarCamposColor(cell);

           fondo.setBackgroundColor(itemView.getResources().getColor(R.color.colorPrimary));
            //
        }

    }

    public void disSelectColor() {
        colorOcultar();
    }

    private void colorOcultar() {
        fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_white_1000));
    }


    public void validarCamposColor(Asistencia cell) {
      //  int position = cell.getTipoAsistencia().getMotivoAsistencia().ordinal();
        switch (cell.getTipoAsistencia().getMotivoAsistencia()) {
            case TIPO_ASISTENCIA_PRESENTE:
                fondo.setBackgroundColor(itemView.getResources().getColor(R.color.colorPrimary));
                break;
            case TTIPO_ASISTENCIA_TARDE:
                fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_yellow_700));
                break;
            case TIPO_ASISTENCIA_FALTO:
                fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_red_A100));
                break;
            default:
                break;
        }
    }

    public Asistencia obtenerAsistenciaUi() {
        return asistenciaUi;
    }*/
}
