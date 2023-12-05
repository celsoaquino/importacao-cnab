package com.celsoaquino.backend.service;

import com.celsoaquino.backend.entity.TransactionReport;
import com.celsoaquino.backend.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionReport> listTotalTransactionNameLoja() {
        var transactions = transactionRepository.findAllByOrderByNomeDaLojaAscIdDesc();
        var reportMap = new LinkedHashMap<String, TransactionReport>();


        transactions.forEach(transaction -> {
            String nomeDaloja = transaction.nomeDaLoja();
            var valor = transaction.valor();

            reportMap.compute(nomeDaloja, (key, existingReport) -> {
                var report = (existingReport != null) ? existingReport : new TransactionReport(key, BigDecimal.ZERO, new ArrayList<>());

                return report.addTotal(valor).addTransaction(transaction.withValor(valor));
            });
        });

        return new ArrayList<>(reportMap.values());
    }
}
