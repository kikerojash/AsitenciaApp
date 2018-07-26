package com.systemvv.grupo.asitenciaapp.fire;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.systemvv.grupo.asitenciaapp.fire.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.fire.entidad.Incidencia;
import com.systemvv.grupo.asitenciaapp.utils.Constantes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
                            if(count==0){
                                postFirePostsCallback.onSuccess(false);
                                Log.d(TAG, "task.exists() "+count);
                            }else if(count>0){
                                postFirePostsCallback.onSuccess(true);
                                Log.d(TAG, "task.exists() "+count);
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
                            if(count==0){
                                listFireCallback.onSuccess(null);
                                Log.d(TAG, "task.exists() "+count);
                            }else if(count>0){
                                listFireCallback.onSuccess(asistenciaList);
                                Log.d(TAG, "task.exists() "+count);
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
        for(Asistencia asistencia : asistenciaUiList){
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


    public void registrarIncidencia(Incidencia incidencia, final FireCallback<Boolean> postFirePostsCallback ){
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
}
