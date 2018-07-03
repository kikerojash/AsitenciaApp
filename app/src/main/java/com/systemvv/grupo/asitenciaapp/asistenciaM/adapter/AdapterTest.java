package com.systemvv.grupo.asitenciaapp.asistenciaM.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.estructura.AsistenciaCeldas;
import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.estructura.AsistenciaColumna;
import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.estructura.AsistenciaFilas;
import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.holder.AsistenciaAlumnosHolder;
import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.holder.AsistenciaCeldasHolder;
import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.holder.AsistenciaFilasHolder;
import com.systemvv.grupo.asitenciaapp.asistenciaM.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.asistenciaM.entidad.TipoAsistencia;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AlumnosUi;

public class AdapterTest extends AbstractTableAdapter<AsistenciaFilas, AsistenciaColumna, AsistenciaCeldas> {


    private final int ROW_SELECTOR_TIPO_ASISTENCIAS = 1;
    private final int COLUMN_ALUMNO = 10;
    private final int ASISTENCIA_CELDAS = 100;


    public AdapterTest(Context p_jContext) {
        super(p_jContext);
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        AsistenciaFilas rowHeader = m_jColumnHeaderItems.get(position);
        if (rowHeader instanceof TipoAsistencia) {
            TipoAsistencia tipoAsistencia = (TipoAsistencia) rowHeader;
            if (tipoAsistencia == null) return 0;
            return ROW_SELECTOR_TIPO_ASISTENCIAS;

        }
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        AsistenciaColumna columnHeader = m_jRowHeaderItems.get(position);
        if (columnHeader instanceof AlumnosUi) {
            return COLUMN_ALUMNO;
        }
        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        int cantidad = m_jCellItems.size();
        if (cantidad != 0) {
            AsistenciaCeldas cell = m_jCellItems.get(0).get(position);
            if (cell instanceof Asistencia) {
                return ASISTENCIA_CELDAS;
            }
        }
        return 0;
    }

   /* @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ASISTENCIA_CELDAS:
                View vistaAsistenciaPuntual = layoutInflater.inflate(R.layout.tabla_celdas_asistencia_puntuales, parent, false);
                viewHolder = new AsistenciaCeldasHolder(vistaAsistenciaPuntual);
                break;
        }
        return null;
       /// return null;
    }*/

    @Override
    public RecyclerView.ViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ASISTENCIA_CELDAS:
                View vistaAsistenciaPuntual = layoutInflater.inflate(R.layout.tabla_celdas_asistencia_puntuales, parent, false);
                viewHolder = new AsistenciaCeldasHolder(vistaAsistenciaPuntual);
                break;
        }
        return viewHolder;
    }


    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nXPosition, int p_nYPosition) {
        if (holder instanceof AsistenciaCeldasHolder && p_jValue instanceof Asistencia) {
            AsistenciaCeldasHolder notaCellViewHolder = (AsistenciaCeldasHolder) holder;
            Asistencia notaUi = (Asistencia) p_jValue;
            notaCellViewHolder.bind(notaUi);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ROW_SELECTOR_TIPO_ASISTENCIAS:
                View vistaAsistenciaPuntual = layoutInflater.inflate(R.layout.tabla_columna_asistencia_puntual, parent, false);
                viewHolder = new AsistenciaFilasHolder(vistaAsistenciaPuntual);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nXPosition) {
        if (holder instanceof AsistenciaFilasHolder && p_jValue instanceof TipoAsistencia) {
            AsistenciaFilasHolder notaRowViewHolder = (AsistenciaFilasHolder) holder;
            TipoAsistencia tipoAsistencia = (TipoAsistencia) p_jValue;
            notaRowViewHolder.bind(tipoAsistencia);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case COLUMN_ALUMNO:
                View vistaAsistenciaPuntual = layoutInflater.inflate(R.layout.tabla_filas_alumnos, parent, false);
                viewHolder = new AsistenciaAlumnosHolder(vistaAsistenciaPuntual);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nYPosition) {
        if (holder instanceof AsistenciaAlumnosHolder && p_jValue instanceof AlumnosUi) {
            AsistenciaAlumnosHolder alumnoColumnViewHolder = (AsistenciaAlumnosHolder) holder;
            AlumnosUi alumnoProcesoUi = (AlumnosUi) p_jValue;
            alumnoColumnViewHolder.bind(alumnoProcesoUi);
        }
    }

    @Override
    public View onCreateCornerView() {
        return LayoutInflater.from(m_jContext).inflate(R.layout.tabla_vista_disenio_esquina_alumnos, null, false);
    }
}
