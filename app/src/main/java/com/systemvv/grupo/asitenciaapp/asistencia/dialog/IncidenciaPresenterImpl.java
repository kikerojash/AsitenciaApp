package com.systemvv.grupo.asitenciaapp.asistencia.dialog;

import android.os.Bundle;
import android.util.Log;

import com.systemvv.grupo.asitenciaapp.asistencia.dialog.entidadui.IncidenciaUi;
import com.systemvv.grupo.asitenciaapp.asistencia.dialog.useCase.GuardarIncidencia;
import com.systemvv.grupo.asitenciaapp.asistencia.dialog.useCase.ObtenerAlumno;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Alumnos;
import com.systemvv.grupo.asitenciaapp.base.BaseView;
import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
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
    private ObtenerAlumno obtenerAlumno;

    public IncidenciaPresenterImpl(UseCaseHandler handler, GuardarIncidencia guardarIncidencia, ObtenerAlumno obtenerAlumno) {
        this.handler = handler;
        this.guardarIncidencia = guardarIncidencia;
        this.obtenerAlumno = obtenerAlumno;
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

        // this.nivelIncidencia = nivelIncidencia;
    }

    //Alumnos alumnosUi;
    String keyAlumno, keyGrado, keyInstitucion, keyPeriodo, keySeccion, keyCurso;

    @Override
    public void onExtras(Bundle bundle) {
        if (bundle == null) return;
        // this.keyAlumno = Parcels.unwrap(bundle.getParcelable("keyAlumno"));
        this.keyAlumno = bundle.getString("keyAlumno");
        this.keyGrado = bundle.getString("keyGrado");
        this.keyInstitucion = bundle.getString("keyInstitucion");
        this.keyPeriodo = bundle.getString("keyPeriodo");
        this.keySeccion = bundle.getString("keySeccion");
        this.keyCurso = bundle.getString("keyCurso");
        mostrarAlumno(keyAlumno);
        /*Log.d(TAG, "alumnosUi : " + alumnosUi.getNombre());*/
        // if (view != null) view.initVistas(alumnosUi);

    }

    private void mostrarAlumno(String keyAlumno) {
        handler.execute(obtenerAlumno, new ObtenerAlumno.RequestValues(keyAlumno),
                new UseCase.UseCaseCallback<ObtenerAlumno.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerAlumno.ResponseValue response) {
                        if (view != null) view.initVistas(response.getAlumnos());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void initGuardarIncidencia(String mensajeIncidencia) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        IncidenciaUi incidenciaUi = new IncidenciaUi();
        incidenciaUi.setMensajeIncidencia(mensajeIncidencia);
        if(nivelIncidencia == null) incidenciaUi.setNivelIncidencia("Prioridad Baja");
        else incidenciaUi.setNivelIncidencia(nivelIncidencia);
        //incidenciaUi.setNivelIncidencia(nivelIncidencia);
        incidenciaUi.setAlu_id_alumno(keyAlumno);
        incidenciaUi.setCur_id_curso(keyCurso);
        incidenciaUi.setGra_id_grado(keyGrado);
        incidenciaUi.setIns_id_institucion(keyInstitucion);
        incidenciaUi.setPrd_id_periodo(keyPeriodo);
        incidenciaUi.setSec_id_seccion(keySeccion);
        incidenciaUi.setFechaIncidencia(date);
        incidenciaUi.setTimeStamp(new Date().getTime());
        handler.execute(guardarIncidencia, new GuardarIncidencia.RequestValues(incidenciaUi),
                new UseCase.UseCaseCallback<GuardarIncidencia.ResponseValue>() {
                    @Override
                    public void onSuccess(GuardarIncidencia.ResponseValue response) {
                        if (response.isaBoolean()) {
                            if (view != null) view.mostrarMensaje("DATOS GUARDADOS CORRECTOS!");
                            Log.d(TAG, "DATOS GUARDADOS CORRECTOS!  ");
                        } else {
                            if (view != null) view.mostrarMensaje("DATOS GUARDADOS INCORRECTOS");
                            Log.d(TAG, "DATOS GUARDADOS INCORRECTOS! ");
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });

    }
}
