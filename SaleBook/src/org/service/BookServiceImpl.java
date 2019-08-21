package org.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mapper.BookMapper;
import org.pager.PageConstant;
import org.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service("IBookService")
public class BookServiceImpl implements IBookService{
	@Autowired
	private BookMapper book_dao;
	int ps = PageConstant.BOOK_PAGE_SIZE;
	
	//通过分类来查询相应的书录
	@Override
	public PageInfo<Book> findByCategory(String cname, int pc){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cname", cname);//前端传来的请求的分类名称
		map.put("ps", ps);//每页的记录数
		map.put("start", getStart(pc));//当前页面的第一条记录
		List<Book> bookList;
		try {
			bookList = book_dao.findByCategory(map);//返回符合的书录
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		int total = 0;
		try {
			total = book_dao.ByCategory(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.print("=============="+total);
		int totalpage = total / ps;	
		PageInfo<Book> pageinfo = toPageBean(bookList, pc);//转为分页
		pageinfo.setPages(totalpage);//设置该类的总页数
		pageinfo.setTotal(total);;//该类的总书数
		System.out.print("=============="+pageinfo.getPages());
		return pageinfo;
	}
	
	public PageInfo<Book> findByBname(String bname, int pc){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bname", bname);
		map.put("ps", ps);
		map.put("start", getStart(pc));
		List<Book> bookList;
		try {
			bookList = book_dao.findByBname(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		int total = 0;
		try {
			total = book_dao.ByName(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.print("=============="+total);
		int totalpage = total / ps;	
		PageInfo<Book> pageinfo = toPageBean(bookList, pc);//转为分页
		pageinfo.setPages(totalpage);//设置该类的总页数
		pageinfo.setTotal(total);;//该类的总书数
		System.out.print("=============="+pageinfo.getPages());
		return pageinfo;
	}
	
	public PageInfo<Book> findByAuthor(String author, int pc){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("author", author);
		map.put("ps", ps);
		map.put("start", getStart(pc));
		List<Book> bookList;
		try {
			bookList = book_dao.findByAuthor(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		int total = 0;
		try {
			total = book_dao.ByAuthor(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.print("=============="+total);
		int totalpage = total / ps;	
		PageInfo<Book> pageinfo = toPageBean(bookList, pc);//转为分页
		pageinfo.setPages(totalpage);//设置该类的总页数
		pageinfo.setTotal(total);;//该类的总书数
		System.out.print("=============="+pageinfo.getPages());
		return pageinfo;
	}
	
	@Override
	public PageInfo<Book> findAll(int pc) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ps", ps);
		map.put("start", getStart(pc));
		List<Book> bookList;
		try {
			bookList = book_dao.findAll(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		int total = 0;
		try {
			total = book_dao.selectCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.print("=============="+total);
		int totalpage = total / ps;	
		PageInfo<Book> pageinfo = toPageBean(bookList, pc);//转为分页
		pageinfo.setPages(totalpage);//设置该类的总页数
		pageinfo.setTotal(total);;//该类的总书数
		System.out.print("=============="+pageinfo.getPages());
		return pageinfo;
	}
	
	//默认开始页为第0页 limit左开右闭
	//获得当前记录
	private int getStart(int pc) {
		return pc*ps;
	}

	//使用插件数据实现分页
	private PageInfo<Book> toPageBean(List<Book> bookList,int pc){
		
        PageHelper.startPage(pc,ps);
        PageInfo<Book> pageinfo = new PageInfo<>(bookList,ps);
		return pageinfo;
	}
	
	@SuppressWarnings("unused")
	private Map<String,Object> toMap(Book book){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bid", book.getbId());
		map.put("bname", book.getbName());
		map.put("author", book.getAuthor());
		map.put("price", book.getPrice());
		map.put("press", book.getPress());
		map.put("publishtime", book.getPublishTime());
		map.put("cid", book.getCategory().getcId());
		map.put("image_b", book.getImageB());
		map.put("image_s", book.getImageS());
		return map;
	}


}
