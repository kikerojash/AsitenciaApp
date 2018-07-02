package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.systemvv.grupo.asitenciaapp.MainActivity;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.cursos.CursoActivity;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.SeccionUi;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SeccionDialog extends DialogFragment {
    public static final String TAG = SeccionDialog.class.getSimpleName();

    @BindView(R.id.imageView2)
    ImageView imageView;
    @BindView(R.id.textViewNombre)
    TextView textViewNombre;
    @BindView(R.id.spnGrado)
    Spinner spinnerGrado;
    @BindView(R.id.spnSeccion)
    Spinner spinnerSeccion;
    InstitutoUi institutoUi;
   /*@BindView(R.id.btnAceptar)
    Button buttonAceptar;*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        this.institutoUi = Parcels.unwrap(bundle.getParcelable("example"));
        Log.d(TAG, "SeccionDialog : " + institutoUi.getNombre());
    }

    @Override
    public void onStart() {
        super.onStart();
        initVistas();
    }

    private void initVistas() {
        textViewNombre.setText(institutoUi.getNombre());
        Glide.with(this).load(institutoUi.getImage()).into(imageView);
        // spinnerGrado.setOnItemSelectedListener(this);
        // spinnerSeccion.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        List<Integer> spinnerArray = new ArrayList<>();
        List<String> spinnerArrayString = new ArrayList<>();
        for (SeccionUi seccionUi : institutoUi.getSeccionList()) {
            spinnerArray.add(seccionUi.getGrado());
        }
        for (SeccionUi seccionUi : institutoUi.getSeccionList()) {
            spinnerArrayString.add(seccionUi.getSeccion());
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                spinnerArray
        );
        spinnerGrado.setAdapter(adapter);

        ArrayAdapter<String> adapterString = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                spinnerArrayString
        );
        spinnerSeccion.setAdapter(adapterString);
        initSpinerValidacion();

    }

    private int gradoSelected = 0;
    private String seccionSelected = "";

    private void initSpinerValidacion() {
        spinnerSeccion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seccionSelected = institutoUi.getSeccionList().get(position).getSeccion();
                Log.d(TAG, "spnSeccion : " + institutoUi.getSeccionList().get(position).getSeccion());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "spnSeccion::onNothingSelected");
            }
        });
        spinnerGrado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gradoSelected = institutoUi.getSeccionList().get(position).getGrado();
                Log.d(TAG, "spnGrado: " + institutoUi.getSeccionList().get(position).getGrado());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "spnGrado::onNothingSelected");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_fragment_seccion, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @OnClick(R.id.btnAceptar)
    public void onClick(View view) {
        if (gradoSelected > 0 && seccionSelected.toString().length() > 0) {
            initActivityAsistencia();
            dismiss();
        }
    }

    private void initActivityAsistencia() {
        Intent intent = new Intent(getActivity(), CursoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("instituto",Parcels.wrap(institutoUi));
        intent.putExtra("gradoSelected", gradoSelected);
        intent.putExtra("seccionSelected", seccionSelected);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
