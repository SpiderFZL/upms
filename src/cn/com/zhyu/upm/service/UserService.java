package cn.com.zhyu.upm.service;

import java.util.List;

import cn.com.zhyu.upm.pojo.User;

/**
 * UserService接口
 * 
 * @ClassName: UserService
 * @author tangwe
 * @date 2014年11月12日 下午2:52:18
 * @Description: TODO(有关于用户业务方法)
 * @version V1.0
 */
public interface UserService {
	/**
	 * 用户登录业务
	 * 
	 * @param userName
	 *            登录用户名
	 * @param password
	 *            登录密码
	 * @return User 用户基本信息
	 */
	public User login(String userName, String password);

	/**
	 * 获取所有用户
	 * 
	 * @return
	 */
	public List<User> findAllUsers();

	/**
	 * 获取用户邮件
	 * 
	 * @param userName
	 * @return
	 */
	public String findMailByUserName(String userName);

	/**
	 * 获取用户邮件
	 * 
	 * @param realName
	 * @return
	 */
	public String findMailByRealName(String realName);

	/**
	 * 获取普通可被授权用户小于当前权限
	 * 
	 * @param currRole
	 * @return
	 */
	public List<User> findNormalUser();

	/**
	 * 删除普通用户
	 * 
	 * @param id
	 * @return
	 */
	public boolean delUser(Integer id);

	/**
	 * 授权用户
	 * 
	 * @MethodName: authNormalUser
	 * @param auth
	 * @param id
	 * @return
	 */
	public boolean authNormalUser(String auth, Integer id);

	/**
	 * 获取指定ID用户
	 * 
	 * @MethodName: findUserByID
	 * @param id
	 * @return
	 */
	public User findUserByID(Integer id);

	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean addUser(User user);

	/**
	 * 修改用户密码
	 * 
	 * @param id
	 * @param newPwd
	 * @return
	 */
	public boolean changeUserPwd(Integer id, String newPwd);

	/**
	 * 获取指定用户名的用户
	 * 
	 * @param userName
	 *            用户名
	 * @return
	 */
	public User findUserByUserName(String userName);
}
