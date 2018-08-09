package com.systemvv.grupo.asitenciaapp.asistencia.entidad;

import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.FilaCabeceraAsistencia;

import java.util.List;

public class Alumnos extends FilaCabeceraAsistencia {

    public enum AlumnoMotivoAsistenciaColumna {COLUMNA_PRESENTE, COLUMNA_TARDE, COLUMNA_FALTO, COLUMNA_DEFECTO}

    private int conteo;
    private String keyAlumno;
    private String nombre;
    private String apellido;
    private String foto;

    private int tipoAsistencia;
    private int tipoPadecimiento;
    private String padecimiento;

    private AlumnoMotivoAsistenciaColumna alumnoMotivoAsistenciaColumna;

    private Asistencia asistencia;
    private List<MotivoAsistencia> motivoAsistenciaList;
    private MotivoAsistencia motivoAsistencia;

    /*Key */

    private String keyCurso;
    private String keyGrado;
    private String keyInstitucion;
    private String keyPeriodo;
    private String keySeccion;


    public Alumnos() {
    }

    public Alumnos(int conteo, String nombre, String apellido, String foto) {
        this.conteo = conteo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.foto = foto;

    }

    public String getKeyAlumno() {
        return keyAlumno;
    }

    public void setKeyAlumno(String keyAlumno) {
        this.keyAlumno = keyAlumno;
    }

    public int getConteo() {
        return conteo;
    }

    public void setConteo(int conteo) {
        this.conteo = conteo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(int tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }

    public int getTipoPadecimiento() {
        return tipoPadecimiento;
    }

    public void setTipoPadecimiento(int tipoPadecimiento) {
        this.tipoPadecimiento = tipoPadecimiento;
    }

    public AlumnoMotivoAsistenciaColumna getAlumnoMotivoAsistenciaColumna() {
        return alumnoMotivoAsistenciaColumna;
    }

    public void setAlumnoMotivoAsistenciaColumna(AlumnoMotivoAsistenciaColumna alumnoMotivoAsistenciaColumna) {
        this.alumnoMotivoAsistenciaColumna = alumnoMotivoAsistenciaColumna;
    }

    public Asistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }

    public List<MotivoAsistencia> getMotivoAsistenciaList() {
        return motivoAsistenciaList;
    }

    public void setMotivoAsistenciaList(List<MotivoAsistencia> motivoAsistenciaList) {
        this.motivoAsistenciaList = motivoAsistenciaList;
    }

    public MotivoAsistencia getMotivoAsistencia() {
        return motivoAsistencia;
    }

    public void setMotivoAsistencia(MotivoAsistencia motivoAsistencia) {
        this.motivoAsistencia = motivoAsistencia;
    }

    public String getPadecimiento() {
        return padecimiento;
    }

    public void setPadecimiento(String padecimiento) {
        this.padecimiento = padecimiento;
    }

    public String getKeyCurso() {
        return keyCurso;
    }

    public void setKeyCurso(String keyCurso) {
        this.keyCurso = keyCurso;
    }

    public String getKeyGrado() {
        return keyGrado;
    }

    public void setKeyGrado(String keyGrado) {
        this.keyGrado = keyGrado;
    }

    public String getKeyInstitucion() {
        return keyInstitucion;
    }

    public void setKeyInstitucion(String keyInstitucion) {
        this.keyInstitucion = keyInstitucion;
    }

    public String getKeyPeriodo() {
        return keyPeriodo;
    }

    public void setKeyPeriodo(String keyPeriodo) {
        this.keyPeriodo = keyPeriodo;
    }

    public String getKeySeccion() {
        return keySeccion;
    }

    public void setKeySeccion(String keySeccion) {
        this.keySeccion = keySeccion;
    }
}
