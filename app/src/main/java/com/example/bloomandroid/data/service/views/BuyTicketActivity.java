package com.example.bloomandroid.data.service.views;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bloomandroid.R;
import com.example.bloomandroid.data.service.Config;
import com.example.bloomandroid.data.service.BloomAndroidApplication;
import com.example.bloomandroid.data.service.models.BoughtTicket;
import com.example.bloomandroid.data.service.models.PromotionalCode;
import com.example.bloomandroid.data.service.models.StringParams;
import com.example.bloomandroid.data.service.models.Ticket;
import com.example.bloomandroid.data.service.utils.NetworkProvider;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;


import java.math.BigDecimal;

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
    private Integer newPrice;
    private Boolean promotionalCodeOpen = false;
    private Integer PAYPAL_REQUEST_CODE = 9999;
    private PayPalConfiguration payPalConfiguration = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIEND_ID);


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
            newPrice = Integer.parseInt(ticket.getPrice());
        }
        setTitle("Buy ticket");
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
                newPrice = Integer.parseInt(priceTextView.getText().toString()) - Integer.parseInt(promotionalCode.getPrice());
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
            PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(newPrice),
                    "EUR",
                    "Ticket order",
                    PayPalPayment.PAYMENT_INTENT_SALE);
            Intent intent = new Intent(this, PaymentActivity.class);
            intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, payPalConfiguration);
            intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
            startActivityForResult(intent, PAYPAL_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PAYPAL_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(confirmation != null){
                    NetworkProvider.getInstance().buyTicket(new BoughtTicket(((BloomAndroidApplication) this.getApplication()).getUserId(), ticket, newPrice));
                    Intent intent = new Intent(this, ListTicketsActivity.class);
                    startActivity(intent);
                }
            } else if(resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(this, "Payment cancel", Toast.LENGTH_SHORT).show();
            } else if(resultCode == PaymentActivity.RESULT_EXTRAS_INVALID){
                Toast.makeText(this, "Invalid payment", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
