package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.cursos.CursoActivity;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.adapter.SeccionAdapter;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.dataSource.SeccionRepository;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.dataSource.remote.SeccionRemote;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad.SeccionUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.listener.ItemListener;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.useCase.ObtenerListaSeccion;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;


import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SeccionDialog extends DialogFragment implements SeccionView,ItemListener{
    public static final String TAG = SeccionDialog.class.getSimpleName();

    @BindView(R.id.imageView2)
    ImageView imageView;
    @BindView(R.id.textViewNombre)
    TextView textViewNombre;
    /*@BindView(R.id.spnGrado)
    Spinner spinnerGrado;
    @BindView(R.id.spnSeccion)
    Spinner spinnerSeccion;*/
    //InstitutoUi institutoUi;
   /*@BindView(R.id.btnAceptar)
    Button buttonAceptar;*/

    @BindView(R.id.reciclador)
    RecyclerView reciclador;

    private SeccionAdapter seccionAdapter;
    private SeccionPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* Bundle bundle = getArguments();
        this.institutoUi = Parcels.unwrap(bundle.getParcelable("example"));
        Log.d(TAG, "SeccionDialog : " + institutoUi.getNombre());*/
    }

    @Override
    public void onStart() {
        super.onStart();
        //initVistas();
        presenter.onStart();
    }

    private void initVistas() {

       /* textViewNombre.setText(institutoUi.getNombre());
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
        //spinnerGrado.setAdapter(adapter);

        ArrayAdapter<String> adapterString = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                spinnerArrayString
        );
        //spinnerSeccion.setAdapter(adapterString);
        initSpinerValidacion();*/

    }

    private int gradoSelected = 0;
    private String seccionSelected = "";

    private void initSpinerValidacion() {
        /*spinnerSeccion.setOnItemSelectedListener(this);
        spinnerGrado.setOnItemSelectedListener(this);*/
      /*  spinnerSeccion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        });*/
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_fragment_seccion, container, false);
        ButterKnife.bind(this, v);
        initPresenter();
       // initVistas();
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onExtras(getArguments());
    }

    private void initPresenter() {
        SeccionRepository repository = new SeccionRepository(new SeccionRemote(new FireStore()));
        presenter = new SeccionPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                new ObtenerListaSeccion(repository));
        setPresenter(presenter);
    }


    private void initActivityAsistencia() {
        Intent intent = new Intent(getActivity(), CursoActivity.class);
        Bundle bundle = new Bundle();
        ///bundle.putParcelable("instituto", Parcels.wrap(institutoUi));
        intent.putExtra("gradoSelected", gradoSelected);
        intent.putExtra("seccionSelected", seccionSelected);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void setPresenter(SeccionPresenter presenter) {
        presenter.attachView(this);
    }


    @Override
    public void onClickItem(SeccionUi seccionUi) {

        Log.d(TAG,"onClickItem");
        Intent intent = new Intent(getActivity(), CursoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("seccionUi", Parcels.wrap(seccionUi));
        intent.putExtras(bundle);
        startActivity(intent);
        dismiss();
    }

    @Override
    public void initVistas(InstitutoUi institutoUi) {
        textViewNombre.setText(institutoUi.getNombre());
        Glide.with(this).load(institutoUi.getImage()).into(imageView);
        seccionAdapter = new SeccionAdapter(new ArrayList<SeccionUi>(),this);
        reciclador.setLayoutManager(new LinearLayoutManager(getActivity()));
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(seccionAdapter);
    }

    @Override
    public void mostrarLista(List<SeccionUi> seccionUiList) {
        seccionAdapter.mostrarListaSeccion(seccionUiList);
    }
}
