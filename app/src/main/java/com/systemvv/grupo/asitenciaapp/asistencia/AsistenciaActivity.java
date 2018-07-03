package com.systemvv.grupo.asitenciaapp.asistencia;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.listener.ITableViewListener;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.AsistenciaAdapter;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.estructura.AsistenciaCelda;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.estructura.AsistenciaColumnaCabecera;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.estructura.AsistenciaFilaCabecera;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder.CeldasAsistenciaHolder;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivity;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsistenciaActivity extends BaseActivity<AsistenciaView, AsistenciaPresenter> implements AsistenciaView, ITableViewListener {

    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.txtNombreCurso)
    TextView textViewNombreInstituto;
    @BindView(R.id.textTitulo)
    TextView textViewNombreCuro;
    @BindView(R.id.txtGrado)
    TextView textViewGradoSeccion;
    @BindView(R.id.txNombreProfe)
    TextView textViewNombreProfe;
   /* @BindView(R.id.fondoCurso)
    ImageView imageViewFondo;*/
    @BindView(R.id.content_container)
    TableView table;
    private AsistenciaAdapter adapter;

    public static final String TAG = AsistenciaActivity.class.getSimpleName();

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected AsistenciaPresenter getPresenter() {
        return new AsistenciaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected AsistenciaView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_asistencia);
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarInformacionBasica(CursoUi cursoUi) {
        textViewNombreInstituto.setText(cursoUi.getInstitutoUi().getNombre());
        textViewNombreCuro.setText(cursoUi.getNombre());
        textViewGradoSeccion.setText("Grado : " + cursoUi.getGradoSelected() + " Sección : " + cursoUi.getSeccionSelected());
        //Glide.with(this).load(cursoUi.getFoto()).into(imageViewFondo);
    }

    @Override
    public void mostrarListaTablas(List<AsistenciaColumnaCabecera> columnaCabeceraList, List<AsistenciaFilaCabecera> filaCabeceraList, List<List<AsistenciaCelda>> bodyList) {
        adapter = new AsistenciaAdapter(this);
        table.setAdapter(adapter);
        table.setTableViewListener(this);
        table.setIgnoreSelectionColors(false);
        table.setHasFixedWidth(false);
        adapter.setAllItems(filaCabeceraList,columnaCabeceraList , bodyList);
        table.setIgnoreSelectionColors(true);
    }


    @Override
    public void actualizarCeldas(AsistenciaUi asistenciaUi) {
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder holder, int column, int row) {

        if (holder instanceof CeldasAsistenciaHolder) {
            CeldasAsistenciaHolder celdasAsistenciaHolder = (CeldasAsistenciaHolder) holder;

            AsistenciaUi asistenciaUi = celdasAsistenciaHolder.obtenerAsistenciaUi();

            Log.d(TAG, "onClickAsistenciaCelda" + asistenciaUi.getTipasistencia());
            if (asistenciaUi == null) return;


            if (asistenciaUi.isPintar()){
                asistenciaUi.setPintar(false);
               // celdasAsistenciaHolder.validarCamposColor(asistenciaUi);
            }else {
                asistenciaUi.setPintar(true);
               // celdasAsistenciaHolder.validarCamposColor(asistenciaUi);
            }
            adapter.notifyDataSetChanged();
        }


    }



    private void onClickAsistenciaTardeJustificado(AsistenciaUi asistenciaUi) {
        if (presenter == null) return;
        presenter.onClickAsistenciaTardeJustificado(asistenciaUi);
    }

    private void onClickAsistenciaTarde(AsistenciaUi asistenciaUi) {
        if (presenter == null) return;
        presenter.onClickAsistenciaTarde(asistenciaUi);
    }

    private void onClickAsistenciaPuntual(AsistenciaUi asistenciaUi) {
        if (presenter == null) return;
        presenter.onClickAsistenciaPuntual(asistenciaUi);
    }

    private void onClickAsistenciaCelda(AsistenciaUi asistenciaUi) {
        if (presenter == null) return;
        //  presenter.onClickAsistenciaFalto(asistenciaUi);
        presenter.onClickAsistenciaCelda(asistenciaUi);
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
