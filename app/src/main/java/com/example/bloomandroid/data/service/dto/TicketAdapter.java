package com.example.bloomandroid.data.service.dto;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.bloomandroid.R;
import com.example.bloomandroid.data.service.models.Ticket;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {
    private List<Ticket> ticketList;
    private ItemClickListener itemClickListener;

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull @Override public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ticket_cell, viewGroup, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder ticketViewHolder, int i) {
        final Ticket ticket = ticketList.get(i);
        ticketViewHolder.nameTextView.setText(ticket.getName());
        ticketViewHolder.priceTextView.setText(ticket.getPrice());

        if (itemClickListener != null) {
            ticketViewHolder.itemView.setOnClickListener(v -> itemClickListener.onClick(ticket));
        }
    }

    @Override public int getItemCount() {
        return ticketList != null ? ticketList.size() : 0;
    }

    class TicketViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.detail_event_activity_name_textView) TextView nameTextView;
        @BindView(R.id.detail_event_activity_price_textView) TextView priceTextView;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ItemClickListener {
        void onClick(Ticket ticket);
    }
}
