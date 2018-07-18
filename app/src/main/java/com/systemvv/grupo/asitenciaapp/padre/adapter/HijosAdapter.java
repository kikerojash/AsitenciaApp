package com.systemvv.grupo.asitenciaapp.padre.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.padre.adapter.holder.HijosHolder;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Hijos;
import com.systemvv.grupo.asitenciaapp.padre.listener.HijosListener;

import java.util.List;

public class HijosAdapter extends RecyclerView.Adapter<HijosHolder> {

    private List<Hijos> hijosList;
    private HijosListener listener;

    public HijosAdapter(List<Hijos> hijosList, HijosListener listener) {
        this.hijosList = hijosList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HijosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hijos, parent, false);
        return new HijosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HijosHolder holder, int position) {
        Hijos hijos = hijosList.get(position);
        int positionToColor = position % 3 ;
        holder.bind(hijos,listener,positionToColor);
    }

    @Override
    public int getItemCount() {
        if (hijosList == null) return 0;
        return hijosList.size();
    }

    public void mostrarLista(List<Hijos> hijosList) {
        this.hijosList = hijosList;
        notifyDataSetChanged();
    }
}
