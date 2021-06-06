package com.example.codefestfinalapplication.Database;

import com.google.firebase.firestore.FirebaseFirestore;

public class Store {
    private static FirebaseFirestore store;


    public static FirebaseFirestore getFirbaseConnection(){
        if(store==null){
            store=FirebaseFirestore.getInstance();
        }

        return store;
    }
}
