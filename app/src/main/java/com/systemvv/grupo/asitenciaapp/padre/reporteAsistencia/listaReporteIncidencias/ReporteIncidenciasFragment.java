package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.base.fragment.BaseFragment;

public class ReporteIncidenciasFragment extends BaseFragment<ReporteIncidenciasView, ReporteIncidenciasPresenter> implements ReporteIncidenciasView {

    public static final String TAG = ReporteIncidenciasFragment.class.getSimpleName();

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
        return new ReporteIncidenciasPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),getResources());
    }

    @Override
    protected ReporteIncidenciasView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_reporte_incidencias_padre,container,false);
        return view;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }
}
