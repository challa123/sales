package com.android.salesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.salesapp.Models.Customer;

import java.util.ArrayList;

public class DistributorsActivity extends AppCompatActivity {

    private ArrayList<Customer> customers=new ArrayList<>();
    private RecyclerView recyclerView;
    private CustomersListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distributors);
        TextView head = (TextView)findViewById(R.id.head);
        head.setText("Distributors");
        getCustomers();
    }
    private void  getCustomers(){
        Customer customer=new Customer(1,"Narayana Distributors");
        customers.add(customer);
        customers.add(new Customer(2,"Sai ram Distributors"));
        customers.add(new Customer(3,"Teja Distributors"));
        customers.add(new Customer(4,"Ambica Distributors"));
        setup();
    }
    private void setup(){
        recyclerView= (RecyclerView) findViewById(R.id.my_recycler_view);
        adapter=new CustomersListAdapter(DistributorsActivity.this,customers, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(DistributorsActivity.this));
        recyclerView.setAdapter(adapter);
    }
}

