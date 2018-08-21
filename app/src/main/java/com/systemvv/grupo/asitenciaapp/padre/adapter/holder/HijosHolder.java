package com.systemvv.grupo.asitenciaapp.padre.adapter.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Hijos;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.padre.listener.HijosListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HijosHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.textView5)
    TextView nombreInstituto;
    @BindView(R.id.imgProfile)
    CircleImageView circleImageViewHijo;
    @BindView(R.id.textViewNombre)
    TextView textViewNombre;
    @BindView(R.id.textViewApellido)
    TextView textViewApellido;
    @BindView(R.id.textViewDni)
    TextView textViewDni;
    @BindView(R.id.textViewEdad)
    TextView textViewEdad;
    @BindView(R.id.textViewGrado)
    TextView textViewGrado;
    @BindView(R.id.cardviewHijos)
    CardView cardView;
    @BindView(R.id.fondo)
    ImageView imageViewFondo;

    Hijos hijos;
    HijosListener listener;


    public HijosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        cardView.setOnClickListener(this);
    }

    public void bind(Hijos hijos, HijosListener listener,int positionToColor) {
        this.hijos = hijos;
        this.listener = listener;
        nombreInstituto.setText(hijos.getNombreInstituto());
        textViewNombre.setText(hijos.getNombre());
        textViewApellido.setText(hijos.getApellido());
        textViewDni.setText("N°: " + hijos.getDni());
        textViewEdad.setText("Edad: " + hijos.getEdad() + " Años");
        textViewGrado.setText("Grado: " + hijos.getGrado() + " Sección: " + hijos.getSeccion());
        Glide.with(itemView.getContext()).load(hijos.getFoto()).into(circleImageViewHijo);
        switch (positionToColor){
            case 0:
                imageViewFondo.setBackground(itemView.getResources().getDrawable(R.drawable.background_primer_hijo));
                break;
            case 1:
                imageViewFondo.setBackground(itemView.getResources().getDrawable(R.drawable.background_primer_hijo));
                break;
            default:
                imageViewFondo.setBackground(itemView.getResources().getDrawable(R.drawable.background_primer_hijo));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardviewHijos:
                listener.onClickItemHijo(hijos);
                break;
        }
    }
}
