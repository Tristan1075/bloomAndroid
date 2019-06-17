package com.example.bloomandroid.data.service.utils;

import android.util.Log;

import com.example.bloomandroid.data.service.Config;
import com.example.bloomandroid.data.service.dto.EventDTO;
import com.example.bloomandroid.data.service.dto.PromotionalCodeDTO;
import com.example.bloomandroid.data.service.dto.TicketDTO;
import com.example.bloomandroid.data.service.dto.mapper.EventMapper;
import com.example.bloomandroid.data.service.dto.mapper.PromotionalCodeMapper;
import com.example.bloomandroid.data.service.dto.mapper.TicketMapper;
import com.example.bloomandroid.data.service.models.BoughtTicket;
import com.example.bloomandroid.data.service.models.Event;
import com.example.bloomandroid.data.service.models.PromotionalCode;
import com.example.bloomandroid.data.service.models.StringParams;
import com.example.bloomandroid.data.service.models.Ticket;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkProvider {
  BloomAndroidAPI bloomAndroidAPI;

  private static NetworkProvider instance;

  public static NetworkProvider getInstance() {
    if (instance == null) {
      instance = new NetworkProvider();
    }
    return instance;
  }

  private NetworkProvider() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(Config.API)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    bloomAndroidAPI = retrofit.create(BloomAndroidAPI.class);
  }

  public void getEvents(Listener<List<Event>> listener) {
    bloomAndroidAPI.getEvents().enqueue(new Callback<List<EventDTO>>() {
      @Override public void onResponse(Call<List<EventDTO>> call, Response<List<EventDTO>> response) {
        List<EventDTO> eventDTOList = response.body();
        List<Event> eventList = EventMapper.map(eventDTOList);
        listener.onSuccess(eventList);
      }

      @Override public void onFailure(Call<List<EventDTO>> call, Throwable t) {
        listener.onError(t);
      }
    });
  }

  public void getTickets(String idEvent, Listener<List<Ticket>> listener) {
    bloomAndroidAPI.getTickets(idEvent).enqueue(new Callback<List<TicketDTO>>() {
      @Override public void onResponse(Call<List<TicketDTO>> call, Response<List<TicketDTO>> response) {
        List<TicketDTO> ticketDTOList = response.body();
        List<Ticket> ticketList = TicketMapper.map(ticketDTOList);

        listener.onSuccess(ticketList);
      }

      @Override public void onFailure(Call<List<TicketDTO>> call, Throwable t) {
        listener.onError(t);
      }
    });
  }

  public void verifyPromotionalCode(String idEvent, StringParams stringParams, Listener<PromotionalCode> listener){
    bloomAndroidAPI.verifyPromotionalCode(idEvent, stringParams).enqueue(new Callback<PromotionalCodeDTO>() {
      @Override public void onResponse(Call<PromotionalCodeDTO> call, Response<PromotionalCodeDTO> response) {
        PromotionalCodeDTO promotionalCodeDTO = response.body();
        PromotionalCode promotionalCode = PromotionalCodeMapper.map(promotionalCodeDTO);
        listener.onSuccess(promotionalCode);
      }

      @Override public void onFailure(Call<PromotionalCodeDTO> call, Throwable t) {
        listener.onError(t);
      }
    });
  }

  public void buyTicket(BoughtTicket boughtTicket){
    bloomAndroidAPI.buyTicket(boughtTicket).enqueue(new Callback<String>(){
      @Override
      public void onResponse(Call call, Response response) {

      }

      @Override
      public void onFailure(Call call, Throwable t) {

      }
    });
  }

  public void getUserTickets(String userId, Listener<List<Event>> listener) {
    bloomAndroidAPI.getUserTickets(userId).enqueue(new Callback<List<EventDTO>>() {
      @Override public void onResponse(Call<List<EventDTO>> call, Response<List<EventDTO>> response) {
        List<EventDTO> eventDTOList = response.body();
        List<Event> eventList = EventMapper.map(eventDTOList);

        listener.onSuccess(eventList);
      }

      @Override public void onFailure(Call<List<EventDTO>> call, Throwable t) {
        listener.onError(t);
      }
    });
  }
  public interface Listener<T> {
    void onSuccess(T data);

    void onError(Throwable t);
  }
}
