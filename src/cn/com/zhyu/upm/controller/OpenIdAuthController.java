package cn.com.zhyu.upm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.tsg.openid.Association;
import cn.com.tsg.openid.Endpoint;
import cn.com.tsg.openid.TrustMoreUser;
import cn.com.zhyu.upm.common.AuthConfig;
import cn.com.zhyu.upm.common.AuthConstant;
import cn.com.zhyu.upm.pojo.User;
import cn.com.zhyu.upm.service.UserService;

/**
 * @author Administrator
 *
 */
@Controller
public class OpenIdAuthController extends BaseController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public void index(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		request.getRequestDispatcher("sign_in.jsp").forward(request, response);
	}

	/**
	 * openId验证
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/auth.do", method = RequestMethod.GET)
	public void openIdAuth(HttpServletResponse response, HttpServletRequest request) throws IOException {
		try {
			Endpoint endpoint = AuthConstant.manager.lookupEndpoint(AuthConfig.OPENID_SERVER);
			Association association = AuthConstant.manager.lookupAssociation(endpoint);
			request.getSession().setAttribute(AuthConstant.ATTR_MAC, association.getRawMacKey());
			request.getSession().setAttribute(AuthConstant.ATTR_ALIAS, endpoint.getAlias());
			System.out.println(request.getSession().getId());
			String url = AuthConstant.manager.getAuthenticationUrl(endpoint, association);
			response.sendRedirect(url);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("authFail.html");
			return;
		}
	}

	/**
	 * 获取auth信息
	 * 
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/getAuthUser.do", method = RequestMethod.GET)
	public void getAuthUser(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
		byte[] mac_key = (byte[]) request.getSession().getAttribute(AuthConstant.ATTR_MAC);
		String alias = (String) request.getSession().getAttribute(AuthConstant.ATTR_ALIAS);
		System.out.println(request.getSession().getId());
		TrustMoreUser authentication = AuthConstant.manager.getAuthentication(request, mac_key, alias);
		if (authentication == null) {
			response.sendRedirect("/");
			return;
		}
		String userName = authentication.getUsername();
		User user = userService.findUserByUserName(userName);
		if (user == null || user.getId() == null) {
			request.getSession().invalidate();
			response.sendRedirect(AuthConfig.OPENID_SERVER_LOGOUT + AuthConfig.OPENID_LOCAL_LOGOUT);
			return;
		}
		this.setSessionUser(request, user);// 将用户信息存储到session中
		response.sendRedirect("index.jsp");
	}

	/**
	 * 注销
	 * 
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/authLogout.do", method = RequestMethod.GET)
	public void authLogOut(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
		request.getSession().invalidate();
		response.sendRedirect(AuthConfig.OPENID_SERVER_LOGOUT + AuthConfig.OPENID_LOCAL_LOGOUT);
	}
}
