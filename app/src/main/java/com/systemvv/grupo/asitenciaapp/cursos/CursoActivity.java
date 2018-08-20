package com.systemvv.grupo.asitenciaapp.cursos;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.asistencia.AsistenciaActivity;
import com.systemvv.grupo.asitenciaapp.asistencia.ControlAsistenciaActivity;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivity;
import com.systemvv.grupo.asitenciaapp.conexion.FireAuthConexion;
import com.systemvv.grupo.asitenciaapp.cursos.adapter.CursoAdapter;
import com.systemvv.grupo.asitenciaapp.cursos.dataSource.CursoRepository;
import com.systemvv.grupo.asitenciaapp.cursos.dataSource.remote.CursoRemote;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.cursos.listener.CursoListener;
import com.systemvv.grupo.asitenciaapp.cursos.useCase.ObtenerCursoLista;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;
import com.systemvv.grupo.asitenciaapp.login.LoginActivity;
import com.systemvv.grupo.asitenciaapp.utils.Utils;

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
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;

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
    protected CursoPresenter getPresenter() {

        CursoRepository cursoRepository = new CursoRepository(new CursoRemote(new FireStore()));
        return new CursoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ObtenerCursoLista(cursoRepository));
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
        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        setupToolbar();
        initVistas();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initVistas() {
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        adapter = new CursoAdapter(new ArrayList<CursoUi>(), this);
        reciclador.setLayoutManager(new LinearLayoutManager(this));
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(adapter);
        reciclador.setItemAnimator(itemAnimator);
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
        Intent intent = new Intent(this, ControlAsistenciaActivity.class);
        //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
      /*  String keyInstituto = cursoUi.getInstitutoUi().getKeyInstituto();
        String keyCurso = cursoUi.getKeyCurso();
        String keyUser = cursoUi.getInstitutoUi().getKeyUsuario();
        String keyPeriodo = cursoUi.getInstitutoUi().getKeyPeriodo();
        String keySeccion = cursoUi.getSeccionSelected();
        String keyGrado = cursoUi.getGradoSelected()+"";*/
        Bundle bundle = new Bundle();
       /* bundle.putString("keyInstituto",keyInstituto);
        bundle.putString("keyCurso",keyCurso);
        bundle.putString("keyUser",keyUser);
        bundle.putString("keyPeriodo",keyPeriodo);
        bundle.putString("keySeccion",keySeccion);
        bundle.putString("keyGrado",keyGrado);*/
        bundle.putParcelable("cursoUi", Parcels.wrap(cursoUi));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

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
        if (savedInstanceState == null) {
            constraintLayout.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    constraintLayout.getViewTreeObserver().removeOnPreDrawListener(this);
                    startIntroAnimation();
                    return true;
                }
            });
        }
    }

    private void startIntroAnimation() {
        ViewCompat.setElevation(toolbar, 0);
        constraintLayout.setScaleY(0.1f);
        constraintLayout.setPivotY(2);
       // llAddComment.setTranslationY(200);

        constraintLayout.animate()
                .scaleY(1)
                .setDuration(200)
                .setInterpolator(new AccelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ViewCompat.setElevation(toolbar, Utils.dpToPx(8));
                      //  animateContent();
                    }
                })
                .start();
    }


}
