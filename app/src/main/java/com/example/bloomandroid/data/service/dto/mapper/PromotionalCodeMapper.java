package com.example.bloomandroid.data.service.dto.mapper;

import com.example.bloomandroid.data.service.dto.PromotionalCodeDTO;
import com.example.bloomandroid.data.service.dto.TicketDTO;
import com.example.bloomandroid.data.service.models.PromotionalCode;
import com.example.bloomandroid.data.service.models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class PromotionalCodeMapper {
    public static PromotionalCode map(PromotionalCodeDTO promotionalCodeDTO) {
        PromotionalCode promotionalCode = new PromotionalCode(promotionalCodeDTO.getIdEvent(), promotionalCodeDTO.getName(), promotionalCodeDTO.getPrice(), promotionalCodeDTO.getQuantity());
        return  promotionalCode;
    }
}
