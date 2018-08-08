package com.systemvv.grupo.asitenciaapp.asistencia.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.systemvv.grupo.asitenciaapp.R;
import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.CeldasAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.ColumnaCabeceraAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.FilaCabeceraAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.celdas.CeldasAsistenciaAlumnoFaltoHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.celdas.CeldasAsistenciaAlumnoJustificadoHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.celdas.CeldasAsistenciaAlumnoPuntualHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.celdas.CeldasAsistenciaAlumnoTardeHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.columnas.ColumnaTipoFaltoHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.columnas.ColumnaTipoPresenteHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.columnas.ColumnaTipoTardeHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.filas.FilasAsistenciaAlumnosHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Alumnos;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.MotivoAsistencia;

public class AsistenciaAdapter extends AbstractTableAdapter<ColumnaCabeceraAsistencia, FilaCabeceraAsistencia, CeldasAsistencia> {

    public final String TAG = AsistenciaAdapter.class.getSimpleName();

    private final int COLUMNAS_SELECTOR_TIPO_ASISTENCIA_PRESENTE = 10;
    private final int COLUMNAS_SELECTOR_TIPO_ASISTENCIA_TARDE = 20;
    private final int COLUMNAS_SELECTOR_TIPO_ASISTENCIA_FALTO = 30;


    public final int TIPO_ASISTENCIA_CELDAS_ALUMNOS_PUNTUAL = 1;
    public final int TIPO_ASISTENCIA_CELDAS_ALUMNOS_TARDE = 2;
    public final int TIPO_ASISTENCIA_CELDAS_ALUMNOS_TARDE_JUSTIFICADO = 3;
    public final int TIPO_ASISTENCIA_CELDAS_ALUMNOS_FALTO = 4;

    public final int FILAS_ALUMNO = 100;


    private final LayoutInflater mInflater;

    public AsistenciaAdapter(Context context) {
        super(context);
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        ColumnaCabeceraAsistencia columnaCabecera = mColumnHeaderItems.get(position);
        Log.d(TAG,"mColumnHeaderItems : "+ mColumnHeaderItems.size());
        /*if (columnaCabecera instanceof MotivosAsistenciaUi) {
            MotivosAsistenciaUi tipoAsistencia = (MotivosAsistenciaUi) columnaCabecera;
            if (tipoAsistencia == null) return 0;
            if (tipoAsistencia.getTipoMotivo() == MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL) {
                return COLUMNAS_SELECTOR_TIPO_ASISTENCIA_PRESENTE;
            } else if (tipoAsistencia.getTipoMotivo() == MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE) {
                return COLUMNAS_SELECTOR_TIPO_ASISTENCIA_TARDE;
            } else if (tipoAsistencia.getTipoMotivo() == MotivosAsistenciaUi.TIPO_ASISTENCIA_FALTO) {
                return COLUMNAS_SELECTOR_TIPO_ASISTENCIA_FALTO;
            }
        }*/
        if (columnaCabecera instanceof MotivoAsistencia) {
            MotivoAsistencia motivoAsistencia = (MotivoAsistencia) columnaCabecera;
            if (motivoAsistencia == null) return 0;
            if (motivoAsistencia.getTipoMotivo() == MotivoAsistencia.TIPO_ASISTENCIA_PUNTUAL) {
                return COLUMNAS_SELECTOR_TIPO_ASISTENCIA_PRESENTE;
            } else if (motivoAsistencia.getTipoMotivo() == MotivoAsistencia.TIPO_ASISTENCIA_TARDE) {
                return COLUMNAS_SELECTOR_TIPO_ASISTENCIA_TARDE;
            } else if (motivoAsistencia.getTipoMotivo() == MotivoAsistencia.TIPO_ASISTENCIA_FALTO) {
                return COLUMNAS_SELECTOR_TIPO_ASISTENCIA_FALTO;
            }
        }
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        FilaCabeceraAsistencia filaCabecera = mRowHeaderItems.get(position);
        /*if (filaCabecera instanceof AlumnosUi) {
            return FILAS_ALUMNO;
        }*/
        if (filaCabecera instanceof Alumnos) {
            return FILAS_ALUMNO;
        }
        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        int cantidad = mCellItems.size();
        if (cantidad != 0) {
            CeldasAsistencia cell = mCellItems.get(0).get(position);
            if (cell instanceof Asistencia) {
                Asistencia asistenciaUi = (Asistencia) cell;
                if (asistenciaUi.getJustificacion() == TIPO_ASISTENCIA_CELDAS_ALUMNOS_PUNTUAL) {
                    Log.d(TAG, "TIPO_ASISTENCIA_CELDAS_ALUMNOS_PUNTUAL : ");
                    return TIPO_ASISTENCIA_CELDAS_ALUMNOS_PUNTUAL;
                } else if (asistenciaUi.getJustificacion() == TIPO_ASISTENCIA_CELDAS_ALUMNOS_TARDE) {
                    Log.d(TAG, "TIPO_ASISTENCIA_CELDAS_ALUMNOS_TARDE : ");
                    return TIPO_ASISTENCIA_CELDAS_ALUMNOS_TARDE;
                } else if (asistenciaUi.getJustificacion() == TIPO_ASISTENCIA_CELDAS_ALUMNOS_FALTO) {
                    Log.d(TAG, "TIPO_ASISTENCIA_CELDAS_ALUMNOS_FALTO : ");
                    return TIPO_ASISTENCIA_CELDAS_ALUMNOS_FALTO;
                }
            }


            /*if (cell instanceof AsistenciaUi) {
                AsistenciaUi asistenciaUi = (AsistenciaUi) cell;
                if (asistenciaUi.getJustificacion() == TIPO_ASISTENCIA_CELDAS_ALUMNOS_PUNTUAL) {
                    Log.d(TAG, "TIPO_ASISTENCIA_CELDAS_ALUMNOS_PUNTUAL : ");
                    return TIPO_ASISTENCIA_CELDAS_ALUMNOS_PUNTUAL;
                } else if (asistenciaUi.getJustificacion() == TIPO_ASISTENCIA_CELDAS_ALUMNOS_TARDE) {
                    Log.d(TAG, "TIPO_ASISTENCIA_CELDAS_ALUMNOS_TARDE : ");
                    return TIPO_ASISTENCIA_CELDAS_ALUMNOS_TARDE;
                } else if (asistenciaUi.getJustificacion() == TIPO_ASISTENCIA_CELDAS_ALUMNOS_FALTO) {
                    Log.d(TAG, "TIPO_ASISTENCIA_CELDAS_ALUMNOS_FALTO : ");
                    return TIPO_ASISTENCIA_CELDAS_ALUMNOS_FALTO;
                }
            }*/
        }
        return 0;
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View layout;
        switch (viewType) {
            case TIPO_ASISTENCIA_CELDAS_ALUMNOS_PUNTUAL:
                layout = mInflater.inflate(R.layout.tabla_celdas_asistencia_puntuales, parent, false);
                return new CeldasAsistenciaAlumnoPuntualHolder(layout);
            case TIPO_ASISTENCIA_CELDAS_ALUMNOS_TARDE:
                layout = mInflater.inflate(R.layout.tabla_celdas_asistencia_puntuales, parent, false);
                return new CeldasAsistenciaAlumnoTardeHolder(layout);
            case TIPO_ASISTENCIA_CELDAS_ALUMNOS_TARDE_JUSTIFICADO:
                layout = mInflater.inflate(R.layout.tabla_celdas_asistencia_puntuales, parent, false);
                return new CeldasAsistenciaAlumnoJustificadoHolder(layout);
            case TIPO_ASISTENCIA_CELDAS_ALUMNOS_FALTO:
                layout = mInflater.inflate(R.layout.tabla_celdas_asistencia_puntuales, parent, false);
                return new CeldasAsistenciaAlumnoFaltoHolder(layout);
            default:
                layout = mInflater.inflate(R.layout.tabla_celdas_asistencia_puntuales, parent, false);
                return new CeldasAsistenciaAlumnoPuntualHolder(layout);
        }
    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object cellItemModel, int columnPosition, int rowPosition) {
       /* if (holder instanceof CeldasAsistenciaAlumnoPuntualHolder && cellItemModel instanceof AsistenciaUi) {
            AsistenciaUi asistencia = (AsistenciaUi) cellItemModel;
            CeldasAsistenciaAlumnoPuntualHolder puntualHolder = (CeldasAsistenciaAlumnoPuntualHolder) holder;
            puntualHolder.bind(asistencia);
        } else if (holder instanceof CeldasAsistenciaAlumnoTardeHolder && cellItemModel instanceof AsistenciaUi) {
            AsistenciaUi asistencia = (AsistenciaUi) cellItemModel;
            CeldasAsistenciaAlumnoTardeHolder tardeHolder = (CeldasAsistenciaAlumnoTardeHolder) holder;
            tardeHolder.bind(asistencia);
        } else if (holder instanceof CeldasAsistenciaAlumnoJustificadoHolder && cellItemModel instanceof AsistenciaUi) {
            AsistenciaUi asistencia = (AsistenciaUi) cellItemModel;
            CeldasAsistenciaAlumnoJustificadoHolder justificadoHolder = (CeldasAsistenciaAlumnoJustificadoHolder) holder;
            justificadoHolder.bind(asistencia);
        } else if (holder instanceof CeldasAsistenciaAlumnoFaltoHolder && cellItemModel instanceof AsistenciaUi) {
            AsistenciaUi asistencia = (AsistenciaUi) cellItemModel;
            CeldasAsistenciaAlumnoFaltoHolder faltoHolder = (CeldasAsistenciaAlumnoFaltoHolder) holder;
            faltoHolder.bind(asistencia);
        }*/

        if (holder instanceof CeldasAsistenciaAlumnoPuntualHolder && cellItemModel instanceof Asistencia) {
            Asistencia asistencia = (Asistencia) cellItemModel;
            CeldasAsistenciaAlumnoPuntualHolder puntualHolder = (CeldasAsistenciaAlumnoPuntualHolder) holder;
            puntualHolder.bind(asistencia);
        } else if (holder instanceof CeldasAsistenciaAlumnoTardeHolder && cellItemModel instanceof Asistencia) {
            Asistencia asistencia = (Asistencia) cellItemModel;
            CeldasAsistenciaAlumnoTardeHolder tardeHolder = (CeldasAsistenciaAlumnoTardeHolder) holder;
            tardeHolder.bind(asistencia);
        }  else if (holder instanceof CeldasAsistenciaAlumnoFaltoHolder && cellItemModel instanceof Asistencia) {
            Asistencia asistencia = (Asistencia) cellItemModel;
            CeldasAsistenciaAlumnoFaltoHolder faltoHolder = (CeldasAsistenciaAlumnoFaltoHolder) holder;
            faltoHolder.bind(asistencia);
        }
    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout;
        switch (viewType) {
            case COLUMNAS_SELECTOR_TIPO_ASISTENCIA_PRESENTE:
                layout = mInflater.inflate(R.layout.tabla_columna_asistencia_puntual, parent, false);
                return new ColumnaTipoPresenteHolder(layout);
            case COLUMNAS_SELECTOR_TIPO_ASISTENCIA_TARDE:
                layout = mInflater.inflate(R.layout.tabla_columna_asistencia_puntual, parent, false);
                return new ColumnaTipoTardeHolder(layout);
            case COLUMNAS_SELECTOR_TIPO_ASISTENCIA_FALTO:
                layout = mInflater.inflate(R.layout.tabla_columna_asistencia_puntual, parent, false);
                return new ColumnaTipoFaltoHolder(layout);
            default:
                layout = mInflater.inflate(R.layout.tabla_columna_asistencia_puntual, parent, false);
                return new ColumnaTipoPresenteHolder(layout);
        }

    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object columnHeaderItemModel, int columnPosition) {
      /*  if (holder instanceof ColumnaTipoPresenteHolder && columnHeaderItemModel instanceof MotivosAsistenciaUi) {
            MotivosAsistenciaUi motivosAsistenciaUi = (MotivosAsistenciaUi) columnHeaderItemModel;
            ColumnaTipoPresenteHolder puntualHolder = (ColumnaTipoPresenteHolder) holder;
            puntualHolder.bind(motivosAsistenciaUi);
        } else if (holder instanceof ColumnaTipoTardeHolder && columnHeaderItemModel instanceof MotivosAsistenciaUi) {
            MotivosAsistenciaUi motivosAsistenciaUi = (MotivosAsistenciaUi) columnHeaderItemModel;
            ColumnaTipoTardeHolder tardeHolder = (ColumnaTipoTardeHolder) holder;
            tardeHolder.bind(motivosAsistenciaUi);
        } else if (holder instanceof ColumnaTipoFaltoHolder && columnHeaderItemModel instanceof MotivosAsistenciaUi) {
            MotivosAsistenciaUi motivosAsistenciaUi = (MotivosAsistenciaUi) columnHeaderItemModel;
            ColumnaTipoFaltoHolder faltoHolder = (ColumnaTipoFaltoHolder) holder;
            faltoHolder.bind(motivosAsistenciaUi);
        }*/

        if (holder instanceof ColumnaTipoPresenteHolder && columnHeaderItemModel instanceof MotivoAsistencia) {
            MotivoAsistencia motivosAsistenciaUi = (MotivoAsistencia) columnHeaderItemModel;
            ColumnaTipoPresenteHolder puntualHolder = (ColumnaTipoPresenteHolder) holder;
            puntualHolder.bind(motivosAsistenciaUi);
        } else if (holder instanceof ColumnaTipoTardeHolder && columnHeaderItemModel instanceof MotivoAsistencia) {
            MotivoAsistencia motivosAsistenciaUi = (MotivoAsistencia) columnHeaderItemModel;
            ColumnaTipoTardeHolder tardeHolder = (ColumnaTipoTardeHolder) holder;
            tardeHolder.bind(motivosAsistenciaUi);
        } else if (holder instanceof ColumnaTipoFaltoHolder && columnHeaderItemModel instanceof MotivoAsistencia) {
            MotivoAsistencia motivosAsistenciaUi = (MotivoAsistencia) columnHeaderItemModel;
            ColumnaTipoFaltoHolder faltoHolder = (ColumnaTipoFaltoHolder) holder;
            faltoHolder.bind(motivosAsistenciaUi);
        }
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout;
        switch (viewType) {
            case FILAS_ALUMNO:
                layout = mInflater.inflate(R.layout.tabla_filas_alumnos, parent, false);
                return new FilasAsistenciaAlumnosHolder(layout);
            default:
                layout = mInflater.inflate(R.layout.tabla_filas_alumnos, parent, false);
                return new FilasAsistenciaAlumnosHolder(layout);
        }

    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object rowHeaderItemModel, int rowPosition) {
        /*if (holder instanceof FilasAsistenciaAlumnosHolder && rowHeaderItemModel instanceof AlumnosUi) {
            AlumnosUi alumnosUi = (AlumnosUi) rowHeaderItemModel;
            FilasAsistenciaAlumnosHolder alumnosHolder = (FilasAsistenciaAlumnosHolder) holder;
            alumnosHolder.bind(alumnosUi);
        }*/

        if (holder instanceof FilasAsistenciaAlumnosHolder && rowHeaderItemModel instanceof Alumnos) {
            Alumnos alumnosUi = (Alumnos) rowHeaderItemModel;
            FilasAsistenciaAlumnosHolder alumnosHolder = (FilasAsistenciaAlumnosHolder) holder;
            alumnosHolder.bind(alumnosUi);
        }
    }

    @Override
    public View onCreateCornerView() {
        return LayoutInflater.from(mContext).inflate(R.layout.tabla_vista_disenio_esquina_alumnos, null, false);
    }
}
