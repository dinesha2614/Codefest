package com.example.codefestfinalapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.codefestfinalapplication.Database.Store;
import com.example.codefestfinalapplication.Model.Ticket1;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

public class Ticket extends AppCompatActivity {

    EditText title,description;
    RadioGroup type;
    Button btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        title=findViewById(R.id.ticket_title);
        description=findViewById(R.id.ticket_description);
        type=findViewById(R.id.ticket_type);
        btn=findViewById(R.id.submiit_ticket);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=type.getCheckedRadioButtonId();
                RadioButton ticket_type=findViewById(id);
                String type_ticket=ticket_type.getText().toString();

                Ticket1 ticket=new Ticket1(title.getText().toString(),description.getText().toString(),type_ticket);
                Store.getFirbaseConnection().collection("Ticket").add(ticket).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), " Ticket  saved Successfully ", Toast.LENGTH_LONG).show();
                        documentReference.update("status","Not Accepted").addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), " Ticket status updated", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), " Ticket status npot updated", Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), " Ticket not Saved", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });





    }
}