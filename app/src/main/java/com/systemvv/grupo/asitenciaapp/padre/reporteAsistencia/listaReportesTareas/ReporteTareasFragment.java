package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.base.fragment.BaseFragment;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Incidencias;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Tareas;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.adapter.ReporteIncidenciasAdapter;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas.adapter.ReporteTareasAdapter;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas.dataSource.ReporteTareasRepository;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas.dataSource.remote.ReporteTareasRemote;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas.useCase.ObtenerTareasLista;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ReporteTareasFragment extends BaseFragment<ReporteTareasView, ReporteTareasPresenter> implements ReporteTareasView {


    public static final String TAG = ReporteTareasFragment.class.getSimpleName();

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.reciclador)
    RecyclerView recyclerView;
    @BindView(R.id.mensajeVacio)
    TextView textViewVacio;
    ReporteTareasAdapter reporteTareasAdapter;

    public static ReporteTareasFragment newInstance(Bundle args) {
        ReporteTareasFragment fragment = new ReporteTareasFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected ReporteTareasPresenter getPresenter() {
        ReporteTareasRepository reporteTareasRepository = new ReporteTareasRepository(new ReporteTareasRemote(new FireStore()));
        return new ReporteTareasPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ObtenerTareasLista(reporteTareasRepository));
    }

    @Override
    protected ReporteTareasView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_reporte_actividades_padre, container, false);
        return view;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarListaCursos(List<Tareas> tareasList) {
        textViewVacio.setVisibility(View.GONE);
        reporteTareasAdapter.mostrarLista(tareasList);
    }

    @Override
    public void fondoVacio() {
        textViewVacio.setVisibility(View.VISIBLE);
        textViewVacio.setText("No hay tareas Archivadas!!");
    }


    @Override
    public void onStart() {
        super.onStart();
        initVistas();
    }

    private void initVistas() {
        reporteTareasAdapter = new ReporteTareasAdapter(new ArrayList<Tareas>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(reporteTareasAdapter);
    }

}
