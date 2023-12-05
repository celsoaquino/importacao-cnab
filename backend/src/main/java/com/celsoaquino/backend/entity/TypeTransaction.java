package com.celsoaquino.backend.entity;

import java.math.BigDecimal;

public enum TypeTransaction {
    DEBITO(1), BOLETO(2), FINANCIAMENTO(3), CREDITO(4),
    RECEBIMENTO_EMPRESTIMO(5), VENDAS(6), RECEBIMENTO_TED(7),
    RECEBIMENTO_DOC(8), ALUGUEL(9);

    private int type;

    TypeTransaction(int type) {
        this.type = type;
    }

    public BigDecimal getSignal() {
        return switch (type) {
            case 1, 4, 5, 6, 7, 8 -> BigDecimal.ONE;
            case 2, 3, 9 -> BigDecimal.ONE.negate();
            default -> BigDecimal.ZERO;
        };
    }

    public static TypeTransaction findByType(int type) {
        for (TypeTransaction transaction: values()) {
            if (transaction.type == type) {
                return transaction;
            }
        }
        throw new IllegalArgumentException("Invalid type: " + type);
    }
}
