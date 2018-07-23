package com.systemvv.grupo.asitenciaapp.asistencia;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.CeldasAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.ColumnaCabeceraAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.FilaCabeceraAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.celdas.CeldasAsistenciaAlumnoFaltoHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.celdas.CeldasAsistenciaAlumnoJustificadoHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.celdas.CeldasAsistenciaAlumnoPuntualHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.celdas.CeldasAsistenciaAlumnoTardeHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.columnas.ColumnaTipoPresenteHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Alumnos;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.MotivoAsistencia;
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


    @Override
    public void onCreate() {
        initVistas();
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


    List<CeldasAsistencia> celdasColumnaListPresente;
    List<CeldasAsistencia> celdasColumnaListas = new ArrayList<>();

    boolean clickColumn = false;

    @Override
    public void onClickColumnaCabecera(@NonNull RecyclerView.ViewHolder holder, List<CeldasAsistencia> celdasColumnaList) {
        if (clickColumn == false) {
            if (holder instanceof ColumnaTipoPresenteHolder) {
                clickColumn = true;
                this.celdasColumnaListPresente = celdasColumnaList;
                Log.d(TAG, "celdasList : " + celdasColumnaList.size());
                for (CeldasAsistencia celdasAsistencia : celdasColumnaList) {
                    AsistenciaUi asistencia = (AsistenciaUi) celdasAsistencia;
                    asistencia.setPintar(true);
                }
            }
            if (view != null) view.actualizarDatosCambiadosTabla();

        } else {
            Log.d(TAG, "no hacer nada");
        }
    }

    @Override
    public void onClickCeldas(@NonNull RecyclerView.ViewHolder holder, AsistenciaUi asistenciaUi, List<CeldasAsistencia> celdasColumnaList) {
        /*if (clickColumn == true) {
            if (holder instanceof CeldasAsistenciaAlumnoPuntualHolder ||
                    holder instanceof CeldasAsistenciaAlumnoTardeHolder ||
                    holder instanceof CeldasAsistenciaAlumnoJustificadoHolder ||
                    holder instanceof CeldasAsistenciaAlumnoFaltoHolder) {
                pintandoCeldas(asistenciaUi, celdasColumnaList);
            }
            if (view != null) view.actualizarDatosCambiadosTabla();
        } else {
        }*/

        if (clickColumn == true) {
            if (holder instanceof CeldasAsistenciaAlumnoPuntualHolder) {
                Log.d(TAG, "CeldasAsistenciaAlumnoPuntualHolder");
                asistenciaUi.setTipoAsistencia("PUNTUAL");
                pintandoCeldas(asistenciaUi, celdasColumnaList);
            } else if (holder instanceof CeldasAsistenciaAlumnoTardeHolder) {
                asistenciaUi.setTipoAsistencia("TARDE");
                pintandoCeldas(asistenciaUi, celdasColumnaList);
                Log.d(TAG, "CeldasAsistenciaAlumnoTardeHolder");
            } else if (holder instanceof CeldasAsistenciaAlumnoJustificadoHolder) {
                asistenciaUi.setTipoAsistencia("JUSTIFICADO");
                pintandoCeldas(asistenciaUi, celdasColumnaList);
                Log.d(TAG, "CeldasAsistenciaAlumnoJustificadoHolder");
            } else if (holder instanceof CeldasAsistenciaAlumnoFaltoHolder) {
                Log.d(TAG, "CeldasAsistenciaAlumnoFaltoHolder");
                asistenciaUi.setTipoAsistencia("FALTO");
                pintandoCeldas(asistenciaUi, celdasColumnaList);
            }
            this.celdasColumnaListas = celdasColumnaList;
            if (view != null) view.actualizarDatosCambiadosTabla();
        } else {
            //Toast.makeText(getApplicationContext(), "Seleccione todos los items primero ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGuardarEntrada() {
        List<AsistenciaUi> asistenciaUiList = new ArrayList<>();


        for (CeldasAsistencia celdasAsistencia : celdasColumnaListPresente) {
            AsistenciaUi asistencia = (AsistenciaUi) celdasAsistencia;
            if (asistencia.isPintar()) {
                Log.d(TAG, "celdasColumnaListPresente: " + asistencia.isPintar());
                asistenciaUiList.add(asistencia);
            }
           /* Log.d(TAG, "onGuardarEntrada : Alumno:  / " + asistencia.getAlumnosUi().getNombre() +
                    " / onGuardarEntrada : TipoAsistencia " + asistencia.getTipoAsistencia() +
                    " / onGuardarEntrada : Extras : " + asistencia.getJustificacion());*/
        }

        for (CeldasAsistencia celdasAsistencia : celdasColumnaListas) {
            AsistenciaUi asistencia = (AsistenciaUi) celdasAsistencia;
            if (asistencia.isPintar()) {
                asistenciaUiList.add(asistencia);
                Log.d(TAG, "celdasColumnaListas: " + asistencia.isPintar());
            }
        }
        int count = 0;
        for (AsistenciaUi asistenciaUi : asistenciaUiList) {
            count++;
            Log.d(TAG, "LISTANUEVA :  " + asistenciaUi.isPintar()+" / count " +count );
        }
    }

    private void pintandoCeldas(AsistenciaUi asistenciaUi, List<CeldasAsistencia> celdasList) {
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
