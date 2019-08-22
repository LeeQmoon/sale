package org.service;

import java.util.List;

import org.pojo.Order;
import org.pojo.Orderitem;

import com.github.pagehelper.PageInfo;

public interface IOrderService {
	
	public void addOrder(Order order);
	public PageInfo<Order> findAll(String uid,int pc);
	public List<Orderitem> findItem(String orderid);
}
