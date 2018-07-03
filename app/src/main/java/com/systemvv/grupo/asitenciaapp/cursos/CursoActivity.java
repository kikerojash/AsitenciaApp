package com.systemvv.grupo.asitenciaapp.cursos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ProgressBar;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.asistencia.AsistenciaActivity;
import com.systemvv.grupo.asitenciaapp.asistenciaM.ControlAsistenciaActivity;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivity;
import com.systemvv.grupo.asitenciaapp.cursos.adapter.CursoAdapter;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.cursos.listener.CursoListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CursoActivity extends BaseActivity<CursoView, CursoPresenter> implements CursoView, CursoListener {

    public static final String TAG = CursoActivity.class.getSimpleName();
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.recicladorCursos)
    RecyclerView reciclador;
    CursoAdapter adapter;
    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;


    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected CursoPresenter getPresenter() {
        return new CursoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected CursoView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_curso);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initVistas();
    }

    private void initVistas() {
        adapter = new CursoAdapter(new ArrayList<CursoUi>(), this);
        reciclador.setLayoutManager(new LinearLayoutManager(this));
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(adapter);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarListaCursos(List<CursoUi> cursoUiList) {
        adapter.mostrarLista(cursoUiList);
    }

    @Override
    public void onClickCursoItem(CursoUi cursoUi) {
        Intent intent = new Intent(getActivity(), ControlAsistenciaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("cursoUi", Parcels.wrap(cursoUi));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

}
