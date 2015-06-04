package cn.com.zhyu.upm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.zhyu.upm.dao.UserDAO;
import cn.com.zhyu.upm.pojo.User;
import cn.com.zhyu.upm.service.UserService;

/**
 * UserService实现
 * 
 * @ClassName: UserServiceImpl
 * @author tangwe
 * @date 2014年11月12日 下午2:56:08
 * @Description: TODO(实现用户业务方法)
 * @version V1.0
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO; // 用户数据访问注入

	@Override
	public User login(String userName, String password) {
		return userDAO.findUserByUserNamePwd(userName, password);
	}

	@Override
	public List<User> findAllUsers() {
		return userDAO.findAllUsers();
	}

	@Override
	public String findMailByUserName(String userName) {
		User user = userDAO.findUserByUserName(userName);
		return user.getMail() == null ? "" : user.getMail();
	}

	@Override
	public String findMailByRealName(String realName) {
		User user = userDAO.findUserByRealName(realName);
		return user.getMail() == null ? "" : user.getMail();
	}

	@Override
	public List<User> findNormalUser() {
		return userDAO.findNormalUser();
	}

	@Override
	public boolean delUser(Integer id) {
		return userDAO.delNormalUser(id);
	}

	@Override
	public boolean authNormalUser(String auth, Integer id) {
		return userDAO.authNormalUser(id, auth);
	}

	@Override
	public User findUserByID(Integer id) {
		return userDAO.findUserByID(id);
	}

	@Override
	public boolean addUser(User user) {
		return userDAO.addUser(user);
	}

	@Override
	public boolean changeUserPwd(Integer id, String newPwd) {
		return userDAO.updateUserPwd(id, newPwd);
	}

	@Override
	public User findUserByUserName(String userName) {
		return userDAO.findUserByUserName(userName);
	}

}
