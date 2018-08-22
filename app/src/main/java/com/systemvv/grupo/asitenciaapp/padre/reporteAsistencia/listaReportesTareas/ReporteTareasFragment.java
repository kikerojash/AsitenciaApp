package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.base.fragment.BaseFragment;


public class ReporteTareasFragment extends BaseFragment<ReporteTareasView, ReporteTareasPresenter> implements ReporteTareasView {


    public static final String TAG = ReporteTareasFragment.class.getSimpleName();

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
        return new ReporteTareasPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),getResources());
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
        return null;
    }
}
