package com.systemvv.grupo.asitenciaapp.asistencia;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.listener.ITableViewListener;
import com.google.firebase.auth.FirebaseAuth;
import com.systemvv.grupo.asitenciaapp.R;

import com.systemvv.grupo.asitenciaapp.asistencia.adapter.AsistenciaAdapter;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.CeldasAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.ColumnaCabeceraAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.FilaCabeceraAsistencia;

import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.filas.FilasAsistenciaAlumnosHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaRepository;
import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.remote.ControlAsistenciaRemote;
import com.systemvv.grupo.asitenciaapp.asistencia.dialog.IncidenciaDialog;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Alumnos;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.useCase.GuardarAsistenciaLista;
import com.systemvv.grupo.asitenciaapp.asistencia.useCase.GuardarAsistenciaListaHoraFin;
import com.systemvv.grupo.asitenciaapp.asistencia.useCase.ObtenerListaAlumnos;
import com.systemvv.grupo.asitenciaapp.asistencia.useCase.ValidarFechaRegistroAsistencia;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivity;
import com.systemvv.grupo.asitenciaapp.conexion.FireAuthConexion;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;
import com.systemvv.grupo.asitenciaapp.login.LoginActivity;
import com.systemvv.grupo.asitenciaapp.utils.MyExcepcionHandler;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ControlAsistenciaActivity extends BaseActivity<ControlAsistenciaView, ControlAsistenciaPresenter>
        implements ControlAsistenciaView, ITableViewListener {

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
    @BindView(R.id.textViewInformacion)
    TextView textViewInformacion;
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
        ControlAsistenciaRepository repository = new ControlAsistenciaRepository(new ControlAsistenciaRemote(new FireStore()));
        return new ControlAsistenciaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new GuardarAsistenciaLista(repository),
                new ValidarFechaRegistroAsistencia(repository),
                new GuardarAsistenciaListaHoraFin(repository),
                new ObtenerListaAlumnos(repository));
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
        //firebaseAuth = FireAuthConexion.getInstance();
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
    }

    @Override
    public void mostrarListaTablas(List<ColumnaCabeceraAsistencia> columnHeaderList, List<FilaCabeceraAsistencia> rowHeaderList, List<List<CeldasAsistencia>> cellsList) {
        adapter.setAllItems(columnHeaderList, rowHeaderList, cellsList);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void actualizarDatosCambiadosTabla() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        toast(mensaje);
    }

    private void snackBar(String mensaje) {
        Snackbar.make(progressBar, mensaje, Snackbar.LENGTH_SHORT).show();
    }

    private void toast(String mensaje) {
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
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
       /* AsistenciaUi asistenciaUi = (AsistenciaUi) table.getAdapter().getCellItem(column, row);
        List<CeldasAsistencia> celdasList = adapter.getCellRowItems(row);
        presenter.onClickCeldas(holder, asistenciaUi, celdasList);*/

        Asistencia asistenciaUi = (Asistencia) table.getAdapter().getCellItem(column, row);
        List<CeldasAsistencia> celdasList = adapter.getCellRowItems(row);
        presenter.onClickCeldas(holder, asistenciaUi, celdasList);
    }


    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder holder, int column, int row) {

    }

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder holder, int p_nXPosition) {
        List<CeldasAsistencia> celdasColumnaList = adapter.getCellColumnItems(p_nXPosition);
        presenter.onClickColumnaCabecera(holder, celdasColumnaList);

    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder p_jColumnHeaderView, int p_nXPosition) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder holder, int row) {
        if (holder instanceof FilasAsistenciaAlumnosHolder) {
            FilasAsistenciaAlumnosHolder filasAsistenciaAlumnosHolder = (FilasAsistenciaAlumnosHolder) holder;
            Alumnos alumnosUi = (Alumnos) table.getAdapter().getRowHeaderItem(row);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);
            IncidenciaDialog dialogFragment = new IncidenciaDialog();
            Bundle bundle = new Bundle();
            //  bundle.putParcelable("alumnoUi", Parcels.wrap(alumnosUi));
            bundle.putString("keyAlumno", alumnosUi.getKeyAlumno());
            bundle.putString("keyGrado", alumnosUi.getKeyGrado());
            bundle.putString("keyInstitucion", alumnosUi.getKeyInstitucion());
            bundle.putString("keyPeriodo", alumnosUi.getKeyPeriodo());
            bundle.putString("keySeccion", alumnosUi.getKeySeccion());
            bundle.putString("keyCurso", alumnosUi.getKeyCurso());

            dialogFragment.setArguments(bundle);
            dialogFragment.show(ft, "dialog");
        }
    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder p_jRowHeaderView, int p_nYPosition) {
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_asistencia_docente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_guardar_entrada:
                //Toast.makeText(getApplicationContext(), "Registro Guardados de Entrada", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "menu_guardar_entrada:");
                presenter.onGuardarEntrada();
                break;
            case R.id.menu_guardar_salida:
                presenter.onGuardarSalida();
                //Toast.makeText(getApplicationContext(), "Registro Guardados de Salida", Toast.LENGTH_SHORT).show();
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
        Thread.setDefaultUncaughtExceptionHandler(new MyExcepcionHandler(this));
        if (getIntent().getBooleanExtra("crash", false)) {
            throw new NullPointerException();
        }
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }


    @Override
    public void mostrarInformacionSnackBar(String mensaje, final int tipoRegistroResultado) {
        Snackbar.make(progressBar, mensaje, Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(getResources().getColor(R.color.md_red_400))
                .setAction("Aceptar", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.onClickAccionBar(tipoRegistroResultado);
                        Log.i("Snackbar", "Pulsada acción snackbar!");

                    }
                })
                .show();
    }
}
