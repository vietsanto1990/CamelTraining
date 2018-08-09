package com.example.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.training.entity.Staff;

@Service
public class StaffService<T> {

	@Autowired
	private StaffRepository staffRepository;

	public void save(List<Staff> staffs) {
		staffRepository.saveAll(staffs);
	}

	public Iterable<T> fetchStaff() {
		return (Iterable<T>) staffRepository.findAll();
	}

	public int countStaffs() {
		return (int) staffRepository.count();
	}

	public T getDetail(int id) {
		return (T) staffRepository.findByStaffId(id).orElse(null);
	}
}
