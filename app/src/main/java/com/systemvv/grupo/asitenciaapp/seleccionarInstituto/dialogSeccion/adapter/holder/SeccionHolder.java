package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.adapter.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.view.View;
import android.widget.TextView;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad.SeccionUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.listener.ItemListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeccionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String TAG = SeccionHolder.class.getSimpleName();
    @BindView(R.id.txtGrado)
    TextView textViewGrado;
    @BindView(R.id.cardview)
    CardView cardViewItem;
    private SeccionUi seccionUi;
    private ItemListener listener;

    public SeccionHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        cardViewItem.setOnClickListener(this);
    }

    public void bind(SeccionUi seccionUi, ItemListener listener) {
        this.seccionUi = seccionUi;
        this.listener = listener;
        textViewGrado.setText("Grado: " + seccionUi.getGrado() + " Seccion: " + seccionUi.getSeccion());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardview:
                Log.d(TAG, "cardViewItem");
                listener.onClickItem(seccionUi);
                break;
            default:
                break;
        }
    }
}
