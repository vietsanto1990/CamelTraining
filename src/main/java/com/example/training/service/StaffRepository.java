package com.example.training.service;

import org.springframework.data.repository.CrudRepository;

import com.example.training.entity.Staff;

public interface StaffRepository extends CrudRepository<Staff, Integer> {

}
