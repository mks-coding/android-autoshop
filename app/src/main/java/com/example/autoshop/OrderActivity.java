package com.example.autoshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    String [] addresses = {"example@gmail.com"};
    String subject = "Order from carshop";
    String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Your Order");

        Intent orderItent = getIntent();
        String userName = orderItent.getStringExtra("userName");
        String goodsName = orderItent.getStringExtra("goodsName");
        int quantity = orderItent.getIntExtra("quantity", 0);
        double price = orderItent.getDoubleExtra("price", 0);
        double orderPrice = orderItent.getDoubleExtra("orderPrice", 0);

        TextView orderTextView = findViewById(R.id.orderTextView);
        emailText = "User: "+userName+
                "\n"+"Goodsname: "+goodsName+
                "\n"+"Quantity: "+quantity+
                "\n"+ "Price: "+price+
                "\n"+"Order price: "+orderPrice;

        orderTextView.setText(emailText);
    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}