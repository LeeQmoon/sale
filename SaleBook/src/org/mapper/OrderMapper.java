package org.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.pojo.Order;

public interface OrderMapper {
	
	//为了关联查询使用
	public Order load(String oid) throws SQLException;
	//增加订单
	public void add(Map<String,Object> map) throws SQLException;
	//查找用户所有的订单
	public List<Order> findAll(Map<String,Object> map) throws SQLException;
	//
	public void updateTotal(Map<String,Object> map)throws SQLException;
}