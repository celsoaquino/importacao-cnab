package com.celsoaquino.backend;

import com.celsoaquino.backend.entity.Transaction;
import com.celsoaquino.backend.repository.TransactionRepository;
import com.celsoaquino.backend.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService service;

    @Mock
    private TransactionRepository repository;

    @Test
    public void testListTotalTransactionNameLoja() {
        final String lojaA = "Loja A", lojaB = "Loja B";

        var transacao1 = new Transaction(
            1L, 1, new Date(System.currentTimeMillis()),
            BigDecimal.valueOf(100), 123456789L,
            "1234-5678-9012-3456",
            new Time(System.currentTimeMillis()),
            "Dono da Loja A", lojaA
        );

        var transacao2 = new Transaction(
            2L, 1, new Date(System.currentTimeMillis()),
            BigDecimal.valueOf(50.00), 123456789L,
            "9876-5678-9012-3456",
            new Time(System.currentTimeMillis()),
            "Dono da Loja B", lojaB
        );

        var transacao3 = new Transaction(
            3L, 1, new Date(System.currentTimeMillis()),
            BigDecimal.valueOf(75.00), 111456789L,
            "1111-2222-3333-4444",
            new Time(System.currentTimeMillis()),
            "Dono da Loja A", lojaA
        );

        var mockTransacoes = List.of(transacao1, transacao2, transacao3);

        when(repository.findAllByOrderByNomeDaLojaAscIdDesc())
            .thenReturn(mockTransacoes);

        var reports = service.listTotalTransactionNameLoja();

        assertEquals(2, reports.size());

        reports.forEach( report -> {
            if (report.nomeDaLoja().equals(lojaA)) {
                assertEquals(2, report.transactions().size());
                assertEquals(BigDecimal.valueOf(175.00), report.total());
                assertTrue(report.transactions().contains(transacao1));
                assertTrue(report.transactions().contains(transacao3));

            } else if (report.nomeDaLoja().equals(lojaB)) {
                assertEquals(1, report.transactions().size());
                assertEquals(BigDecimal.valueOf(50.00), report.total());
                assertTrue(report.transactions().contains(transacao2));

            }
        });
    }
}
