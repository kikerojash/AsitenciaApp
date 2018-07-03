package com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.asistencia.AsistenciaActivity;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.estructura.AsistenciaCelda;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.estructura.AsistenciaColumnaCabecera;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.estructura.AsistenciaFilaCabecera;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder.CeldasAsistenciaHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder.CeldasAsistenciaPuntualHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder.CeldassAsistenciaTardeHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder.CeldassAsistenciaTardeJustificadoHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder.CeldasAsistenciaFaltoHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder.ColumnaAsistenciaFaltoHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder.ColumnaAsistenciaPuntualHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder.ColumnaAsistenciaTardeHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder.ColumnaAsistenciaTardeJustificadoHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder.FilasAsistenciaAlumnosHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.listener.AsitenciaCeldasListener;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AlumnosUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.MotivosAsistenciaUi;

//public class AsistenciaAdapter extends AbstractTableAdapter<AsistenciaColumnaCabecera, AsistenciaFilaCabecera, AsistenciaCelda> {
public class AsistenciaAdapter extends AbstractTableAdapter<AsistenciaFilaCabecera,AsistenciaColumnaCabecera, AsistenciaCelda> {

    public static final String TAG = AsistenciaAdapter.class.getSimpleName();
    public static final int COLUMNA_ALUMNOS = 1;


 /*   public static final int ASISTENCIA_PUNTUALES = 10 ;
    public static final int ASISTENCIA_TARDE = 20 ;
    public static final int ASISTENCIA_TARDE_JUSTIFICADO = 30 ;
    public static final int ASISTENCIA_FALTO = 50 ;*/


    public static final int MENSAJE_PUNTUAL = 100;
    public static final int MENSAJE_TARDE = 101;
    public static final int MENSAJE_TARDE_JUSTIFICADO = 102;
    public static final int MENSAJE_FALTO = 103;

    public static final int TIPOS_ASISTENCIAS = 200;

    public AsistenciaAdapter(Context p_jContext) {
        super(p_jContext);
    }


    @Override
    public int getColumnHeaderItemViewType(int position) {
        /*Cabecera Columnas*/
       // AsistenciaCelda filaCabecera = m_jColumnHeaderItems.get(position);
       AsistenciaFilaCabecera filaCabecera = m_jColumnHeaderItems.get(position);
        if (filaCabecera == null) return 0;
        if (filaCabecera instanceof MotivosAsistenciaUi) {
            MotivosAsistenciaUi motivosAsistenciaUi = (MotivosAsistenciaUi) filaCabecera;
            if (motivosAsistenciaUi.getTipoMotivo() == MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL) {
                return MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL;
            } else if (motivosAsistenciaUi.getTipoMotivo() == MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE) {
                return MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE;
            } else if (motivosAsistenciaUi.getTipoMotivo() == MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE_JUSTIFICADO) {
                return MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE_JUSTIFICADO;
            } else if (motivosAsistenciaUi.getTipoMotivo() == MotivosAsistenciaUi.TIPO_ASISTENCIA_FALTO) {
                return MotivosAsistenciaUi.TIPO_ASISTENCIA_FALTO;
            }
        }
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        /*Celdas del lado Izquierdo*/
        //AsistenciaCelda filasLadiIzquierdoCabeceras = m_jRowHeaderItems.get(position);
        AsistenciaColumnaCabecera filasLadiIzquierdoCabeceras= m_jRowHeaderItems.get(position);
        //if (filasLadiIzquierdoCabeceras == null) return 0;
        /*if (filasLadiIzquierdoCabeceras instanceof AlumnosUi) {
            return COLUMNA_ALUMNOS;
        }*/
        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        int cantidad = m_jCellItems.size();
        if (cantidad != 0) {
            AsistenciaCelda celdasItemContador = m_jCellItems.get(0).get(position);
            if (celdasItemContador instanceof AsistenciaUi) {
                return TIPOS_ASISTENCIAS;

                // AsistenciaUi asistenciaUi = (AsistenciaUi) celdasItemContador;
                //Log.d(TAG, "alumnnos : " + asistenciaUi.getJustificacion());



               /*if (asistenciaUi.getJustificacion() == MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL) {
                    return MENSAJE_PUNTUAL;
                } else if (asistenciaUi.getJustificacion() == MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE) {
                    return MENSAJE_TARDE;
                } else if (asistenciaUi.getJustificacion() == MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE_JUSTIFICADO) {
                    return MENSAJE_TARDE_JUSTIFICADO;
                } else if (asistenciaUi.getJustificacion() == MotivosAsistenciaUi.TIPO_ASISTENCIA_FALTO) {
                    return MENSAJE_FALTO;
                }*/
            }
        }
        return 0;
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

//    @Override
//    public RecyclerView.ViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
//        RecyclerView.ViewHolder viewHolder = null;
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        switch (viewType) {
//            case TIPOS_ASISTENCIAS:
//                View vistaAsistenciaPuntual = layoutInflater.inflate(R.layout.tabla_celdas_asistencia_puntuales, parent, false);
//                viewHolder = new CeldasAsistenciaHolder(vistaAsistenciaPuntual);
//                break;
//           /* case MENSAJE_PUNTUAL:
//                View vistaAsistenciaPuntual = layoutInflater.inflate(R.layout.tabla_celdas_asistencia_puntuales, parent, false);
//                viewHolder = new CeldasAsistenciaPuntualHolder(vistaAsistenciaPuntual);
//                break;
//            case MENSAJE_TARDE:
//                View vistaSelectorValores = layoutInflater.inflate(R.layout.tabla_celdas_asistencia_tarde, parent, false);
//                viewHolder = new CeldassAsistenciaTardeHolder(vistaSelectorValores);
//                break;
//            case MENSAJE_TARDE_JUSTIFICADO:
//                View vistaSelectorIconos = layoutInflater.inflate(R.layout.tabla_celdas_asistencia_tarde_justificado, parent, false);
//                viewHolder = new CeldassAsistenciaTardeJustificadoHolder(vistaSelectorIconos);
//                break;
//            case MENSAJE_FALTO:
//                View vistaValorTipoNota = layoutInflater.inflate(R.layout.tabla_celdas_asistencia_falto, parent, false);
//                viewHolder = new CeldasAsistenciaFaltoHolder(vistaValorTipoNota);
//                break;*/
//        }
//        return viewHolder;
//    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nXPosition, int p_nYPosition) {
      if(holder instanceof CeldasAsistenciaHolder && p_jValue instanceof AsistenciaUi ){
          CeldasAsistenciaHolder celdasAsistenciaHolder = (CeldasAsistenciaHolder) holder;
          AsistenciaUi asistenciaUi = (AsistenciaUi) p_jValue;
          celdasAsistenciaHolder.bind(asistenciaUi);
      }

       /* if (holder instanceof CeldasAsistenciaPuntualHolder && p_jValue instanceof AsistenciaUi) {
            CeldasAsistenciaPuntualHolder celdasAsistenciaPuntualHolder = (CeldasAsistenciaPuntualHolder) holder;
            AsistenciaUi asistenciaUi = (AsistenciaUi) p_jValue;
            celdasAsistenciaPuntualHolder.bind(asistenciaUi);
        } else if (holder instanceof CeldassAsistenciaTardeHolder && p_jValue instanceof AsistenciaUi) {
            CeldassAsistenciaTardeHolder celdassAsistenciaTardeHolder = (CeldassAsistenciaTardeHolder) holder;
            AsistenciaUi asistenciaUi = (AsistenciaUi) p_jValue;
            celdassAsistenciaTardeHolder.bind(asistenciaUi);
        } else if (holder instanceof CeldassAsistenciaTardeJustificadoHolder && p_jValue instanceof AsistenciaUi) {
            CeldassAsistenciaTardeJustificadoHolder celdassAsistenciaTardeJustificadoHolder = (CeldassAsistenciaTardeJustificadoHolder) holder;
            AsistenciaUi asistenciaUi = (AsistenciaUi) p_jValue;
            celdassAsistenciaTardeJustificadoHolder.bind(asistenciaUi);
        } else if (holder instanceof CeldasAsistenciaFaltoHolder && p_jValue instanceof AsistenciaUi) {
            CeldasAsistenciaFaltoHolder celdasAsistenciaFaltoHolder = (CeldasAsistenciaFaltoHolder) holder;
            AsistenciaUi asistenciaUi = (AsistenciaUi) p_jValue;
            celdasAsistenciaFaltoHolder.bind(asistenciaUi);
        }*/
    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

//    @Override
//    public RecyclerView.ViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
//        RecyclerView.ViewHolder viewHolder = null;
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        switch (viewType) {
//            case MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL:
//                View vistaAsistenciaPuntual = layoutInflater.inflate(R.layout.tabla_columna_asistencia_puntual, parent, false);
//                viewHolder = new ColumnaAsistenciaPuntualHolder(vistaAsistenciaPuntual);
//                break;
//            case MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE:
//                View vistaAsistenciaTarde = layoutInflater.inflate(R.layout.tabla_columna_asistencia_tarde, parent, false);
//                viewHolder = new ColumnaAsistenciaTardeHolder(vistaAsistenciaTarde);
//                break;
//            case MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE_JUSTIFICADO:
//                View vistaAsistenciaTardeJustificado = layoutInflater.inflate(R.layout.tabla_columna_asistencia_tarde_justificado, parent, false);
//                viewHolder = new ColumnaAsistenciaTardeJustificadoHolder(vistaAsistenciaTardeJustificado);
//                break;
//            case MotivosAsistenciaUi.TIPO_ASISTENCIA_FALTO:
//                View vistaAsistenciaFalto = layoutInflater.inflate(R.layout.tabla_columna_asistencia_falto, parent, false);
//                viewHolder = new ColumnaAsistenciaFaltoHolder(vistaAsistenciaFalto);
//                break;
//            /*default:
//                View vistaRubroNormal = layoutInflater.inflate(R.layout.tabla_columna_rubro_normal, parent, false);
//                viewHolder = new ColumnaRubroNormalHolder(vistaRubroNormal);
//                break;*/
//        }
//        return viewHolder;
//    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nXPosition) {
        if (holder instanceof ColumnaAsistenciaPuntualHolder && p_jValue instanceof MotivosAsistenciaUi) {
            ColumnaAsistenciaPuntualHolder columnaAsistenciaPuntualHolder = (ColumnaAsistenciaPuntualHolder) holder;
            MotivosAsistenciaUi motivosAsistenciaUi = (MotivosAsistenciaUi) p_jValue;
            columnaAsistenciaPuntualHolder.bind(motivosAsistenciaUi);
        } else if (holder instanceof ColumnaAsistenciaTardeHolder && p_jValue instanceof MotivosAsistenciaUi) {
            ColumnaAsistenciaTardeHolder columnaAsistenciaTardeHolder = (ColumnaAsistenciaTardeHolder) holder;
            MotivosAsistenciaUi motivosAsistenciaUi = (MotivosAsistenciaUi) p_jValue;
            columnaAsistenciaTardeHolder.bind(motivosAsistenciaUi);
        } else if (holder instanceof ColumnaAsistenciaTardeJustificadoHolder && p_jValue instanceof MotivosAsistenciaUi) {
            ColumnaAsistenciaTardeJustificadoHolder columnaAsistenciaTardeJustificadoHolder = (ColumnaAsistenciaTardeJustificadoHolder) holder;
            MotivosAsistenciaUi motivosAsistenciaUi = (MotivosAsistenciaUi) p_jValue;
            columnaAsistenciaTardeJustificadoHolder.bind(motivosAsistenciaUi);
        } else if (holder instanceof ColumnaAsistenciaFaltoHolder && p_jValue instanceof MotivosAsistenciaUi) {
            ColumnaAsistenciaFaltoHolder columnaAsistenciaFaltoHolder = (ColumnaAsistenciaFaltoHolder) holder;
            MotivosAsistenciaUi motivosAsistenciaUi = (MotivosAsistenciaUi) p_jValue;
            columnaAsistenciaFaltoHolder.bind(motivosAsistenciaUi);
        }
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

//    @Override
//    public RecyclerView.ViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
//      /*  RecyclerView.ViewHolder viewHolder = null;
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        switch (viewType) {
//            case COLUMNA_ALUMNOS:
//                View vistaAlumnos = layoutInflater.inflate(R.layout.tabla_filas_alumnos, parent, false);
//                viewHolder = new FilasAsistenciaAlumnosHolder(vistaAlumnos);
//                break;
//            default:
//                View vistaRubrosNormal2 = layoutInflater.inflate(R.layout.tabla_filas_alumnos, parent, false);
//                viewHolder = new FilasAsistenciaAlumnosHolder(vistaRubrosNormal2);
//                break;
//        }*/
//        return null;
//    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nYPosition) {
       /* if (holder instanceof FilasAsistenciaAlumnosHolder && p_jValue instanceof AlumnosUi) {
            FilasAsistenciaAlumnosHolder filasAsistenciaAlumnosHolder = (FilasAsistenciaAlumnosHolder) holder;
            AlumnosUi alumnosUi = (AlumnosUi) p_jValue;
            filasAsistenciaAlumnosHolder.bind(alumnosUi);
        }*/
    }

    @Override
    public View onCreateCornerView() {
        return LayoutInflater.from(m_jContext).inflate(R.layout.tabla_vista_disenio_esquina_alumnos, null, false);
    }
}
