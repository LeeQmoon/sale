package org.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.pojo.Book;

public interface BookMapper {
	
	public Integer ByAuthor(Map<String,Object> map) throws SQLException;
	
	public Integer ByCategory(Map<String,Object> map) throws SQLException;
    
	public Integer ByName(Map<String,Object> map) throws SQLException;
	
	public Integer selectCount() throws SQLException;
	//按照分类查询书录
	public List<Book> findByCategory(Map<String,Object> map) throws SQLException;
	
	public List<Book> findByBname(Map<String,Object> map) throws SQLException;
	
	public List<Book> findByAuthor(Map<String,Object> map) throws SQLException;
	
	public List<Book> findByPress(Map<String,Object> map) throws SQLException;
	
	public List<Book> findAll(Map<String,Object> map) throws SQLException;
	//为了在购物车中关联查询使用
	public Book findByBid(String bid) throws SQLException;
	
}