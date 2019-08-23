<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
  <script type="text/javascript">
	    //窗体改变大小时触发事件
		window.onresize = setViewSize;
		var marginLeft=0;
		var marginTop=0;
		//设置画布大小，登录页面显示在屏幕最中间
		function setViewSize() {
			//计算屏幕大小
			var w=window.innerWidth
			|| document.documentElement.clientWidth
			|| document.body.clientWidth;
			var h=window.innerHeight
			|| document.documentElement.clientHeight
			|| document.body.clientHeight;
			//设置登陆div的位置
			marginLeft = (w-468)/2;
			marginTop = (h-262)/2;
			document.getElementById("loginFormMain").style.marginLeft=marginLeft;
			document.getElementById("loginFormMain").style.marginTop=marginTop;
		}
		
		//默认聚焦在用户名输入框上
		function focusOnUsername() {
			//调整画布大小和登陆框位置
		    setViewSize();
			//默认聚焦在输入框上
			if (document.loginForm) {
				var usernameInput = document.loginForm.username;
				if (usernameInput) {
					usernameInput.focus();
				}
			}
			return;
		}
    
    	//检查用户输入
		function checkLogin(){
			if(checkUsername() && checkPassword()){
				 return true;
			 }		  
			return false;
		}
		//检查登录用户名
		function checkUsername(){
			var username = document.loginForm.username;
			if(username.value.length!=0){
				return true;
			}else{
				alert("请输入用户名");
				return false;
			}
		}
		//检查登录密码
		function checkPassword(){
			var password = document.loginForm.password;
			if(password.value.length!=0){
				return true;
			}else{
				alert("请输入密码");
				return false;
			}
		}
	</script>
</head>


<table border=0 ><tr><th width=2000 height=150 border=1 bgcolor='ccffff'><center><table><th><img src="${pageContext.request.contextPath }/images/30.jpg" width='100' height='100'></th>
<th><font size='7' color='ff7517'>三味书屋</th>
<td colspan=2 height=65><input type='button' style="background-color:red;height:55px;width:160px;font-size:25px;color:white;border:none" 
value='首页'  />
</table></center></th><tr>

<table style="background-image:url(${pageContext.request.contextPath }/images/.jpg);background-size: 100%; opacity: 1; filter: alpha(opacity = 30)"><th >
<table border=0><tr><th width=1500 height=600 border=1><img src="${pageContext.request.contextPath }/images/4.jpg" height='400' width=400></th><th>
<table border=0 bgcolor=F0F0F0>
<tr><th width=12 height=45></th><th colspan=3 width=82 height=45 align='left'>账户登录</th></tr>
<tr><th width=12 height=20></th><th width=20 height=20><img src="${pageContext.request.contextPath }/images/7.png" ></th><th width=50 height=20>

<form name="LoginForm" action="${pageContext.request.contextPath }/user/login.action" 
οnsubmit="return checkLogin();" method="post">
<input type="text" style="height:40px" placeholder="请输入用户名" size=40 name="username"></th><th width=12 height=20></th></tr>
<tr><th colspan=4 width=94 height=20></th></tr>
<tr><th width=12 height=20></th><th width=20 height=20><img src="${pageContext.request.contextPath }/images/8.png" ></th><th width=50 height=20>
<input type="password" style="height:40px" placeholder="请输入密码" name="password" size=40></th><th width=12 height=20></th></tr>
<tr><th colspan=4 width=94 height=20></th></tr>
<tr><th width=12 height=20></th>

<th colspan=2 width=70 height=20 bgcolor=FF5809><input type="submit" 
	style="background-color:FF5809;height:40px;width:140px;font-size:20px;color:white;border:none" 
	value="登录" ></th>
	<th width=12 height=20></th></tr>
	<tr><th colspan=4 width=94 height=20></th></tr>
	
<tr><th colspan=4 width=94 height=20></th></tr>
<tr><th colspan=4 width=94 height=6></th></tr>
<tr><th width=12 height=40><th colspan=2><table width=310 border=0><tr><th  width=35 height=20 align='left'><font size=2>忘记密码</font></th>
<th  width=35 height=20 align='right'><font size=2><a href="/SaleBook/jsp/user/register.jsp" target="_blank">注册</a></font></th></tr></table></th><th></th></tr>
<tr><th colspan=4 width=94 height=6></th></tr>
</table>

</th><th width='700'></th></tr></table></th><tr><th bgcolor='f9fa9b' height=120><p>📧联系邮箱：xxxx@qq.com</p><p>联系地址：xxxx</p><p>📞联系电话：xxxx</p></th></table>
</body>
</form> 
	
</table>










 
<%-- <body bgcolor="#1E81C2" οnlοad="javascript:focusOnUsername();" >
	 <form  action="welcome.jsp" name="loginForm" >
	 <div  id="loginFormMain">
		 <table  style="width:468px;height:262px;background-color: gray;text-align: center;">
			  <tr>
			  	 <th colspan="2" align="center" >销售系统登录</th>
			  </tr>
			  <form name="myForm" action="${pageContext.request.contextPath }/jsp/user/login.action" οnsubmit="return checkLogin();" method="post">
			  <tr>
			      <td>用户名:<input type="text" style="width: 200px;height: 30px;"  name="username"></td>
			  </tr>
			  <tr>
			      <td>密  码:<input type="password"  style="width: 200px;height: 30px;"  name="password"></td>
			  </tr>
			  <tr>
 				<td align="center" ><input  formmethod="post" type="submit" style="cursor: pointer;font-style: inherit;" value="登录" > 
			</form> 
			   
			  </tr>
		 </table>
	 </div>
	 </form> --%>






	<%-- <form action="${pageContext.request.contextPath }/user/login.action" method="post">
    username:<input type="text" name = "username" ><p> <!-- name属性必须与controller中传入得参数名同名 -->
    password:<input type="password" name = "password" ><p>
    <input type="submit" value="登录"> 
   <a href="${pageContext.request.contextPath }/welcome.jsp"> <input type="button" value="返回"> </a>
    
</form> 
 <div class="login">
       <form action="${pageContext.request.contextPath }/user/login.action" method="post">
        <div class="login-title">登录会员<span><a href="javascript:void(0);" class="close-login">关闭</a></span></div>
        <div class="login-input-content">
        
            <div class="login-input">
                <label>用&nbsp;户&nbsp;&nbsp;名：</label>
                <input type="text" placeholder="请输入用户名"  name="username" id="username" class="list-input"/>
            </div>
            <div class="login-input">
                <label>登录密码：</label>
                <input type="password" placeholder="请输入登录密码" name="password" id="password" class="list-input"/>
            </div>
        </div>
        <div class="control-group">
            <div class="controls" >
                <button class="btn btn-success" style="width:120px;" >确认</button>           
            </div>
        </div>
       </form>
    </div> --%>
</body>
</html>
