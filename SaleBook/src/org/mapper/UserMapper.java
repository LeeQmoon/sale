package org.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(String uId)throws SQLException;

    int insert(User record)throws SQLException;

    int insertSelective(User record)throws SQLException;

    User selectByPrimaryKey(String uId)throws SQLException;

    int updateByPrimaryKeySelective(User record)throws SQLException;

    int updateByPrimaryKey(User record)throws SQLException;
    
    //查询密码是否正确
    boolean checkByPwd(Map<String,String> map)throws SQLException;
    //更改密码
    void updatePwd(Map<String,String> map)throws SQLException;
    //查询密码跟用户名
    public User findByLoginnameAndLoginpass(Map<String,String> map)throws SQLException;
    
    //检查用户名，若是一样则返回true
    boolean checkLoginname(Map<String,String> map)throws SQLException;
    
    //增加用户
    void add(Map<String,String> map) throws SQLException;
}