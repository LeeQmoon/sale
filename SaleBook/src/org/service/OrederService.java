package org.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mapper.OrderMapper;
import org.mapper.OrderitemMapper;
import org.pager.PageConstant;
import org.pojo.Book;
import org.pojo.Order;
import org.pojo.Orderitem;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

//按了确认提交订单就是支付完成了，按了取消就是没有完成，不要支付功能了

public class OrederService implements IOrderService{
	@Autowired
	private OrderMapper order_dao;
	private int ps = PageConstant.ORDER_PAGE_SIZE;//每页固定有几个订单
	@Autowired
	private OrderitemMapper orderitem_dao;
	//支付之后需要更新订单状态
	@Override
	public void updateStatus(String orderid, int status) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderid", orderid);
		map.put("status", status);
		try {
			order_dao.updateStatus(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//按状态查询订单
	@Override
	public PageInfo<Order> findByStatus(int uid,int status, int pc) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uid", uid);
		map.put("status", status);
		map.put("start", getStart(pc));
		List<Order> orderList;
		try {
			orderList = order_dao.findByStatus(map);//返回该用户某个状态的订单
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
		int total = 0;
		try {
			total = order_dao.ByStatus(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.print("=============="+total);
		int totalpage = total / ps;	
		PageInfo<Order> pageinfo = toPageBean(orderList, pc);//转为分页
		pageinfo.setPages(totalpage);//设置该类的总页数
		pageinfo.setTotal(total);;//该类的总书数
		System.out.print("=============="+pageinfo.getPages());
		return pageinfo;//返回某一页
	}
	
	//查找某用户的所有订单
	@Override
	public PageInfo<Order> findAll(String uid, int pc) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uid", uid);
		map.put("ps", ps);
		map.put("start", getStart(pc));
		List<Order> orderkList;
		try {
			orderkList = order_dao.findAll(map);//返回该用户的所有订单
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		int total = 0;
		try {
			total = order_dao.ByUid(map);//返回总的订单数来分页
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.print("=============="+total);
		int totalpage = total / ps;	
		PageInfo<Order> pageinfo = toPageBean(orderkList, pc);//转为分页
		pageinfo.setPages(totalpage);//设置该类的总页数
		pageinfo.setTotal(total);;//该类的总书数
		System.out.print("=============="+pageinfo.getPages());
		return pageinfo;
	}

	@Override
	public void addOrder(Order order) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderid", order.getOrderId());
		map.put("ordertime", order.getOrderTime());
		map.put("total", order.getTotalMoney());
		map.put("status", order.getStatus());
		map.put("address", order.getAddress());
		map.put("uid", order.getUser().getuId());
		try {
			order_dao.add(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Orderitem> orderItemList = order.getItem();//获得order的itemlist属性
		List<Map<String,Object>> itemList = new ArrayList<Map<String,Object>>();//存item map序列
		for(Orderitem orderItem : orderItemList){//遍历赋值
			Map<String,Object> temp = new HashMap<String,Object>();
			temp.put("orderItemId", orderItem.getItemId());
			temp.put("quantity", orderItem.getQuantity());
			temp.put("subtotal", orderItem.getSubTotal());
			temp.put("bid", orderItem.getBook().getbId());
			temp.put("bname", orderItem.getBook().getbName());
			temp.put("currPrice", orderItem.getBook().getPrice());
			temp.put("imagesb", orderItem.getBook().getImageB());
			temp.put("orderid", orderItem.getOrder().getOrderId());
			itemList.add(temp);
		}
		orderitem_dao.addOrderItem(itemList);//调用item的add来填充数据库
	}

	//默认开始页为第0页 limit左开右闭
	//获得当前记录
	private int getStart(int pc) {
		return pc*ps;
	}

	//使用插件数据实现分页
	private PageInfo<Order> toPageBean(List<Order> orderList,int pc){
		
        PageHelper.startPage(pc,ps);
        PageInfo<Order> pageinfo = new PageInfo<>(orderList,ps);
		return pageinfo;
	}
}
