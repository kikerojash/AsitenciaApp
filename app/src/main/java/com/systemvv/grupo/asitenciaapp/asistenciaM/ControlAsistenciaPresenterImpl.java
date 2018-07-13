package com.systemvv.grupo.asitenciaapp.asistenciaM;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;


import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.estructura.CeldasAsistencia;
import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.estructura.ColumnaCabeceraAsistencia;
import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.estructura.FilaCabeceraAsistencia;
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
        cellsList.addAll(getCellListForSortingTest());

        if (view != null) view.mostrarListaTablas(columnHeaderList, rowHeaderList, cellsList);
    }



   private List<List<CeldasAsistencia>> getCellListForSortingTest() {
        List<List<CeldasAsistencia>> list = new ArrayList<>();
        for (int i = 0; i < cursoUi.getAlumnosUiList().size(); i++) {
            List<CeldasAsistencia> cellList = new ArrayList<>();

            for (int j = 0; j < cursoUi.getMotivosAsistenciaUiList().size(); j++) {

                AsistenciaUi asistencia = new AsistenciaUi();
                asistencia.setPintar(false);
                asistencia.setTipoAsistencia("" + j);
                asistencia.setTipoAsistencia("asdasdasd" + j);
               cellList.add(asistencia);
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
