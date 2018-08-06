package com.example.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.training.model.Staff;

@Service
public class StaffService {

	@Autowired
	private StaffRepository staffRepository;

	public void save(List<Staff> staffs) {
		staffRepository.saveAll(staffs);
	}
}
