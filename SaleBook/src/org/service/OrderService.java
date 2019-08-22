package org.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.mapper.BookMapper;
import org.mapper.OrderMapper;
import org.mapper.OrderitemMapper;
import org.pager.PageConstant;
import org.pojo.Book;
import org.pojo.Order;
import org.pojo.Orderitem;
import org.pojo.Shoppingcart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

//按了确认提交订单就是支付完成了，按了取消就是没有完成，不要支付功能了

@Service("IOrderService")
public class OrderService implements IOrderService{
	@Autowired
	private OrderMapper order_dao;
	private int ps = PageConstant.ORDER_PAGE_SIZE;//每页固定有几个订单
	@Autowired
	private OrderitemMapper orderitem_dao;
	@Autowired
	private BookMapper book_dao;
	
	//查找某用户的所有订单 在前端判断是否登录 阻止未登录就查询
	@Override
	public PageInfo<Order> findAll(String uid, int pc) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uid", uid);
		List<Order> orderkList;
		try {
			orderkList = order_dao.findAll(map);//返回该用户的所有订单
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		PageInfo<Order> pageinfo = toPageBean(orderkList, pc);//转为分页
		return pageinfo;
	}

	
	public void addOrder(Order order) {
		
		//订单增加完成了 还要给item库遍历赋值
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderid", order.getOrderId());
		map.put("ordertime", order.getOrderTime());
		map.put("status", order.getStatus());
		map.put("address", order.getAddress());
		map.put("uid", order.getUser().getuId());
		try {
			order_dao.add(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("订单");
		System.out.println("orderid===="+order.getOrderId()+" ordertime==="+order.getOrderTime()+
		"  status=="+order.getStatus());
		
		double total = 0;
		//计算总金额的
		for(Orderitem temp:order.getItem())
		{
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("bid", temp.getbId());
			map1.put("itemid", temp.getItemId());
			map1.put("quantity", temp.getQuantity());
			map1.put("orderid", temp.getOrderId());
			
			//设置该条目书的名字
			try {
				temp.setbName(book_dao.findByBid(temp.getbId()).getbName());
				map1.put("bname", book_dao.findByBid(temp.getbId()).getbName());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//设置该书的单价
			try {
				temp.setPrice(book_dao.findByBid(temp.getbId()).getPrice());
				map1.put("price", book_dao.findByBid(temp.getbId()).getPrice());
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			orderitem_dao.add(map1);//先插入部分数据给item 再计算小计
			orderitem_dao.updateSub(map1);//插入小计
			double sub = orderitem_dao.getSub(map1);//拿出没条目的小计
			temp.setSubTotal(sub);
			orderitem_dao.updateSub(map1);//计算每个条目的小计
			total += sub;//加上去
			System.out.println("订单明细");
			System.out.println("itemid===="+temp.getItemId()+" quantity==="+temp.getQuantity()+"  subtotal"+temp.getSubTotal()
			+"  bname=="+temp.getbName()+"price=="+temp.getPrice());
		}
		//把总价格赋给order
		map.put("total", total);
		try {
			order_dao.updateTotal(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//至此完成数据库的存储
		
	}

	//默认开始页为第0页 limit左开右闭
	//获得当前记录
	@SuppressWarnings("unused")
	private int getStart(int pc) {
		return pc*ps;
	}

	//使用插件数据实现分页
	private PageInfo<Order> toPageBean(List<Order> orderList,int pc){
		
        PageHelper.startPage(pc,ps);
        PageInfo<Order> pageinfo = new PageInfo<>(orderList,ps);
		return pageinfo;
	}

	//查看某订单详情
	@Override
	public List<Orderitem> findItem(String orderid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderid", orderid);
		List<Orderitem> orderkList = new ArrayList<Orderitem>();
		for(Orderitem item:orderitem_dao.findByUidAndOid(map)) {
			//返回该用户的所有订单
			orderkList.add(item);
			System.out.println("itemid===="+item.getItemId()+" quantity==="+item.getQuantity()+"  subtotal"+item.getSubTotal()
			+"  bname=="+item.getbName()+"price=="+item.getPrice());
		}
		
		return orderkList;
		
	}
}
