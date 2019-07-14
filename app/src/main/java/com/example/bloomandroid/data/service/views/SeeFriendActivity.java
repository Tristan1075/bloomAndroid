package com.example.bloomandroid.data.service.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.bloomandroid.R;
import com.example.bloomandroid.data.service.Config;
import com.example.bloomandroid.data.service.GlobalClass;
import com.example.bloomandroid.data.service.dto.DataDTO;
import com.example.bloomandroid.data.service.dto.FriendsDTO;
import com.example.bloomandroid.data.service.dto.ListFriendsAdapter;
import com.example.bloomandroid.data.service.dto.ListFriendsDTO;
import com.example.bloomandroid.data.service.dto.TicketBoughtAdapter;
import com.example.bloomandroid.data.service.models.Event;
import com.example.bloomandroid.data.service.models.Ticket;
import com.example.bloomandroid.data.service.utils.NetworkProvider;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeeFriendActivity extends AppCompatActivity {

    private Gson gson;
    @BindView(R.id.see_friends_activity_recycler_view)
    RecyclerView listFriendsRecyclerView;
    private ListFriendsAdapter listFriendsAdapter;
    private String idEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_friend);
        ButterKnife.bind(this);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        if (bundle != null) {
            idEvent = bundle.getString("idEvent");
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        GraphRequest request = GraphRequest.newMeRequest(
                ((GlobalClass) getApplication()).getTokenId(),
                (JSONObject object, GraphResponse response) -> {

                    ListFriendsDTO listFriendsDTO = gson.fromJson(object.toString(), ListFriendsDTO.class);
                    Log.d("test list", listFriendsDTO.getFriendsDTO().getData().toString());
                    loadData(listFriendsDTO.getFriendsDTO().getData());
                    initRecyclerView();
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "friends");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void initRecyclerView() {
        listFriendsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listFriendsAdapter = new ListFriendsAdapter();
        listFriendsRecyclerView.setAdapter(listFriendsAdapter);
    }

    private void loadData(List<DataDTO> data) {
        NetworkProvider.getInstance().getFriendsParticipating(((GlobalClass) getApplication()).getTokenId().getUserId(), data, idEvent, new NetworkProvider.Listener<List<DataDTO>>() {
            @Override
            public void onSuccess(List<DataDTO> data) {
                Log.d("data", data.toString());
                listFriendsAdapter.setListFriends(data);
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }
}
