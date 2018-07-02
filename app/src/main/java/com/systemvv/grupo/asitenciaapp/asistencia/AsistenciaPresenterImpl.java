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

public class AsistenciaPresenterImpl extends BaseActivityPresenterImpl<AsistenciaView> implements AsistenciaPresenter {

    public static final String TAG = AsistenciaPresenterImpl.class.getSimpleName();

    private List<AsistenciaColumnaCabecera> columnheaderList;

    public AsistenciaPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
        formulaCeldasList = new ArrayList<>();
        columnheaderList = new ArrayList<>();

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

    List<List<AsistenciaCelda>> formulaCeldasList;


    //List<List<AsistenciaCelda>> formulaCeldas2;

   List<AsistenciaCelda> formulaCeldas1;

    @Override
    public void onStart() {
        super.onStart();
        if (view != null) view.mostrarInformacionBasica(cursoUi);

        // Log.d(TAG, "cursoUi.getAlumnosUiList()" + cursoUi.getAlumnosUiList().size());
        initVistas(cursoUi.getAlumnosUiList(), cursoUi.getMotivosAsistenciaUiList(), formulaCeldasList);
    }



    private void initVistas(List<AlumnosUi> alumnosUiList, List<MotivosAsistenciaUi> motivosAsistenciaUiList, List<List<AsistenciaCelda>> formulaCeldas) {
        try {


            List<AsistenciaColumnaCabecera> formulaColumnaCabeceras = (List<AsistenciaColumnaCabecera>) (Object) motivosAsistenciaUiList;
            List<AsistenciaFilaCabecera> formulaFilaCabeceras = (List<AsistenciaFilaCabecera>) (Object) alumnosUiList;

            List<List<AsistenciaCelda>> formulaCeldas2 = new ArrayList<>();
         //   List<AsistenciaCelda> formulaCeldas1 = new ArrayList<>();
            formulaCeldas1 = new ArrayList<>();
            formulaCeldas1.addAll(cursoUi.getAsistenciaUis());
            for (int i = 0; i < cursoUi.getAlumnosUiList().size(); i++) {
                formulaCeldas2.add(formulaCeldas1);
            }
            /*
            formulaCeldas2.add(formulaCeldas1);*/

            if (view != null)
                view.mostrarListaTablas(formulaColumnaCabeceras, formulaFilaCabeceras, formulaCeldas2);
        } catch (Exception ignored) {

        }
    }


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
//            asistenciaUi.setPintar(true);
            onDesSelectAsistencia(asistenciaUi);

        } else {
           // asistenciaUi.setPintar(false);
            onSelectAsistencia(asistenciaUi);
        }

        /*for (AsistenciaUi asistenciaUi1Anterior : cursoUi.getAsistenciaUis()) {
            if (asistenciaUi1Anterior != asistenciaUi) {
                asistenciaUi1Anterior.setPintar(false);
                break;
            } else {
                asistenciaUi1Anterior.setPintar(true);
                break;
            }
        }*/
       //evaluadorAsistencia(asistenciaUi);
        if (view != null) view.actualizarCeldas(asistenciaUi);
    }

    private void evaluadorAsistencia(AsistenciaUi asistenciaUi) {

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
        //if (asistenciaUis == null) return;
        int posicion = formulaCeldas1.indexOf(asistenciaUi);
        // if (posicion == -1) return;

        Log.d(TAG, "posicion: " + posicion);

       /* for (AsistenciaUi asistenciaUi1Anterior : cursoUi.getAsistenciaUis()) {
            if (asistenciaUi1Anterior != asistenciaUi) {
                asistenciaUi1Anterior.setPintar(false);
            } else {
                asistenciaUi1Anterior.setPintar(true);
            }
        }*/

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

        List<AsistenciaUi> asistenciaUis = cursoUi.getAsistenciaUis();
        //if (asistenciaUis == null) return;
        int posicion = formulaCeldas1.indexOf(asistenciaUi);
        // if (posicion == -1) return;

        Log.d(TAG, "posicion: " + posicion);


      /*  for (AsistenciaUi asistenciaUi1Anterior : cursoUi.getAsistenciaUis()) {
            if (asistenciaUi1Anterior != asistenciaUi) {
                asistenciaUi1Anterior.setPintar(false);
            } else {
                asistenciaUi1Anterior.setPintar(true);
            }
        }
*/
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
        //if (asistenciaUis == null) return;
        int posicion = formulaCeldas1.indexOf(asistenciaUi);
        // if (posicion == -1) return;

        Log.d(TAG, "posicion: " + posicion);

        /*for (AsistenciaUi asistenciaUi1Anterior : cursoUi.getAsistenciaUis()) {
            if (asistenciaUi1Anterior != asistenciaUi) {
                asistenciaUi1Anterior.setPintar(false);
            } else {
                asistenciaUi1Anterior.setPintar(true);
            }
        }
*/
        if (view != null) view.actualizarCeldas(asistenciaUi);
    }

    @Override
    public void onDesSelectAsistencia(AsistenciaUi asistenciaUi) {
        Log.d(TAG,"onDesSelectAsistencia");
        asistenciaUi.setPintar(true);
    }

    @Override
    public void onSelectAsistencia(AsistenciaUi asistenciaUi) {
       Log.d(TAG,"onSelectAsistencia");
        asistenciaUi.setPintar(false);
    }


   /* @Override
    public void onClickAsistenciaFaltoHolder(AsistenciaUi asistenciaUi) {
        Log.d(TAG, "onClickAsistenciaFaltoHolder" + asistenciaUi.getTipoAsistencia());
        Log.d(TAG, "onClickAsistenciaFalto" + asistenciaUi.getTipoAsistencia());
        if (!asistenciaUi.isPintar()) {
            asistenciaUi.setPintar(true);
        } else {
            asistenciaUi.setPintar(false);
        }

        for (AsistenciaUi asistenciaUi1Anterior : cursoUi.getAsistenciaUis()){
            if(asistenciaUi1Anterior != asistenciaUi){
                asistenciaUi1Anterior.setPintar(false);
            }
            break;
        }

        if(view!=null)view.actualizarCeldas(asistenciaUi);
    }*/
}
