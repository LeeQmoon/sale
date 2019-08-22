package org.mapper;

import java.util.List;
import java.util.Map;

import org.pojo.Orderitem;

public interface OrderitemMapper {
	//计算每个item的小计 传入itemid bid  用
	public void updateSub(Map<String,Object> map);
	//获得每个条目的小计 传入itemid 用
	public Double getSub(Map<String,Object> map);
	//生成订单的同时也把item给填充满  用
	public void add(Map<String,Object>item);
	
	//通过订单id来查找对应的item 用
	public List<Orderitem> findByUidAndOid(Map<String,Object>item);
	
}