package org.service;

import java.util.List;

import org.pojo.Book;

import com.github.pagehelper.PageInfo;

public interface IBookService {
	
	public PageInfo<Book> findByCategory(String cname, int pc);
	
	public PageInfo<Book> findByBname(String bname, int pc);
	
	public PageInfo<Book> findByAuthor(String author, int pc);
	//按首页后，分页出现所有书
	public PageInfo<Book> findAll(int pc);

}
