package com.systemvv.grupo.asitenciaapp.fire;

import com.google.firebase.firestore.FirebaseFirestore;

public class Fire {

    protected FirebaseFirestore mFirestore;

    public Fire() {
        mFirestore = FirebaseFirestore.getInstance();
    }
}
