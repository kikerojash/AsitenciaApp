package com.systemvv.grupo.asitenciaapp.padre.cursoHijos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivity;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.cursos.adapter.decarador.ItemDecarador;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;
import com.systemvv.grupo.asitenciaapp.login.LoginActivity;
import com.systemvv.grupo.asitenciaapp.padre.cursoHijos.adapter.CursoHijosAdapter;
import com.systemvv.grupo.asitenciaapp.padre.cursoHijos.dataSource.CursoHijosRepository;
import com.systemvv.grupo.asitenciaapp.padre.cursoHijos.dataSource.remote.CursoHijosRemote;
import com.systemvv.grupo.asitenciaapp.padre.cursoHijos.listener.CursoHijosListener;
import com.systemvv.grupo.asitenciaapp.padre.cursoHijos.useCase.ObtenerCursoHijos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.ReportesActivity;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CursoHijosActivity extends BaseActivity<CursoHijosView, CursoHijosPresenter> implements CursoHijosView, CursoHijosListener {

    public static final String TAG = CursoHijosActivity.class.getSimpleName();


    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.reciclador)
    RecyclerView reciclador;
    CursoHijosAdapter adapter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    //firebase auth object
    private FirebaseAuth firebaseAuth;

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
        CursoHijosRepository cursoHijosRepository = new CursoHijosRepository(new CursoHijosRemote(new FireStore()));
        return new CursoHijosPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ObtenerCursoHijos(cursoHijosRepository));
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
        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        initVistas();
        setupToolbar();

    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initVistas() {

        adapter = new CursoHijosAdapter(new ArrayList<Cursos>(), this);
        reciclador.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
