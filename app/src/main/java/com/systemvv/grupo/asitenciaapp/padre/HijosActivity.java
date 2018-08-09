package com.systemvv.grupo.asitenciaapp.padre;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivity;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;
import com.systemvv.grupo.asitenciaapp.login.LoginActivity;
import com.systemvv.grupo.asitenciaapp.padre.adapter.HijosAdapter;
import com.systemvv.grupo.asitenciaapp.padre.cursoHijos.CursoHijosActivity;
import com.systemvv.grupo.asitenciaapp.padre.dataSource.HijosRepository;
import com.systemvv.grupo.asitenciaapp.padre.dataSource.remote.HijosRemote;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Hijos;
import com.systemvv.grupo.asitenciaapp.padre.listener.HijosListener;
import com.systemvv.grupo.asitenciaapp.padre.useCase.ObtenerInstituto;
import com.systemvv.grupo.asitenciaapp.padre.useCase.ObtenerMisHijos;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HijosActivity extends BaseActivity<HijosView, HijosPresenter> implements HijosView, HijosListener {
    public static final String TAG = HijosActivity.class.getSimpleName();
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.reciclador)
    RecyclerView reciclador;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    HijosAdapter hijosAdapter;
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
    protected HijosPresenter getPresenter() {

        HijosRepository hijosRepository = new HijosRepository(new HijosRemote(new FireStore()));
        return new HijosPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ObtenerMisHijos(hijosRepository),
                new ObtenerInstituto(hijosRepository));
    }

    @Override
    protected HijosView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_hijos);
        ButterKnife.bind(this);
        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        initVistas();
        setupToolbar();
    }

    private void initVistas() {
        hijosAdapter = new HijosAdapter(new ArrayList<Hijos>(), this);
        reciclador.setLayoutManager(new LinearLayoutManager(this));
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(hijosAdapter);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarListaHijos(List<Hijos> hijosList) {
        hijosAdapter.mostrarLista(hijosList);
    }

    @Override
    public void onClickItemHijo(Hijos hijos) {
        Log.d(TAG, "onClickItemHijo : " + hijos.getNombre());
        Intent intent = new Intent(this, CursoHijosActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("hijos", Parcels.wrap(hijos));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_padre, menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_opcion_desconectar:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            default:
                Log.d(TAG, "default:");
                finish();
                onBackPressed();
                break;
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
