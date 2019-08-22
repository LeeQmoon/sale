package org.service;

import org.pojo.Order;

import com.github.pagehelper.PageInfo;

public interface IOrderService {
	//更新订单状态
	//public void updateStatus(String orderid,int status);
	////根据支付状态查询订单
	//public PageInfo<Order> findByStatus (int uid,int stauts ,int pc);
	//生成订单
	public void addOrder(Order order);
	//查询我的订单
	//public PageInfo<Order> findAll(String uid,int pc);
	//查询所有订单
	//public PageInfo<Order> findAll(int pc);
	//根据支付状态查询订单
	//public int findStatus(String oid);
	
}
