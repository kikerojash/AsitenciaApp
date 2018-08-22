package com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal;


import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.dataSource.TareaGlobalRepository;
import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.dataSource.remote.TareaGlobalRemote;
import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.useCase.ObtenerListaAlumnos;
import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.useCase.RegistrarTareaGlobales;
import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.view.TareaGlobalView;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TareaGlobalFragment extends DialogFragment implements TareaGlobalView {

    public static final String TAG = TareaGlobalFragment.class.getSimpleName();

    TareaGlobalPresenter presenter;
    Unbinder unbinder;
    @BindView(R.id.editTextTarea)
    EditText editTextTarea;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_tarea_global, container, false);
        unbinder = ButterKnife.bind(this, v);
        initPresenter();
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onExtras(getArguments());
    }


    private void initPresenter() {

        TareaGlobalRepository tareaGlobalRepository = new TareaGlobalRepository(new TareaGlobalRemote(new FireStore()));
        presenter = new TareaGlobalPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                new ObtenerListaAlumnos(tareaGlobalRepository),
                new RegistrarTareaGlobales(tareaGlobalRepository));
        setPresenter(presenter);
       /* IncidenciaRepository repository = new IncidenciaRepository(new IncidenciaRemote(new FireStore()));
        presenter = new IncidenciaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                new GuardarIncidencia(repository),
                new ObtenerAlumno(repository));
        setPresenter(presenter);*/
    }


    @Override
    public void setPresenter(TareaGlobalPresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.button_cancelar, R.id.button_enviar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_cancelar:
                dismiss();
                break;
            case R.id.button_enviar:
                String tarea = editTextTarea.getText().toString();
                presenter.onClickEnviarTarea(tarea);
                break;
        }
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        dismiss();
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }
}
