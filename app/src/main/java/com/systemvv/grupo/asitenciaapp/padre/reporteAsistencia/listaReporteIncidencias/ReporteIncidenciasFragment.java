package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.systemvv.grupo.asitenciaapp.padre.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Incidencias;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.adapter.ReporteIncidenciasAdapter;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.dataSource.ReporteIncidenciaRepository;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.dataSource.remote.ReporteIncidenciaRemote;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.listener.IncidenciasListener;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.useCase.EliminarIncidencia;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.useCase.ObtenerIncidenciaLista;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ReporteIncidenciasFragment extends BaseFragment<ReporteIncidenciasView, ReporteIncidenciasPresenter>
        implements ReporteIncidenciasView, IncidenciasListener {

    public static final String TAG = ReporteIncidenciasFragment.class.getSimpleName();
    @BindView(R.id.reciclador)
    RecyclerView recyclerView;
    @BindView(R.id.mensajeVacio)
    TextView textViewVacio;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
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
        ReporteIncidenciaRepository reporteIncidenciaRepository = new ReporteIncidenciaRepository(new ReporteIncidenciaRemote(new FireStore()));
        return new ReporteIncidenciasPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ObtenerIncidenciaLista(reporteIncidenciaRepository),
                new EliminarIncidencia(reporteIncidenciaRepository));
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
        reporteIncidenciasAdapter = new ReporteIncidenciasAdapter(new ArrayList<Incidencias>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(reporteIncidenciasAdapter);
    }


    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarLista(List<Incidencias> incidenciasList) {
        if (incidenciasList == null || incidenciasList.isEmpty()) {
            textViewVacio.setVisibility(View.VISIBLE);
        } else {
            reporteIncidenciasAdapter.mostrarLista(incidenciasList);
            textViewVacio.setVisibility(View.GONE);
        }
    }

    @Override
    public void eliminarItem(Incidencias incidencias) {
        reporteIncidenciasAdapter.eliminarItem(incidencias);
    }

    @Override
    public void onLongClickListener(final Incidencias incidencias) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Eliminar");
        builder.setMessage("Esta seguro que desea quitar ?");
        //  builder.setIcon(R.drawable.ic_launcher);
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                presenter.onEliminarClick(incidencias);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
