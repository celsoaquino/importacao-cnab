package com.celsoaquino.backend.repository;

import com.celsoaquino.backend.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> findAllByOrderByNomeDaLojaAscIdDesc();
}
