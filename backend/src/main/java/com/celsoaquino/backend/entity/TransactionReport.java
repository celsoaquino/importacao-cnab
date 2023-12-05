package com.celsoaquino.backend.entity;

import java.math.BigDecimal;
import java.util.List;

public record TransactionReport(
        String nomeDaLoja,
        BigDecimal total,
        List<Transaction> transactions
) {

    public TransactionReport addTotal(BigDecimal valor) {
        return new TransactionReport(nomeDaLoja, total.add(valor), transactions);
    }

    public TransactionReport addTransaction(Transaction transaction) {
        transactions.add(transaction);
        return new TransactionReport(nomeDaLoja, total, transactions);
    }
}
