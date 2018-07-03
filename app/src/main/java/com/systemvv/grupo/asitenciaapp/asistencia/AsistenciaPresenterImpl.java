package com.systemvv.grupo.asitenciaapp.asistencia;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.estructura.AsistenciaCelda;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.estructura.AsistenciaColumnaCabecera;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.estructura.AsistenciaFilaCabecera;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenterImpl;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AlumnosUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.MotivosAsistenciaUi;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsistenciaPresenterImpl extends BaseActivityPresenterImpl<AsistenciaView> implements AsistenciaPresenter {

    public static final String TAG = AsistenciaPresenterImpl.class.getSimpleName();


    //List<List<AsistenciaCelda>> formulaAsistenciaCelda2;
    List<AsistenciaCelda> formulaCeldasAsistencia1;
    List<AsistenciaColumnaCabecera> formulaColumnaCabeceras;
    List<AsistenciaFilaCabecera> formulaFilaCabeceras;


    public AsistenciaPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
        formulaCeldasAsistencia1 = new ArrayList<>();
        //formulaAsistenciaCelda2 = new ArrayList<>();
        formulaColumnaCabeceras = new ArrayList<>();
        formulaFilaCabeceras = new ArrayList<>();
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    CursoUi cursoUi;

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
        initVistas();
    }


   // private void initVistas(List<AlumnosUi> alumnosUiList, List<MotivosAsistenciaUi> motivosAsistenciaUiList) {
   private void initVistas() {
        try {


//            List<AsistenciaColumnaCabecera> formulaColumnaCabeceras = (List<AsistenciaColumnaCabecera>) (Object) motivosAsistenciaUiList;
//            List<AsistenciaFilaCabecera> formulaFilaCabeceras = (List<AsistenciaFilaCabecera>) (Object) alumnosUiList;
            //List<AsistenciaColumnaCabecera> formulaColumnaCabeceras = (List<AsistenciaColumnaCabecera>) (Object) alumnosUiList;
           // List<AsistenciaFilaCabecera> formulaFilaCabeceras = (List<AsistenciaFilaCabecera>) (Object) motivosAsistenciaUiList;

            // formulaColumnaCabeceras.addAll(cursoUi.getAlumnosUiList());
             formulaFilaCabeceras.addAll(cursoUi.getMotivosAsistenciaUiList());
            List<List<AsistenciaCelda>> formulaAsistenciaCelda2 = new ArrayList<>();

            for (int i = 0; i < cursoUi.getAlumnosUiList().size(); i++) {
                List<AsistenciaCelda> formulaCeldas1 = new ArrayList<>();
                 for (int j = 0; j < 4; j++) {
                for (AsistenciaUi asistenciaUi : cursoUi.getAsistenciaUis()) {
                    formulaCeldas1.add(asistenciaUi);
                }

                formulaAsistenciaCelda2.add(formulaCeldas1);
                  }

            }


            if (view != null)
                view.mostrarListaTablas(formulaColumnaCabeceras, formulaFilaCabeceras, formulaAsistenciaCelda2);
        } catch (Exception ignored) {

        }
    }




  /*  private List<List<Cell>> getCellListForSortingTest() {
        List<List<Cell>> list = new ArrayList<>();
        for (int i = 0; i < ROW_SIZE; i++) {
            List<Cell> cellList = new ArrayList<>();
            for (int j = 0; j < COLUMN_SIZE; j++) {
                Object text = "cell " + j + " " + i;

                final int random = new Random().nextInt();
                if (j == 0) {
                    text = i;
                } else if (j == 1) {
                    text = random;
                } else if (j == MOOD_COLUMN_INDEX) {
                    text = random % 2 == 0 ? HAPPY : SAD;
                } else if (j == GENDER_COLUMN_INDEX) {
                    text = random % 2 == 0 ? BOY : GIRL;
                }

                // Create dummy id.
                String id = j + "-" + i;

                Cell cell;
                if (j == 3) {
                    cell = new Cell(id, text);
                } else if (j == 4) {
                    // NOTE female and male keywords for filter will have conflict since "female"
                    // contains "male"
                    cell = new Cell(id, text);
                } else {
                    cell = new Cell(id, text);
                }
                cellList.add(cell);
            }
            list.add(cellList);
        }

        return list;
    }*/

    /*@Override
    public void onClickAsistenciaFalto(AsistenciaUi asistenciaUi) {
        Log.d(TAG, "onClickAsistenciaFalto" + asistenciaUi.getTipoAsistencia());

        List<AsistenciaUi> asistenciaUis = cursoUi.getAsistenciaUis();
        if (asistenciaUis == null) return;
        int posicion = formulaCeldas1.indexOf(asistenciaUi);
        if (posicion == -1) return;
        Log.d(TAG, "posicion: " + posicion);
        AsistenciaUi asistenciaUi1 = (AsistenciaUi) formulaCeldas1.get(posicion);

        if (asistenciaUi1==null)return;

        if (!asistenciaUi1.isPintar()) {
            asistenciaUi1.setPintar(true);
        } else {
            asistenciaUi1.setPintar(false);
        }

        if (view != null) view.actualizarCeldas(asistenciaUi);
    }*/

    @Override
    public void onClickAsistenciaCelda(AsistenciaUi asistenciaUi) {
        Log.d(TAG, "onClickAsistenciaCelda" + asistenciaUi.isPintar());
        if (!asistenciaUi.isPintar()) {
            asistenciaUi.setPintar(true);
            // onDesSelectAsistencia(asistenciaUi);
        } else {
            asistenciaUi.setPintar(false);
        }

       for (AsistenciaUi asistenciaUi1Anterior : cursoUi.getAsistenciaUis()) {
            if (asistenciaUi1Anterior != asistenciaUi) {
                asistenciaUi1Anterior.setPintar(false);
                break;
            } else {
                asistenciaUi1Anterior.setPintar(true);
                break;
            }
        }

        if (view != null) view.actualizarCeldas(asistenciaUi);
        //evaluadorAsistencia(asistenciaUi);

    }


    @Override
    public void onClickAsistenciaTardeJustificado(AsistenciaUi asistenciaUi) {
        Log.d(TAG, "onClickAsistenciaTardeJustificado" + asistenciaUi.getTipoAsistencia());
        if (!asistenciaUi.isPintar()) {
            asistenciaUi.setPintar(true);
        } else {
            asistenciaUi.setPintar(false);
        }

        List<AsistenciaUi> asistenciaUis = cursoUi.getAsistenciaUis();


        if (view != null) view.actualizarCeldas(asistenciaUi);
    }

    @Override
    public void onClickAsistenciaTarde(AsistenciaUi asistenciaUi) {
        Log.d(TAG, "onClickAsistenciaTarde" + asistenciaUi.getTipoAsistencia());

        if (!asistenciaUi.isPintar()) {
            asistenciaUi.setPintar(true);
        } else {
            asistenciaUi.setPintar(false);
        }


        if (view != null) view.actualizarCeldas(asistenciaUi);
    }

    @Override
    public void onClickAsistenciaPuntual(AsistenciaUi asistenciaUi) {
        Log.d(TAG, "onClickAsistenciaPuntual" + asistenciaUi.getTipoAsistencia());

        if (!asistenciaUi.isPintar()) {
            asistenciaUi.setPintar(true);
        } else {
            asistenciaUi.setPintar(false);
        }
        List<AsistenciaUi> asistenciaUis = cursoUi.getAsistenciaUis();
        if (view != null) view.actualizarCeldas(asistenciaUi);
    }

    @Override
    public void onDesSelectAsistencia(AsistenciaUi asistenciaUi) {
        Log.d(TAG, "onDesSelectAsistencia");
        asistenciaUi.setPintar(true);
    }

    @Override
    public void onSelectAsistencia(AsistenciaUi asistenciaUi) {
        Log.d(TAG, "onSelectAsistencia");
        // int posicionColumna = formulaColumnaCabeceras.indexOf(asistenciaUi.getAlumnosUi());
        // Log.d(TAG, "posicionColumna : " + posicionColumna + "/ alumnos : " + asistenciaUi.getAlumnosUi().getNombre());
        asistenciaUi.setPintar(false);
    }

}
