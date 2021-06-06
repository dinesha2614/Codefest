package com.example.codefestfinalapplication.Holder;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codefestfinalapplication.Cart;
import com.example.codefestfinalapplication.Model.Order;
import com.example.codefestfinalapplication.R;

public class Product_holder extends RecyclerView.ViewHolder{

    public TextView pro_name,cost;
    public Button addto_Cart;

    public String jobDocid;
    public Order order;

    public Product_holder(@NonNull View itemView) {
        super(itemView);

        pro_name=itemView.findViewById(R.id.product_name);
        cost=itemView.findViewById(R.id.product_price);
        addto_Cart=itemView.findViewById(R.id.buy);

        addto_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(),"Added to Cart:"+pro_name.getText().toString(),Toast.LENGTH_LONG).show();

                Intent intent=new Intent(itemView.getContext(), Cart.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               // intent.putExtra("riderDocId",job.getRiderDocid());
               // intent.putExtra("jobDocid",jobDocid);
                itemView.getContext().startActivity(intent);
            }
        });
    }
}
