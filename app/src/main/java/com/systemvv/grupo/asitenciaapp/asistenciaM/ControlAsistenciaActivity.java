package com.systemvv.grupo.asitenciaapp.asistenciaM;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
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

import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.AdapterTest;
import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.estructura.AsistenciaCeldas;
import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.estructura.AsistenciaColumna;
import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.estructura.AsistenciaFilas;
import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.holder.AsistenciaCeldasHolder;
import com.systemvv.grupo.asitenciaapp.asistenciaM.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivity;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ControlAsistenciaActivity extends BaseActivity<ControlAsistenciaView, ControlAsistenciaPresenter> implements ControlAsistenciaView, ITableViewListener {

    public static final String TAG = ControlAsistenciaActivity.class.getSimpleName();


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
    @BindView(R.id.backdrop)
    ImageView imageViewFondo;
    @BindView(R.id.content_container)
    TableView table;
    /*@BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;*/
    @BindView(R.id.profile_image)
    CircleImageView circleImageView;
    private AdapterTest adapter;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected ControlAsistenciaPresenter getPresenter() {
        return new ControlAsistenciaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected ControlAsistenciaView getBaseView() {
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
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarInformacionBasica(CursoUi cursoUi) {
        String imageDocente="https://get.pxhere.com/photo/blackboard-university-speech-lecturer-lecture-teacher-teaching-physics-professor-orator-birger-kollmeier-public-speaking-698271.jpg";
        Glide.with(this).load(imageDocente).into(circleImageView);
        textViewNombreInstituto.setText(cursoUi.getInstitutoUi().getNombre());
        textViewNombreCuro.setText(cursoUi.getNombre());
        textViewGradoSeccion.setText("Grado : " + cursoUi.getGradoSelected() + " Sección : " + cursoUi.getSeccionSelected());
        Glide.with(this).load(cursoUi.getFoto()).into(imageViewFondo);
        initAdapter();
    }

    @Override
    public void mostrarListaTablas(List<AsistenciaColumna> columnHeaderList, List<AsistenciaFilas> rowHeaderList, List<List<AsistenciaCeldas>> cellsList) {
        adapter.setAllItems(rowHeaderList, columnHeaderList, cellsList);
        //adapter.setAllItems(rowHeaderList, columnHeaderList, getListaCeldas());
    }

    private void initAdapter() {
        adapter = new AdapterTest(this);
        table.setAdapter(adapter);
        table.setIgnoreSelectionColors(false);
        table.setHasFixedWidth(false);
        table.setIgnoreSelectionColors(true);
        presenter.onListAdapter();
        table.setTableViewListener(this);

    }

    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder holder, int p_nXPosition, int p_nYPosition) {
        if (holder instanceof AsistenciaCeldasHolder) {
            AsistenciaCeldasHolder celdasAsistenciaHolder = (AsistenciaCeldasHolder) holder;
            Asistencia asistenciaUi = celdasAsistenciaHolder.obtenerAsistenciaUi();
            Log.d(TAG, "asistenciaUi : " + asistenciaUi.getNombreAsistencia() + " / bollean " + asistenciaUi.isaBoolean());
            if (asistenciaUi == null) return;
            if (asistenciaUi.isaBoolean()) {
                asistenciaUi.setaBoolean(false);
            } else {
                asistenciaUi.setaBoolean(true);
            }
            adapter.notifyDataSetChanged();

        }
    }

   /* @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder holder, int column, int row) {
        // Do What you want
        if (holder instanceof AsistenciaCeldasHolder) {
            AsistenciaCeldasHolder celdasAsistenciaHolder = (AsistenciaCeldasHolder) holder;
            Asistencia asistenciaUi = celdasAsistenciaHolder.obtenerAsistenciaUi();
            Log.d(TAG, "asistenciaUi : " + asistenciaUi.getNombreAsistencia() + " / bollean " + asistenciaUi.isaBoolean());

        }
    }*/



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
