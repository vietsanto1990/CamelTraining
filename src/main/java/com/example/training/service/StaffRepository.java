package com.example.training.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.training.entity.Staff;

public interface StaffRepository extends PagingAndSortingRepository<Staff, Integer> {

	@Query(value = "SELECT * FROM STAFF WHERE STAFFID = ?1", nativeQuery = true)
	Optional<Staff> findByStaffId(int staffId);
}
