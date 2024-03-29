package com.example.bloomandroid.data.service.utils;

import com.example.bloomandroid.data.service.dto.DataDTO;
import com.example.bloomandroid.data.service.dto.EventDTO;
import com.example.bloomandroid.data.service.dto.PromotionalCodeDTO;
import com.example.bloomandroid.data.service.dto.TicketDTO;
import com.example.bloomandroid.data.service.models.BoughtTicket;
import com.example.bloomandroid.data.service.models.FriendsParticipating;
import com.example.bloomandroid.data.service.models.PromotionalCode;
import com.example.bloomandroid.data.service.models.StringParams;
import com.example.bloomandroid.data.service.models.Ticket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BloomAndroidAPI {
  @GET("/events") Call<List<EventDTO>> getEvents();

  @GET("/tickets/{idEvent}")
  Call<List<TicketDTO>> getTickets(@Path("idEvent") String idEvent);

  @POST("/promotionalCode/{idEvent}")
  Call<PromotionalCodeDTO> verifyPromotionalCode(@Path("idEvent") String idEvent, @Body StringParams stringParams);

  @POST("/paiement/ticket")
  Call<String> buyTicket(@Body BoughtTicket boughtTicket);

  @GET("/events/user/{userId}")
  Call<List<EventDTO>> getUserTickets(@Path("userId") String userId);

  @POST("/tickets/user/{userId}")
  Call<TicketDTO> getTicketDetails(@Path("userId") String userId, @Body StringParams idEvent);

  @POST("/events/friends")
  Call<List<DataDTO>> getFriendsParticipating(@Body FriendsParticipating friendsParticipating);
}
