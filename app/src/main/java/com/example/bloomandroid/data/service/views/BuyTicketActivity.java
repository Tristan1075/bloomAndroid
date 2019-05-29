package com.example.bloomandroid.data.service.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bloomandroid.R;
import com.example.bloomandroid.data.service.Config;
import com.example.bloomandroid.data.service.models.Ticket;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyTicketActivity extends AppCompatActivity {

    @BindView(R.id.buy_ticket_activity_name_textView) TextView nameTextView;
    @BindView(R.id.buy_ticket_activity_price_textView) TextView priceTextView;
    @BindView(R.id.buy_ticket_activity_imageView) ImageView eventImageView;
    @BindView(R.id.buy_ticket_activity_pay_button) Button payButton;
    private Ticket ticket;
    private  String imageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket);
        ButterKnife.bind(this);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        if (bundle != null) {
            ticket = (Ticket) bundle.getSerializable("ticket");
            imageURI = bundle.getString("imageURI");
            Glide.with(this).load(Config.API + "/images/" + imageURI).into(eventImageView);
            nameTextView.setText(ticket.getName());
            priceTextView.setText(ticket.getPrice());
        }

        payButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, BuyTicketFormActivity.class);
            startActivity(intent);
        });
    }
}
