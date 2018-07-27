package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.adapter.holder.SeccionHolder;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad.SeccionUi;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.listener.ItemListener;

import java.util.List;

public class SeccionAdapter extends RecyclerView.Adapter<SeccionHolder> {

    private List<SeccionUi> seccionUiList;
    private ItemListener listener;

    public SeccionAdapter(List<SeccionUi> seccionUiList, ItemListener listener) {
        this.seccionUiList = seccionUiList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SeccionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seccion, parent, false);
        return new SeccionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeccionHolder holder, int position) {
        SeccionUi seccionUi = seccionUiList.get(position);
        holder.bind(seccionUi,listener);
    }

    @Override
    public int getItemCount() {
        if (seccionUiList == null) return 0;
        return seccionUiList.size();
    }

    public void mostrarListaSeccion(List<SeccionUi> seccionUiList){
        this.seccionUiList = seccionUiList;
        notifyDataSetChanged();
    }
}
