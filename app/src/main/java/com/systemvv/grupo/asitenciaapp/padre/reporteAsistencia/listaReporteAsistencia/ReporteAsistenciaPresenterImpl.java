package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteAsistencia;

import android.content.res.Resources;

import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.fragment.BaseFragmentPresenterImpl;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Asistencia;

import java.util.ArrayList;
import java.util.List;

public class ReporteAsistenciaPresenterImpl extends BaseFragmentPresenterImpl<ReporteAsistenciaView> implements ReporteAsistenciaPresenter {


    public static final String TAG = ReporteAsistenciaPresenterImpl.class.getSimpleName();

    public ReporteAsistenciaPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onActivityCreated() {

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onResume() {
        super.onResume();
        initListaAsistencias();
    }

    private void initListaAsistencias() {
        if (view != null) view.mostrarLista(getListaAsistencia());
    }

    private List<Asistencia> getListaAsistencia() {
        List<Asistencia> asistenciaList = new ArrayList<>();

        Asistencia asistencia1 = new Asistencia("id1", "09/07/2017", 1, 1,"8:00 A.M","11:00 A.M");
        Asistencia asistencia2 = new Asistencia("id1", "10/07/2017", 1, 2,"8:40 A.M","11:00 A.M");
        Asistencia asistencia3 = new Asistencia("id1", "11/07/2017", 1, 3,"8:20 A.M","11:00 A.M");
        Asistencia asistencia4 = new Asistencia("id1", "12/07/2017", 1, 4,"9:00 A.M","11:00 A.M");
        Asistencia asistencia5 = new Asistencia("id1", "13/07/2017", 2, 5,"9:30 A.M","11:00 A.M");
        asistenciaList.add(asistencia1);
        asistenciaList.add(asistencia2);
        asistenciaList.add(asistencia3);
        asistenciaList.add(asistencia4);
        asistenciaList.add(asistencia5);
        return asistenciaList;
    }

}
