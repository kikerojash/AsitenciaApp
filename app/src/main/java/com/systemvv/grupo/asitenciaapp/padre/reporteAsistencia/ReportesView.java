package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia;

import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityView;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;

public interface ReportesView extends BaseActivityView<ReportesPresenter>{

    void mostrarDatos(Cursos cursos);

    void initAdapter(Cursos cursos);
}
