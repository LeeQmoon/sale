package org.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.pojo.User;
import org.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //标注为控制器，所以不需要再使用继承了
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService u_service;
	
	@RequestMapping("/login.action")
	public String login(String username,String password,HttpSession session,ModelMap map,HttpServletResponse response)throws UnsupportedEncodingException, SQLException{
		
		User formUser = new User();
		formUser.setNickName(username);
		formUser.setPassword(password);
		User user = u_service.login(formUser);
		
		if(user==null){
			map.addAttribute("msg", "用户名或密码错误！");
			map.addAttribute("user", formUser);
			return "/jsp/user/login";
		}else{
			//打开一个浏览器只允许保留一个用户
			session.setAttribute("sessionUser", user);
			String loginname = user.getNickName();
			loginname = java.net.URLEncoder.encode(loginname, "utf-8");
			Cookie cookie = new Cookie("loginname",loginname);
			cookie.setMaxAge(60*60*24*10);
			response.addCookie(cookie);
			return "/welcomeAndLoginSuccess";
			}
		}
	
	@RequestMapping("/register.action")
	public String register(String username,String password,String sex,HttpSession session,ModelMap map,HttpServletResponse response)
			throws UnsupportedEncodingException, SQLException{
		
		if(username==""||password=="") {
			map.addAttribute("msg", "用户名或密码不能为空！");
			return "/jsp/user/register";
		}
		
		User formUser = new User();
		formUser.setNickName(username);
		formUser.setPassword(password);
		formUser.setSex(sex);
		User user = u_service.register(formUser);
		if(user==null){
			map.addAttribute("msg", "用户名已存在！");
			map.addAttribute("user", formUser);
			return "/jsp/user/register";
		}else{
				return "/welcome";
			}
		
		}
	
	@RequestMapping("/updatePwd.action")
	public String updatePassword(String old_pwd, String new_pwd, ModelMap modelandview,HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("sessionUser");
		if(user==null){
			modelandview.addAttribute("msg", "您还未登录！");
			return "/jsp/user/login";
		}
		if(new_pwd=="") {
			modelandview.addAttribute("msg", "密码不能为空！");
			return "/welcomeAndLoginSuccess";
		}
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("uid", user.getuId());
		map.put("old_pwd", old_pwd);
		map.put("new_pwd", new_pwd);
		try {
			if(u_service.updatePwd(map)) {
			modelandview.addAttribute("msg", "修改密码成功！");
			modelandview.addAttribute("code", "success");
			return "/welcomeAndLoginSuccess";
			}
			else 
				return "/jsp/user/pwd";
				
		} catch (SQLException e) {
			request.setAttribute("msg", e.getMessage());
			return "/jsp/user/pwd";
		}
	
	}
	
}

