package cn.com.zhyu.upm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.zhyu.upm.common.JsonMsgResponse;
import cn.com.zhyu.upm.common.JsonObjResponse;
import cn.com.zhyu.upm.pojo.OpLog;
import cn.com.zhyu.upm.pojo.User;
import cn.com.zhyu.upm.service.OpLogService;
import cn.com.zhyu.upm.service.UserService;

/**
 * 用户路由层(控制层)
 * 
 * @ClassName: UserController
 * @author tangwe
 * @date 2014年11月12日 下午3:01:31
 * @Description: TODO(用户模块url拦截，分发处理)
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/UserController")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private OpLogService opLogService;

	/**
	 * 用户登录
	 * 
	 * @param userName
	 *            登录用户名
	 * @param password
	 *            登录密码
	 * @param request
	 *            request
	 * @return JsonMsgResponse json封装信息
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	@ResponseBody
	public JsonMsgResponse login(String userName, String password, HttpServletRequest request) {
		JsonMsgResponse msgResponse = new JsonMsgResponse();
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			msgResponse.setJsonMsg(403, false, "用户名或密码不为空！");// 登录失败用户名密码为空
			return msgResponse;
		}
		User user = userService.login(userName.trim(), password.trim());
		if (null != user.getId()) {// 用户存在
			this.setSessionUser(request, user);// 将用户信息存储到session中
			opLogService.writeLog(new OpLog(userName, "登录系统。", new Date()));
			msgResponse.jsonMsgSuccess();
		} else {
			msgResponse.jsonMsgFail();
		}
		return msgResponse;
	}

	/**
	 * 用户注销
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout.do")
	@ResponseBody
	public JsonMsgResponse logout(HttpServletRequest request) {
		JsonMsgResponse msgResponse = new JsonMsgResponse();
		User user = this.getSessionUser(request);
		if (user == null || user.getId() == null) {
			msgResponse.setJsonMsg(403, false, "未登陆用户!");
			return msgResponse;
		}
		// 清除session用户信息
		request.getSession().setAttribute(USER_CONTEXT, "");
		request.getSession().invalidate();
		opLogService.writeLog(new OpLog(user.getUserName(), "注销系统。", new Date()));
		msgResponse.jsonMsgSuccess();// 设置返回信息
		return msgResponse;
	}

	/**
	 * 获取登陆用户基本信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/loadLoginedUser.do", method = RequestMethod.GET)
	@ResponseBody
	public JsonObjResponse loadLoginedUser(HttpServletRequest request) {
		JsonObjResponse objResponse = new JsonObjResponse();
		User user = this.getSessionUser(request);
		if (user == null || user.getId() == null) {
			objResponse.setJsonObj(403, false, "未登录用户！", null);
			return objResponse;
		}
		objResponse.jsonObjSuccess(user);// 放置用户信息
		return objResponse;
	}

	/**
	 * 获取所有用户数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loadAllUsers.do", method = RequestMethod.GET)
	@ResponseBody
	public List<User> loadAllUsers() {
		List<User> users = userService.findAllUsers();
		return users;
	}

	/**
	 * 获取可被授权的用户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/loadNormalUser.do", method = RequestMethod.GET)
	@ResponseBody
	public List<User> loadNormalUser(HttpServletRequest request) {
		User user = this.getSessionUser(request);
		// 判断是否为可授权用户
		if (user == null || user.getId() == null || user.getRole() != 10) {
			return null;
		}
		List<User> users = new ArrayList<User>();
		users = userService.findNormalUser();
		return users;
	}

	/**
	 * 删除普通用户
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delNormalUser.do", method = RequestMethod.GET)
	@ResponseBody
	public JsonMsgResponse delNormalUser(HttpServletRequest request, String id) {
		JsonMsgResponse msgResponse = new JsonMsgResponse();
		User user = this.getSessionUser(request);
		// 判断是否为可授权用户
		if (user == null || user.getId() == null || user.getRole() == 0) {
			msgResponse.setJsonMsg(403, false, "未登陆用户或没有权限!");
			return msgResponse;
		}
		if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
			msgResponse.setJsonMsg(403, false, "提交参数不合法!");
			return msgResponse;
		}
		boolean ifDeleted = userService.delUser(Integer.valueOf(id));// 是否删除
		if (ifDeleted) {
			opLogService.writeLog(new OpLog(user.getUserName(), "删除了id为" + id + "的用户。", new Date()));
			msgResponse.jsonMsgSuccess();
		} else {
			msgResponse.jsonMsgFail();
		}
		return msgResponse;
	}

	/**
	 * 授权普通用户
	 * 
	 * @param request
	 * @param id
	 * @param auth
	 * @return
	 */
	@RequestMapping(value = "/authNormalUser.do", method = RequestMethod.POST)
	@ResponseBody
	public JsonMsgResponse authNormalUser(HttpServletRequest request, String id, String auth) {
		JsonMsgResponse msgResponse = new JsonMsgResponse();
		User user = this.getSessionUser(request);
		if (user == null || user.getId() == null || user.getRole() != 10) {
			msgResponse.setJsonMsg(403, false, "未登陆用户或没有权限!");
			return msgResponse;
		}
		if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
			msgResponse.setJsonMsg(403, false, "提交参数不合法!");
			return msgResponse;
		}
		if (StringUtils.isEmpty(auth)) {
			msgResponse.setJsonMsg(403, false, "提交参数不合法!");
			return msgResponse;
		}
		boolean ifAuthed = userService.authNormalUser(auth, Integer.valueOf(id));// 修改权限
		if (ifAuthed) {
			opLogService.writeLog(new OpLog(user.getUserName(), "用户" + user.getUserName() + "赋予了id为" + id + "的用户项目权限。", new Date()));
			msgResponse.jsonMsgSuccess();
		} else {
			msgResponse.jsonMsgFail();
		}
		return msgResponse;
	}

	/**
	 * 加载用户权限
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/loadNormalUserAuth.do", method = RequestMethod.GET)
	@ResponseBody
	public String loadNormalUserAuth(String id) {
		String auth = "";
		if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
			return "";
		}
		User user = userService.findUserByID(Integer.valueOf(id));// 获取用户信息
		if (user != null && user.getId() != null) {
			auth = user.getAuth();// 获取用户项目权限
		}
		return auth;
	}

	/**
	 * 添加用户
	 * 
	 * @param u
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addNormalUser.do", method = RequestMethod.POST)
	@ResponseBody
	public JsonMsgResponse addNormalUser(User u, HttpServletRequest request) {
		JsonMsgResponse msgResponse = new JsonMsgResponse();
		User user = this.getSessionUser(request);
		// 判断是否为可授权用户
		if (user == null || user.getId() == null || user.getRole() != 10) {
			msgResponse.setJsonMsg(403, false, "未登陆用户或权限不够!");
			return msgResponse;
		}
		u.setPassword("zywt123456");// 设置默认密码
		boolean ifAdded = userService.addUser(u);
		if (ifAdded) {
			opLogService.writeLog(new OpLog(user.getUserName(), "新增了名为" + u.getUserName() + "的用户。", new Date()));
			msgResponse.jsonMsgSuccess();
		} else {
			msgResponse.jsonMsgFail();
		}
		return msgResponse;
	}

	/**
	 * 修改密码
	 * 
	 * @param newPwd
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/changeUserPwd.do", method = RequestMethod.POST)
	@ResponseBody
	public JsonMsgResponse changeUserPwd(String newPwd, HttpServletRequest request) {
		JsonMsgResponse msgResponse = new JsonMsgResponse();
		User user = this.getSessionUser(request);// 获取当前登录用户
		if (user == null || user.getId() == null) {
			msgResponse.setJsonMsg(403, false, "未登陆用户!");
			return msgResponse;
		}
		if (StringUtils.isEmpty(newPwd)) {
			msgResponse.setJsonMsg(403, false, "新设置的用户密码不能为空!");
			return msgResponse;
		}
		boolean ifChanged = userService.changeUserPwd(user.getId(), newPwd);// 修改密码
		if (ifChanged) {
			opLogService.writeLog(new OpLog(user.getUserName(), "修改了密码。", new Date()));
			msgResponse.jsonMsgSuccess();
		} else {
			msgResponse.jsonMsgFail();
		}
		return msgResponse;
	}
}
