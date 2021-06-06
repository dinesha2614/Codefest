package com.example.codefestfinalapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.codefestfinalapplication.Database.Store;
import com.example.codefestfinalapplication.Model.Customer;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MeowBottomNavigation navigation;
   MaterialButton Signin_btn;
    private int RC_SIGN_IN=134;
    String FCMToken = null;
    private String documentId;
    private String TAG="Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFCM();

        Signin_btn=findViewById(R.id.google_signin_btn);
        Signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSignInIntent();
            }
        });

    }

    private void initFCM() {

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        FCMToken = task.getResult().toString();
                        Toast.makeText(MainActivity.this, FCMToken, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void updateFCMToken(String customer_id) {
        Log.d(TAG,"Customer Id : "+customer_id);
        Store.getFirbaseConnection().collection("Customer").document(customer_id).update("fcmId",FCMToken).
                addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG,"FCM Token Updated");
                    }
                });

    }



    public void createSignInIntent() {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(

                new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
        // [END auth_fui_create_intent]
    }



    // [START auth_fui_result]
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(getApplicationContext(),"Signin successfull",Toast.LENGTH_LONG).show();
                String email=user.getEmail();
                String authid=user.getUid();

                String name=user.getDisplayName();



                FirebaseFirestore store=Store.getFirbaseConnection();
                store.collection("Customer").whereEqualTo("email",email).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if(!queryDocumentSnapshots.isEmpty()){
                            System.out.println("Successfull");
                            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            //result data found
                            DocumentSnapshot document = queryDocumentSnapshots.getDocuments().get(0);
//                                        for(QueryDocumentSnapshot document: task.getResult()){
                            // }
                            documentId = document.getId();
                            Customer customer = document.toObject(Customer.class);
                            updateFCMToken(documentId);
                            updateDocId(documentId);


                            Intent homeIntent = new Intent(MainActivity.this, Home.class);
                            homeIntent.putExtra("customerDocId",documentId);
                            homeIntent.putExtra("customerName",customer.getName());
                            homeIntent.putExtra("customerEmail",customer.getEmail());
                            homeIntent.putExtra("customerMobile",customer.getMobile());
                            Log.d("TAG onComplete doc id",documentId);
                            Log.d("TAG onComplete customer",customer.getEmail());
                            startActivity(homeIntent);
                        }else{
                            //no data results found on given query
                            Toast.makeText(MainActivity.this, " Please Register !", Toast.LENGTH_SHORT).show();
                            Intent homeIntent = new Intent(MainActivity.this, Registration.class);
                            homeIntent.putExtra("customerEmail",email);
                            homeIntent.putExtra("customerName",name);
                            homeIntent.putExtra("customerAuth",name);


                            startActivity(homeIntent);
                        }
                    }
                })

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });




            } else {
                Toast.makeText(this, "Error, Login Failed !", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void updateDocId(String documentId) {
        Store.getFirbaseConnection().collection("Customer").document(documentId).update(" customerDocId",documentId).
                addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG,"Docid Updated");
                    }
                });


    }
    // [END auth_fui_result]



    public void delete() {
        // [START auth_fui_delete]
        AuthUI.getInstance()
                .delete(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
        // [END auth_fui_delete]
    }

    public void themeAndLogo() {
        List<AuthUI.IdpConfig> providers = Collections.emptyList();

        // [START auth_fui_theme_logo]
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        // .setLogo(R.drawable.my_great_logo)      // Set logo drawable
                        .setTheme(R.style.Theme_AppCompat_DayNight)      // Set theme
                        .build(),
                RC_SIGN_IN);
        // [END auth_fui_theme_logo]
    }

    public void privacyAndTerms() {
        List<AuthUI.IdpConfig> providers = Collections.emptyList();
        // [START auth_fui_pp_tos]
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTosAndPrivacyPolicyUrls(
                                "https://example.com/terms.html",
                                "https://example.com/privacy.html")
                        .build(),
                RC_SIGN_IN);
        // [END auth_fui_pp_tos]
    }



}