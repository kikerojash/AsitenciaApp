package com.systemvv.grupo.asitenciaapp.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.listener.ITableViewListener;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.test.adapter.AdapterTest;
import com.systemvv.grupo.asitenciaapp.test.adapter.estructura.AsistenciaCeldas;
import com.systemvv.grupo.asitenciaapp.test.adapter.estructura.AsistenciaColumna;
import com.systemvv.grupo.asitenciaapp.test.adapter.estructura.AsistenciaFilas;
import com.systemvv.grupo.asitenciaapp.test.adapter.holder.AsistenciaCeldasHolder;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity implements ITableViewListener {

    public static final String TAG = TestActivity.class.getSimpleName();

    @BindView(R.id.content_container)
    TableView table;
    private AdapterTest adapter;
//    private List<AsistenciaColumna> rowHeaderList;
//    private List<List<AsistenciaCeldas>> cellsList;
//    private List<AsistenciaFilas> columnHeaderList;


    private List<AsistenciaColumna> columnHeaderList;
    private List<List<AsistenciaCeldas>> cellsList;
    private List<AsistenciaFilas> rowHeaderList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        ButterKnife.bind(this);
        columnHeaderList = new ArrayList<>();
        rowHeaderList = new ArrayList<>();
        cellsList = new ArrayList<>();

        initViewAdapter();
    }

    private void initViewAdapter() {


        adapter = new AdapterTest(this);
        table.setAdapter(adapter);
        table.setIgnoreSelectionColors(false);
        table.setHasFixedWidth(false);
        table.setIgnoreSelectionColors(true);
        table.setTableViewListener(this);

        columnHeaderList.addAll(getAlumnos());
        rowHeaderList.addAll(getTipoAsistencia());


        adapter.setAllItems(rowHeaderList, columnHeaderList, getListaCeldas());
    }

    private List<List<AsistenciaCeldas>> getListaCeldas() {
        List<List<AsistenciaCeldas>> cellsList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            List<AsistenciaCeldas> celdas = new ArrayList<>();
            for (int j=0;j<3;j++){
                Asistencia asistencia = new Asistencia();
                asistencia.setaBoolean(false);
                //asistencia.setTipoAsistencia(TipoAsistencia.MotivoAsistencia.TIPO_ASISTENCIA_PRESENTE.name()+"");
                asistencia.setNombreAsistencia("asdasdasd");
                celdas.add(asistencia);
            }

            cellsList.add(celdas);
        }

        return cellsList;
    }

    private List<TipoAsistencia> getTipoAsistencia() {
        List<TipoAsistencia> tipoAsistencias = new ArrayList<>();

        TipoAsistencia tipoAsistencia = new TipoAsistencia();
        tipoAsistencia.setMotivoAsistencia(TipoAsistencia.MotivoAsistencia.TIPO_ASISTENCIA_PRESENTE);
        tipoAsistencias.add(tipoAsistencia);

        TipoAsistencia tipoAsistencia2 = new TipoAsistencia();
        tipoAsistencia.setMotivoAsistencia(TipoAsistencia.MotivoAsistencia.TTIPO_ASISTENCIA_TARDE);
        tipoAsistencias.add(tipoAsistencia2);

        TipoAsistencia tipoAsistencia3 = new TipoAsistencia();
        tipoAsistencia.setMotivoAsistencia(TipoAsistencia.MotivoAsistencia.TIPO_ASISTENCIA_FALTO);
        tipoAsistencias.add(tipoAsistencia3);


       /* for (int i = 0;i<10;i++){
            tipoAsistencias.add(new TipoAsistencia(Mo, "puntual"));
        }*/
        return tipoAsistencias;
    }

    private List<Alumnos> getAlumnos() {
        List<Alumnos> alumnos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            alumnos.add(new Alumnos("Luis Enrique", "Rojas palomino", ""));
        }
        return alumnos;
    }

    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder holder, int p_nXPosition, int p_nYPosition) {
        if (holder instanceof AsistenciaCeldasHolder) {
            AsistenciaCeldasHolder celdasAsistenciaHolder = (AsistenciaCeldasHolder) holder;

            Asistencia asistenciaUi = celdasAsistenciaHolder.obtenerAsistenciaUi();
            Log.d(TAG,"asistenciaUi : "+ asistenciaUi.getNombreAsistencia()+ " / bollean "+ asistenciaUi.isaBoolean());

            if (asistenciaUi == null) return;
            if (asistenciaUi.isaBoolean()){
                asistenciaUi.setaBoolean(false);
               // celdasAsistenciaHolder.validarCamposColor(asistenciaUi);
            }else {
                asistenciaUi.setaBoolean(true);
               // celdasAsistenciaHolder.validarCamposColor(asistenciaUi);
            }
            adapter.notifyDataSetChanged();
           // Log.d(TAG, "onClickAsistenciaCelda" + asistenciaUi.getTipasistencia());


            //onClickAsistenciaCelda(asistenciaUi);

        }
    }

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder p_jColumnHeaderView, int p_nXPosition) {

    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder p_jColumnHeaderView, int p_nXPosition) {

    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder p_jRowHeaderView, int p_nYPosition) {

    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder p_jRowHeaderView, int p_nYPosition) {

    }
}
