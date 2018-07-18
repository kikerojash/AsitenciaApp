package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteAsistencia;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.base.fragment.BaseFragment;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteAsistencia.adapter.ReporteAsistenciaAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ReporteAsistenciaFragment extends BaseFragment<ReporteAsistenciaView, ReporteAsistenciaPresenter> implements ReporteAsistenciaView {

    public static final String TAG = ReporteAsistenciaFragment.class.getSimpleName();
    @BindView(R.id.reciclador)
    RecyclerView recyclerView;
    ReporteAsistenciaAdapter reporteAsistenciaAdapter;


    public ReporteAsistenciaFragment() {

    }

    public static ReporteAsistenciaFragment newInstance(Bundle args) {
        ReporteAsistenciaFragment fragment = new ReporteAsistenciaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected ReporteAsistenciaPresenter getPresenter() {
        return new ReporteAsistenciaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected ReporteAsistenciaView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_reporte_asistencia_padre, container, false);
        return view;
    }

    private void initVistas() {
        reporteAsistenciaAdapter = new ReporteAsistenciaAdapter(new ArrayList<Asistencia>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(reporteAsistenciaAdapter);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();
        initVistas();
    }

    @Override
    public void mostrarLista(List<Asistencia> asistenciaList) {
        Log.d(TAG, "mostrarLista : " + asistenciaList.size() + "");
        reporteAsistenciaAdapter.mostrarLista(asistenciaList);
    }
}
