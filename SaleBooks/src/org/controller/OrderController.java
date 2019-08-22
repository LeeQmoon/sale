package org.controller;

import org.pojo.Order;
import org.springframework.ui.ModelMap;

import com.github.pagehelper.PageInfo;

public class OrderController {
	//更新订单状态
	public String updateStatus(String orderid,int status) {		
		return " ";
	}
	////根据支付状态查询订单
	public String findByStatus (int stauts){
		return null;
		
	}
	//生成订单
	//不需要传数据
	public String addOrder() {
		return null;
		
	}
	//查询我的订单 
	//即点击订单管理后出现的该用户的所有订单
	//需要传数据
	//会向前端传回modelandview 里面含有数据
	public String findAll(ModelMap map){
		return null;
		
		
	}
	//查询所有订单
	//public PageInfo<Order> findAll(int pc);
	//根据支付状态查询订单
	//public int findStatus(String oid);
}
