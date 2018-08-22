package com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal;


import android.os.Bundle;
import android.util.Log;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;

import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.entidad.TareasGlobales;
import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.useCase.ObtenerListaAlumnos;
import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.useCase.RegistrarTareaGlobales;
import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.view.TareaGlobalView;

import org.parceler.Parcels;

import java.util.List;

public class TareaGlobalPresenterImpl implements TareaGlobalPresenter {


    public static final String TAG = TareaGlobalPresenterImpl.class.getSimpleName();

    UseCaseHandler handler;
    ObtenerListaAlumnos obtenerListaAlumnos;
    RegistrarTareaGlobales registrarTareaGlobales;
    TareaGlobalView view;


    public TareaGlobalPresenterImpl(UseCaseHandler handler, ObtenerListaAlumnos obtenerListaAlumnos, RegistrarTareaGlobales registrarTareaGlobales) {
        this.handler = handler;
        this.obtenerListaAlumnos = obtenerListaAlumnos;
        this.registrarTareaGlobales = registrarTareaGlobales;
    }

    @Override
    public void attachView(TareaGlobalView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {

    }

    private List<TareasGlobales> tareasGlobalesList;


    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onBackPressed() {

    }

    CursoUi cursoUi;

    @Override
    public void onExtras(Bundle bundle) {
        if (bundle == null) return;
        this.cursoUi = Parcels.unwrap(bundle.getParcelable("cursoUi"));
        Log.d(TAG, "extras : " + cursoUi.getNombre() +
                " / key : " + cursoUi.getKeyCurso());
        if(view!=null)view.informacionDialog(cursoUi);
        initObtenerListaAlumnos(cursoUi);
    }

    private void initObtenerListaAlumnos(CursoUi cursoUi) {
        handler.execute(obtenerListaAlumnos, new ObtenerListaAlumnos.RequestValues(cursoUi),
                new UseCase.UseCaseCallback<ObtenerListaAlumnos.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerListaAlumnos.ResponseValue response) {
                        if (response.getTareasGlobalesList() == null) return;
                        tareasGlobalesList = response.getTareasGlobalesList();
                    }

                    @Override
                    public void onError() {

                    }
                });
    }


    @Override
    public void onClickEnviarTarea(String tarea) {
        handler.execute(registrarTareaGlobales, new RegistrarTareaGlobales.RequestValues(tareasGlobalesList,tarea),
                new UseCase.UseCaseCallback<RegistrarTareaGlobales.ResponseValue>() {
                    @Override
                    public void onSuccess(RegistrarTareaGlobales.ResponseValue response) {
                        if (response.getaBoolean()) {
                            if (view != null) view.mostrarMensaje("Tarea Enviada!!!");

                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
