package com.android.salesapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.android.salesapp.Models.Product;

import java.util.ArrayList;

/**
 * Created by challa on 2/5/2017.
 */
public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.MyVH> {
    private Context mContext;
    private ArrayList<Product> products;
    private boolean viewOnly;
    public ProductsListAdapter(Context mContext, ArrayList<Product> products, boolean viewOnly) {
        this.mContext = mContext;
        this.products = products;
        this.viewOnly=viewOnly;
    }

    @Override
    public MyVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product, null);
        MyVH vh = new MyVH(view);
        return vh;
    }

    public void setProviders(ArrayList<Product> providers) {
        this.products = providers;
    }
    @Override
    public void onBindViewHolder(MyVH holder, int position) {
        Product product=products.get(position);
        holder.name.setText(product.getName());
        holder.quantity.setText(product.getQuantity()+"("+product.getUnit()+")");
        holder.amount.setText(String.valueOf(product.getAmount()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyVH extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView quantity;
        private TextView amount;
        public MyVH(View convertView) {
            super(convertView);
            name= (TextView) convertView.findViewById(R.id.products_name);
            quantity= (TextView) convertView.findViewById(R.id.quantity);
            amount= (TextView) convertView.findViewById(R.id.amount);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewOnly) {
                        Intent intent = new Intent(mContext, ProductActivity.class);
                        intent.putExtra("product",products.get(getAdapterPosition()));
                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }
}

