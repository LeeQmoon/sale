package org.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.pojo.Order;

public interface OrderMapper {
	
	//供分页使用
	public Integer ByUid(Map<String, Object> map) throws SQLException;
	//供分页使用
	public Integer ByStatus(Map<String,Object> map) throws SQLException;
	//为了关联查询使用
	public Order load(String oid) throws SQLException;
	//更新支付状态
	public void updateStatus(Map<String,Object> map)throws SQLException;
	//public void updateStatus(Map<String,Object> map) throws SQLException;
	//增加订单
	public void add(Map<String,Object> map) throws SQLException;
	//通过订单的状态找订单
	public List<Order> findByStatus(Map<String,Object> map) throws SQLException;
	//查找某用户的订单
	public List<Order> findByUser(Map<String,Object> map) throws SQLException;
	//查找所有的订单
	public List<Order> findAll(Map<String,Object> map) throws SQLException;
	
}