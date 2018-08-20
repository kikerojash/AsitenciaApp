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
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Alumnos;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.MotivoAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.useCase.GuardarAsistenciaLista;
import com.systemvv.grupo.asitenciaapp.asistencia.useCase.GuardarAsistenciaListaHoraFin;
import com.systemvv.grupo.asitenciaapp.asistencia.useCase.ObtenerListaAlumnos;
import com.systemvv.grupo.asitenciaapp.asistencia.useCase.ValidarFechaRegistroAsistencia;
import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenterImpl;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.utils.Constantes;
import org.parceler.Parcels;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ControlAsistenciaPresenterImpl extends BaseActivityPresenterImpl<ControlAsistenciaView> implements ControlAsistenciaPresenter {

    public static final String TAG = ControlAsistenciaPresenterImpl.class.getSimpleName();

    List<ColumnaCabeceraAsistencia> columnHeaderList;
    List<List<CeldasAsistencia>> cellsList;
    List<FilaCabeceraAsistencia> rowHeaderList;
    CursoUi cursoUi;

    /*Caso de Uso*/
    GuardarAsistenciaLista guardarAsistenciaLista;
    ValidarFechaRegistroAsistencia validarFechaRegistroAsistencia;
    GuardarAsistenciaListaHoraFin guardarAsistenciaListaHoraFin;
    ObtenerListaAlumnos obtenerListaAlumnos;


    public ControlAsistenciaPresenterImpl(UseCaseHandler handler, Resources res, GuardarAsistenciaLista guardarAsistenciaLista, ValidarFechaRegistroAsistencia validarFechaRegistroAsistencia, GuardarAsistenciaListaHoraFin guardarAsistenciaListaHoraFin, ObtenerListaAlumnos obtenerListaAlumnos) {
        super(handler, res);
        this.guardarAsistenciaLista = guardarAsistenciaLista;
        this.validarFechaRegistroAsistencia = validarFechaRegistroAsistencia;
        this.guardarAsistenciaListaHoraFin = guardarAsistenciaListaHoraFin;
        this.obtenerListaAlumnos = obtenerListaAlumnos;
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


    List<Alumnos> alumnosList;
    List<MotivoAsistencia> motivoAsistenciaList;

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initTablaInstancia() {
        columnHeaderList = new ArrayList<>();
        cellsList = new ArrayList<>();
        rowHeaderList = new ArrayList<>();
        alumnosList = new ArrayList<>();

        celdasAsistenciasColumnaPresentes = new ArrayList<>();
        celdasAsistencias = new ArrayList<>();

    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate :");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dfHours = new SimpleDateFormat("HH:mm aaa");
        String date = df.format(Calendar.getInstance().getTime());
        initTablaInstancia();
        initObtenerListaAlumnos(cursoUi);
        validarExisteFecha(date);
        initObtenerListaMotivosAsistencia();
    }

    private void initObtenerListaMotivosAsistencia() {
        motivoAsistenciaList = new ArrayList<>();
        motivoAsistenciaList.addAll(motivoAsistenciaList());
    }


    private List<MotivoAsistencia> motivoAsistenciaList() {
        List<MotivoAsistencia> motivoAsistencias = new ArrayList<>();
        MotivoAsistencia motivosAsistenciaPuntual = new MotivoAsistencia();
        motivosAsistenciaPuntual.setTipoMotivo(MotivoAsistencia.TIPO_ASISTENCIA_PUNTUAL);


        MotivoAsistencia motivosAsistenciaTardeUi = new MotivoAsistencia();
        motivosAsistenciaTardeUi.setTipoMotivo(MotivoAsistencia.TIPO_ASISTENCIA_TARDE);


        MotivoAsistencia motivosAsistenciaFaltoUi = new MotivoAsistencia();
        motivosAsistenciaFaltoUi.setTipoMotivo(MotivoAsistencia.TIPO_ASISTENCIA_FALTO);


        motivoAsistencias.add(motivosAsistenciaPuntual);
        motivoAsistencias.add(motivosAsistenciaTardeUi);
        motivoAsistencias.add(motivosAsistenciaFaltoUi);

        return motivoAsistencias;
    }


    private void initObtenerListaAlumnos(CursoUi cursoUi) {
        if (view != null) view.mostrarProgressBar();
        handler.execute(obtenerListaAlumnos, new ObtenerListaAlumnos.RequestValues(cursoUi),
                new UseCase.UseCaseCallback<ObtenerListaAlumnos.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerListaAlumnos.ResponseValue response) {
                        alumnosList.addAll(response.getAlumnosList());
                        columnHeaderList.addAll(motivoAsistenciaList);
                        rowHeaderList.addAll(alumnosList);
                        cellsList.addAll(getCellListForSortingTest(alumnosList));
                        if (view != null) {
                            view.ocultarProgressBar();
                            view.mostrarListaTablas(columnHeaderList, rowHeaderList, cellsList);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });

    }


    private List<List<CeldasAsistencia>> getCellListForSortingTest(List<Alumnos> alumnosList) {
        List<List<CeldasAsistencia>> list = new ArrayList<>();
        for (Alumnos alumnosUi : alumnosList) {
            Log.d(TAG, "alumnosUi : " + alumnosUi.getNombre());
            List<CeldasAsistencia> cellList = new ArrayList<>();
            Log.d(TAG, "motivoAsistencias : " + motivoAsistenciaList().size());
            for (int j = 0; j < motivoAsistenciaList.size(); j++) {
                MotivoAsistencia motivosAsistenciaUi = motivoAsistenciaList().get(j);
                if (motivosAsistenciaUi.getTipoMotivo() == 1) {
                    Asistencia asistencia = new Asistencia();
                    asistencia.setPintar(false);
                    asistencia.setJustificacion(MotivoAsistencia.TIPO_ASISTENCIA_PUNTUAL);
                    asistencia.setTipoAsistencia("asdasdasd" + j);
                    asistencia.setAlumnosUi(alumnosUi);
                    cellList.add(asistencia);
                    Log.d(TAG, "alumnosUi.getTipoAsistencia() 1: " + motivosAsistenciaUi.getTipoMotivo());
                } else if (motivosAsistenciaUi.getTipoMotivo() == 2) {
                    Asistencia asistencia = new Asistencia();
                    asistencia.setPintar(false);
                    asistencia.setJustificacion(MotivoAsistencia.TIPO_ASISTENCIA_TARDE);
                    asistencia.setTipoAsistencia("asdasdasd" + j);
                    asistencia.setAlumnosUi(alumnosUi);
                    cellList.add(asistencia);
                    Log.d(TAG, "alumnosUi.getTipoAsistencia() 2: " + motivosAsistenciaUi.getTipoMotivo());
                } else if (motivosAsistenciaUi.getTipoMotivo() == 4) {
                    Asistencia asistencia = new Asistencia();
                    asistencia.setPintar(false);
                    asistencia.setJustificacion(MotivoAsistencia.TIPO_ASISTENCIA_FALTO);
                    asistencia.setTipoAsistencia("asdasdasd" + j);
                    asistencia.setAlumnosUi(alumnosUi);
                    cellList.add(asistencia);
                    Log.d(TAG, "alumnosUi.getTipoAsistencia() 4: " + motivosAsistenciaUi.getTipoMotivo());
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
                    Asistencia asistencia = (Asistencia) celdasAsistencia;
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
    public void onClickCeldas(@NonNull RecyclerView.ViewHolder holder, Asistencia asistenciaUi, List<CeldasAsistencia> clickCeldasList) {
        if (clickColumn == true) {
            if (holder instanceof CeldasAsistenciaAlumnoPuntualHolder) {
                asistenciaUi.setTipoAsistencia("PUNTUAL");
                pintandoCeldas(asistenciaUi, clickCeldasList);
                Log.d(TAG, "onClickCeldasPUNTUAL : ");
            } else if (holder instanceof CeldasAsistenciaAlumnoTardeHolder) {
                asistenciaUi.setTipoAsistencia("TARDE");
                pintandoCeldas(asistenciaUi, clickCeldasList);
                Log.d(TAG, "onClickCeldasTARDE : ");
            } else if (holder instanceof CeldasAsistenciaAlumnoFaltoHolder) {
                asistenciaUi.setTipoAsistencia("FALTO");
                pintandoCeldas(asistenciaUi, clickCeldasList);
                Log.d(TAG, "onClickCeldasFALTO : ");
            }
            if (view != null) view.actualizarDatosCambiadosTabla();
        } else {
            if (view!=null)view.mostrarMensaje("Inicie la Selección de Registro!!");
            // Toast.makeText(getApplicationContext(), "Seleccione todos los items primero ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGuardarEntrada() {
        List<Asistencia> guardandoListasAsistencias = new ArrayList<>();
        switch (tipoValidacionExistFecha) {
            case Constantes.FALTA_ASISTENCIA_HORA_CIERRE:
                Log.d(TAG, "FALTA_ASISTENCIA_HORA_CIERRE : ");
                if (view != null) {
                    view.mostrarMensaje("Falta Registrar Hora Cierre");
                    return;
                }
                break;
            case Constantes.FALTA_ASISTENCIA_REGISTRO_HOY:
                Log.d(TAG, "FALTA_ASISTENCIA_REGISTRO_HOY : ");
                // List<Asistencia> guardandoListasAsistencias = new ArrayList<>();

                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                DateFormat dfHours = new SimpleDateFormat("HH:mm aaa");
                String date = df.format(Calendar.getInstance().getTime());
                String horaInicioCurso = dfHours.format(Calendar.getInstance().getTime());

                for (CeldasAsistencia celdasAsistencia : celdasAsistencias) {
                    Asistencia asistenciaUi = (Asistencia) celdasAsistencia;
                    asistenciaUi.setHoraInicioCurso(horaInicioCurso);
                    asistenciaUi.setFecha(date);
                    if (asistenciaUi.isPintar()) {
                        /*Log.d(TAG, "celdasAsistencias : " + asistenciaUi.getTipoAsistencia() +
                                " celdasAsistencias : " + asistenciaUi.getAlumnosUi().getNombre());*/
                        guardandoListasAsistencias.add(asistenciaUi);
                        continue;
                    }
                }
                for (CeldasAsistencia celdasAsistencia : celdasAsistenciasColumnaPresentes) {
                    Asistencia asistenciaUi = (Asistencia) celdasAsistencia;
                    asistenciaUi.setHoraInicioCurso(horaInicioCurso);
                    asistenciaUi.setFecha(date);
                    if (asistenciaUi.isPintar()) {
                        Log.d(TAG, "celdasAsistenciasColumnaPresentes : " + asistenciaUi.getTipoAsistencia() +
                                " celdasAsistenciasColumnaPresentes : " + asistenciaUi.getAlumnosUi().getNombre());
                        guardandoListasAsistencias.add(asistenciaUi);
                        continue;
                    }
                }
                if (guardandoListasAsistencias.size() == 0 || guardandoListasAsistencias == null) {
                    if (view != null) view.mostrarMensaje("No se Permiten Campos Vacíos");
                    return;
                }
                initGuardarListaAsistencia(guardandoListasAsistencias);
                break;
            case Constantes.REGISTROS_COMPLETOS:
                if (view != null) {
                    view.mostrarMensaje("Vuelva Mañana, Registros Completos");
                    return;
                }
                break;
        }
        if (guardandoListasAsistencias.size() == 0) {
            if (view != null) view.mostrarMensaje("No se Permiten Campos Vacíos");
        }
    }

    int tipoValidacionExistFecha = 0;
    List<String> stringListAsistencia;

    private void validarExisteFecha(String date) {
        if (view != null) view.mostrarProgressBar();
        stringListAsistencia = new ArrayList<>();
        handler.execute(validarFechaRegistroAsistencia, new ValidarFechaRegistroAsistencia.RequestValues(date, cursoUi),
                new UseCase.UseCaseCallback<ValidarFechaRegistroAsistencia.ResponseValue>() {
                    @Override
                    public void onSuccess(ValidarFechaRegistroAsistencia.ResponseValue response) {
                        switch (response.getTipoValidacionFecha()) {
                            case Constantes.FALTA_ASISTENCIA_HORA_CIERRE:
                                tipoValidacionExistFecha = Constantes.FALTA_ASISTENCIA_HORA_CIERRE;
                                stringListAsistencia.addAll(response.getStringList());
                                if (view != null) {
                                    view.mostrarInformacionSnackBar("Falta Registrar Hora Cierre", Constantes.FALTA_ASISTENCIA_HORA_CIERRE);
                                }
                                Log.d(TAG, "SOLO FALTA REGISTRAR HORA DE CIERRE");
                                break;
                            case Constantes.FALTA_ASISTENCIA_REGISTRO_HOY:
                                tipoValidacionExistFecha = Constantes.FALTA_ASISTENCIA_REGISTRO_HOY;
                                if (view != null) {
                                    view.mostrarInformacionSnackBar("Falta Registro de Hoy", Constantes.FALTA_ASISTENCIA_REGISTRO_HOY);
                                }
                                Log.d(TAG, "AUN NO SE REGISTRAR ASISTENCIA");
                                break;

                            case Constantes.REGISTROS_COMPLETOS:
                                if (view != null) {
                                    view.mostrarInformacionSnackBar("Vuelva Mañana, Registros Completos", Constantes.REGISTROS_COMPLETOS);
                                }
                                tipoValidacionExistFecha = Constantes.REGISTROS_COMPLETOS;
                                Log.d(TAG, "REGISTROS_COMPLETOS");
                                break;

                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void onGuardarSalida() {

        switch (tipoValidacionExistFecha) {
            case Constantes.FALTA_ASISTENCIA_HORA_CIERRE:
                Log.d(TAG, "FALTA_ASISTENCIA_HORA_CIERRE : ");
                initRegistroSalida(stringListAsistencia);
                break;
            case Constantes.FALTA_ASISTENCIA_REGISTRO_HOY:
                Log.d(TAG, "FALTA_ASISTENCIA_REGISTRO_HOY : ");
                if (view != null) {
                    view.mostrarMensaje("Falta Registros Hoy");
                    return;
                }
                break;
            case Constantes.REGISTROS_COMPLETOS:
                if (view != null) {
                    view.mostrarMensaje("Vuelva Mañana, Registros Completos");
                    return;
                }
                break;
        }
    }

    private void initRegistroSalida(List<String> stringListAsistencia) {
        handler.execute(guardarAsistenciaListaHoraFin, new GuardarAsistenciaListaHoraFin.RequestValues(stringListAsistencia),
                new UseCase.UseCaseCallback<GuardarAsistenciaListaHoraFin.ResponseValue>() {
                    @Override
                    public void onSuccess(GuardarAsistenciaListaHoraFin.ResponseValue response) {
                        if (response.isaBoolean()) {
                            if (view != null)
                                view.mostrarMensaje(res.getString(R.string.validacion_mensaje_guardar_hora_fin_correctos));
                        } else {
                            Log.d(TAG, "ALGO PASO PAPU");
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void onClickAccionBar(int tipoResultado) {
        switch (tipoResultado) {
            case Constantes.FALTA_ASISTENCIA_HORA_CIERRE:
                //  mostrarListaTablas();
                break;
            case Constantes.FALTA_ASISTENCIA_REGISTRO_HOY:
                //  mostrarListaTablas();
                break;
        }
    }


    private void initGuardarListaAsistencia(List<Asistencia> guardandoListasAsistencias) {
        if (view != null) view.mostrarProgressBar();
        handler.execute(guardarAsistenciaLista, new GuardarAsistenciaLista.ResquestValues(guardandoListasAsistencias, cursoUi),
                new UseCase.UseCaseCallback<GuardarAsistenciaLista.ResponseValue>() {
                    @Override
                    public void onSuccess(GuardarAsistenciaLista.ResponseValue response) {
                        if (response.isaBoolean()) {
                            if (view != null) {
                                view.mostrarMensaje(res.getString(R.string.validacion_mensaje_guardar_correcto));
                                //view.mostrarMensaje("NO SE OLVIDE SUS REGISTRO DE SALIDA");
                                view.ocultarProgressBar();
                            }

                            if (view != null)
                                view.mostrarMensaje("NO SE OLVIDE SU REGISTRO DE SALIDA");


                        }
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "onError");
                    }
                });


    }

    private void pintandoCeldas(Asistencia asistenciaUi, List<CeldasAsistencia> celdasList) {
        celdasAsistencias.addAll(celdasList);
        for (int i = 0; i < celdasList.size(); i++) {
            Asistencia asistencia = (Asistencia) celdasList.get(i);
            if (asistencia.isPintar()) {
                remplazarItem(asistencia, asistenciaUi);
                return;
            }
        }
        asistenciaUi.setPintar(true);
    }

    private void remplazarItem(Asistencia asistenciaAnterior, Asistencia asistenciaNueva) {
        asistenciaAnterior.setPintar(false);
        asistenciaNueva.setPintar(true);
        if (view != null) view.actualizarDatosCambiadosTabla();
    }


}
