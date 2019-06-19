package com.example.bloomandroid.data.service.dto.mapper;

import com.example.bloomandroid.data.service.dto.TicketDTO;
import com.example.bloomandroid.data.service.models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketMapper {

    public static List<Ticket> map(List<TicketDTO> ticketDTOList) {
        List<Ticket> ticketList = new ArrayList<>();
        for (TicketDTO ticketDTO : ticketDTOList) {
            ticketList.add(mapOne(ticketDTO));
        }
        return ticketList;
    }

    public static Ticket mapOne(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket(ticketDTO.get_id(), ticketDTO.getIdEvent(), ticketDTO.getName(), ticketDTO.getPrice(), ticketDTO.getQuantity());
        return ticket;
    }
}
