package org.service;

import java.util.List;

import org.pojo.Shoppingcart;

public interface IShoppingcartService {
	
	//增加一个购物车，若存在了，则更改数目
	public Shoppingcart add(Shoppingcart cart);
	//展示我的所有购物车
	public List<Shoppingcart> myCart(String uid);
	//为了在增添的时候查询该购物车是否已经存在
	public Shoppingcart getByUidAndBid(String uid,String bid);
	//获得该购物车书的价格
	public double getPri(String bid);
	//更新数量以及合计金额
	public void updateShoppingCar(Shoppingcart cart);
	//删除某一个购物车
	public void deleteShoppingcart(String uid,String bid);
}
