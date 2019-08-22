package org.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.mapper.ShoppingcartMapper;
import org.pojo.Shoppingcart;
import org.springframework.beans.factory.annotation.Autowired;

public class ShoppingcartService implements IShoppingcartService{
	@Autowired
	private ShoppingcartMapper cart_dao;
	
	//更新购物中的书的数目
	@Override
	public Shoppingcart updateQuantity(String cartid, int quantity) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cartid", cartid);
		map.put("quantity", quantity);
		try {
			cart_dao.updateQuantity(map);//更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			return cart_dao.findByCartId(cartid);//返回给前台来更新、展示
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//增加之前检查是否有相同的购物车
	@SuppressWarnings("null")
	@Override
	public void add(Shoppingcart shoppingcart) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("uid", shoppingcart.getUser().getuId());
			map.put("bid", shoppingcart.getBook().getbId());
			Shoppingcart cart = cart_dao.findByUidAndBid(map);
			if(cart==null){
				//数据库中不存在则进行赋cart_id，随机分配
				map.put("cartid", UUID.randomUUID().toString().replace("-", "").toUpperCase());
				map.put("quantity", cart.getQuantity());
				cart_dao.addCartItem(map);
			}else{
				//若存在则在相应的购物车上进行数量自动加倍
				int quantity = cart.getQuantity()+cart.getQuantity();
				map.put("quantity", quantity);
				map.put("cartid", shoppingcart.getCartId());
				cart_dao.updateQuantity(map);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	//controller传来uid来展示所有的购物车
	@Override
	public List<Shoppingcart> myCart(String uid) {
		try {
			return cart_dao.findByUid(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
