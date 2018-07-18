package com.systemvv.grupo.asitenciaapp.padre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivity;
import com.systemvv.grupo.asitenciaapp.padre.adapter.HijosAdapter;
import com.systemvv.grupo.asitenciaapp.padre.cursoHijos.CursoHijosActivity;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Hijos;
import com.systemvv.grupo.asitenciaapp.padre.listener.HijosListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HijosActivity extends BaseActivity<HijosView, HijosPresenter> implements HijosView,HijosListener {
    public static final String TAG = HijosActivity.class.getSimpleName();
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.reciclador)
    RecyclerView reciclador;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    HijosAdapter hijosAdapter;

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
        return new HijosPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
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
        initVistas();
        setupToolbar();
    }

    private void initVistas() {
        hijosAdapter = new HijosAdapter(new ArrayList<Hijos>(),this);
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
        Log.d(TAG,"onClickItemHijo : " + hijos.getNombre());
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
}
