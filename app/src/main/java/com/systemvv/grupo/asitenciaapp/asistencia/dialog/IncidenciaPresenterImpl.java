package com.systemvv.grupo.asitenciaapp.asistencia.dialog;

import android.os.Bundle;
import android.util.Log;

import com.systemvv.grupo.asitenciaapp.asistencia.dialog.entidadui.IncidenciaUi;
import com.systemvv.grupo.asitenciaapp.asistencia.dialog.useCase.GuardarIncidencia;
import com.systemvv.grupo.asitenciaapp.base.BaseView;
import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AlumnosUi;
import com.systemvv.grupo.asitenciaapp.utils.Utils;

import org.parceler.Parcels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class IncidenciaPresenterImpl implements IncidenciaPresenter {

    public static final String TAG = IncidenciaPresenterImpl.class.getSimpleName();

    private IncidenciaView view;
    private UseCaseHandler handler;
    private GuardarIncidencia guardarIncidencia;


    public IncidenciaPresenterImpl(UseCaseHandler handler, GuardarIncidencia guardarIncidencia) {
        this.handler = handler;
        this.guardarIncidencia = guardarIncidencia;
    }

    @Override
    public void attachView(BaseView view) {
        this.view = (IncidenciaView) view;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
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

    @Override
    public void onBtnAceptar(String mensajeIncidencia) {
        if (!Utils.isValideString(mensajeIncidencia)) {
            if (view != null)
                view.mostrarMensaje("No se permieten campos vacios!");
            return;
        }
        initGuardarIncidencia(mensajeIncidencia);
    }

    String nivelIncidencia;

    @Override
    public void onSeleccionSpinnerIncidencia(String nivelIncidencia) {
        this.nivelIncidencia = nivelIncidencia;
    }

    AlumnosUi alumnosUi;

    @Override
    public void onExtras(Bundle bundle) {
        this.alumnosUi = Parcels.unwrap(bundle.getParcelable("alumnoUi"));
        Log.d(TAG, "alumnosUi : " + alumnosUi.getNombre());
        if (view != null) view.initVistas(alumnosUi);
    }

    private void initGuardarIncidencia(String mensajeIncidencia) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        IncidenciaUi incidenciaUi = new IncidenciaUi();
        incidenciaUi.setMensajeIncidencia(mensajeIncidencia);
        incidenciaUi.setNivelIncidencia(nivelIncidencia);
        incidenciaUi.setAlu_id_alumno(alumnosUi.getNombre());
        incidenciaUi.setFechaIncidencia(date);
        incidenciaUi.setTimeStamp(new Date().getTime());
        handler.execute(guardarIncidencia, new GuardarIncidencia.RequestValues(incidenciaUi),
                new UseCase.UseCaseCallback<GuardarIncidencia.ResponseValue>() {
                    @Override
                    public void onSuccess(GuardarIncidencia.ResponseValue response) {
                        if(response.isaBoolean()){
                            Log.d(TAG, "DATOS GUARDADOS CORRECTOS!  ");
                        }else{
                            Log.d(TAG, "DATOS GUARDADOS INCORRECTOS! ");
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });

    }
}
