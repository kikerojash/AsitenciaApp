package com.systemvv.grupo.asitenciaapp.asistencia.adapter.justificacion;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.justificacion.listener.JustificacionListener;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AlumnosUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JustificacionDialog extends DialogFragment {
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

    public static final String TAG = JustificacionDialog.class.getSimpleName();
    private AlumnosUi alumnosUi;
    private AsistenciaUi asistenciaUi;
    JustificacionListener listener;
    int columna = 0, fila = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (JustificacionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement JustificacionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        //this.columna = bundle.getInt("column", 0);
        this.fila = bundle.getInt("row", 0);
        this.alumnosUi = Parcels.unwrap(bundle.getParcelable("alumnoUi"));
        // this.asistenciaUi = Parcels.unwrap(bundle.getParcelable("asistenciaUi"));
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
    }

    private void validarTipoPadencia(int tipoPadecimiento) {
        switch (tipoPadecimiento) {
            case 1:
                textViewPadecimiento.setText("Padecimiento: Epilesia");
                break;
            case 2:
                textViewPadecimiento.setText("Padecimiento: Alergia");
                break;
            default:
                textViewPadecimiento.setText("Padecimiento: No Padece");
                break;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_fragment_justificacion, container, false);
        ButterKnife.bind(this, v);
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        return v;
    }

    @OnClick(R.id.btnAceptar)
    public void onClick() {
        if (fila == -1) return;
        //listener.onClickAceptar(fila);
        dismiss();
    }

}
