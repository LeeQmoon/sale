package org.service;
import java.sql.SQLException;
import java.util.Map;

import org.pojo.User;

public interface IUserService {
	public User login(User user) throws SQLException;
	public User register(User user) throws SQLException;
	public boolean updatePwd(Map<String,String> map)throws SQLException;
}
