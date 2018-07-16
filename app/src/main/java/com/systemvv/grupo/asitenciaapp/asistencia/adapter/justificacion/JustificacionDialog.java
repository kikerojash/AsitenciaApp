package com.systemvv.grupo.asitenciaapp.asistencia.adapter.justificacion;

import android.app.DialogFragment;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AlumnosUi;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JustificacionDialog extends DialogFragment {
    @BindView(R.id.textView3)
    TextView textViewNombreAlumno;
    @BindView(R.id.textView4)
    TextView textViewApellidoeAlumno;
    @BindView(R.id.imgProfile)
    ImageView imgProfile;

    public static final String TAG = JustificacionDialog.class.getSimpleName();
    private AlumnosUi alumnosUi;

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
        Glide.with(getActivity()).load(alumnosUi.getFoto()).into(imgProfile);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_fragment_justificacion, container, false);
        ButterKnife.bind(this, v);
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        return v;
    }
}
