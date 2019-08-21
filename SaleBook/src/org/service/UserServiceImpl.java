package org.service;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.mapper.UserMapper;
import org.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("IUserService")
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserMapper user_dao;

	@Override
	public User login(User user) throws SQLException {
		Map<String,String> map = new HashMap<String, String>();
		map.put("loginname", user.getNickName());
		map.put("loginpass", user.getPassword());
	    return user_dao.findByLoginnameAndLoginpass(map);
	}

	@Override
	public User register(User user) throws SQLException {
		Map<String,String> map = new HashMap<String, String>();
		
		map.put("loginname", user.getNickName());
		//如果数据库中有这个昵称返回true
		if(user_dao.checkLoginname(map))
			return null;
		//否则在数据库中增加这个用户
		//用UUID产生随机字符串不会重复 随机分配id号
		String uid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
		map.put("uid", uid);
		map.put("loginpass", user.getPassword());
		map.put("sex", user.getSex());
		user_dao.add(map);
		return user;
	}

	@Override
	public boolean updatePwd(Map<String, String> map) throws SQLException {
		Map<String,String> temp = new HashMap<String, String>();
		String uid = map.get("uid");
		String old_pwd = map.get("old_pwd");
		
		temp.put("uid", uid);
		temp.put("old_pwd", old_pwd);
		if(user_dao.checkByPwd(temp)) {
			user_dao.updatePwd(map);
			return true;
		}
		
		//user_dao.updateByPrimaryKey();
		return false;
	}
	

}
