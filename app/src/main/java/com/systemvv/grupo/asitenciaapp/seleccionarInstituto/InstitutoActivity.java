package com.systemvv.grupo.asitenciaapp.seleccionarInstituto;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
import com.systemvv.grupo.asitenciaapp.login.LoginActivity;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.adapter.InstitutoAdapter;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.SeccionDialog;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.listener.InstitutoListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstitutoActivity extends BaseActivity<InstitutoView, InstitutoPresenter> implements InstitutoView, InstitutoListener {
    public static final String TAG = InstitutoActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.reciclador)
    RecyclerView reciclador;
    InstitutoAdapter adapter;

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
    protected InstitutoPresenter getPresenter() {
        return new InstitutoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected InstitutoView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_instituto);
        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        ButterKnife.bind(this);
        initVistas();
        initToolbar();
    }

    private void initVistas() {
        adapter = new InstitutoAdapter(new ArrayList<InstitutoUi>(), this);
        reciclador.setLayoutManager(new LinearLayoutManager(this));
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(adapter);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarListaInstitutos(List<InstitutoUi> institutoUiList) {
        adapter.mostrarLista(institutoUiList);
    }

    @Override
    public void onClickInstituto(InstitutoUi institutoUi) {
        Log.d(TAG, "onClickInstituto : " + institutoUi.getNombre());

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        SeccionDialog dialogFragment = new SeccionDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable("example", Parcels.wrap(institutoUi));
        dialogFragment.setArguments(bundle);
        dialogFragment.show(ft, "dialog");
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_instituto_docente, menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

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
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
