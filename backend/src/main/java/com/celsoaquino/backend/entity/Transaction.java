package com.celsoaquino.backend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public record Transaction(
        @Id Long id,
        Integer tipo,
        Date data,
        BigDecimal valor,
        Long cpf,
        String cartao,
        Time hora,
        @Column("dono_loja") String donoDaLoja,
        @Column("nome_loja") String nomeDaLoja
) {
    public Transaction withValor(BigDecimal valor) {
        return new Transaction(
                id, tipo, data,
                valor,
                cpf, cartao, hora,
                donoDaLoja, nomeDaLoja);
    }

    public Transaction withData(String data) throws ParseException {
        var dateFormat = new SimpleDateFormat("yyyyMMdd");
        var date = dateFormat.parse(data);

        return new Transaction(
                id, tipo,
                new Date(date.getTime()),
                valor, cpf, cartao, hora,
                donoDaLoja, nomeDaLoja);
    }

    public Transaction withHora(String hora) throws ParseException {
        var dateFormat = new SimpleDateFormat("HHmmss");
        var date = dateFormat.parse(hora);

        return new Transaction(
                id, tipo, data,
                valor, cpf, cartao,
                new Time(date.getTime()),
                donoDaLoja, nomeDaLoja);
    }
}
