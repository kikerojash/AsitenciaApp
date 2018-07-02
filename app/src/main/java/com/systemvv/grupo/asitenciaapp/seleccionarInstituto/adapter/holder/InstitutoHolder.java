package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.adapter.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.listener.InstitutoListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstitutoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.textViewNombre)
    TextView textViewNombre;
    @BindView(R.id.textViewCedeo)
    TextView textViewCede;
    @BindView(R.id.textViewDireccion)
    TextView textViewDireccion;
    @BindView(R.id.imageView2)
    ImageView circleImageView2;
    @BindView(R.id.cardViewItem)
    CardView cardView;
    InstitutoListener listener;
    InstitutoUi institutoUi;

    public InstitutoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        cardView.setOnClickListener(this);
    }

    public void bind(InstitutoUi institutoUi, InstitutoListener listener) {
        this.listener = listener;
        this.institutoUi = institutoUi;
        textViewNombre.setText(institutoUi.getNombre());
        textViewCede.setText(institutoUi.getCede());
        textViewDireccion.setText(institutoUi.getDireccion());
        Glide.with(itemView.getContext()).load(institutoUi.getImage()).into(circleImageView2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardViewItem:
                listener.onClickInstituto(institutoUi);
                break;
        }
    }
}
