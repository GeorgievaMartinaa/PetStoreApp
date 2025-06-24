package com.example.petstoreapp.service.impl;

import com.example.petstoreapp.entity.PurchaseHistory;
import com.example.petstoreapp.repository.PurchaseHistoryRepository;
import com.example.petstoreapp.service.PurchaseHistoryService;
import com.example.petstoreapp.service.mapper.PurchaseHistoryMapper;
import com.example.petstoreapp.web.DTO.PurchaseHistoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {

    private final PurchaseHistoryRepository historyRepository;
    private final PurchaseHistoryMapper historyMapper;

    public PurchaseHistoryServiceImpl(PurchaseHistoryRepository historyRepository, PurchaseHistoryMapper historyMapper) {
        this.historyRepository = historyRepository;
        this.historyMapper = historyMapper;
    }

    @Override
    public List<PurchaseHistoryDTO> getAllHistoryLogs() {

        List<PurchaseHistory> allHistories = historyRepository.findAll();

        List<PurchaseHistoryDTO> historyDTOs = allHistories.stream()
                                                    .map(historyMapper::toHistoryDTO)
                                                    .collect(Collectors.toList());
        return historyDTOs;
    }
}
