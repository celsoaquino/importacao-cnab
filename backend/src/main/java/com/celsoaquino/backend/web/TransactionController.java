package com.celsoaquino.backend.web;

import com.celsoaquino.backend.entity.TransactionReport;
import com.celsoaquino.backend.service.TransactionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    @CrossOrigin(origins = {"http://localhost:9090", "https://frontend-pagnet-ef5r.onrender.com"})
    public Iterable<TransactionReport> listAll() {
        return service.listTotalTransactionNameLoja();
    }
}
