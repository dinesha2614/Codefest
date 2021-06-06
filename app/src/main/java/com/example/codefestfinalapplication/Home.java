package com.example.codefestfinalapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.codefestfinalapplication.Database.Store;
import com.example.codefestfinalapplication.Holder.Product_holder;
import com.example.codefestfinalapplication.Model.Order;
import com.example.codefestfinalapplication.Model.Product;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import me.riddhimanadib.library.BottomBarHolderActivity;
import me.riddhimanadib.library.NavigationPage;

public class Home extends AppCompatActivity {

    TextView name,email;
    Button logout;
    FirebaseFirestore store= Store.getFirbaseConnection();
    FirestoreRecyclerAdapter adapter;
    RecyclerView jobList;
    String c_email;
    String c_name;
    String c_rider_docid;
    Button ticket,news;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_home);



        Bundle bundle=getIntent().getExtras();
        c_name=bundle.getString("customerName");
        c_email=bundle.getString("customerEmail");
        c_rider_docid=bundle.getString("customerDocId");
        setContentView(R.layout.activity_home);
        email=findViewById(R.id.email_profile);
        name=findViewById(R.id.name_profile);
        ticket=findViewById(R.id.Ticket);
        news=findViewById(R.id.News);
        email.setText(c_email);
        name.setText(c_name);

        jobList=findViewById(R.id.productlist);
        jobList.setLayoutManager(new LinearLayoutManager(this));

        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();

            }
        });

        ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,Ticket.class);
                startActivity(intent);

            }
        });

        Query loadProduct=store.collection("Product").whereEqualTo("status","Available");
        FirestoreRecyclerOptions recyclerOptions=new FirestoreRecyclerOptions.Builder<Product>().setQuery(loadProduct,Product.class).build();


        adapter=new FirestoreRecyclerAdapter<Product, Product_holder>(recyclerOptions) {

            @Override
            protected void onBindViewHolder(@NonNull Product_holder holder, int position, @NonNull Product model) {
                holder.pro_name.setText("Product:"+ model.getProduct_name());
                holder.cost.setText("Description:"+ model.getDescription());
                holder.jobDocid=getSnapshots().getSnapshot(position).getId();




            }

            @NonNull
            @Override
            public Product_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
                return new Product_holder(view);

            }



        };
        jobList.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent=new Intent(Home.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
        // [END auth_fui_signout]
    }





    }

