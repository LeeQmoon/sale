package org.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.pojo.Shoppingcart;

public interface ShoppingcartMapper {
	//传入id以及数量 更新数量跟合计金额 用
	public void update(Map<String,Object> map) throws SQLException;
	//传入id跟quantity  修改数量&&增加 用
	public void addCartItem(Map<String,Object> map) throws SQLException;
	//获得书的价格  更新&&增加 用
	public Double getPrice(Map<String,Object> map);
	//为了在删除之前进行查询是否存在 为了检查增加之前是否存在 用
	public Shoppingcart findByUidAndBid(Map<String,Object> map) throws SQLException;
	
	//删除购物车 传入购物车的id
	public void delete(Map<String,Object> map) throws SQLException;
	//通过cart_id来寻找购物车，方便service层的更新时调用
	public Shoppingcart findByCartId(String cartid) throws SQLException;
	//通过uid来查询该用户所拥有的购物车
	public List<Shoppingcart> findByUid(String uid) throws SQLException;
	
	
}