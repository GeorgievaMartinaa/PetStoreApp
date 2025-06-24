package com.example.petstoreapp.service;

import com.example.petstoreapp.web.DTO.PurchaseHistoryDTO;

import java.util.List;

public interface PurchaseHistoryService {
    List<PurchaseHistoryDTO> getAllHistoryLogs();
}
