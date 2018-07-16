package com.systemvv.grupo.asitenciaapp.padre.cursoHijos;

import android.content.res.Resources;
import android.os.Bundle;

import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenterImpl;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Hijos;

import org.parceler.Parcels;

public class CursoHijosPresenterImpl extends BaseActivityPresenterImpl<CursoHijosView> implements CursoHijosPresenter {

    public static final String TAG = CursoHijosPresenterImpl.class.getSimpleName();

    public CursoHijosPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }
    private Hijos hijos;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.hijos = Parcels.unwrap(extras.getParcelable("hijos"));
    }

    @Override
    public void onStart() {
        super.onStart();
        if (view!=null)view.mostrarListaCursos(hijos.getCursosList());
    }
}
