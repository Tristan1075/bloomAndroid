package com.example.bloomandroid.data.service.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bloomandroid.R;
import com.example.bloomandroid.data.service.Config;
import com.example.bloomandroid.data.service.GlobalClass;
import com.example.bloomandroid.data.service.models.Event;
import com.example.bloomandroid.data.service.models.StringParams;
import com.example.bloomandroid.data.service.models.Ticket;
import com.example.bloomandroid.data.service.utils.NetworkProvider;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResumeTicket extends AppCompatActivity {

    private Event event;
    private Ticket ticket;
    @BindView(R.id.resume_ticket_activity_qrcode_imageView) ImageView qrCodeImageView;
    @BindView(R.id.resume_ticket_activity_ticket_id_textView) TextView ticketId;
    @BindView(R.id.resume_ticket_activity_event_name) TextView eventName;
    @BindView(R.id.resume_ticket_activity_ticket_name_textView) TextView ticketName;
    @BindView(R.id.resume_ticket_activity_ticket_price_textView) TextView ticketPrice;
    @BindView(R.id.resume_ticket_activity_collectif_name) TextView associationName;
    @BindView(R.id.resume_ticket_activity_dateTextView) TextView dateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_ticket);
        ButterKnife.bind(this);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        if (bundle != null) {
            event = (Event) bundle.getSerializable("event");
        }
        NetworkProvider.getInstance().getTicketDetails(((GlobalClass) getApplication()).getUserId(), new StringParams(event.getId()), new NetworkProvider.Listener<Ticket>() {
            @Override
            public void onSuccess(Ticket t) {
                ticketId.setText(t.get_id());
                ticketName.setText(t.getName());
                ticketPrice.setText(t.getPrice());
                eventName.setText(event.getTitle());
                associationName.setText(event.getAssociationName());
                dateTextView.setText(event.getDateEvent());

                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                BitMatrix bitMatrix = null;
                try {
                    bitMatrix = multiFormatWriter.encode(Config.API + "/tickets/qrcode/" + ((GlobalClass) getApplication()).getUserId() + "/" + t.get_id(), BarcodeFormat.QR_CODE, 500, 500);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                qrCodeImageView.setImageBitmap(bitmap);
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }
}
