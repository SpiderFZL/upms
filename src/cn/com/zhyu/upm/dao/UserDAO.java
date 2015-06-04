package cn.com.zhyu.upm.dao;

import java.util.List;

import cn.com.zhyu.upm.pojo.User;

/**
 * UserDAO接口
 * 
 * @ClassName: UserDAO
 * @author tangwe
 * @date 2014年11月12日 下午2:21:38
 * @Description: TODO(规范数据访问层方法，用于继承)
 * @version V1.0
 */
public interface UserDAO {
	/**
	 * 通过用户名密码查找用户信息
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return User 用户信息实体
	 */
	public User findUserByUserNamePwd(String userName, String password);

	/**
	 * 获取所有用户
	 * 
	 * @return
	 */
	public List<User> findAllUsers();

	/**
	 * 通过用户名查询用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public User findUserByUserName(String userName);

	/**
	 * 通过真实姓名查询用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public User findUserByRealName(String realName);

	/**
	 * 获取所有普通 用户
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
	public boolean delNormalUser(Integer id);

	/**
	 * 授权普通用户
	 * 
	 * @param id
	 * @param auth
	 * @return
	 */
	public boolean authNormalUser(Integer id, String auth);

	/**
	 * 获取指定id用户
	 * 
	 * @param id
	 * @return
	 */
	public User findUserByID(Integer id);

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean addUser(User user);

	/**
	 * 修改密码
	 * 
	 * @param id
	 * @param newPwd
	 * @return
	 */
	public boolean updateUserPwd(Integer id, String newPwd);

}
