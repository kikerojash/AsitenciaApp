package com.systemvv.grupo.asitenciaapp.asistencia;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.listener.ITableViewListener;
import com.systemvv.grupo.asitenciaapp.R;

import com.systemvv.grupo.asitenciaapp.asistencia.adapter.AdapterTest;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.AsistenciaAdapter;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.CeldasAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.ColumnaCabeceraAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.FilaCabeceraAsistencia;

import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.celdas.CeldasAsistenciaAlumnoFaltoHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.celdas.CeldasAsistenciaAlumnoJustificadoHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.celdas.CeldasAsistenciaAlumnoPuntualHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.celdas.CeldasAsistenciaAlumnoTardeHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.columnas.ColumnaTipoPresenteHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.justificacion.JustificacionDialog;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivity;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AlumnosUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.SeccionDialog;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ControlAsistenciaActivity extends BaseActivity<ControlAsistenciaView, ControlAsistenciaPresenter> implements ControlAsistenciaView, ITableViewListener {

    public static final String TAG = ControlAsistenciaActivity.class.getSimpleName();


    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.txtNombreCurso)
    TextView textViewNombreInstituto;
    @BindView(R.id.textTitulo)
    TextView textViewNombreCuro;
    @BindView(R.id.txtGrado)
    TextView textViewGradoSeccion;
    @BindView(R.id.txNombreProfe)
    TextView textViewNombreProfe;
    @BindView(R.id.backdrop)
    ImageView imageViewFondo;
    @BindView(R.id.content_container)
    TableView table;
    /*@BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;*/
    @BindView(R.id.profile_image)
    CircleImageView circleImageView;
    private AsistenciaAdapter adapter;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected ControlAsistenciaPresenter getPresenter() {
        return new ControlAsistenciaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected ControlAsistenciaView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_asistencia);
        ButterKnife.bind(this);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarInformacionBasica(CursoUi cursoUi) {
        String imageDocente = "https://get.pxhere.com/photo/blackboard-university-speech-lecturer-lecture-teacher-teaching-physics-professor-orator-birger-kollmeier-public-speaking-698271.jpg";
        Glide.with(this).load(imageDocente).into(circleImageView);
        textViewNombreInstituto.setText(cursoUi.getInstitutoUi().getNombre());
        textViewNombreCuro.setText(cursoUi.getNombre());
        textViewGradoSeccion.setText("Grado : " + cursoUi.getGradoSelected() + " Sección : " + cursoUi.getSeccionSelected());
        Glide.with(this).load(cursoUi.getFoto()).into(imageViewFondo);
        initAdapter();
    }

    @Override
    public void mostrarListaTablas(List<ColumnaCabeceraAsistencia> columnHeaderList, List<FilaCabeceraAsistencia> rowHeaderList, List<List<CeldasAsistencia>> cellsList) {
        adapter.setAllItems(columnHeaderList, rowHeaderList, cellsList);
    }
    //adapter.setAllItems(rowHeaderList, columnHeaderList, cellsList);
    //adapter.setAllItems(rowHeaderList, columnHeaderList, getListaCeldas());

    private void initAdapter() {
        adapter = new AsistenciaAdapter(this);
        table.setAdapter(adapter);
        table.setIgnoreSelectionColors(false);
        table.setHasFixedWidth(false);
        table.setIgnoreSelectionColors(true);
        presenter.onListAdapter();
        table.setTableViewListener(this);

    }

    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder holder, int column, int row) {
       /* if (clickColumn == true) {
            if (holder instanceof CeldasAsistenciaAlumnoPuntualHolder ||
                    holder instanceof CeldasAsistenciaAlumnoTardeHolder ||
                    holder instanceof CeldasAsistenciaAlumnoJustificadoHolder ||
                    holder instanceof CeldasAsistenciaAlumnoFaltoHolder) {

                List<CeldasAsistencia> celdasList = adapter.getCellRowItems(row);
                AsistenciaUi asistenciaUi = (AsistenciaUi) table.getAdapter().getCellItem(column, row);
                for (int i = 0; i < celdasList.size(); i++) {
                    AsistenciaUi asistencia = (AsistenciaUi) celdasList.get(i);
                    if (asistencia.isPintar()) {
                        remplazarItem(asistencia, asistenciaUi);
                        return;
                    }
                }
                asistenciaUi.setPintar(true);
            }
            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(getApplicationContext(),"Seleccione todos los items primero ",Toast.LENGTH_SHORT).show();
        }*/


        if (clickColumn == true) {
            List<CeldasAsistencia> celdasList = adapter.getCellRowItems(row);
            AsistenciaUi asistenciaUi = (AsistenciaUi) table.getAdapter().getCellItem(column, row);
            if (holder instanceof CeldasAsistenciaAlumnoPuntualHolder) {
                pintandoCeldas(asistenciaUi, celdasList);
                AlumnosUi alumnosUi = asistenciaUi.getAlumnosUi();
                Log.d(TAG,"alumnosUiPuntual : "+ alumnosUi.getNombre());
            } else if (holder instanceof CeldasAsistenciaAlumnoTardeHolder) {
                AlumnosUi alumnosUi = asistenciaUi.getAlumnosUi();
                Log.d(TAG,"alumnosUiTarde : "+ alumnosUi.getNombre());
                pintandoCeldas(asistenciaUi, celdasList);
            } else if (holder instanceof CeldasAsistenciaAlumnoJustificadoHolder) {
                AlumnosUi alumnosUi = asistenciaUi.getAlumnosUi();
                Log.d(TAG,"alumnosUiTardeJustificada : "+ alumnosUi.getTipoAsistencia());
                pintandoCeldas(asistenciaUi, celdasList);
            } else if (holder instanceof CeldasAsistenciaAlumnoFaltoHolder) {
                AlumnosUi alumnosUi = asistenciaUi.getAlumnosUi();
                Log.d(TAG,"alumnosUiFalto : "+ alumnosUi.getTipoAsistencia());
                pintandoCeldas(asistenciaUi, celdasList);
            }
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(), "Seleccione todos los items primero ", Toast.LENGTH_SHORT).show();
        }

    }

    private void pintandoCeldas(AsistenciaUi asistenciaUi, List<CeldasAsistencia> celdasList) {
        for (int i = 0; i < celdasList.size(); i++) {
            AsistenciaUi asistencia = (AsistenciaUi) celdasList.get(i);
            if (asistencia.isPintar()) {
                remplazarItem(asistencia, asistenciaUi);
                return;
            }
        }
        asistenciaUi.setPintar(true);
    }

    private void remplazarItem(AsistenciaUi asistenciaAnterior, AsistenciaUi asistenciaNueva) {
        asistenciaAnterior.setPintar(false);
        asistenciaNueva.setPintar(true);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder holder, int column, int row) {
        // Do What you want
        if (holder instanceof CeldasAsistenciaAlumnoTardeHolder) {
            CeldasAsistenciaAlumnoTardeHolder celdasAsistenciaHolder = (CeldasAsistenciaAlumnoTardeHolder) holder;
            AsistenciaUi asistenciaUi = (AsistenciaUi) table.getAdapter().getCellItem(column, row);
            AlumnosUi alumnosUi = asistenciaUi.getAlumnosUi();

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);
            JustificacionDialog dialogFragment = new JustificacionDialog();
            Bundle bundle = new Bundle();
            bundle.putParcelable("alumnoUi", Parcels.wrap(alumnosUi));
            dialogFragment.setArguments(bundle);
            dialogFragment.show(ft, "dialog");

        }
    }


    boolean clickColumn = false;

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder holder, int p_nXPosition) {
        if (clickColumn == false) {
            if (holder instanceof ColumnaTipoPresenteHolder) {
                clickColumn = true;
                List<CeldasAsistencia> celdasColumnaList = adapter.getCellColumnItems(p_nXPosition);
                Log.d(TAG, "celdasList : " + celdasColumnaList.size());
                for (CeldasAsistencia celdasAsistencia : celdasColumnaList) {
                    AsistenciaUi asistencia = (AsistenciaUi) celdasAsistencia;
                    asistencia.setPintar(true);
                }

            }
            adapter.notifyDataSetChanged();
        } else {
            Log.d(TAG, "no hacer nada");
        }

    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder p_jColumnHeaderView, int p_nXPosition) {

    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder p_jRowHeaderView, int p_nYPosition) {

    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder p_jRowHeaderView, int p_nYPosition) {

    }
}
