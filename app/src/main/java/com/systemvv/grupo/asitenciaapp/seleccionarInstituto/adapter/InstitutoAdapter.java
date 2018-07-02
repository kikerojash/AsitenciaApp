package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.adapter.holder.InstitutoHolder;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.listener.InstitutoListener;

import java.util.List;

public class InstitutoAdapter extends RecyclerView.Adapter<InstitutoHolder> {

    private List<InstitutoUi> institutoUiList;
    private InstitutoListener listener;

    public InstitutoAdapter(List<InstitutoUi> institutoUiList, InstitutoListener listener) {
        this.institutoUiList = institutoUiList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public InstitutoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_institutos, parent, false);
        return new InstitutoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstitutoHolder holder, int position) {
        InstitutoUi institutoUi = institutoUiList.get(position);
        holder.bind(institutoUi,listener);
    }

    @Override
    public int getItemCount() {
        if (institutoUiList == null) return 0;
        return institutoUiList.size();
    }

    public void mostrarLista(List<InstitutoUi> institutoUi) {
        this.institutoUiList = institutoUi;
        notifyDataSetChanged();
    }

    public void agregarItem(InstitutoUi institutoUi) {
        this.institutoUiList.add(institutoUi);
        notifyItemInserted(getItemCount() - 1);
    }

    public void editarItem(InstitutoUi institutoUi) {
        int position = this.institutoUiList.indexOf(institutoUi);
        if (position != -1) {
            this.institutoUiList.set(position, institutoUi);
            notifyItemChanged(position);
        }
    }

    public void eliminarItem(InstitutoUi institutoUi) {
        int position = this.institutoUiList.indexOf(institutoUi);
        if (position != -1) {
            this.institutoUiList.remove(institutoUi);
            notifyItemRemoved(position);
        }
    }

    public void actualizarItem(InstitutoUi institutoUi) {
        int position = this.institutoUiList.indexOf(institutoUi);
        if (position != -1) {
            this.institutoUiList.set(position, institutoUi);
            notifyItemChanged(position);
        }
    }

}
