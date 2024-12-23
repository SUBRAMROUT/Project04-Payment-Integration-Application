package com.nt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="STUDENT_ORDERS")
public class StudentOrder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer orderId;
	private String name;
	private String email;
	private String phoneNo;
	private String course;
	private Integer amount;
	private String orderStatus;
	private String razorpayOrderId;
	
	

}
