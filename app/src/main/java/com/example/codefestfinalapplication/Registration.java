package com.example.codefestfinalapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.codefestfinalapplication.Database.Store;
import com.example.codefestfinalapplication.Model.Customer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class Registration extends AppCompatActivity {
    RadioGroup gender;
    EditText name, email, mobile,nic;

    CircleImageView profileimg;
    FirebaseStorage storage;
    Button sign_up;
    Uri imageUri;
    StorageReference storageRef;

    private static final int PICK_IMAGE = 1;

    FirebaseFirestore store = Store.getFirbaseConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        gender=findViewById(R.id.user_gender);


        name = findViewById(R.id.name_field);
        email = findViewById(R.id.email_field);
        mobile = findViewById(R.id.mobile_field);
        nic= findViewById(R.id.nic_field);




        Bundle bundle=getIntent().getExtras();


        String user_name=bundle.getString("customerName");
        String user_email=bundle.getString("customerEmail");

        String google_id6=bundle.getString("customerAuth");

        //set by accesing the google account
        name.setText(user_name);
        email.setText(user_email);




        sign_up = findViewById(R.id.signup);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=gender.getCheckedRadioButtonId();
                RadioButton gender=findViewById(id);
                String g=gender.getText().toString();
                Customer customer=new Customer(name.getText().toString(),email.getText().toString(),nic.getText().toString(),mobile.getText().toString(),null,null,null,1);
                storage = FirebaseStorage.getInstance();
                storageRef = storage.getReference();

                RegisterPre1(customer);


            }
        });

        profileimg = findViewById(R.id.profile_image);
        profileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Select Driver Photo"), PICK_IMAGE);

            }
        });





    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            System.out.println(imageUri);
            Picasso.with(Registration.this).load(imageUri).into(profileimg);


        }else {
            Toast.makeText(getApplicationContext(), "Not Selected Photo", Toast.LENGTH_LONG).show();

        }

    }

    public void RegisterPre1(Customer customer) {

        String photoURLPath = "CustomerImages_" + nic.getText().toString() + ".png";
        System.out.println(photoURLPath);


        storageRef.child("CustomerImages/" + photoURLPath).putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                Toast.makeText(getApplicationContext(), " Profile Picture Saved Successfully ", Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Profile Picture Not Saved ", Toast.LENGTH_LONG).show();
            }

        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                storageRef.child("CustomerImages/" + photoURLPath).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // Got the download URL for 'users/me/profile.png'
                        Toast.makeText(getApplicationContext(), "Found downloadUri ", Toast.LENGTH_LONG).show();
                        String PhotoimageUri = uri.toString();
                        customer.setCustomerPhotopath(PhotoimageUri);
                        registerCustomer(customer);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(), "No DownloadUri ", Toast.LENGTH_LONG).show();

                    }
                });

            }
        });
    }










    public void registerCustomer(Customer customer){
        store.collection("Customer").add(customer).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {


                Toast.makeText(getApplicationContext(), "Saved Successfully ", Toast.LENGTH_LONG).show();

                Intent home_intent=new Intent(Registration.this,Home.class);
                home_intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                home_intent.putExtra("customerName",name.getText().toString()+"");
                home_intent.putExtra("customerEmail",email.getText().toString()+"");
                startActivity(home_intent);

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Save Unsccessfully"+e.getMessage(), Toast.LENGTH_LONG).show();
                        finish();


                    }
                });
    }



    }
