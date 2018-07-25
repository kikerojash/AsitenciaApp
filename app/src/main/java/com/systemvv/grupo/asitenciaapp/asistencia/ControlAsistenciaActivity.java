package com.systemvv.grupo.asitenciaapp.asistencia;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.listener.ITableViewListener;
import com.google.firebase.auth.FirebaseAuth;
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
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.filas.FilasAsistenciaAlumnosHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.justificacion.JustificacionDialog;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.justificacion.listener.JustificacionListener;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivity;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AlumnosUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.MotivosAsistenciaUi;
import com.systemvv.grupo.asitenciaapp.login.LoginActivity;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.SeccionDialog;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ControlAsistenciaActivity extends BaseActivity<ControlAsistenciaView, ControlAsistenciaPresenter>
        implements ControlAsistenciaView, ITableViewListener, JustificacionListener {

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
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.profile_image)
    CircleImageView circleImageView;
    private AsistenciaAdapter adapter;

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
        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        ButterKnife.bind(this);
        setupToolbar();
        initAdapter();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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
        //initAdapter();
    }

    @Override
    public void mostrarListaTablas(List<ColumnaCabeceraAsistencia> columnHeaderList, List<FilaCabeceraAsistencia> rowHeaderList, List<List<CeldasAsistencia>> cellsList) {
        adapter.setAllItems(columnHeaderList, rowHeaderList, cellsList);
    }

    @Override
    public void actualizarDatosCambiadosTabla() {
        adapter.notifyDataSetChanged();
    }

    private void initAdapter() {
        adapter = new AsistenciaAdapter(this);
        table.setAdapter(adapter);
        table.setIgnoreSelectionColors(false);
        table.setHasFixedWidth(false);
        table.setIgnoreSelectionColors(true);
        table.setTableViewListener(this);

    }

    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder holder, int column, int row) {
        AsistenciaUi asistenciaUi = (AsistenciaUi) table.getAdapter().getCellItem(column, row);

        List<CeldasAsistencia> celdasList = adapter.getCellRowItems(row);
        presenter.onClickCeldas(holder, asistenciaUi, celdasList);


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
        /* Do What you want
        if (clickColumn == true) {
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
                bundle.putInt("column",column);
                bundle.putInt("row",row);
                bundle.putParcelable("alumnoUi", Parcels.wrap(alumnosUi));
                bundle.putParcelable("asistenciaUi", Parcels.wrap(asistenciaUi));
                dialogFragment.setArguments(bundle);
                dialogFragment.show(ft, "dialog");
            }
        }*/
    }
    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder holder, int p_nXPosition) {


        //List<CeldasAsistencia> celdasColumnaList = adapter.getCellColumnItems(p_nXPosition);

        List<CeldasAsistencia> celdasColumnaList = adapter.getCellColumnItems(p_nXPosition);
        presenter.onClickColumnaCabecera(holder, celdasColumnaList);

    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder p_jColumnHeaderView, int p_nXPosition) {

    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder holder, int row) {
        if (holder instanceof FilasAsistenciaAlumnosHolder) {
            FilasAsistenciaAlumnosHolder filasAsistenciaAlumnosHolder = (FilasAsistenciaAlumnosHolder) holder;
            AlumnosUi alumnosUi = (AlumnosUi) table.getAdapter().getRowHeaderItem(row);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);
            JustificacionDialog dialogFragment = new JustificacionDialog();
            Bundle bundle = new Bundle();

            bundle.putInt("row", row);
            bundle.putParcelable("alumnoUi", Parcels.wrap(alumnosUi));
            dialogFragment.setArguments(bundle);
            dialogFragment.show(ft, "dialog");
        }
    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder p_jRowHeaderView, int p_nYPosition) {

    }

    @Override
    public void onClickAceptar(int rowPosition) {
        AlumnosUi alumnosUi = (AlumnosUi) table.getAdapter().getRowHeaderItem(rowPosition);
        Log.d(TAG, "alumnosUi : " + alumnosUi.getNombre());
        /*AsistenciaUi asistenciaUi2 = (AsistenciaUi) table.getAdapter().getCellItem(columnPosition, rowPosition);
        asistenciaUi2.setTipasistencia(AsistenciaUi.TipoAsistencia.ASISTENCIA_TARDE_JUSTIFICADA);
        List<CeldasAsistencia> celdasList = adapter.getCellRowItems(rowPosition);
        pintandoCeldas(asistenciaUi2, celdasList);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_asistencia_docente, menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_guardar_entrada:
                Toast.makeText(getApplicationContext(), "Registro Guardados de Entrada", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "menu_guardar_entrada:");
                presenter.onGuardarEntrada();
                break;
            case R.id.menu_guardar_salida:
                Toast.makeText(getApplicationContext(), "Registro Guardados de Salida", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "menu_guardar_salida:");
                break;
            default:
                Log.d(TAG, "default:");
                onBackPressed();
                break;
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
    }
}
