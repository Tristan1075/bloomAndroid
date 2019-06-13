package com.example.bloomandroid.data.service.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.bloomandroid.R;
import com.example.bloomandroid.data.service.Config;
import com.example.bloomandroid.data.service.models.PromotionalCode;
import com.example.bloomandroid.data.service.models.StringParams;
import com.example.bloomandroid.data.service.models.Ticket;
import com.example.bloomandroid.data.service.utils.NetworkProvider;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyTicketActivity extends AppCompatActivity {

    @BindView(R.id.buy_ticket_activity_name_textView) TextView nameTextView;
    @BindView(R.id.buy_ticket_activity_price_textView) TextView priceTextView;
    @BindView(R.id.buy_ticket_activity_imageView) ImageView eventImageView;
    @BindView(R.id.buy_ticket_activity_pay_button) Button payButton;
    @BindView(R.id.buy_ticket_activity_promotional_code_button) Button promotionalCodeButton;
    @BindView(R.id.buy_ticket_activity_promotionalCode_editText) EditText promotionalCodeEditText;
    @BindView(R.id.buy_ticket_activity_valid_promotional_code) ImageView submitPromotionalCode;
    private Ticket ticket;
    private  String imageURI;
    private String idEvent;
    private Boolean promotionalCodeOpen = false;

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
            idEvent = bundle.getString("idEvent");
            Glide.with(this).load(Config.   API + "/images/" + imageURI).into(eventImageView);
            nameTextView.setText(ticket.getName());
            priceTextView.setText(ticket.getPrice());
        }
        submitPromotionalCode.setVisibility(View.GONE);
        promotionalCodeEditText.setVisibility(View.GONE);
        promotionalCodeButton.setOnClickListener((View v) -> {
            if(!promotionalCodeOpen) {
                promotionalCodeEditText.setVisibility(View.VISIBLE);
                submitPromotionalCode.setVisibility(View.VISIBLE);
                promotionalCodeOpen = true;
            } else {
                promotionalCodeEditText.setVisibility(View.GONE);
                submitPromotionalCode.setVisibility(View.GONE);
                promotionalCodeOpen = false;
            }
        });

        submitPromotionalCode.setOnClickListener(v -> NetworkProvider.getInstance().verifyPromotionalCode(idEvent, new StringParams(promotionalCodeEditText.getText().toString()), new NetworkProvider.Listener<PromotionalCode>() {

            @Override
            public void onSuccess(PromotionalCode promotionalCode) {
                Integer newPrice = Integer.parseInt(priceTextView.getText().toString()) - Integer.parseInt(promotionalCode.getPrice());
                priceTextView.setText(newPrice.toString());
                submitPromotionalCode.setVisibility(View.GONE);
                promotionalCodeEditText.setVisibility(View.GONE);
                promotionalCodeButton.setEnabled(false);
            }

            @Override
            public void onError(Throwable t) {

            }
        }));


        payButton.setOnClickListener(v -> {

        });
    }
}
