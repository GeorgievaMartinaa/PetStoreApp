package com.example.petstoreapp.service.mapper;

import com.example.petstoreapp.entity.PurchaseHistory;
import com.example.petstoreapp.web.DTO.PurchaseHistoryDTO;
import org.springframework.stereotype.Component;

@Component
public class PurchaseHistoryMapper {

    public PurchaseHistory toEntity(PurchaseHistoryDTO historyDTO){
        PurchaseHistory history = new PurchaseHistory();
        history.setExecutionDate(historyDTO.getExecutionDate());
        history.setSuccessfulBuyers(historyDTO.getSuccessfulBuyers());
        history.setFailedBuyers(historyDTO.getFailedBuyers());

        return history;
    }

    public PurchaseHistoryDTO toHistoryDTO( PurchaseHistory history){
        PurchaseHistoryDTO historyDTO = new PurchaseHistoryDTO();
        historyDTO.setId(history.getId());
        historyDTO.setExecutionDate(history.getExecutionDate());
        historyDTO.setSuccessfulBuyers(history.getSuccessfulBuyers());
        historyDTO.setFailedBuyers(history.getFailedBuyers());

        return historyDTO;
    }
}
