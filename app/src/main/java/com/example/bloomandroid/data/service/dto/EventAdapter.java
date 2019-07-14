package com.example.bloomandroid.data.service.dto;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bloomandroid.R;
import com.example.bloomandroid.data.service.Config;
import com.example.bloomandroid.data.service.models.Event;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private List<Event> eventsList;
    private ItemClickListener itemClickListener;

    public void setEventsList(List<Event> eventsList) {
        this.eventsList = eventsList;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull @Override public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_cell, viewGroup, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.EventViewHolder eventViewHolder, int i) {
        final Event event = eventsList.get(i);
        eventViewHolder.titleTextView.setText(event.getTitle());
        eventViewHolder.associationNameTextView.setText(event.getAssociationName());
        eventViewHolder.dateTextView.setText(event.getDateEvent());

        Glide.with(eventViewHolder.itemView).load(Config.API + "/images/" + event.getImageURl()).into(eventViewHolder.backgroundImageView);

        if (itemClickListener != null) {
            eventViewHolder.itemView.setOnClickListener(v -> itemClickListener.onClick(event));
        }
    }

    @Override public int getItemCount() {
        return eventsList != null ? eventsList.size() : 0;
    }

    class EventViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_activity_event_image_view) ImageView backgroundImageView;
        @BindView(R.id.home_activity_event_text_view) TextView titleTextView;
        @BindView(R.id.home_activity_collectif_name) TextView associationNameTextView;
        @BindView(R.id.home_activity_date_text_view) TextView dateTextView;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ItemClickListener {
        void onClick(Event event);
    }
}
