package org.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.pojo.Book;
import org.pojo.Shoppingcart;
import org.pojo.User;
import org.service.IShoppingcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class ShoppingcartController {
	@Autowired
	private IShoppingcartService cart_service;
	
	//更新购物车 更新之后展示该用户的购物车
	@RequestMapping("/updateQuantity.action")
	public String updateQuantity(ModelMap map,String cartId,int quantity,HttpServletRequest request){
		Shoppingcart cart = cart_service.updateQuantity(cartId, quantity);
		Map<String,Object> temp = new HashMap<String,Object>();
		temp.put("quantity", cart.getQuantity());
		temp.put("subtotal", cart.getSubTotal());
		return showCart(map,request);
	}
	
	//增加购物车
	@RequestMapping("/addcart.action")
	public String add(ModelMap map,HttpServletRequest request,Shoppingcart cart,Book book){
		User user = (User) request.getSession().getAttribute("sessionUser");
		if(user==null) {//未登录不可以增加
			request.setAttribute("msg", "未登录！");
			return "/welcome";
		}
		cart.setBook(book);
		cart.setUser(user);
		//调用service，完成添加
		cart_service.add(cart);
		//增加之后 展示购物车
		return showCart(map,request);
	}
	
	//展示本用户的购物车
	@RequestMapping("/showCart.action")
	public String showCart(ModelMap map,HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("sessionUser");
		if(user==null)//未登录不可以展示
			return "/welcome";
		String uid = user.getuId();
		List<Shoppingcart> cartList = cart_service.myCart(uid);
		map.addAttribute("cartItemList", cartList);
		return "/jsp/cartlist";
	}
	
}
