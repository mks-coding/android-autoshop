package com.example.autoshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity;
    Spinner spinner;
    ArrayList spinnerArayList;
    ArrayAdapter spinnerAdapter;
    EditText userNameEditText;

    HashMap goodsMap;
    String goodsName;
    double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSpinner();
        createMap();

        userNameEditText = findViewById(R.id.editTextTextPersonName);

    }

    void createSpinner(){
        spinner = findViewById(R.id.spinnerItem);
        spinner.setOnItemSelectedListener(this);
        spinnerArayList = new ArrayList();

        spinnerArayList.add("Ford Focus");
        spinnerArayList.add("Kia Rio");
        spinnerArayList.add("Reno Duster");

        spinnerAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                spinnerArayList
        );

        spinner.setAdapter(spinnerAdapter);
    }

    void createMap(){
        goodsMap=new HashMap();
        goodsMap.put("Ford Focus", 14500.0);
        goodsMap.put("Kia Rio", 16900.0);
        goodsMap.put("Reno Duster", 10000.0);
    }

    public void increaseQuantity(View view) {
        TextView value = findViewById(R.id.value);
        quantity +=1;
        value.setText(""+quantity);
        TextView priceview = findViewById(R.id.priceview);
        priceview.setText(quantity*price+"$");
    }

    public void decreaseQuantity(View view) {
        TextView value = findViewById(R.id.value);
        if (quantity>0) {
            quantity -=1;
            value.setText(""+quantity);
        }
        TextView priceview = findViewById(R.id.priceview);
        priceview.setText(quantity*price+"$");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double)goodsMap.get(goodsName);

        TextView priceview = findViewById(R.id.priceview);
        priceview.setText(quantity*price+"$");

        ImageView goodsImgView = findViewById(R.id.car_img);
        switch (goodsName) {
            case "Ford Focus":
                goodsImgView.setImageResource(R.drawable.car);
                break;
            case "Kia Rio":
                goodsImgView.setImageResource(R.drawable.kia);
                break;
            case "Reno Duster":
                goodsImgView.setImageResource(R.drawable.reno);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addcard(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.price = price;
        order.orderPrice = quantity*price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userName", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("price", order.price);
        orderIntent.putExtra("orderPrice", order.orderPrice);

        startActivity(orderIntent);
    }
}

