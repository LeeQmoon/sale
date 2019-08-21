package org.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.pojo.Shoppingcart;

public interface ShoppingcartMapper {
	//传入id以及数量
	public void updateQuantity(Map<String,Object> map) throws SQLException;
	//传入Shoppingcart
	public void addCartItem(Map<String,Object> map) throws SQLException;
	//删除购物车
	public void delete(Map<String,Object> map) throws SQLException;
	//通过cart_id来寻找购物车，方便service层的更新时调用
	public Shoppingcart findByCartId(String cartid) throws SQLException;
	//通过uid来查询该用户所拥有的购物车
	public List<Shoppingcart> findByUid(String uid) throws SQLException;
	//为了在删除之前进行查询是否存在
	public Shoppingcart findByUidAndBid(Map<String,Object> map) throws SQLException;
}