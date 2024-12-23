package com.nt.service;

import java.util.Map;

import com.nt.entity.StudentOrder;

public interface IOrderMgmtService {
	public StudentOrder createOrder(StudentOrder order) throws Exception;
	public StudentOrder updateOrder(Map<String, String> responsePayLoad);

}
