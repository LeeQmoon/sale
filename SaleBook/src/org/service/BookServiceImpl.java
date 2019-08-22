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
		List<Book> bookList;
		try {
			bookList = book_dao.findByCategory(map);//返回符合的书录
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		PageInfo<Book> pageinfo = toPageBean(bookList, pc);//转为分页
		return pageinfo;
	}
	
	//通过名字来查询书本--精确查询
	public PageInfo<Book> findByBname(String bname, int pc){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bname", bname);
		List<Book> bookList;
		try {
			bookList = book_dao.findByBname(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		PageInfo<Book> pageinfo = toPageBean(bookList, pc);//转为分页
		return pageinfo;
	}
	//通过作者来查询
	public PageInfo<Book> findByAuthor(String author, int pc){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("author", author);
		List<Book> bookList;
		try {
			bookList = book_dao.findByAuthor(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		PageInfo<Book> pageinfo = toPageBean(bookList, pc);//转为分页
		return pageinfo;
	}
	
	//查询所有的书
	@Override
	public PageInfo<Book> findAll(int pc){
		
		List<Book> bookList;
		bookList = book_dao.findAll();
		PageInfo<Book> pageinfo = toPageBean(bookList, pc);//转为分页

		return pageinfo;
	}
	
	//默认开始页为第0页 limit左开右闭
	//获得当前记录
	@SuppressWarnings("unused")
	private int getStart(int pc) {
		return pc*ps;
	}

	//使用插件数据实现分页
	@SuppressWarnings("unused")
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
