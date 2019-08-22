package org.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.pojo.Book;
import org.pojo.User;
import org.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller //标注为控制器，所以不需要再使用继承了
@RequestMapping("/book")
public class BookController {
	@Autowired
	private IBookService book_service;
	
	//前端如何把页数跟类名传过来
/*	//把当前页数传来，url也传来，调用后端的，查询这页所对应的书录
	@RequestMapping("/findByCategory.action")
	public String findByCategory(@RequestParam(required=true,defaultValue="0")Integer page,String cname,ModelMap map,HttpServletRequest request){
		//前端传来当前页以及查询的名字 如何从前端把页面传到后台
		PageInfo<Book> pb = book_service.findByCategory(cname, page);
		List<Book> list = pb.getList();
		map.put("list", list);//把modelandview返回给前端
		map.put("pageinfo", pb);//把modelandview返回给前端
		
		return "/jsp/list";
	}
	
	@RequestMapping("/findByAuthor.action")
	public String findByAuthor(@RequestParam(required=true,defaultValue="0")Integer page,String author,ModelMap map,HttpServletRequest request){
		PageInfo<Book> pb = book_service.findByAuthor(author, page);
		List<Book> list = pb.getList();

		map.put("list", list);//把modelandview返回给前端
		map.put("pageinfo", pb);//把modelandview返回给前端
		
		return "/jsp/list";
	}
	
	@RequestMapping("/findByBname.action")
	public String findByBname(@RequestParam(required=true,defaultValue="0")Integer page,String bname,ModelMap map,HttpServletRequest request){

		PageInfo<Book> pb = book_service.findByBname(bname, page);
		List<Book> list = pb.getList();
		
		map.put("list", list);//把modelandview返回给前端
		map.put("pageinfo", pb);//把modelandview返回给前端
		
		return "/jsp/list";
	}*/
	
	//按首页之后会出现所有书
	@RequestMapping("/findAll.action")
	public String findAll(@RequestParam(required=true,defaultValue="0")Integer page,ModelMap map,HttpServletRequest request){
		PageInfo<Book> pb = book_service.findAll(page);
		List<Book> list = pb.getList();
		
		map.put("list", list);//把modelandview返回给前端
		map.put("pageinfo", pb);//把modelandview返回给前端
		
		return "/jsp/showBook";
	}
}
