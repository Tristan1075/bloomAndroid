package com.example.bloomandroid.data.service.dto.mapper;

import com.example.bloomandroid.data.service.dto.EventDTO;
import com.example.bloomandroid.data.service.models.Event;

import java.util.ArrayList;
import java.util.List;

public class EventMapper {

    public static List<Event> map(List<EventDTO> eventDTOlist) {
        List<Event> eventList = new ArrayList<>();
        for (EventDTO eventDTO : eventDTOlist) {
            eventList.add(map(eventDTO));
        }
        return eventList;
    }

    private static Event map(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setImageURl(eventDTO.getImage());
        event.setLongitude(eventDTO.getLongitude());
        event.setLatitude(eventDTO.getLatitude());
        event.setAssociationName(eventDTO.getAssociationName());
        return event;
    }
}
