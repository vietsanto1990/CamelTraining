package com.example.training.service;

import java.util.ArrayList;
import java.util.Iterator;
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

	public List<Staff> fetchAll() {
		List<Staff> staffs = new ArrayList<>();
		Iterator<Staff> iterator = staffRepository.findAll().iterator();
		while (iterator.hasNext()) {
			staffs.add(iterator.next());
		}
		return staffs.isEmpty() ? null : staffs;
	}
}
