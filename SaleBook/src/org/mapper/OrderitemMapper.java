package org.mapper;

import java.util.List;
import java.util.Map;

import org.pojo.Orderitem;

public interface OrderitemMapper {
	//通过订单id来查找对应的item
	public List<Orderitem> findByOid(String orderid);
	//生成订单的同时也把item给填充满
	public void addOrderItem(List<Map<String,Object>> itemList);
}