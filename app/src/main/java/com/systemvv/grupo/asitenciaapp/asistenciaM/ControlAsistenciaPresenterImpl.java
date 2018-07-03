package com.systemvv.grupo.asitenciaapp.asistenciaM;

import android.content.res.Resources;
import android.os.Bundle;

import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.estructura.AsistenciaCeldas;
import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.estructura.AsistenciaColumna;
import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.estructura.AsistenciaFilas;
import com.systemvv.grupo.asitenciaapp.asistenciaM.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.asistenciaM.entidad.TipoAsistencia;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenterImpl;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ControlAsistenciaPresenterImpl extends BaseActivityPresenterImpl<ControlAsistenciaView> implements ControlAsistenciaPresenter {

    public static final String TAG = ControlAsistenciaPresenterImpl.class.getSimpleName();

    private List<AsistenciaColumna> columnHeaderList;
    private List<List<AsistenciaCeldas>> cellsList;
    private List<AsistenciaFilas> rowHeaderList;

    CursoUi cursoUi;

    public ControlAsistenciaPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);

    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.cursoUi = Parcels.unwrap(extras.getParcelable("cursoUi"));
    }

    @Override
    public void onStart() {
        super.onStart();
        if (view != null) view.mostrarInformacionBasica(cursoUi);
        //initVistas(cursoUi.getAlumnosUiList(), cursoUi.getMotivosAsistenciaUiList());

    }

    private void initVistas() {
        columnHeaderList = new ArrayList<>();
        cellsList=new ArrayList<>();
        rowHeaderList=new ArrayList<>();

        columnHeaderList.addAll(cursoUi.getAlumnosUiList());
        rowHeaderList.addAll(getTipoAsistencia());
        cellsList.addAll(getListaCeldas());
        if (view!=null)view.mostrarListaTablas(columnHeaderList,rowHeaderList,getListaCeldas());
    }

    private List<List<AsistenciaCeldas>> getListaCeldas() {
        List<List<AsistenciaCeldas>> cellsList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            List<AsistenciaCeldas> celdas = new ArrayList<>();
            for (int j=0;j<3;j++){
                Asistencia asistencia = new Asistencia();
                asistencia.setaBoolean(false);
                //asistencia.setTipoAsistencia(TipoAsistencia.MotivoAsistencia.TIPO_ASISTENCIA_PRESENTE.name()+"");
                //asistencia.setNombreAsistencia("asdasdasd");
                celdas.add(asistencia);
            }
            cellsList.add(celdas);
        }
        return cellsList;
    }

    private List<TipoAsistencia> getTipoAsistencia() {
        List<TipoAsistencia> tipoAsistencias = new ArrayList<>();

        TipoAsistencia tipoAsistencia = new TipoAsistencia();
        tipoAsistencia.setMotivoAsistencia(TipoAsistencia.MotivoAsistencia.TIPO_ASISTENCIA_PRESENTE);
        tipoAsistencias.add(tipoAsistencia);

        TipoAsistencia tipoAsistencia2 = new TipoAsistencia();

        tipoAsistencia2.setMotivoAsistencia(TipoAsistencia.MotivoAsistencia.TTIPO_ASISTENCIA_TARDE);
        tipoAsistencias.add(tipoAsistencia2);

        TipoAsistencia tipoAsistencia3 = new TipoAsistencia();
        tipoAsistencia3.setMotivoAsistencia(TipoAsistencia.MotivoAsistencia.TIPO_ASISTENCIA_FALTO);
        tipoAsistencias.add(tipoAsistencia3);


       /* for (int i = 0;i<10;i++){
            tipoAsistencias.add(new TipoAsistencia(Mo, "puntual"));
        }*/
        return tipoAsistencias;
    }

    @Override
    public void onListAdapter() {
        initVistas();
    }
}
