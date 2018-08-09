package com.systemvv.grupo.asitenciaapp.asistencia.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.asistencia.dialog.dataSource.IncidenciaRepository;
import com.systemvv.grupo.asitenciaapp.asistencia.dialog.dataSource.remote.IncidenciaRemote;
import com.systemvv.grupo.asitenciaapp.asistencia.dialog.useCase.GuardarIncidencia;
import com.systemvv.grupo.asitenciaapp.asistencia.dialog.useCase.ObtenerAlumno;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Alumnos;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IncidenciaDialog extends DialogFragment implements IncidenciaView, AdapterView.OnItemSelectedListener {

    public static final String TAG = IncidenciaDialog.class.getSimpleName();

    @BindView(R.id.textView3)
    TextView textViewNombreAlumno;
    @BindView(R.id.textView4)
    TextView textViewApellidoeAlumno;
    @BindView(R.id.imgProfile)
    ImageView imgProfile;
    @BindView(R.id.spnIncidencias)
    Spinner spinnerIncidencias;
    @BindView(R.id.textViewPadece)
    TextView textViewPadecimiento;
    @BindView(R.id.editText4)
    EditText editTextIncidencia;
    private IncidenciaPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_fragment_justificacion, container, false);
        ButterKnife.bind(this, v);
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        initPresenter();
        return v;
    }

    private void initPresenter() {
        IncidenciaRepository repository = new IncidenciaRepository(new IncidenciaRemote(new FireStore()));
        presenter = new IncidenciaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                new GuardarIncidencia(repository),
                new ObtenerAlumno(repository));
        setPresenter(presenter);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onExtras(getArguments());
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getDialog() == null) {
            return;
        }
        int dialogWidth = getResources().getDimensionPixelSize(R.dimen.dialog_profile_width);
        int dialogHeight = getResources().getDimensionPixelSize(R.dimen.dialog_profile_height);
        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);
        presenter.onCreate();
        presenter.onStart();
    }


  /*  private void validarTipoPadencia(int tipoPadecimiento) {
        switch (tipoPadecimiento) {
            case 1:
                textViewPadecimiento.setText("Padece: Epilesia");
                break;
            case 2:
                textViewPadecimiento.setText("Padece: Alergia");
                break;
            default:
                textViewPadecimiento.setText("Padece: No Tiene");
                break;
        }
    }*/

    @OnClick({R.id.btnAceptar, R.id.btnSalir})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAceptar:
                String mensajeIncidencia = editTextIncidencia.getText().toString();
                presenter.onBtnAceptar(mensajeIncidencia);

                //dismiss();
                break;
            case R.id.btnSalir:
                dismiss();
                break;
        }
        // dismiss();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spnIncidencias:
                presenter.onSeleccionSpinnerIncidencia(spinnerIncidencias.getSelectedItem().toString());
                Log.d(TAG, "spinnerCede: " + spinnerIncidencias.getSelectedItemId() + " nombre : " + spinnerIncidencias.getSelectedItem());
                break;
            default:
                Toast.makeText(getActivity(), "NO HACER NADA ", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
        public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getActivity(), "NO onNothingSelected NADA ", Toast.LENGTH_SHORT).show();
       // return;
    }

    @Override
    public void setPresenter(IncidenciaPresenter presenter) {
        presenter.attachView(this);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initVistas(Alumnos alumnosUi) {
        textViewNombreAlumno.setText(alumnosUi.getNombre());
        textViewApellidoeAlumno.setText(alumnosUi.getApellido());
        // validarTipoPadencia(alumnosUi.getTipoPadecimiento());
        validarTipoPadencia(alumnosUi.getPadecimiento());
        Glide.with(getActivity()).load(alumnosUi.getFoto()).into(imgProfile);
        spinnerIncidencias.setOnItemSelectedListener(this);
    }

    private void validarTipoPadencia(String padecimiento) {
        if (padecimiento.length() > 0) {
            textViewPadecimiento.setText("Padece: " + padecimiento);
        } else {
            textViewPadecimiento.setText("Padece: No Tiene");
        }
    }
}
