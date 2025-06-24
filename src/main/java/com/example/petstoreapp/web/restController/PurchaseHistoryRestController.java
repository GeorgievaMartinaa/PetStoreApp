package com.example.petstoreapp.web.restController;

import com.example.petstoreapp.service.PurchaseHistoryService;
import com.example.petstoreapp.web.DTO.PurchaseHistoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history-log")
public class PurchaseHistoryRestController {

    private final PurchaseHistoryService historyService;

    public PurchaseHistoryRestController(PurchaseHistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping()
    public ResponseEntity<List<PurchaseHistoryDTO>>getAllLogs(){
        List<PurchaseHistoryDTO> historyLogs = historyService.getAllHistoryLogs();

        return ResponseEntity.ok(historyLogs);
    }
}
