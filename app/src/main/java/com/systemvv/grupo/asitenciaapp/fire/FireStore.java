package com.systemvv.grupo.asitenciaapp.fire;

import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.fire.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.fire.entidad.Incidencia;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad.SeccionUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;
import com.systemvv.grupo.asitenciaapp.utils.Constantes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
                            postFirePostsCallback.onSuccess(true);
                            Log.d(TAG, "DocumentSnapshot successfully written! : " + keyAsistencia);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Log.d(TAG, "DocumentSnapshot successfully written!");
                }
            });
        }
    }

    public void validarFechaRegistroAsistencia(String fecha, final FireCallback<Boolean> postFirePostsCallback) {
        mFirestore.collection(Constantes.NODO_ASISTENCIA)
                .whereEqualTo("asi_fecha", fecha)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count = 0;
                            for (DocumentSnapshot doc : task.getResult()) {
                                count++;
                            }
                            if (count == 0) {
                                postFirePostsCallback.onSuccess(false);
                                Log.d(TAG, "task.exists() " + count);
                            } else if (count > 0) {
                                postFirePostsCallback.onSuccess(true);
                                Log.d(TAG, "task.exists() " + count);
                            }

                           /*List<Asistencia> asistenciaList = new ArrayList<>();
                            for(DocumentSnapshot doc : task.getResult()){
                                Asistencia e = doc.toObject(Asistencia.class);
                                e.setKeyAsistencia(doc.getId());
                                asistenciaList.add(e);
                            }
                            postFirePostsCallback.onSuccess(asistenciaList);*/
                        } else {
                            postFirePostsCallback.onSuccess(false);
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void onObtenerAsistenciaLista(String fecha, final FireCallback<List<Asistencia>> listFireCallback) {
        mFirestore.collection(Constantes.NODO_ASISTENCIA)
                .whereEqualTo("asi_fecha", fecha)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count = 0;
                            List<Asistencia> asistenciaList = new ArrayList<>();
                            for (DocumentSnapshot doc : task.getResult()) {
                                Asistencia e = doc.toObject(Asistencia.class);
                                e.setAsi_id_asistencia(doc.getId());
                                asistenciaList.add(e);
                                count++;
                            }
                            if (count == 0) {
                                listFireCallback.onSuccess(null);
                                Log.d(TAG, "task.exists() " + count);
                            } else if (count > 0) {
                                listFireCallback.onSuccess(asistenciaList);
                                Log.d(TAG, "task.exists() " + count);
                            }

                           /*List<Asistencia> asistenciaList = new ArrayList<>();
                            for(DocumentSnapshot doc : task.getResult()){
                                Asistencia e = doc.toObject(Asistencia.class);
                                e.setKeyAsistencia(doc.getId());
                                asistenciaList.add(e);
                            }
                            postFirePostsCallback.onSuccess(asistenciaList);*/
                        }
                    }
                });
    }

    public void guardarListaAsistenciaHoraFin(List<Asistencia> asistenciaUiList, final FireCallback<Boolean> postFirePostsCallback) {
        DateFormat dfHours = new SimpleDateFormat("HH:mm aaa");
        String horaInicioCurso = dfHours.format(Calendar.getInstance().getTime());
        for (Asistencia asistencia : asistenciaUiList) {
            DocumentReference docRef = mFirestore.collection(Constantes.NODO_ASISTENCIA).document(asistencia.getAsi_id_asistencia());
            Map<String, Object> updates = new HashMap<>();
            updates.put("asi_hora_fin", horaInicioCurso);
            docRef.update(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        postFirePostsCallback.onSuccess(true);
                    } else {
                        postFirePostsCallback.onSuccess(false);
                    }
                }
            });
        }
    }


    public void registrarIncidencia(Incidencia incidencia, final FireCallback<Boolean> postFirePostsCallback) {
        Map<String, Object> postValues = incidencia.toMap();
        mFirestore.collection(Constantes.NODO_INCIDENCIA)
                .add(postValues)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        String keyIncidencia = documentReference.getId();
                        Log.d(TAG, "DocumentSnapshot successfully written! : " + keyIncidencia);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "DocumentSnapshot successfully written!");
            }
        });
    }

    public void onObtenerListaInstituto(String keyUser, final FireCallback<List<InstitutoUi>> listFireCallback) {
        mFirestore.collection(Constantes.NODO_INSTITUTO)
                .whereEqualTo("keyDocente", keyUser)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<InstitutoUi> institutoUiList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                InstitutoUi institutoUi = new InstitutoUi();
                                String nombre = (String) document.get("ins_nombre");
                                String foto = (String) document.get("ins_foto");
                                String direccion = (String) document.get("ins_direccion");
                                String distrito = (String) document.get("ins_distrito");


                                institutoUi.setNombre(nombre);
                                institutoUi.setImage(foto);
                                institutoUi.setDireccion(direccion);
                                institutoUi.setCede(distrito);
                                institutoUiList.add(institutoUi);
                            }
                            listFireCallback.onSuccess(institutoUiList);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void onObtenerListaSeccionGrado(String keyUser, final FireCallback<List<SeccionUi>> listFireCallback) {
        mFirestore.collection(Constantes.NODO_SECCION_GRADO)
                .whereEqualTo("pro_id_profesor", keyUser)
                .whereEqualTo("prd_id_periodo", "18")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<SeccionUi> seccionUiList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                SeccionUi seccionUi = new SeccionUi();
                                String grado_nombre = (String) document.get("grado_nombre");
                                String seccion_nombre = (String) document.get("seccion_nombre");
                                // seccionUi.setGradoySeccion("Grado: "+grado_nombre+" "+"Seccion:"+seccion_nombre);
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
        mFirestore.collection(Constantes.NODO_SECCION_GRADO)
                .whereEqualTo("pro_id_profesor", keyUser)
                .whereEqualTo("prd_id_periodo", "18")
                .whereEqualTo("grado_nombre", grado)
                .whereEqualTo("seccion_nombre", seccion)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<CursoUi> cursoUiList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                String curso_nombre = (String) document.get("curso_nombre");
                                /*String grado_nombre = (String) document.get("grado_nombre");
                                String seccion_nombre = (String) document.get("seccion_nombre");*/

                                CursoUi cursoUi = new CursoUi();
                                cursoUi.setNombre(curso_nombre);
                                cursoUi.setGradoSelected(Integer.parseInt(seccionUi.getGrado()));
                                cursoUi.setSeccionSelected(seccionUi.getSeccion());
                                cursoUi.setInstitutoUi(seccionUi.getInstitutoUi());
                               /* seccionUi.setGrado(grado_nombre);
                                seccionUi.setSeccion(seccion_nombre);*/

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
}
