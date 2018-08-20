package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias;

import android.content.res.Resources;
import android.os.Bundle;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.fragment.BaseFragmentPresenterImpl;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Incidencias;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.useCase.EliminarIncidencia;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.useCase.ObtenerIncidenciaLista;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ReporteIncidenciasPresenterImpl extends BaseFragmentPresenterImpl<ReporteIncidenciasView> implements ReporteIncidenciasPresenter {

    public static final String TAG = ReporteIncidenciasPresenterImpl.class.getSimpleName();

    Cursos cursos;
    ObtenerIncidenciaLista obtenerIncidenciaLista;
    EliminarIncidencia eliminarIncidencia;

    public ReporteIncidenciasPresenterImpl(UseCaseHandler handler, Resources res, ObtenerIncidenciaLista obtenerIncidenciaLista, EliminarIncidencia eliminarIncidencia) {
        super(handler, res);
        this.obtenerIncidenciaLista = obtenerIncidenciaLista;
        this.eliminarIncidencia = eliminarIncidencia;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onActivityCreated() {

    }


    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.cursos = Parcels.unwrap(extras.getParcelable("cursosUi"));
        initListaIncidencias();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initListaIncidencias() {
        handler.execute(obtenerIncidenciaLista, new ObtenerIncidenciaLista.RequestValues(cursos),
                new UseCase.UseCaseCallback<ObtenerIncidenciaLista.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerIncidenciaLista.ResponseValue response) {
                        if (view != null) view.mostrarLista(response.getAsistenciaList());
                    }

                    @Override
                    public void onError() {

                    }
                });
//        if (view != null) view.mostrarLista(cursos.getIncidenciasList());
    }

    private List<Incidencias> getListaIncidencias() {

        List<Incidencias> incidenciasList = new ArrayList<>();

        Incidencias incidencias1 = new Incidencias(1, "El alumno presento un mal comportamiento", "09/07/2017", "10:00", 1);
        Incidencias incidencias2 = new Incidencias(2, "El alumno presento sintomas de fiebre", "09/07/2017", "10:00", 1);
        Incidencias incidencias3 = new Incidencias(3, "El alumno no presta atención en clase", "10/07/2017", "10:00", 2);
        Incidencias incidencias4 = new Incidencias(4, "Habla mucho con su compañero de clase, no hace caso", "12/07/2017", "10:00", 3);
        incidenciasList.add(incidencias1);
        incidenciasList.add(incidencias2);
        incidenciasList.add(incidencias3);
        incidenciasList.add(incidencias4);
        return incidenciasList;
    }

    @Override
    public void onEliminarClick(final Incidencias incidencias) {
        handler.execute(eliminarIncidencia, new EliminarIncidencia.RequestValues(incidencias),
                new UseCase.UseCaseCallback<EliminarIncidencia.ResponseValue>() {
                    @Override
                    public void onSuccess(EliminarIncidencia.ResponseValue response) {
                        if (response.isaBoolean()) {
                            if (view != null) view.eliminarItem(incidencias);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
