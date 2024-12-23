package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.StudentOrder;

public interface StudentOrderRepository extends JpaRepository<StudentOrder, Integer> {
	
	public StudentOrder  findByRazorpayOrderId(String OrderId);

}
