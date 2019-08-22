package org.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.mapper.BookMapper;
import org.pojo.Book;
import org.pojo.Shoppingcart;
import org.pojo.User;
import org.service.IShoppingcartService;
import org.service.ShoppingcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //标注为控制器，所以不需要再使用继承了
@RequestMapping("/cart")
public class ShoppingcartController {
	@Autowired
	private IShoppingcartService cart;
	
	//前端传来所选book的id以及所选择书的数量,此函数的意思：如果存在该购物车了就直接增加相应的数量，不存在则增加一个购物车
	@RequestMapping("/add.action")
	@ResponseBody
	public Map<String,Object> add(String bid,int quantity,ModelMap map,HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("sessionUser");
		Shoppingcart shoppingCar = cart.getByUidAndBid(user.getuId(),bid);//看看数据库本身有么有这个购物车
	    if(shoppingCar == null){//没有的话
	        	Shoppingcart shoppingCar1 = new Shoppingcart();
	            shoppingCar1.setUser(user);
	            shoppingCar1.setuId(user.getuId());//设置用户id
	            shoppingCar1.setbId(bid);//设置书的id
	            
	            shoppingCar1.setQuantity(quantity);
	            shoppingCar1.setSubTotal(shoppingCar1.getQuantity()*cart.getPri(bid));
	            shoppingCar1.setCartId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
	            cart.add(shoppingCar1);
	            System.out.println("我进入了");
	            System.out.print("cartid====="+shoppingCar1.getCartId()+"  Bookid"+shoppingCar1.getbId()+"  quantity" +shoppingCar1.getQuantity()
	            +"  subtotal"+shoppingCar1.getSubTotal());
	        }
	        else{//有的话
	            shoppingCar.setQuantity(shoppingCar.getQuantity()+quantity);
	            shoppingCar.setSubTotal(shoppingCar.getQuantity()*cart.getPri(bid));
	            cart.updateShoppingCar(shoppingCar);
	            System.out.println("已经存在！");
	            System.out.print("cartid====="+shoppingCar.getCartId()+"  Bookid"+shoppingCar.getbId()+"  quantity" +shoppingCar.getQuantity()
	            +"  subtotal==="+shoppingCar.getSubTotal());
	        }
	    //这段话是为了测试使用，返回给前端表明已经添加成功
	        Map<String, Object> resultMap = new HashMap<String,Object>();
	        resultMap.put("result","success");
	        System.out.println("我返回了");
	        return resultMap;
	}

	//点击购物车之后会出现该用户所有的购物车清单
	@RequestMapping("/myCart.action")
	public String myCart(ModelMap map,HttpServletRequest request){
		
		User user = (User) request.getSession().getAttribute("sessionUser");
		String uid = user.getuId();
		List<Shoppingcart> cartList = cart.myCart(uid);
		map.addAttribute("cartList", cartList);//返回购物车列表
		
        return "/jsp/showcart";
	}

	//删除某一个购物车 传入书的id以及用户的id
	@RequestMapping("/delete.action")
	public String delete(String bid,ModelMap map,HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("sessionUser");
		String uid = user.getuId();
		cart.deleteShoppingcart(uid, bid);
		System.out.print("已经删除");
		//删除之后展示我的购物车
        return "/jsp/showcart";
	}
}
