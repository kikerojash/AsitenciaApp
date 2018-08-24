package com.systemvv.grupo.asitenciaapp.fire.entidad;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Asistencia {

    private String asi_id_asistencia;
    private String asi_hora_inicio;
    private String asi_hora_fin;
    private String asi_tipo_asistencia;
    private String asi_fecha;
    private String alu_id_alumno;
    private String cur_id_curso;
    private String sec_id_seccion;
    private String prd_id_periodo;
    private String ins_id_institucion;
    private String gra_id_grado;
    private long timeStamp;


    public Asistencia() {
    }

    public String getAsi_id_asistencia() {
        return asi_id_asistencia;
    }

    public void setAsi_id_asistencia(String asi_id_asistencia) {
        this.asi_id_asistencia = asi_id_asistencia;
    }

    public String getAsi_hora_inicio() {
        return asi_hora_inicio;
    }

    public void setAsi_hora_inicio(String asi_hora_inicio) {
        this.asi_hora_inicio = asi_hora_inicio;
    }

    public String getAsi_hora_fin() {
        return asi_hora_fin;
    }

    public void setAsi_hora_fin(String asi_hora_fin) {
        this.asi_hora_fin = asi_hora_fin;
    }

    public String getAsi_tipo_asistencia() {
        return asi_tipo_asistencia;
    }

    public void setAsi_tipo_asistencia(String asi_tipo_asistencia) {
        this.asi_tipo_asistencia = asi_tipo_asistencia;
    }

    public String getAsi_fecha() {
        return asi_fecha;
    }

    public void setAsi_fecha(String asi_fecha) {
        this.asi_fecha = asi_fecha;
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


    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        // result.put("asi_id_asistencia", asi_id_asistencia);
        result.put("asi_hora_inicio", asi_hora_inicio);
        result.put("asi_hora_fin", asi_hora_fin);
        result.put("asi_tipo_asistencia", asi_tipo_asistencia);
        result.put("asi_fecha", asi_fecha);
        result.put("alu_id_alumno", alu_id_alumno);
        result.put("cur_id_curso", cur_id_curso);
        result.put("sec_id_seccion", sec_id_seccion);
        result.put("prd_id_periodo", prd_id_periodo);
        result.put("ins_id_institucion", ins_id_institucion);
        result.put("gra_id_grado", gra_id_grado);
        result.put("timeStamp", timeStamp);
        return result;
    }
}
