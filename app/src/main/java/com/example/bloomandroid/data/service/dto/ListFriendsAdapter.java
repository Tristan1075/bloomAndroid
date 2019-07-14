package com.example.bloomandroid.data.service.dto;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.bloomandroid.R;
import com.example.bloomandroid.data.service.models.Ticket;
import com.facebook.login.widget.ProfilePictureView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFriendsAdapter extends RecyclerView.Adapter<ListFriendsAdapter.ListFriendsViewHolder> {
    private List<DataDTO> dataDTOS;

    public void setListFriends(List<DataDTO> dataDTOS) {
        this.dataDTOS = dataDTOS;
        notifyDataSetChanged();
    }

    @NonNull @Override public ListFriendsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_friend_cell, viewGroup, false);
        return new ListFriendsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListFriendsViewHolder listFriendsViewHolder, int i) {
        final DataDTO dataDTO = dataDTOS.get(i);
        listFriendsViewHolder.nameTextView.setText(dataDTO.getName());
        listFriendsViewHolder.profilePictureView.setProfileId(dataDTO.getId());
    }

    @Override public int getItemCount() {
        return dataDTOS != null ? dataDTOS.size() : 0;
    }

    class ListFriendsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_friend_activity_name_textView) TextView nameTextView;
        @BindView(R.id.list_friend_activity_friendProfilePicture)
        ProfilePictureView profilePictureView;
        public ListFriendsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
