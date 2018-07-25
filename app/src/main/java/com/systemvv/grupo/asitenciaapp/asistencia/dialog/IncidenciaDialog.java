package com.systemvv.grupo.asitenciaapp.asistencia.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AlumnosUi;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IncidenciaDialog extends DialogFragment implements AdapterView.OnItemSelectedListener {

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
    private AlumnosUi alumnosUi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_fragment_justificacion, container, false);
        ButterKnife.bind(this, v);
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        return v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        this.alumnosUi = Parcels.unwrap(bundle.getParcelable("alumnoUi"));
        Log.d(TAG, "alumnosUi : " + alumnosUi.getNombre());
    }

    @Override
    public void onStart() {
        super.onStart();
        initVistas();
        if (getDialog() == null) {
            return;
        }
        int dialogWidth = getResources().getDimensionPixelSize(R.dimen.dialog_profile_width);
        int dialogHeight = getResources().getDimensionPixelSize(R.dimen.dialog_profile_height);
        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);
    }

    private void initVistas() {
        textViewNombreAlumno.setText(alumnosUi.getNombre());
        textViewApellidoeAlumno.setText(alumnosUi.getApellido());
        validarTipoPadencia(alumnosUi.getTipoPadecimiento());
        Glide.with(getActivity()).load(alumnosUi.getFoto()).into(imgProfile);
        spinnerIncidencias.setOnItemSelectedListener(this);
    }

    private void validarTipoPadencia(int tipoPadecimiento) {
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
    }

    @OnClick({R.id.btnAceptar, R.id.btnSalir})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAceptar:
                dismiss();
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
               // presenter.onSeleccionSpinnerCede(spinnerIncidencias.getSelectedItem().toString());
                Log.d(TAG, "spinnerCede: " + spinnerIncidencias.getSelectedItemId() + " nombre : " + spinnerIncidencias.getSelectedItem());
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
