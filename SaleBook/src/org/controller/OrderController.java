package org.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.pojo.Order;
import org.pojo.Orderitem;
import org.pojo.User;
import org.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//疑问:导入json包之后，需要配置mybatis以及spring吗
@Controller //标注为控制器，所以不需要再使用继承了
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private IOrderService order_service;
	
	//订单状态1 表示支付，2 表示未支付
	//没有地址，地址是默认值
	//应传入所选择书的id以及数量  用json传来转换成String 后端进行转换 以及用户的id
	@RequestMapping("/addOrder.action")
	@ResponseBody
	public Map<String,Object> addOrder(ModelMap map,HttpServletRequest request){
		//String jsonStr,
		/*JSONArray jsonArr = JSONArray.fromObject(jsonStr);
		List<Orderitem> list = new ArrayList<Orderitem>();
		User user = (User) request.getSession().getAttribute("sessionUser");
		//创建order
		Order order = new Order();
		//随机分配订单号 唯一
		order.setOrderId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
		order.setOrderTime(String.format("%tF %<tT", new Date()));//生成订单的时间
		order.setStatus(2);//最初都设置为未支付
		
		for(int i = 0; i < jsonArr.size(); i++ ) {
			
			//转json对象
			JSONObject jsonObj = jsonArr.getJSONObject(i);
			//取值
			Orderitem item = new Orderitem();
			item.setbId((String) jsonObj.get("bid"));//获取书的id
			//获取每一本书的数量
			item.setQuantity((Integer) jsonObj.get("quantity"));
			item.setItemId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
			item1.setOrderId(order.getOrderId());
			list.add(item);//插入orderitem里面		
		}
		order.setItem(list);//订单中的各种条目
		order.setUser(user);//订单所属的用户
		order.setuId(user.getuId());
		//传一个order给Service
		order_service.addOrder(order);//传order过去
		System.out.print("增加成功！");
		return map;
		
		*/
		
		//创建order
		Order order = new Order();
		//随机分配订单号 唯一
		order.setOrderId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
		List<Orderitem> list = new ArrayList<Orderitem>();
		Orderitem item1 = new Orderitem();
		item1.setbId("1");
		item1.setQuantity(2);
		item1.setItemId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
		item1.setOrderId(order.getOrderId());
		list.add(item1);
		Orderitem item2 = new Orderitem();
		item2.setbId("2");
		item2.setQuantity(3);
		item2.setOrderId(order.getOrderId());
		item2.setItemId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
		list.add(item2);
		User user = (User) request.getSession().getAttribute("sessionUser");
		order.setOrderTime(String.format("%tF %<tT", new Date()));//生成订单的时间
		order.setStatus(2);//最初都设置为未支付
		order.setItem(list);//订单中的各种条目
		order.setUser(user);//订单所属的用户
		order.setuId(user.getuId());
		//传一个order给Service
		order_service.addOrder(order);//传order过去
		System.out.print("增加成功！");
		return map;
	
	}
	
	//查看所有订单
	@RequestMapping("/findAllOrder.action")
	public String findAllOrder(@RequestParam(required=true,defaultValue="0")Integer page,ModelMap map,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("sessionUser");
		PageInfo<Order> orderlist = order_service.findAll(user.getuId(),0);
		List<Order>list = orderlist.getList();
		for(Order order:list) {
			System.out.print("orderid==="+order.getOrderId()+"  ordertime==="+order.getOrderTime()+"  totalmoney==="+order.getTotalMoney()
			+"  status==="+order.getStatus()+"  uid=="+order.getuId());
		}

		map.put("list", list);//把modelandview返回给前端
		map.put("pageinfo", orderlist);//把modelandview返回给前端
		return "/jsp/showorder";
	}
	
	//查看订单详情
		@RequestMapping("/findOrderItem.action")
		public String findOrderItem(@RequestParam(required=true,defaultValue="76850E78B638480AB52D964DFCC76BC4")String orderid,
				ModelMap map,HttpServletRequest request) {
			User user = (User) request.getSession().getAttribute("sessionUser");
			List<Orderitem>list = order_service.findItem(orderid);
			
			map.put("list", list);//把modelandview返回给前端
			return "/jsp/showitem";
		}
	
}
