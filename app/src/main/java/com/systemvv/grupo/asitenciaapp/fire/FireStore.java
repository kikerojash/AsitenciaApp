package com.systemvv.grupo.asitenciaapp.fire;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Alumnos;
import com.systemvv.grupo.asitenciaapp.asistencia.fireListener.FireCallBackList;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.entidad.TareasGlobales;
import com.systemvv.grupo.asitenciaapp.fire.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.fire.entidad.Incidencia;
import com.systemvv.grupo.asitenciaapp.login.dataSource.entidad.UsuarioUi;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Hijos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Incidencias;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Instituto;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Tareas;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad.SeccionUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;
import com.systemvv.grupo.asitenciaapp.utils.Constantes;
import com.systemvv.grupo.asitenciaapp.utils.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class FireStore extends Fire {

    public static final String TAG = FireStore.class.getSimpleName();


    public void guardarListaAsistenciaAlumnos(List<Asistencia> asistenciaUiList, final FireCallback<Boolean> postFirePostsCallback) {
        Log.d(TAG, "guardarListaAsistenciaAlumnos");

        for (Asistencia asistencia : asistenciaUiList) {
            Map<String, Object> postValues = asistencia.toMap();
            mFirestore.collection(Constantes.NODO_ASISTENCIA)
                    .add(postValues)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            String keyAsistencia = documentReference.getId();
                            // postFirePostsCallback.onSuccess(true);
                            Log.d(TAG, "DocumentSnapshot successfully written! : " + keyAsistencia);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Log.d(TAG, "DocumentSnapshot successfully written!");
                }
            });
            continue;
        }
        postFirePostsCallback.onSuccess(true);
    }

    public void validarFechaRegistroAsistencia(String fecha, CursoUi cursoUi, final FireCallBackList<Boolean> postFirePostsCallback) {
        String grado = String.valueOf(cursoUi.getGradoSelected());
        String seccion = cursoUi.getSeccionSelected();
        String keyGrado = cursoUi.getKeyGrado();
        String keySeccion = cursoUi.getKeySeccion();
        String keyPeriodo = cursoUi.getInstitutoUi().getKeyPeriodo();
        String keyCurso = cursoUi.getKeyCurso();
        String keyInstituto = cursoUi.getInstitutoUi().getKeyInstituto();
        mFirestore.collection(Constantes.NODO_ASISTENCIA)
                .whereEqualTo("asi_fecha", fecha)
                .whereEqualTo("cur_id_curso", keyCurso)
                .whereEqualTo("gra_id_grado", keyGrado)
                .whereEqualTo("ins_id_institucion", keyInstituto)
                .whereEqualTo("prd_id_periodo", keyPeriodo)
                .whereEqualTo("sec_id_seccion", keySeccion)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count = 0;
                            List<String> asistenciaList = new ArrayList<>();
                            List<String> registroHoraFinList = new ArrayList<>();
                            for (DocumentSnapshot doc : task.getResult()) {
                               /*  Timestamp timestamp = doc.getTimestamp("created_at");
                                Date date = timestamp.toDate();*/
                                /*Timestamp timestamp = doc.getTimestamp("asi_hora_fin");
                                java.util.Date date = timestamp.toDate();*/

                                String horaFin = (String) doc.get("asi_hora_fin");

                                asistenciaList.add(doc.getId());
                                registroHoraFinList.add(horaFin + "");
                                Log.d(TAG, "task.doc() ");
                                count++;
                            }
                            if (count == 0) {
                                postFirePostsCallback.onSuccess(false, null, Constantes.FALTA_ASISTENCIA_REGISTRO_HOY);
                                Log.d(TAG, "task.exists() " + count);
                            } else if (registroHoraFinList != null) {
                                for (String string : registroHoraFinList) {
                                    Log.d(TAG, "stringHOraFIN" + string);
                                    if (string.equals("null") || string == null) {
                                        postFirePostsCallback.onSuccess(true, asistenciaList, Constantes.FALTA_ASISTENCIA_HORA_CIERRE);
                                        break;
                                    } else {
                                        postFirePostsCallback.onSuccess(true, asistenciaList, Constantes.REGISTROS_COMPLETOS);
                                        break;
                                    }
                                    //  Log.d(TAG, "stringHOraFIN" + string);
                                }

                            } else if (count > 0) {

                                Log.d(TAG, "task.exists() " + count);
                                postFirePostsCallback.onSuccess(true, asistenciaList, Constantes.FALTA_ASISTENCIA_HORA_CIERRE);
                                Log.d(TAG, "task.exists() " + count);
                            }
                            // Log.d(TAG, "task.isSuccessful()");
                        } else {
                            postFirePostsCallback.onSuccess(false, null, Constantes.FALTA_ASISTENCIA_REGISTRO_HOY);
                            //Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


    public void guardarListaAsistenciaHoraFin(List<String> asistenciaUiList, final FireCallback<Boolean> postFirePostsCallback) {
        DateFormat dfHours = new SimpleDateFormat("HH:mm aaa");
        String horaInicioCurso = dfHours.format(Calendar.getInstance().getTime());
        for (String asistencia : asistenciaUiList) {
            DocumentReference docRef = mFirestore.collection(Constantes.NODO_ASISTENCIA).document(asistencia);
            Map<String, Object> updates = new HashMap<>();
            updates.put("asi_hora_fin", horaInicioCurso);
            docRef.update(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        //  postFirePostsCallback.onSuccess(true);
                    } else {
                        postFirePostsCallback.onSuccess(false);
                    }
                }
            });
            continue;
        }
        postFirePostsCallback.onSuccess(true);
    }


    public void registrarIncidencia(Incidencia incidencia, final FireCallback<Boolean> postFirePostsCallback) {
        Map<String, Object> postValues = incidencia.toMap();
        mFirestore.collection(Constantes.NODO_INCIDENCIA)
                .add(postValues)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        String keyIncidencia = documentReference.getId();
                        postFirePostsCallback.onSuccess(true);
                        Log.d(TAG, "DocumentSnapshot successfully written! : " + keyIncidencia);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "DocumentSnapshot successfully written!");
            }
        });
    }

    public void onObtenerAlumno(String keyAlumno, final FireCallback<Alumnos> alumnosFireCallback) {
        Log.d(TAG, "onObtenerAlumno: " + keyAlumno);
        DocumentReference docRef = mFirestore.collection(Constantes.NODO_ALUMNOS).document(keyAlumno);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Alumnos alumno = new Alumnos();
                        String nombre = (String) document.get("per_nombre");
                        String apellido = (String) document.get("per_apellido");
                        String foto = (String) document.get("per_foto");
                        String padecimiento = (String) document.get("alu_padecimiento");
                        alumno.setNombre(nombre);
                        alumno.setApellido(apellido);
                        alumno.setFoto(foto);
                        alumno.setPadecimiento(padecimiento);
                        alumnosFireCallback.onSuccess(alumno);
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    public void onObtenerListaInstituto(final String keyUser, final FireCallback<List<InstitutoUi>> listFireCallback) {

        DocumentReference docRef = mFirestore.collection(Constantes.NODO_INSTITUCION)
                .document("1801");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    listFireCallback.onSuccess(null);
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }
                List<InstitutoUi> institutoUiList = new ArrayList<>();
                if (snapshot != null && snapshot.exists()) {
                    String fotoInstituto = (String) snapshot.get("ins_foto");
                    String nombreInstituo = (String) snapshot.get("ins_nombre");
                    String direccionInstituto = (String) snapshot.get("ins_direccion");
                    String distritoInstituto = (String) snapshot.get("ins_distrito");
                    InstitutoUi institutoUi = new InstitutoUi();
                    institutoUi.setKeyInstituto(snapshot.getId());
                    institutoUi.setNombre(nombreInstituo);
                    institutoUi.setImage(fotoInstituto);
                    institutoUi.setDireccion(direccionInstituto);
                    institutoUi.setCede(distritoInstituto);
                    institutoUi.setKeyPeriodo(keyUser); /*llegaria Hacer keyPeriodo*/
                    institutoUiList.add(institutoUi);
                    listFireCallback.onSuccess(institutoUiList);
                } else {
                    listFireCallback.onSuccess(null);
                    Log.d(TAG, "Current data: null");
                }
            }
        });

    }

    public void onObtenerListaSeccionGrado(String keyPeriodo, String keyProfesor, final FireCallback<List<SeccionUi>> listFireCallback) {
        mFirestore.collection(Constantes.NODO_PERIODO_GRADO_CURSO)
                .whereEqualTo("pro_id_profesor", keyProfesor)
                .whereEqualTo("prd_id_periodo", keyPeriodo)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<SeccionUi> seccionUiList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                SeccionUi seccionUi = new SeccionUi();
                                String keySeccion = (String) document.get("sec_id_seccion");
                                String keyGrado = (String) document.get("gra_id_grado");
                                String grado_nombre = (String) document.get("gra_nombre");
                                String seccion_nombre = (String) document.get("sec_nombre");
                                seccionUi.setKeyGrado(keyGrado);
                                seccionUi.setKeySeccion(keySeccion);
                                seccionUi.setGrado(grado_nombre);
                                seccionUi.setSeccion(seccion_nombre);
                                seccionUiList.add(seccionUi);
                            }


                            List<SeccionUi> seccionUiList2 = new ArrayList<>(new LinkedHashSet<>(seccionUiList));


                            listFireCallback.onSuccess(seccionUiList2);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


    public void onObtenerListaCursos(final SeccionUi seccionUi, final FireCallback<List<CursoUi>> listFireCallback) {
        String keyUser = seccionUi.getInstitutoUi().getKeyUsuario();
        String grado = seccionUi.getGrado();
        String seccion = seccionUi.getSeccion();
        final String keyGrado = seccionUi.getKeyGrado();
        final String keySeccion = seccionUi.getKeySeccion();
        String keyPeriodo = seccionUi.getInstitutoUi().getKeyPeriodo();
        mFirestore.collection(Constantes.NODO_PERIODO_GRADO_CURSO)
                .whereEqualTo("pro_id_profesor", keyUser)
                .whereEqualTo("prd_id_periodo", keyPeriodo)
                .whereEqualTo("gra_nombre", grado)
                .whereEqualTo("sec_nombre", seccion)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<CursoUi> cursoUiList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                String curso_nombre = (String) document.get("cur_nombre");
                                String cur_id_curso = (String) document.get("cur_id_curso");
                                String horaInicio = (String) document.get("pgc_hora_inicio");
                                String horaFin = (String) document.get("pgc_hora_fin");
                                String conteoAlumnos = (String) document.get("pgc_cantidad_alumno");
                                String fotoCurso = (String) document.get("cur_foto");
                                CursoUi cursoUi = new CursoUi();
                                cursoUi.setKeyCurso(cur_id_curso);
                                cursoUi.setNombre(curso_nombre);
                                cursoUi.setGradoSelected(Integer.parseInt(seccionUi.getGrado()));
                                cursoUi.setSeccionSelected(seccionUi.getSeccion());
                                cursoUi.setSeccionUi(seccionUi);
                                cursoUi.setInstitutoUi(seccionUi.getInstitutoUi());
                                cursoUi.setHoraInicio(horaInicio);
                                cursoUi.setHoraFin(horaFin);
                                cursoUi.setKeyGrado(keyGrado);
                                cursoUi.setKeySeccion(keySeccion);
                                cursoUi.setFoto(fotoCurso);
                                cursoUi.setConteoAlumnos(conteoAlumnos);
                                cursoUiList.add(cursoUi);
                            }
                            listFireCallback.onSuccess(cursoUiList);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                            listFireCallback.onSuccess(null);
                        }
                    }
                });
    }

    public void onObtenerListaAlumnos(final CursoUi cursoUi, final FireCallback<List<Alumnos>> listFireCallback) {
        final String keyCurso = cursoUi.getKeyCurso();
        final String keyPeriodo = cursoUi.getInstitutoUi().getKeyPeriodo();
        final String keyGrado = cursoUi.getKeyGrado();
        final String keyInstitucion = cursoUi.getInstitutoUi().getKeyInstituto();
        final String keySeccion = cursoUi.getKeySeccion();
        String grado = String.valueOf(cursoUi.getGradoSelected());
        String seccion = cursoUi.getSeccionSelected();
        Log.d(TAG, "keyCurso/" + keyCurso + "/" + keyPeriodo + "/" + grado + "/" + seccion);
        mFirestore.collection(Constantes.NODO_PERIODO_GRADO_ALUMNO)
                //.whereEqualTo("cur_id_curso", keyCurso)
                .whereEqualTo("prd_id_periodo", keyPeriodo)
                .whereEqualTo("gra_nombre", grado)
                .whereEqualTo("sec_nombre", seccion)
                .get()
                .continueWithTask(new Continuation<QuerySnapshot, Task<List<DocumentSnapshot>>>() {
                    @Override
                    public Task<List<DocumentSnapshot>> then(@NonNull Task<QuerySnapshot> task) {
                        List<Task<DocumentSnapshot>> tasks = new ArrayList<Task<DocumentSnapshot>>();
                        for (DocumentSnapshot document : task.getResult()) {
                            String keyAlumno = (String) document.get("alu_id_alumno");
                            Log.d(TAG, "continueWithTask " + keyAlumno);
                            Task<DocumentSnapshot> querySnapshotTask = mFirestore.
                                    collection("alumno")
                                    .document(keyAlumno)
                                    .get();
                            tasks.add(querySnapshotTask);
                        }
                        return Tasks.whenAllSuccess(tasks);
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<List<DocumentSnapshot>>() {
                    @Override
                    public void onComplete(@NonNull Task<List<DocumentSnapshot>> task) {
                        List<Alumnos> alumnosList = new ArrayList<>();

                        for (DocumentSnapshot documentSnapshot : task.getResult()) {
                            Alumnos alumnos = new Alumnos();
                            String per_nombre = (String) documentSnapshot.get("per_nombre");
                            String per_apellido = (String) documentSnapshot.get("per_apellido");
                            String per_foto = (String) documentSnapshot.get("per_foto");
                            String alu_padecimiento = (String) documentSnapshot.get("alu_padecimiento");
                            Log.d(TAG, "onCompletecontinueWithTask " + documentSnapshot.getData() + "" +
                                    "/ Data " + documentSnapshot.getId() +
                                    "/ Reference PAth" + documentSnapshot.getReference().getPath() +
                                    "/ CollectionReference " + documentSnapshot.getReference().getParent() +
                                    "/ CollectionReference " + documentSnapshot.getReference().get());
                            alumnos.setKeyAlumno(documentSnapshot.getId());
                            alumnos.setNombre(per_nombre);
                            alumnos.setApellido(per_apellido);
                            alumnos.setFoto(per_foto);
                            alumnos.setKeyGrado(keyGrado);
                            alumnos.setKeyInstitucion(keyInstitucion);
                            alumnos.setKeyPeriodo(keyPeriodo);
                            alumnos.setKeySeccion(keySeccion);
                            alumnos.setKeyCurso(keyCurso);
                            alumnos.setPadecimiento(alu_padecimiento);
                            alumnos.setAsistencia(asistenciaPresente(alumnos));
                            alumnosList.add(alumnos);
                        }

                        listFireCallback.onSuccess(alumnosList);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listFireCallback.onSuccess(null);
            }
        });
    }


    public void onObtenerDatosDocente(String keyDocente, final FireCallbackTwo<String, String> stringFireCallback) {
        DocumentReference docRef = mFirestore.collection(Constantes.NODO_PROFESOR)
                .document(keyDocente);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    stringFireCallback.resultado(null, null);
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    String fotoDocente = (String) snapshot.get("per_foto");
                    String nombreDocente = (String) snapshot.get("per_nombre");
                    stringFireCallback.resultado(fotoDocente, nombreDocente);
                } else {
                    stringFireCallback.resultado(null, null);
                    Log.d(TAG, "Current data: null");
                }
            }
        });
    }

    public void onObtenerListaAlumnosTareasGlobales(final CursoUi cursoUi, final FireCallback<List<TareasGlobales>> listFireCallback) {
        final String keyCurso = cursoUi.getKeyCurso();
        final String keyPeriodo = cursoUi.getInstitutoUi().getKeyPeriodo();
        final String keyGrado = cursoUi.getKeyGrado();
        final String keyInstitucion = cursoUi.getInstitutoUi().getKeyInstituto();
        final String keySeccion = cursoUi.getKeySeccion();
        String grado = String.valueOf(cursoUi.getGradoSelected());
        String seccion = cursoUi.getSeccionSelected();
        Log.d(TAG, "keyCurso/" + keyCurso + "/" + keyPeriodo + "/" + grado + "/" + seccion);
        mFirestore.collection(Constantes.NODO_PERIODO_GRADO_ALUMNO)
                //.whereEqualTo("cur_id_curso", keyCurso)
                .whereEqualTo("prd_id_periodo", keyPeriodo)
                .whereEqualTo("gra_nombre", grado)
                .whereEqualTo("sec_nombre", seccion)
                .get()
                .continueWithTask(new Continuation<QuerySnapshot, Task<List<DocumentSnapshot>>>() {
                    @Override
                    public Task<List<DocumentSnapshot>> then(@NonNull Task<QuerySnapshot> task) {
                        List<Task<DocumentSnapshot>> tasks = new ArrayList<Task<DocumentSnapshot>>();
                        for (DocumentSnapshot document : task.getResult()) {
                            String keyAlumno = (String) document.get("alu_id_alumno");
                            Log.d(TAG, "continueWithTask " + keyAlumno);
                            Task<DocumentSnapshot> querySnapshotTask = mFirestore.
                                    collection("alumno")
                                    .document(keyAlumno)
                                    .get();
                            tasks.add(querySnapshotTask);
                        }
                        return Tasks.whenAllSuccess(tasks);
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<List<DocumentSnapshot>>() {
                    @Override
                    public void onComplete(@NonNull Task<List<DocumentSnapshot>> task) {
                        List<TareasGlobales> tareasGlobalesList = new ArrayList<>();

                        for (DocumentSnapshot documentSnapshot : task.getResult()) {
                            TareasGlobales tareasGlobales = new TareasGlobales();
                            String per_nombre = (String) documentSnapshot.get("per_nombre");
                            String per_apellido = (String) documentSnapshot.get("per_apellido");
                            String per_foto = (String) documentSnapshot.get("per_foto");
                            String alu_padecimiento = (String) documentSnapshot.get("alu_padecimiento");
                            Log.d(TAG, "onCompletecontinueWithTask " + documentSnapshot.getData() + "" +
                                    "/ Data " + documentSnapshot.getId() +
                                    "/ Reference PAth" + documentSnapshot.getReference().getPath() +
                                    "/ CollectionReference " + documentSnapshot.getReference().getParent() +
                                    "/ CollectionReference " + documentSnapshot.getReference().get());

                            tareasGlobales.setKeyAlumnos(documentSnapshot.getId());
                            tareasGlobales.setKeyCurso(keyCurso);
                            tareasGlobales.setKeyGrado(keyGrado);
                            tareasGlobales.setKeyInstitucion(keyInstitucion);
                            tareasGlobales.setKeyPeriodo(keyPeriodo);
                            tareasGlobales.setKeySeccion(keySeccion);
                            tareasGlobalesList.add(tareasGlobales);
                        }

                        listFireCallback.onSuccess(tareasGlobalesList);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listFireCallback.onSuccess(null);
            }
        });
    }


    public void guardarListaAsistenciaAlumnosTareaGlobales(List<TareasGlobales> tareasGlobalesList, final FireCallback<Boolean> postFirePostsCallback) {
        Log.d(TAG, "guardarListaAsistenciaAlumnos");

          for (TareasGlobales tareasGlobales : tareasGlobalesList) {
            Map<String, Object> postValues = tareasGlobales.toMap();
            mFirestore.collection("actividades")
                    .add(postValues)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            String keyActividades = documentReference.getId();
                            // postFirePostsCallback.onSuccess(true);
                            Log.d(TAG, "DocumentSnapshot successfully written! : " + keyActividades);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Log.d(TAG, "DocumentSnapshot successfully written!");
                }
            });
            continue;
        }
        postFirePostsCallback.onSuccess(true);
    }


    private com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia asistenciaPresente(Alumnos alumnos) {
        com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia asistencia = new com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia();
        asistencia.setJustificacion(1);
        ///asistencia.setMotivosAsistenciaUi(motivosAsistenciaPuntual);
        asistencia.setPintar(true);
        asistencia.setTipoAsistencia("Puntual");
        asistencia.setAlumnosUi(alumnos);
        return asistencia;
    }


    public void onValidarPeriodo(final FireCallback<String> stringFireCallback) {
        DocumentReference docRef = mFirestore.collection(Constantes.NODO_PERIODO)
                .document("1814");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    stringFireCallback.onSuccess(null);
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    String keyPeriodo = (String) snapshot.get("prd_id_periodo");
                    String tipoEstadoPeriodo = (String) snapshot.get("est_id_estado");
                    if (tipoEstadoPeriodo.equals(Constantes.PERIODO_INICIADO)) {
                        Log.d(TAG, "Current data: " + snapshot.getData() + " / tipoEstadoPeriodo " + tipoEstadoPeriodo);
                        stringFireCallback.onSuccess(keyPeriodo);
                    } else {
                        stringFireCallback.onSuccess(null);
                    }

                } else {
                    stringFireCallback.onSuccess(null);
                    Log.d(TAG, "Current data: null");
                }
            }
        });

    }

    public void onValidarTipoUsuario(String email, final FireCallback<UsuarioUi> usuarioUiFireCallback) {
        mFirestore.collection(Constantes.NODO_USUARIO)
                .whereEqualTo("usu_email", email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // String tipoUsuario = "";

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String per_apellido = (String) document.get("per_apellido");
                                String per_nombre = (String) document.get("per_nombre");
                                String tip_usuario = (String) document.get("tip_usuario");
                                String tip_usuario_nombre = (String) document.get("tip_usuario_nombre");
                                String usu_email = (String) document.get("usu_email");
                                UsuarioUi usuarioUi = new UsuarioUi();
                                usuarioUi.setKeyUser(document.getId());
                                usuarioUi.setPer_apellido(per_apellido);
                                usuarioUi.setPer_nombre(per_nombre);
                                usuarioUi.setTip_usuario(tip_usuario);
                                usuarioUi.setTip_usuario_nombre(tip_usuario_nombre);
                                usuarioUi.setUsu_email(usu_email);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                usuarioUiFireCallback.onSuccess(usuarioUi);
                            }

                        } else {
                            usuarioUiFireCallback.onSuccess(null);
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


    /*Rol - Padre */
    public void onObtenerListaHijos(final UsuarioUi usuarioUi, final FireCallback<List<Hijos>> listFireCallback) {
        final String keyUser = usuarioUi.getKeyUser();

        Log.d(TAG, "keyUser /" + keyUser);

        mFirestore.collection(Constantes.NODO_APODERADO_HAS_ALUMNO)
                .whereEqualTo("apo_id_apoderado", keyUser)
                .get()
                .continueWithTask(new Continuation<QuerySnapshot, Task<List<QuerySnapshot>>>() {
                    @Override
                    public Task<List<QuerySnapshot>> then(@NonNull Task<QuerySnapshot> task) {

                        List<Task<QuerySnapshot>> tasks = new ArrayList<Task<QuerySnapshot>>();
                        for (DocumentSnapshot document : task.getResult()) {
                            String keyAlumno = (String) document.get("alu_id_alumno");
                            Log.d(TAG, "continueWithTask " + keyAlumno);
                            Task<QuerySnapshot> querySnapshotTask = mFirestore.
                                    collection("periodo_grado_alumno")
                                    .whereEqualTo("alu_id_alumno", keyAlumno)
                                    .get();

                            tasks.add(querySnapshotTask);
                        }
                        return Tasks.whenAllSuccess(tasks);
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<List<QuerySnapshot>>() {
                    @Override
                    public void onComplete(@NonNull Task<List<QuerySnapshot>> task) {
                        List<Hijos> hijosList = new ArrayList<>();


                        for (QuerySnapshot qs : task.getResult()) {
                            for (DocumentSnapshot documentSnapshot : qs) {
                                Hijos hijos = new Hijos();
                                //Log.d(TAG, "Documentos : " + ds.get("alu_nombrecompleto"));
                                String id_alumno = (String) documentSnapshot.get("alu_id_alumno");
                                String nombre_completo = (String) documentSnapshot.get("alu_nombrecompleto");
                                String apellidoHijo = (String) documentSnapshot.get("per_apellido");
                                String sec_nombre = (String) documentSnapshot.get("sec_nombre");
                                String gra_nombre = (String) documentSnapshot.get("gra_nombre");
                                String keyGrado = (String) documentSnapshot.get("gra_id_grado");
                                String keySeccion = (String) documentSnapshot.get("sec_id_seccion");
                                String fotoHijos = (String) documentSnapshot.get("per_foto");
                                String edadHijo = (String) documentSnapshot.get("alu_edad");
                                String dniHijo = (String) documentSnapshot.get("alu_codigo");

                                hijos.setId(id_alumno);

                                hijos.setNombre(nombre_completo);
                                hijos.setApellido(apellidoHijo);
                                hijos.setFoto(fotoHijos);
                                hijos.setEdad(edadHijo);
                                hijos.setDni(dniHijo);

                                hijos.setGrado(gra_nombre);
                                hijos.setSeccion(sec_nombre);
                                hijos.setKeyGrado(keyGrado);
                                hijos.setKeySeccion(keySeccion);
                                hijosList.add(hijos);
                            }
                        }
                        listFireCallback.onSuccess(hijosList);

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listFireCallback.onSuccess(null);
            }
        });
    }


    public void onObtenerInstituto(UsuarioUi usuarioUi, final FireCallback<Instituto> institutoFireCallback) {

        DocumentReference docRef = mFirestore.collection(Constantes.NODO_INSTITUCION).document("1801");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //String keyInstituto = (String)document.get("ins_nombre");
                        String nombreInstituto = (String) document.get("ins_nombre");
                        Instituto instituto = new Instituto();
                        instituto.setKeyInstituto(document.getId());
                        instituto.setNombreInstituto(nombreInstituto);
                        institutoFireCallback.onSuccess(instituto);
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        institutoFireCallback.onSuccess(null);
                        Log.d(TAG, "No such document");
                    }
                } else {
                    institutoFireCallback.onSuccess(null);
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    public void onObtenerListaCursosHijos(final Hijos hijos, final FireCallback<List<Cursos>> listFireCallback) {
        String keyGrado = hijos.getKeyGrado();
        String keySeccion = hijos.getKeySeccion();

        final String keyAlumno = hijos.getId();

        mFirestore.collection(Constantes.NODO_PERIODO_GRADO_CURSO)
                .whereEqualTo("gra_id_grado", keyGrado)
                .whereEqualTo("sec_id_seccion", keySeccion)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // String tipoUsuario = "";
                            final List<Cursos> cursosList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String cur_id_curso = (String) document.get("cur_id_curso");
                                String cur_nombre = (String) document.get("cur_nombre");
                                String pro_id_profesor = (String) document.get("pro_id_profesor");
                                String pro_nombrecompleto = (String) document.get("pro_nombrecompleto");
                                String cur_foto = (String) document.get("cur_foto");
                                String gra_id_grado = (String) document.get("gra_id_grado");
                                String sec_id_seccion = (String) document.get("sec_id_seccion");

                                final Cursos cursos = new Cursos();
                                cursos.setId(cur_id_curso);
                                cursos.setNombreCurso(cur_nombre);
                                cursos.setNombreProfesor(pro_nombrecompleto);
                                cursos.setFotoCurso(cur_foto);
                                cursos.setHijos(hijos);


                                mFirestore.collection(Constantes.NODO_INCIDENCIA)
                                        .whereEqualTo("alu_id_alumno", keyAlumno)
                                        .whereEqualTo("cur_id_curso", cur_id_curso)
                                        .whereEqualTo("gra_id_grado", gra_id_grado)
                                        .whereEqualTo("sec_id_seccion", sec_id_seccion)
                                        .whereEqualTo("estadoActivo", true)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, "querySnapshotTask " + task.getResult().size());
                                                    cursos.setCountIncidencias(task.getResult().size());
                                                    cursosList.add(cursos);
                                                    listFireCallback.onSuccess(cursosList);
                                                } else {
                                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                                }
                                            }
                                        });


                                 /*cursos.setIncidenciasList(incidenciasListCursoHijos(keyAlumno,
                                        cur_id_curso,
                                        gra_id_grado,
                                        sec_id_seccion));*/


//                                cursosList.add(cursos);


                            }
                            //listFireCallback.onSuccess(cursosList);
                        } else {
                            listFireCallback.onSuccess(null);
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }


    public void onMostrarListaReporteAsistencia(Cursos cursos, final FireCallback<List<com.systemvv.grupo.asitenciaapp.padre.entidad.Asistencia>> listFireCallback) {
        String keyAlumno = cursos.getHijos().getId();
        String keyCurso = cursos.getId();
        String keyGrado = cursos.getHijos().getKeyGrado();
        String keySeccion = cursos.getHijos().getKeySeccion();
        Log.d(TAG, "Institucion ;" + cursos.getHijos().getNombreInstituto()
                + "keyCurso" + keyCurso
                + "keyGrado" + keyGrado
                + "keySeccion" + keySeccion
                + "keyAlumno" + keyAlumno);

        mFirestore.collection(Constantes.NODO_ASISTENCIA)
                .whereEqualTo("alu_id_alumno", keyAlumno)
                .whereEqualTo("cur_id_curso", keyCurso)
                .whereEqualTo("gra_id_grado", keyGrado)
                .whereEqualTo("sec_id_seccion", keySeccion)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count = 0;
                            List<com.systemvv.grupo.asitenciaapp.padre.entidad.Asistencia> asistenciaList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                count++;
                                String fecha = (String) document.get("asi_fecha");
                                String horaInicio = (String) document.get("asi_hora_inicio");
                                String horaFin = (String) document.get("asi_hora_fin");
                                String asi_tipo_asistencia = (String) document.get("asi_tipo_asistencia");
                                com.systemvv.grupo.asitenciaapp.padre.entidad.Asistencia asistencia = new com.systemvv.grupo.asitenciaapp.padre.entidad.Asistencia();
                                asistencia.setConteo(count);
                                asistencia.setFecha(fecha);
                                asistencia.setInicioRegistroHora(horaInicio);
                                asistencia.setFinRegistroHora(horaFin);
                                asistencia.setTipASistencia(asi_tipo_asistencia);
                                asistenciaList.add(asistencia);

                            }

                            listFireCallback.onSuccess(asistenciaList);
                        } else {
                            listFireCallback.onSuccess(null);
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void onMostrarListaIncidencia(Cursos cursos, final FireCallback<List<Incidencias>> listFireCallback) {
        String keyAlumno = cursos.getHijos().getId();
        String keyCurso = cursos.getId();
        String keyGrado = cursos.getHijos().getKeyGrado();
        String keySeccion = cursos.getHijos().getKeySeccion();
        Log.d(TAG, "Institucion ;" + cursos.getHijos().getNombreInstituto()
                + "keyCurso" + keyCurso
                + "keyGrado" + keyGrado
                + "keySeccion" + keySeccion
                + "keyAlumno" + keyAlumno);

        mFirestore.collection(Constantes.NODO_INCIDENCIA)
                .whereEqualTo("alu_id_alumno", keyAlumno)
                .whereEqualTo("cur_id_curso", keyCurso)
                .whereEqualTo("gra_id_grado", keyGrado)
                .whereEqualTo("sec_id_seccion", keySeccion)
                .whereEqualTo("estadoActivo", true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count = 0;
                            List<Incidencias> incidenciasList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                count++;
                                String fecha = (String) document.get("inc_fecha");
                                long timeStamp = (long) document.get("timeStamp");
                                String inc_prioridad = (String) document.get("inc_prioridad");
                                String inc_descripcion = (String) document.get("inc_descripcion");
                                Incidencias incidencias = new Incidencias();
                                incidencias.setKeyIncidencia(document.getId());
                                incidencias.setConteo(count);
                                incidencias.setFecha(fecha);
                                incidencias.setNombreIncidencias(inc_descripcion);
                                incidencias.setHora(Utils.convertTime(timeStamp));
                                incidencias.setTipoIncidencia(inc_prioridad);
                                incidenciasList.add(incidencias);
                            }
                            listFireCallback.onSuccess(incidenciasList);
                        } else {
                            listFireCallback.onSuccess(null);
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void onEliminarIncidencia(Incidencias incidencias, final FireCallback<Boolean> booleanFireCallback) {
        String keyIncidencia = incidencias.getKeyIncidencia();
        DocumentReference washingtonRef = mFirestore.collection(Constantes.NODO_INCIDENCIA).document(keyIncidencia);
        // Set the "isCapital" field of the city 'DC'
        washingtonRef
                .update("estadoActivo", false)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        booleanFireCallback.onSuccess(true);
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        booleanFireCallback.onSuccess(false);
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }


    public void onMostrarListaTareas(Cursos cursos, final FireCallback<List<Tareas>> listFireCallback) {
        String keyAlumno = cursos.getHijos().getId();
        String keyCurso = cursos.getId();
        String keyGrado = cursos.getHijos().getKeyGrado();
        String keySeccion = cursos.getHijos().getKeySeccion();
        Log.d(TAG, "Institucion ;" + cursos.getHijos().getNombreInstituto()
                + "keyCurso" + keyCurso
                + "keyGrado" + keyGrado
                + "keySeccion" + keySeccion
                + "keyAlumno" + keyAlumno);


        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(Calendar.getInstance().getTime());

        mFirestore.collection(Constantes.NODO_ACTIVIDADES)
                .whereEqualTo("keyAlumnos", keyAlumno)
                .whereEqualTo("keyCurso", keyCurso)
                .whereEqualTo("keyGrado", keyGrado)
                .whereEqualTo("keySeccion", keySeccion)
                .whereEqualTo("fecha",date)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count = 0;
                            List<Tareas> tareasList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                count++;
                                String fecha = (String) document.get("fecha");
                                long timeStamp = (long) document.get("timeStamp");

                                String descripcionTarea = (String) document.get("descripcionTarea");

                                Tareas tareas = new Tareas();
                                tareas.setKeyTareas(document.getId());
                                tareas.setConteo(count);
                                tareas.setFecha(fecha);
                                tareas.setDescripcionTarea(descripcionTarea);
                                tareas.setHora(Utils.convertTime(timeStamp));

                                tareasList.add(tareas);
                            }
                            listFireCallback.onSuccess(tareasList);
                        } else {
                            listFireCallback.onSuccess(null);
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
