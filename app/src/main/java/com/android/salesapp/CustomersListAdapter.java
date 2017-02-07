package com.android.salesapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.android.salesapp.Models.Customer;

import java.util.ArrayList;

/**
 * Created by challa on 2/5/2017.
 */
public class CustomersListAdapter extends RecyclerView.Adapter<CustomersListAdapter.MyVH> {
    private Context mContext;
    private ArrayList<Customer> customers;
    private  boolean addProduct = false;
    public CustomersListAdapter(Context mContext, ArrayList<Customer> customers, boolean addProduct) {
        this.mContext = mContext;
        this.customers = customers;
        this.addProduct = addProduct;
    }

    @Override
    public MyVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        MyVH vh = new MyVH(view);
        return vh;
    }

    public void setCustomers(ArrayList<Customer> providers) {
        this.customers = providers;
    }
    @Override
    public void onBindViewHolder(MyVH holder, int position) {
        Customer provider=customers.get(position);
        holder.pic.setText(provider.getName().substring(0,1).toUpperCase());
        holder.customerName.setText(provider.getName());
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class MyVH extends RecyclerView.ViewHolder {
        private TextView customerName;
        private TextView pic;
        public MyVH(View convertView) {
            super(convertView);
            pic= (TextView) convertView.findViewById(R.id.pic);
            customerName= (TextView) convertView.findViewById(R.id.name);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!addProduct) {
                        Intent intent = new Intent(mContext, CustomersActivity.class);
                        mContext.startActivity(intent);
                    }else{
                        Intent intent = new Intent(mContext, AddNewProductActivity.class);
                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }
}
