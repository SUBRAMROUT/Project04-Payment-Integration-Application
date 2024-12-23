package com.nt.service;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nt.entity.StudentOrder;
import com.nt.repository.StudentOrderRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;


@Service
public class OrderMgmtServiceImpl implements IOrderMgmtService {
	
	@Autowired
	private StudentOrderRepository studentRepo;
	
	@Value("${razorpay.key.id}")
	private String razorPayKey;
	
	@Value("${razorpay.secret.key}")
	private String razorPaySecret;
	
	private RazorpayClient client;
	
	public StudentOrder createOrder(StudentOrder stuOrder) throws Exception{
		
		JSONObject orderReq = new JSONObject();
		
		orderReq.put("amount", stuOrder.getAmount() * 100); // amount in paisa
		orderReq.put("currency", "INR");
		orderReq.put("receipt", stuOrder.getEmail());
		
		this.client = new RazorpayClient(razorPayKey, razorPaySecret);
		
		//create order in razorpay
	    Order razorPayOrder =client.orders.create(orderReq);
	    
	    System.out.println(razorPayOrder);
	    
	    stuOrder.setRazorpayOrderId(razorPayOrder.get("id"));
	    stuOrder.setOrderStatus(razorPayOrder.get("status"));
	    
	    studentRepo.save(stuOrder);
	    return stuOrder;
	}
	
	public StudentOrder updateOrder(Map<String, String> responsePayLoad) {
		String razorPayOrderId = responsePayLoad.get("razorpay_order_id");
		StudentOrder order = studentRepo.findByRazorpayOrderId(razorPayOrderId);
		order.setOrderStatus("PAYMENT_COMPLETED");
		StudentOrder updatedOrder= studentRepo.save(order);
		return updatedOrder;
	}
	
	

}
