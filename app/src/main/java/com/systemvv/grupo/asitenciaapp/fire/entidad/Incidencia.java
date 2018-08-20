package com.systemvv.grupo.asitenciaapp.fire.entidad;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Incidencia {
    private String inc_id_incidencia;
    private String inc_prioridad;
    private String inc_descripcion;
    private String inc_fecha;
    private long timeStamp;
    private String alu_id_alumno;
    private String cur_id_curso;
    private String sec_id_seccion;
    private String prd_id_periodo;
    private String ins_id_institucion;
    private String gra_id_grado;
    private boolean estadoActivo;

    public Incidencia() {
    }

    public String getInc_id_incidencia() {
        return inc_id_incidencia;
    }

    public void setInc_id_incidencia(String inc_id_incidencia) {
        this.inc_id_incidencia = inc_id_incidencia;
    }

    public String getInc_prioridad() {
        return inc_prioridad;
    }

    public void setInc_prioridad(String inc_prioridad) {
        this.inc_prioridad = inc_prioridad;
    }

    public String getInc_descripcion() {
        return inc_descripcion;
    }

    public void setInc_descripcion(String inc_descripcion) {
        this.inc_descripcion = inc_descripcion;
    }

    public String getInc_fecha() {
        return inc_fecha;
    }

    public void setInc_fecha(String inc_fecha) {
        this.inc_fecha = inc_fecha;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAlu_id_alumno() {
        return alu_id_alumno;
    }

    public void setAlu_id_alumno(String alu_id_alumno) {
        this.alu_id_alumno = alu_id_alumno;
    }

    public String getCur_id_curso() {
        return cur_id_curso;
    }

    public void setCur_id_curso(String cur_id_curso) {
        this.cur_id_curso = cur_id_curso;
    }

    public String getSec_id_seccion() {
        return sec_id_seccion;
    }

    public void setSec_id_seccion(String sec_id_seccion) {
        this.sec_id_seccion = sec_id_seccion;
    }

    public String getPrd_id_periodo() {
        return prd_id_periodo;
    }

    public void setPrd_id_periodo(String prd_id_periodo) {
        this.prd_id_periodo = prd_id_periodo;
    }

    public String getIns_id_institucion() {
        return ins_id_institucion;
    }

    public void setIns_id_institucion(String ins_id_institucion) {
        this.ins_id_institucion = ins_id_institucion;
    }

    public String getGra_id_grado() {
        return gra_id_grado;
    }

    public void setGra_id_grado(String gra_id_grado) {
        this.gra_id_grado = gra_id_grado;
    }

    public boolean isEstadoActivo() {
        return estadoActivo;
    }

    public void setEstadoActivo(boolean estadoActivo) {
        this.estadoActivo = estadoActivo;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        //result.put("inc_id_incidencia", inc_id_incidencia);
        result.put("inc_prioridad", inc_prioridad);
        result.put("inc_descripcion", inc_descripcion);
        result.put("inc_fecha", inc_fecha);
        result.put("timeStamp", timeStamp);
        result.put("alu_id_alumno", alu_id_alumno);
        result.put("cur_id_curso", cur_id_curso);
        result.put("sec_id_seccion", sec_id_seccion);
        result.put("prd_id_periodo", prd_id_periodo);
        result.put("ins_id_institucion", ins_id_institucion);
        result.put("gra_id_grado", gra_id_grado);
        result.put("estadoActivo", estadoActivo);
        return result;
    }
}
