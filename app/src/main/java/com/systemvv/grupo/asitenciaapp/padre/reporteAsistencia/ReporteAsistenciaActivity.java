package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivity;

public class ReporteAsistenciaActivity extends BaseActivity<ReporteAsistenciaView, ReporteAsistenciaPresenter> implements ReporteAsistenciaView {

    public static final String TAG = ReporteAsistenciaActivity.class.getSimpleName();

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected ReporteAsistenciaPresenter getPresenter() {
        return new ReporteAsistenciaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),getResources());
    }

    @Override
    protected ReporteAsistenciaView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_reporte_asistencia);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }
}
