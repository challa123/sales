package com.android.salesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.salesapp.Models.Product;

import java.util.ArrayList;
import java.util.List;

public class AddNewProductActivity extends AppCompatActivity {

    private Button add;
    private Spinner pNameFld;
    private Spinner packageTypeFld;
    private EditText quantityFld;
    private EditText billedQuantityFld;
    private TextView amountFld;
    private TextView totalAmountTxt;
    private double amount=245;


    private boolean flag;
    private Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        add= (Button) findViewById(R.id.add);
        pNameFld= (Spinner) findViewById(R.id.nameSpinner);
        packageTypeFld= (Spinner) findViewById(R.id.typeSpinner);
        quantityFld= (EditText) findViewById(R.id.quantity_fld);
        billedQuantityFld= (EditText) findViewById(R.id.billed_fld);
        amountFld= (TextView) findViewById(R.id.amount_fld);
        totalAmountTxt= (TextView) findViewById(R.id.total);

        flag=getIntent().getBooleanExtra("flag",false);

        List<String> list = new ArrayList<String>();
        list.add("Desire");
        list.add("Pleasure");
        list.add("Aroma");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pNameFld.setAdapter(dataAdapter);
        amountFld.setText(String.valueOf(amount));
        List<String> list2 = new ArrayList<String>();
        list2.add("cases");
        list2.add("boxes");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        packageTypeFld.setAdapter(dataAdapter2);
        billedQuantityFld.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int q=Integer.parseInt(s.toString());
                totalAmountTxt.setText(String.valueOf(q*amount));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pNameFld.getSelectedItem() instanceof String){
                    Product product=new Product();
                    product.setName((String) pNameFld.getSelectedItem());
                    if (packageTypeFld.getSelectedItem() instanceof String){
                        product.setUnit((String) packageTypeFld.getSelectedItem());
                        if (Integer.parseInt(quantityFld.getText().toString())!=0){
                            product.setQuantity(Integer.parseInt(quantityFld.getText().toString()));
                            if (Integer.parseInt(billedQuantityFld.getText().toString())!=0){
                                product.setBilledQuantity( Integer.parseInt(billedQuantityFld.getText().toString()));
                                product.setAmount(amount*(Integer.parseInt(billedQuantityFld.getText().toString())));
                                if (flag){
                                    Intent intent=new Intent();
                                    intent.putExtra("product",product);
                                    setResult(RESULT_OK,intent);
                                    finish();

                                }else {
                                    Intent intent=new Intent(AddNewProductActivity.this,NewOrderActivity.class);
                                    intent.putExtra("product",product);
                                    startActivity(intent);
                                }
                            }else {
                                Toast.makeText(AddNewProductActivity.this,"Enter billed quantity",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(AddNewProductActivity.this,"Enter quantity",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(AddNewProductActivity.this,"Select the package type",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(AddNewProductActivity.this,"Select a product",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
