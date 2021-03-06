package com.systemvv.grupo.asitenciaapp.base.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.systemvv.grupo.asitenciaapp.base.BaseView;


import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends BaseView<P>,P extends BaseFragmentPresenter<V>>extends Fragment implements BaseView<P> {


    protected abstract String getLogTag();

    protected abstract P getPresenter();

    protected abstract V getBaseView();

    protected P presenter;

    protected abstract View inflateView(LayoutInflater inflater, ViewGroup container);

    protected abstract ProgressBar getProgressBar();

    public BaseFragment() {
    }

    private ProgressBar progressBar;

    Unbinder unbinder;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(getLogTag(), "onCreate");
        super.onCreate(savedInstanceState);
        setupPresenter();
        if (presenter != null) presenter.onCreate();
        if (getArguments() != null) {
            /*mIdCargaCurso = getArguments().getInt(ARG_ID_CARGA_CURSO);
            mIdCurso = getArguments().getInt(ARG_ID_CURSO);
            Log.d(TAG, "mIdCargaCurso: " + mIdCargaCurso);
            Log.d(TAG, "mIdCurso: " + mIdCurso);*/
        }
    }

    private void setupPresenter() {
        if (presenter == null) {
            presenter = getPresenter();
        }
        setPresenter(presenter);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(getLogTag(), "onCreateView");
        if (presenter != null) presenter.onCreateView();
        return inflateView(inflater, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(getLogTag(), "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        setupProgressBar();
        setRetainInstance(true);
        if (presenter != null) presenter.onViewCreated();
        if (presenter != null) presenter.setExtras(getArguments());
    }


    private void setupProgressBar() {
        progressBar = getProgressBar();
    }

    @Override
    public void onStart() {
        Log.d(getLogTag(), "onResume");
        super.onStart();
        if (presenter != null) presenter.onStart();
    }

    @Override
    public void onResume() {
        Log.d(getLogTag(), "onResume");
        super.onResume();
        if (presenter != null) presenter.onResume();
    }

    @Override
    public void onPause() {
        Log.d(getLogTag(), "onPause");
        super.onPause();
        if (presenter != null) presenter.onPause();
    }

    @Override
    public void onStop() {
        Log.d(getLogTag(), "onStop");
        super.onStop();
        if (presenter != null) presenter.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d(getLogTag(), "onDestroyView");
        super.onDestroyView();
        unbinder.unbind();
        if (presenter != null) presenter.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(getLogTag(), "onDestroy");
        super.onDestroy();
        if (presenter != null) presenter.onDestroy();
    }


    @Override
    public void onDetach() {
        Log.d(getLogTag(), "onDetach");
        super.onDetach();
        if (presenter != null) presenter.onDetach();
    }


    @Override
    public void setPresenter(P presenter) {
        if (presenter != null) {
            presenter.attachView(getBaseView());
        }
    }

    public void mostrarProgressBar() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void ocultarProgressBar() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }





}
