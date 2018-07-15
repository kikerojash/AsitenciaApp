package com.systemvv.grupo.asitenciaapp.asistencia;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;


import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.CeldasAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.ColumnaCabeceraAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.FilaCabeceraAsistencia;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenterImpl;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AlumnosUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.MotivosAsistenciaUi;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControlAsistenciaPresenterImpl extends BaseActivityPresenterImpl<ControlAsistenciaView> implements ControlAsistenciaPresenter {

    public static final String TAG = ControlAsistenciaPresenterImpl.class.getSimpleName();

    private List<ColumnaCabeceraAsistencia> columnHeaderList;
    private List<List<CeldasAsistencia>> cellsList;
    private List<FilaCabeceraAsistencia> rowHeaderList;

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
        cellsList = new ArrayList<>();
        rowHeaderList = new ArrayList<>();

        columnHeaderList.addAll(cursoUi.getMotivosAsistenciaUiList());
        rowHeaderList.addAll(cursoUi.getAlumnosUiList());
        //cellsList.addAll(getCellListForSortingTest());

        if (view != null) view.mostrarListaTablas(columnHeaderList, rowHeaderList, getCellListForSortingTest());
    }


//    private List<List<CeldasAsistencia>> getCellListForSortingTest() {
//        List<List<CeldasAsistencia>> list = new ArrayList<>();
//        for (int i = 0; i < cursoUi.getAlumnosUiList().size(); i++) {
//            List<CeldasAsistencia> cellList = new ArrayList<>();
//
//            for (int j = 0; j < cursoUi.getMotivosAsistenciaUiList().size(); j++) {
//
//                AsistenciaUi asistencia = new AsistenciaUi();
//                asistencia.setPintar(false);
//                asistencia.setTipoAsistencia("" + j);
//                asistencia.setTipoAsistencia("asdasdasd" + j);
//                cellList.add(asistencia);
//            }
//            list.add(cellList);
//        }
//        return list;
//    }


    private List<List<CeldasAsistencia>> getCellListForSortingTest() {
        List<List<CeldasAsistencia>> list = new ArrayList<>();
        for (AlumnosUi alumnosUi : cursoUi.getAlumnosUiList()) {

            List<CeldasAsistencia> cellList = new ArrayList<>();

            for (int j = 0; j < cursoUi.getMotivosAsistenciaUiList().size(); j++) {
                MotivosAsistenciaUi motivosAsistenciaUi = cursoUi.getMotivosAsistenciaUiList().get(j);

                Log.d(TAG,"MotivoAsistencia : " + motivosAsistenciaUi.getAsistenciaUiList().size());
                for (AsistenciaUi asistenciaUi :motivosAsistenciaUi.getAsistenciaUiList()){
                    Log.d(TAG, "CURSO::ASISTENCIA : " + cursoUi.getAsistenciaUis().size());
                    if (asistenciaUi.getJustificacion() == 1) {
                        AsistenciaUi asistencia = new AsistenciaUi();
                        asistencia.setPintar(false);
                        asistencia.setJustificacion(MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL);
                        asistencia.setTipoAsistencia("asdasdasd" + j);
                        asistencia.setAlumnosUi(alumnosUi);
                        cellList.add(asistencia);
                        Log.d(TAG, "alumnosUi.getTipoAsistencia() 1: " + motivosAsistenciaUi.getTipoMotivo());
                    } else if (asistenciaUi.getJustificacion() == 2) {
                        AsistenciaUi asistencia = new AsistenciaUi();
                        asistencia.setPintar(false);
                        asistencia.setJustificacion(MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE);
                        asistencia.setTipoAsistencia("asdasdasd" + j);
                        asistencia.setAlumnosUi(alumnosUi);
                        cellList.add(asistencia);
                        Log.d(TAG, "alumnosUi.getTipoAsistencia() 2: " + motivosAsistenciaUi.getTipoMotivo());
                    } else if (asistenciaUi.getJustificacion() == 3) {
                        AsistenciaUi asistencia = new AsistenciaUi();
                        asistencia.setPintar(false);
                        asistencia.setJustificacion(MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE_JUSTIFICADO);
                        asistencia.setTipoAsistencia("asdasdasd" + j);
                        asistencia.setAlumnosUi(alumnosUi);
                        cellList.add(asistencia);
                        Log.d(TAG, "alumnosUi.getTipoAsistencia() 3: " + motivosAsistenciaUi.getTipoMotivo());
                    } else if (asistenciaUi.getJustificacion() == 4) {
                        AsistenciaUi asistencia = new AsistenciaUi();
                        asistencia.setPintar(false);
                        asistencia.setJustificacion(MotivosAsistenciaUi.TIPO_ASISTENCIA_FALTO);
                        asistencia.setTipoAsistencia("asdasdasd" + j);
                        asistencia.setAlumnosUi(alumnosUi);
                        cellList.add(asistencia);
                        Log.d(TAG, "alumnosUi.getTipoAsistencia() 4: " + motivosAsistenciaUi.getTipoMotivo());
                    }
                }
            }
            list.add(cellList);
        }

        return list;
    }

    //  for(AlumnosUi alumnosUi:cursoUi.getAlumnosUiList()){}

    @Override
    public void onListAdapter() {
        initVistas();
    }
}
