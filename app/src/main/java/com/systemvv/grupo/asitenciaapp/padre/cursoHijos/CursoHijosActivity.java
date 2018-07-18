package com.systemvv.grupo.asitenciaapp.padre.cursoHijos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivity;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.cursos.adapter.decarador.ItemDecarador;
import com.systemvv.grupo.asitenciaapp.padre.cursoHijos.adapter.CursoHijosAdapter;
import com.systemvv.grupo.asitenciaapp.padre.cursoHijos.listener.CursoHijosListener;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.ReportesActivity;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CursoHijosActivity extends BaseActivity<CursoHijosView, CursoHijosPresenter> implements CursoHijosView,CursoHijosListener{

    public static final String TAG = CursoHijosActivity.class.getSimpleName();


    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.reciclador)
    RecyclerView reciclador;
    CursoHijosAdapter adapter;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected CursoHijosPresenter getPresenter() {
        return new CursoHijosPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected CursoHijosView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_curso_hijos);
        ButterKnife.bind(this);
        initVistas();

    }

    private void initVistas() {
        adapter = new CursoHijosAdapter(new ArrayList<Cursos>(),this);
        reciclador.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        reciclador.addItemDecoration(new ItemDecarador(
                getResources().getDimensionPixelSize(R.dimen.tamanio_item_decarador),
                getResources().getInteger(R.integer.tamanio_columnas)));
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(adapter);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarListaCursos(List<Cursos> cursosList) {
        adapter.mostrarLista(cursosList);
    }

    @Override
    public void onItemClickCursoHijos(Cursos cursos) {
        Intent intent = new Intent(this, ReportesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("cursosUi", Parcels.wrap(cursos));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
