package com.example.bloomandroid.data.service.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bloomandroid.R;
import com.example.bloomandroid.data.service.GlobalClass;
import com.example.bloomandroid.data.service.dto.EventAdapter;
import com.example.bloomandroid.data.service.dto.TicketAdapter;
import com.example.bloomandroid.data.service.dto.TicketBoughtAdapter;
import com.example.bloomandroid.data.service.models.Event;
import com.example.bloomandroid.data.service.models.Ticket;
import com.example.bloomandroid.data.service.utils.NetworkProvider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListTicketsActivity extends AppCompatActivity {

    @BindView(R.id.list_ticket_activity_recycler_view)
    RecyclerView listTicketRecyclerView;
    private TicketBoughtAdapter ticketBoughtAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tickets);
        ButterKnife.bind(this);
        loadData(((GlobalClass) getApplication()).getUserId());
        initRecyclerView();
        setTitle("My tickets");
    }

    private void initRecyclerView() {
        listTicketRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ticketBoughtAdapter = new TicketBoughtAdapter();
        listTicketRecyclerView.setAdapter(ticketBoughtAdapter);

        ticketBoughtAdapter.setItemClickListener(event -> {
            Intent intent = new Intent(this, ResumeTicket.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("event", event);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    private void loadData(String userId) {
        NetworkProvider.getInstance().getUserTickets(userId, new NetworkProvider.Listener<List<Event>>() {
            @Override
            public void onSuccess(List<Event> data) {
                ticketBoughtAdapter.setEventsList(data);
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }
}
