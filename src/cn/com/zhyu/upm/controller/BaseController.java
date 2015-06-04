package cn.com.zhyu.upm.controller;

import javax.servlet.http.HttpServletRequest;

import cn.com.zhyu.upm.pojo.User;

/**
 * @ClassName: BaseController
 * @author tangwe
 * @date 2014年11月12日 上午9:42:25
 * @Description: TODO(控制层基类，控制层调用共有方法)
 * @version V1.0
 */
public class BaseController {
	protected static final String USER_CONTEXT = "user";

	/**
	 * 获取session中的User信息
	 * 
	 * @param request
	 * @return
	 */
	protected User getSessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(USER_CONTEXT);
	}

	/**
	 * 将用户信息存储到session中
	 * 
	 * @param request
	 * @param user
	 */
	protected void setSessionUser(HttpServletRequest request, User user) {
		request.getSession().setAttribute(USER_CONTEXT, user);
	}
}
