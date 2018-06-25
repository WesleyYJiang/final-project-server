package com.example.finalserver.repositories;

import com.example.finalserver.models.Bill;

import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Integer> {
}
