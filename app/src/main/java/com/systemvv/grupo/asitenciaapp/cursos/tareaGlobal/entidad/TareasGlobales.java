package com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.entidad;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

public class TareasGlobales {
    String keyAlumnos;
    String descripcionTarea;
    String fecha;
    long timeStamp;
    String keyCurso;
    String keyGrado;
    String keyInstitucion;
    String keyPeriodo;
    String keySeccion;

    public TareasGlobales() {
    }

    public String getKeyAlumnos() {
        return keyAlumnos;
    }

    public void setKeyAlumnos(String keyAlumnos) {
        this.keyAlumnos = keyAlumnos;
    }

    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public void setDescripcionTarea(String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
        result.put("keyAlumnos", keyAlumnos);
        result.put("descripcionTarea", descripcionTarea);
        result.put("keyCurso", keyCurso);
        result.put("keyGrado", keyGrado);
        result.put("keyInstitucion", keyInstitucion);
        result.put("keyPeriodo", keyPeriodo);
        result.put("keySeccion", keySeccion);
        result.put("fecha",fecha);
        result.put("timeStamp",timeStamp);
        return result;
    }
}
