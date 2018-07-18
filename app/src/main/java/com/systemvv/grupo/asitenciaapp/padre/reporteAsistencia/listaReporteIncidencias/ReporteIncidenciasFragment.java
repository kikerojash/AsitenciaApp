package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.base.fragment.BaseFragment;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Incidencias;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.adapter.ReporteIncidenciasAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ReporteIncidenciasFragment extends BaseFragment<ReporteIncidenciasView, ReporteIncidenciasPresenter> implements ReporteIncidenciasView {

    public static final String TAG = ReporteIncidenciasFragment.class.getSimpleName();
    @BindView(R.id.reciclador)
    RecyclerView recyclerView;
    ReporteIncidenciasAdapter reporteIncidenciasAdapter;

    public ReporteIncidenciasFragment() {

    }

    public static ReporteIncidenciasFragment newInstance(Bundle args) {
        ReporteIncidenciasFragment fragment = new ReporteIncidenciasFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected ReporteIncidenciasPresenter getPresenter() {
        return new ReporteIncidenciasPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected ReporteIncidenciasView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_reporte_incidencias_padre, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initVistas();
    }

    private void initVistas() {
        reporteIncidenciasAdapter = new ReporteIncidenciasAdapter(new ArrayList<Incidencias>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(reporteIncidenciasAdapter);
    }


    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarLista(List<Incidencias> incidenciasList) {
        reporteIncidenciasAdapter.mostrarLista(incidenciasList);
    }
}
