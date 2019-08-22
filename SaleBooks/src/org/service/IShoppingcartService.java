package org.service;

import java.util.List;

import org.pojo.Shoppingcart;

public interface IShoppingcartService {
	//返回给controller一个pojo使得前台拿来更改
	public Shoppingcart updateQuantity(String cartid,int quantity);
	//清空购物车
	//public void batchDelete(String cartItemIds);
	//增加一个购物车
	public void add(Shoppingcart shoppingcart);
	//展示我的所有购物车
	public List<Shoppingcart> myCart(String uid);

}
