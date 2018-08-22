package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivity;
import com.systemvv.grupo.asitenciaapp.login.LoginActivity;
import com.systemvv.grupo.asitenciaapp.padre.cursoHijos.CursoHijosActivity;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteAsistencia.ReporteAsistenciaFragment;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.ReporteIncidenciasFragment;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas.ReporteTareasFragment;
import com.systemvv.grupo.asitenciaapp.utils.MyFragmentAdapter;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ReportesActivity extends BaseActivity<ReportesView, ReportesPresenter> implements ReportesView {

    public static final String TAG = ReportesActivity.class.getSimpleName();
    @BindView(R.id.viewPager)
    ViewPager viewPager;//tab_reportes
    @BindView(R.id.tab_reportes)
    TabLayout tabLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.backdrop)
    ImageView imageViewFondo;
    @BindView(R.id.txtNombreCurso)
    TextView textViewNombreInstituo;
    @BindView(R.id.textTitulo)
    TextView textViewNombreCurso;
    @BindView(R.id.txtGrado)
    TextView textViewGradoSeccion;
    @BindView(R.id.txNombreProfe)
    TextView textViewNombreProfesor;
    @BindView(R.id.txNombreHijo)
    TextView textViewNombreHijo;
    @BindView(R.id.profile_image)
    CircleImageView circleImageView;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
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
    protected ReportesPresenter getPresenter() {
        return new ReportesPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected ReportesView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_reporte_asistencia);
        ButterKnife.bind(this);
        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        setupToolbar();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    private void initViewAdapter(Cursos cursos) {
        Bundle args = new Bundle();
        /*args.putInt("idCargaCurso", idCargaCurso);
        args.putInt("cursoId", idCurso);*/
        args.putAll(getIntent().getExtras());
        args.putParcelable("cursosUi", Parcels.wrap(cursos));
        MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(ReporteAsistenciaFragment.newInstance(args), "ASISTENCIA");
        fragmentAdapter.addFragment(ReporteIncidenciasFragment.newInstance(args), "INCIDENCIAS");
        fragmentAdapter.addFragment(ReporteTareasFragment.newInstance(args), "ACTIVIDADES");
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //if (presenter != null)
                ///   presenter.onPageChanged(fragmentAdapter.getItem(position) != null ? fragmentAdapter.getItem(position).getClass() : null);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void mostrarDatos(Cursos cursos) {
        collapsingToolbarLayout.setTitle("");
        textViewNombreInstituo.setText(cursos.getHijos().getNombreInstituto());
        textViewNombreCurso.setText("Curso: " + cursos.getNombreCurso());
        textViewGradoSeccion.setText("Grado: " + cursos.getHijos().getGrado() + " Sección: " + cursos.getHijos().getSeccion());
        textViewNombreProfesor.setText("Profesor: " + cursos.getNombreProfesor());
        textViewNombreHijo.setText(cursos.getHijos().getNombre());
        Glide.with(this).load(cursos.getFotoCurso()).into(imageViewFondo);
        Glide.with(this).load(cursos.getHijos().getFoto()).into(circleImageView);
    }

    @Override
    public void initAdapter(Cursos cursos) {
        initViewAdapter(cursos);
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
