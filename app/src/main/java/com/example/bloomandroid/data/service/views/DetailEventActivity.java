package com.example.bloomandroid.data.service.views;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bloomandroid.R;
import com.example.bloomandroid.data.service.Config;
import com.example.bloomandroid.data.service.dto.TicketAdapter;
import com.example.bloomandroid.data.service.models.Event;
import com.example.bloomandroid.data.service.models.Ticket;
import com.example.bloomandroid.data.service.utils.NetworkProvider;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailEventActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private static LatLng LOCATION;


    private Marker mLocation;
    private GoogleMap mMap;
    private TicketAdapter ticketAdapter;
    private Event event;
    @BindView(R.id.detail_event_activity_imageView)
    ImageView imageView;
    @BindView(R.id.detail_event_activity_description_view)
    TextView descriptionView;
    @BindView(R.id.details_event_activity_recycler_view)
    RecyclerView ticketRecyclerView;
    @BindView(R.id.detail_event_activity_see_more_button)
    Button seeMoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        ButterKnife.bind(this);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        if (bundle != null) {
            event = (Event) bundle.getSerializable("event");
        }
        loadData(event.getId());
        initRecyclerView();
        setTitle(event.getTitle());
        Glide.with(this).load(Config.API + "/images/" + event.getImageURl()).into(imageView);
        descriptionView.setText(event.getDescription());
        LOCATION = new LatLng(Double.parseDouble(event.getLatitude()), Double.parseDouble(event.getLongitude()));

        seeMoreButton.setOnClickListener(v -> {
            if (seeMoreButton.getText().toString().equalsIgnoreCase("See more...")){
                descriptionView.setMaxLines(Integer.MAX_VALUE);//your TextView
                seeMoreButton.setText("Show less");
            } else {
                descriptionView.setMaxLines(4);//your TextView
                seeMoreButton.setText("Show more...");
            }
        });

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.detail_event_activity_map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

    }

    private void initRecyclerView() {
        ticketRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ticketAdapter = new TicketAdapter();
        ticketRecyclerView.setAdapter(ticketAdapter);

        ticketAdapter.setItemClickListener(ticket -> {
            Intent intent = new Intent(this, BuyTicketActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("ticket", ticket);
            intent.putExtras(bundle);
            intent.putExtra("imageURI", event.getImageURl());
            startActivity(intent);
        });
    }

    private void loadData(String idEvent) {
        NetworkProvider.getInstance().getTickets(idEvent, new NetworkProvider.Listener<List<Ticket>>() {
            @Override
            public void onSuccess(List<Ticket> data) {
                ticketAdapter.setTicketList(data);
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LOCATION, 15));
        mLocation = mMap.addMarker(new MarkerOptions()
                .position(LOCATION)
                .title("SYDNEY"));
        mLocation.setTag(0);
    }
}
