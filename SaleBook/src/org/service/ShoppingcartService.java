package org.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.mapper.ShoppingcartMapper;
import org.pojo.Shoppingcart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("IShoppingcartService")
public class ShoppingcartService implements IShoppingcartService{
	@Autowired
	private ShoppingcartMapper cart_dao;
	
	////增加一个购物车，若存在了，则更改数目
	@Override
	public Shoppingcart add(Shoppingcart shoppingcart) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uid", shoppingcart.getuId());
		map.put("bid", shoppingcart.getbId());
		map.put("cartid",shoppingcart.getCartId());
		map.put("quantity", shoppingcart.getQuantity());
		
		try {
			cart_dao.addCartItem(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		updateShoppingCar(shoppingcart);//更新数据库
		return shoppingcart;
	}

	//controller传来uid来展示所有的购物车
	public List<Shoppingcart> myCart(String uid) {
		try {
			return cart_dao.findByUid(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	////为了在增添的时候查询该购物车是否已经存在
	@Override
	public Shoppingcart getByUidAndBid(String uid, String bid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uid", uid);
		map.put("bid", bid);
		Shoppingcart cart = new Shoppingcart();
		try {
			cart = cart_dao.findByUidAndBid(map);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return cart;
	}

	////更新数量以及合计金额
	@Override
	public void updateShoppingCar(Shoppingcart cart) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cartid", cart.getCartId());
		map.put("quantity", cart.getQuantity());
		map.put("subtotal", cart.getSubTotal());
		
		try {
			cart_dao.update(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//获得书的价格
	@Override
	public double getPri(String bid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bid", bid);
		cart_dao.getPrice(map);
		return cart_dao.getPrice(map);
	}

	//删除某一个购物车 传入用户的id以及书的id 记得在前端警告是否登录
	@Override
	public void deleteShoppingcart(String uid,String bid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uid", uid);
		map.put("bid", bid);
		try {
			cart_dao.delete(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
