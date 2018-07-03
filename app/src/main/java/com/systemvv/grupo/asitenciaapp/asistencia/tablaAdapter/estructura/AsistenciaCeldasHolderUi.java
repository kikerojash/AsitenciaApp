package com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.estructura;

import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder.CeldasAsistenciaHolder;


public abstract class AsistenciaCeldasHolderUi   {

    CeldasAsistenciaHolder selectCell;

    public CeldasAsistenciaHolder getSelectCell() {
        return selectCell;
    }

    public void setSelectCell(CeldasAsistenciaHolder selectCell) {
        this.selectCell = selectCell;
    }
}
