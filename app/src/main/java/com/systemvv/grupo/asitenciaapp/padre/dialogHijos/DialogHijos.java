package com.systemvv.grupo.asitenciaapp.padre.dialogHijos;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.systemvv.grupo.asitenciaapp.R;

import butterknife.ButterKnife;

public class DialogHijos extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_fragment_hijos, container, false);
        ButterKnife.bind(this, v);
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        return v;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

}
