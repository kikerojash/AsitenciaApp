package com.systemvv.grupo.asitenciaapp.asistencia;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.CeldasAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.ColumnaCabeceraAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.FilaCabeceraAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.celdas.CeldasAsistenciaAlumnoFaltoHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.celdas.CeldasAsistenciaAlumnoPuntualHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.celdas.CeldasAsistenciaAlumnoTardeHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.columnas.ColumnaTipoPresenteHolder;

import com.systemvv.grupo.asitenciaapp.asistencia.useCase.GuardarAsistenciaLista;
import com.systemvv.grupo.asitenciaapp.asistencia.useCase.GuardarAsistenciaListaHoraFin;
import com.systemvv.grupo.asitenciaapp.asistencia.useCase.ObtenerListaAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.useCase.ValidarFechaRegistroAsistencia;
import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenterImpl;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AlumnosUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.MotivosAsistenciaUi;
import com.systemvv.grupo.asitenciaapp.fire.entidad.Asistencia;

import org.parceler.Parcels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ControlAsistenciaPresenterImpl extends BaseActivityPresenterImpl<ControlAsistenciaView> implements ControlAsistenciaPresenter {

    public static final String TAG = ControlAsistenciaPresenterImpl.class.getSimpleName();

    private List<ColumnaCabeceraAsistencia> columnHeaderList;
    private List<List<CeldasAsistencia>> cellsList;
    private List<FilaCabeceraAsistencia> rowHeaderList;

    CursoUi cursoUi;

    /*Caso de Uso*/
    private GuardarAsistenciaLista guardarAsistenciaLista;
    private ValidarFechaRegistroAsistencia validarFechaRegistroAsistencia;
    private ObtenerListaAsistencia obtenerListaAsistencia;
    private GuardarAsistenciaListaHoraFin guardarAsistenciaListaHoraFin;

    public ControlAsistenciaPresenterImpl(UseCaseHandler handler, Resources res, GuardarAsistenciaLista guardarAsistenciaLista, ValidarFechaRegistroAsistencia validarFechaRegistroAsistencia, ObtenerListaAsistencia obtenerListaAsistencia, GuardarAsistenciaListaHoraFin guardarAsistenciaListaHoraFin) {
        super(handler, res);
        this.guardarAsistenciaLista = guardarAsistenciaLista;
        this.validarFechaRegistroAsistencia = validarFechaRegistroAsistencia;
        this.obtenerListaAsistencia = obtenerListaAsistencia;
        this.guardarAsistenciaListaHoraFin = guardarAsistenciaListaHoraFin;

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

    List<AsistenciaUi> guardandoListasAsistencias;

    @Override
    public void onCreate() {
        initVistas();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        // validarExisteFecha(date);
        celdasAsistenciasColumnaPresentes = new ArrayList<>();
        celdasAsistencias = new ArrayList<>();
        guardandoListasAsistencias = new ArrayList<>();
    }

    private List<List<CeldasAsistencia>> getCellListForSortingTest() {
        List<List<CeldasAsistencia>> list = new ArrayList<>();
        for (AlumnosUi alumnosUi : cursoUi.getAlumnosUiList()) {

            List<CeldasAsistencia> cellList = new ArrayList<>();

            for (int j = 0; j < cursoUi.getMotivosAsistenciaUiList().size(); j++) {
                MotivosAsistenciaUi motivosAsistenciaUi = cursoUi.getMotivosAsistenciaUiList().get(j);

                Log.d(TAG, "MotivoAsistencia : " + motivosAsistenciaUi.getAsistenciaUiList().size());
                for (AsistenciaUi asistenciaUi : motivosAsistenciaUi.getAsistenciaUiList()) {
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
                        asistencia.setTipasistencia(AsistenciaUi.TipoAsistencia.ASISTENCIA_TARDE);
                        asistencia.setTipoAsistencia("asdasdasd" + j);
                        asistencia.setAlumnosUi(alumnosUi);
                        cellList.add(asistencia);
                        Log.d(TAG, "alumnosUi.getTipoAsistencia() 2: " + motivosAsistenciaUi.getTipoMotivo());
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


    boolean clickColumn = false;


    List<CeldasAsistencia> celdasAsistenciasColumnaPresentes;

    @Override
    public void onClickColumnaCabecera(@NonNull RecyclerView.ViewHolder holder, List<CeldasAsistencia> clickColumnaList) {
        if (clickColumn == false) {
            if (holder instanceof ColumnaTipoPresenteHolder) {
                clickColumn = true;

                for (CeldasAsistencia celdasAsistencia : clickColumnaList) {
                    AsistenciaUi asistencia = (AsistenciaUi) celdasAsistencia;
                    asistencia.setJustificacion(1);
                    asistencia.setTipoAsistencia("PUNTUAL");
                    asistencia.setPintar(true);
                    celdasAsistenciasColumnaPresentes.add(celdasAsistencia);
                }
            }
            if (view != null) view.actualizarDatosCambiadosTabla();
        } else {
            Log.d(TAG, "no hacer nada");
        }
    }

    List<CeldasAsistencia> celdasAsistencias;

    @Override
    public void onClickCeldas(@NonNull RecyclerView.ViewHolder holder, AsistenciaUi asistenciaUi, List<CeldasAsistencia> clickCeldasList) {
        if (clickColumn == true) {
            if (holder instanceof CeldasAsistenciaAlumnoPuntualHolder) {
                asistenciaUi.setTipoAsistencia("PUNTUAL");
                pintandoCeldas(asistenciaUi, clickCeldasList);
            } else if (holder instanceof CeldasAsistenciaAlumnoTardeHolder) {
                asistenciaUi.setTipoAsistencia("TARDE");
                pintandoCeldas(asistenciaUi, clickCeldasList);
            } else if (holder instanceof CeldasAsistenciaAlumnoFaltoHolder) {
                asistenciaUi.setTipoAsistencia("FALTO");
                pintandoCeldas(asistenciaUi, clickCeldasList);
            }
            if (view != null) view.actualizarDatosCambiadosTabla();
        } else {
            //Toast.makeText(getApplicationContext(), "Seleccione todos los items primero ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGuardarEntrada() {
        List<AsistenciaUi> guardandoListasAsistencias = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dfHours = new SimpleDateFormat("HH:mm aaa");
        String date = df.format(Calendar.getInstance().getTime());
        String horaInicioCurso = dfHours.format(Calendar.getInstance().getTime());

        for (CeldasAsistencia celdasAsistencia : celdasAsistencias) {
            AsistenciaUi asistenciaUi = (AsistenciaUi) celdasAsistencia;
            asistenciaUi.setHoraInicioCurso(horaInicioCurso);
            asistenciaUi.setFecha(date);
            if (asistenciaUi.isPintar()) {
                Log.d(TAG, "celdasAsistencias : " + asistenciaUi.getTipoAsistencia() +
                        " celdasAsistencias : " + asistenciaUi.getAlumnosUi().getNombre());
                guardandoListasAsistencias.add(asistenciaUi);
                continue;
            }
        }
        for (CeldasAsistencia celdasAsistencia : celdasAsistenciasColumnaPresentes) {
            AsistenciaUi asistenciaUi = (AsistenciaUi) celdasAsistencia;
            asistenciaUi.setHoraInicioCurso(horaInicioCurso);
            asistenciaUi.setFecha(date);
            if (asistenciaUi.isPintar()) {
                Log.d(TAG, "celdasAsistenciasColumnaPresentes : " + asistenciaUi.getTipoAsistencia() +
                        " celdasAsistenciasColumnaPresentes : " + asistenciaUi.getAlumnosUi().getNombre());
                guardandoListasAsistencias.add(asistenciaUi);
                continue;
            }
        }
        Log.d(TAG, "CONTADORFINAL : " + guardandoListasAsistencias.size());
        validarExisteFecha(date, guardandoListasAsistencias);
        //initGuardarListaAsistencia(guardandoListasAsistencias);
    }

    private void validarExisteFecha(String date, final List<AsistenciaUi> guardandoListasAsistencias) {
        handler.execute(validarFechaRegistroAsistencia, new ValidarFechaRegistroAsistencia.RequestValues(date),
                new UseCase.UseCaseCallback<ValidarFechaRegistroAsistencia.ResponseValue>() {
                    @Override
                    public void onSuccess(ValidarFechaRegistroAsistencia.ResponseValue response) {
                        if (response.isaBoolean()) {
                            Log.d(TAG, "SOLO FALTA REGISTRAR HORA DE FIN");
                            if (view != null)
                                view.mostrarMensaje(res.getString(R.string.validacion_mensaje_guardar));
                        } else {
                            initGuardarListaAsistencia(guardandoListasAsistencias);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void onGuardarSalida() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        handler.execute(obtenerListaAsistencia, new ObtenerListaAsistencia.RequestValues(date),
                new UseCase.UseCaseCallback<ObtenerListaAsistencia.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerListaAsistencia.ResponseValue response) {
                        if (response.getAsistenciaList() == null) {
                            if (view != null)
                                view.mostrarMensaje(res.getString(R.string.validacion_mensaje_guardar_hora_fin));
                        } else {
                            actualizarHoraFinAsistenciaLista(response.getAsistenciaList());
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void actualizarHoraFinAsistenciaLista(List<Asistencia> asistenciaList) {

        handler.execute(guardarAsistenciaListaHoraFin, new GuardarAsistenciaListaHoraFin.RequestValues(asistenciaList),
                new UseCase.UseCaseCallback<GuardarAsistenciaListaHoraFin.ResponseValue>() {
                    @Override
                    public void onSuccess(GuardarAsistenciaListaHoraFin.ResponseValue response) {
                        if (response.isaBoolean()) {
                           /* if (view != null)
                                view.mostrarMensaje(res.getString(R.string.validacion_mensaje_guardar_hora_fin_correctos));*/
                        } else {
                            Log.d(TAG, "ALGO PASO PAPU");
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }


    private void initGuardarListaAsistencia(List<AsistenciaUi> guardandoListasAsistencias) {
        handler.execute(guardarAsistenciaLista, new GuardarAsistenciaLista.ResquestValues(guardandoListasAsistencias),
                new UseCase.UseCaseCallback<GuardarAsistenciaLista.ResponseValue>() {
                    @Override
                    public void onSuccess(GuardarAsistenciaLista.ResponseValue response) {
                        if (response.isaBoolean()) {
                            if (view != null)
                                view.mostrarMensaje(res.getString(R.string.validacion_mensaje_guardar_correcto));return;
                         //   Log.d(TAG, "REGISTROS GUARDADOS CORRECTAMENTE");

                        }
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "onError");
                    }
                });
    }

    private void pintandoCeldas(AsistenciaUi asistenciaUi, List<CeldasAsistencia> celdasList) {
        celdasAsistencias.addAll(celdasList);
        for (int i = 0; i < celdasList.size(); i++) {
            AsistenciaUi asistencia = (AsistenciaUi) celdasList.get(i);
            if (asistencia.isPintar()) {
                remplazarItem(asistencia, asistenciaUi);
                return;
            }
        }
        asistenciaUi.setPintar(true);
    }

    private void remplazarItem(AsistenciaUi asistenciaAnterior, AsistenciaUi asistenciaNueva) {
        asistenciaAnterior.setPintar(false);
        asistenciaNueva.setPintar(true);
        if (view != null) view.actualizarDatosCambiadosTabla();
    }


}
