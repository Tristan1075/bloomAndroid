package com.example.bloomandroid.data.service.utils;

import com.example.bloomandroid.data.service.dto.EventDTO;
import com.example.bloomandroid.data.service.dto.TicketDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BloomAndroidAPI {
  @GET("/events") Call<List<EventDTO>> getEvents();


  @GET("/tickets/{idEvent}")
  Call<List<TicketDTO>> getTickets(@Path("idEvent") String idEvent);



}
