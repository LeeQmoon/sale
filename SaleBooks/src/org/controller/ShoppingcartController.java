package org.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.pojo.Book;
import org.pojo.Shoppingcart;
import org.pojo.User;
import org.service.IShoppingcartService;
import org.service.ShoppingcartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //标注为控制器，所以不需要再使用继承了
@RequestMapping("/book")
public class ShoppingcartController {
	private IShoppingcartService cart;
	
	//更新之后在页面自动显示更新结果
	@RequestMapping("/updateQuantity.action")
	public Map<String,Object> updateQuantity(String cartid,int quantity,ModelMap map){
		Shoppingcart temp= cart.updateQuantity(cartid, quantity);
		double sub = temp.getQuantity() * temp.getBook().getPrice();
		temp.setSubTotal(sub);//计算合计并更改
		map.put("quantity", temp.getQuantity());
		map.put("subtotal", temp.getSubTotal());
		return map;
	}
	
	//前端传来cartitem,以及所含的book
	@RequestMapping("/add.action")
	public String add(Shoppingcart cartitem,Book book,ModelMap map,HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("sessionUser");
		cartitem.setBook(book);
		cartitem.setUser(user);		
		cart.add(cartitem);
		return myCart(map,request);
	}
	
	//点击购物车之后会出现该用户所有的购物车清单
	@RequestMapping("/myCart.action")
	public String myCart(ModelMap map,HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("sessionUser");
		String uid = user.getuId();
		List<Shoppingcart> cartList = cart.myCart(uid);
		map.addAttribute("cartList", cartList);
		return "/jsp/cartlist";
	}

}
