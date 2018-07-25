package com.systemvv.grupo.asitenciaapp.fire.entidad;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Asistencia {

    private String keyAsistencia;
    private String tipoAsistencia;
    private String fechaRegistroAsistencia;
    private String horaInicioCurso;
    private String horaFinCurso;
    private String alu_id_alumno;
    private String cur_id_curso;
    private String sec_id_seccion;
    private String prd_id_periodo;
    private String ins_id_institucion;
    private String gra_id_grado;


    public Asistencia() {
    }

    public String getKeyAsistencia() {
        return keyAsistencia;
    }

    public void setKeyAsistencia(String keyAsistencia) {
        this.keyAsistencia = keyAsistencia;
    }

    public String getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(String tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }

    public String getFechaRegistroAsistencia() {
        return fechaRegistroAsistencia;
    }

    public void setFechaRegistroAsistencia(String fechaRegistroAsistencia) {
        this.fechaRegistroAsistencia = fechaRegistroAsistencia;
    }

    public String getHoraInicioCurso() {
        return horaInicioCurso;
    }

    public void setHoraInicioCurso(String horaInicioCurso) {
        this.horaInicioCurso = horaInicioCurso;
    }

    public String getHoraFinCurso() {
        return horaFinCurso;
    }

    public void setHoraFinCurso(String horaFinCurso) {
        this.horaFinCurso = horaFinCurso;
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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("keyAsistencia", keyAsistencia);
        result.put("tipoAsistencia", tipoAsistencia);
        result.put("fechaRegistroAsistencia",fechaRegistroAsistencia);
        result.put("horaInicioCurso", horaInicioCurso);
        result.put("horaFinCurso", horaFinCurso);
        result.put("alu_id_alumno",alu_id_alumno);
        result.put("cur_id_curso",cur_id_curso);
        result.put("sec_id_seccion",sec_id_seccion);
        result.put("prd_id_periodo",prd_id_periodo);
        result.put("ins_id_institucion",ins_id_institucion);
        result.put("gra_id_grado",gra_id_grado);
        return result;
    }
}
