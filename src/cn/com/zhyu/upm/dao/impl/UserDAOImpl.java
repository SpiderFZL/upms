package cn.com.zhyu.upm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import cn.com.zhyu.upm.dao.BaseDAO;
import cn.com.zhyu.upm.dao.UserDAO;
import cn.com.zhyu.upm.pojo.User;

/**
 * UserDAO实现
 * 
 * @ClassName: UserDAOImpl
 * @author tangwe
 * @date 2014年11月12日 下午2:25:42
 * @Description: TODO(实现UserDAO规范方法，访问数据层，返回数据)
 * @version V1.0
 */
@Repository("UserDAO")
public class UserDAOImpl extends BaseDAO implements UserDAO {

	@Override
	public User findUserByUserNamePwd(String userName, String password) {
		String sql = "SELECT U.ID,U.USERNAME,U.REALNAME,U.MAIL,U.AUTH,U.ROLE FROM ZHYU_USER U WHERE U.USERNAME=? AND U.PASSWORD=? AND STATUS=1";
		final String[] params = { userName, password };
		final User user = new User();
		this.jdbcTemplate.query(sql, params, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setAuth(rs.getString("auth"));
				user.setId(rs.getInt("id"));
				user.setMail(rs.getString("mail"));
				user.setRealName(rs.getString("realname"));
				user.setUserName(rs.getString("username"));
				user.setRole(rs.getInt("role"));
			}
		});
		return user;
	}

	@Override
	public List<User> findAllUsers() {
		String sql = "SELECT U.ID,U.USERNAME,U.REALNAME,U.MAIL,U.AUTH FROM ZHYU_USER U";
		final List<User> users = new ArrayList<User>();
		this.jdbcTemplate.query(sql, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				User user = new User();
				user.setAuth(rs.getString("auth"));
				user.setId(rs.getInt("id"));
				user.setMail(rs.getString("mail"));
				user.setRealName(rs.getString("realname"));
				user.setUserName(rs.getString("username"));
				users.add(user);
			}
		});
		return users;
	}

	@Override
	public User findUserByUserName(String userName) {
		String sql = "SELECT U.ID,U.USERNAME,U.REALNAME,U.MAIL,U.AUTH,U.ROLE FROM ZHYU_USER U WHERE U.USERNAME=? AND STATUS=1";
		final String[] params = { userName };
		final User user = new User();
		this.jdbcTemplate.query(sql, params, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setAuth(rs.getString("auth"));
				user.setId(rs.getInt("id"));
				user.setMail(rs.getString("mail"));
				user.setRealName(rs.getString("realname"));
				user.setUserName(rs.getString("username"));
				user.setRole(rs.getInt("role"));
			}
		});
		return user;
	}

	@Override
	public User findUserByRealName(String realName) {
		String sql = "SELECT U.ID,U.USERNAME,U.REALNAME,U.MAIL,U.AUTH FROM ZHYU_USER U WHERE U.REALNAME=? AND STATUS=1";
		final String[] params = { realName };
		final User user = new User();
		this.jdbcTemplate.query(sql, params, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setAuth(rs.getString("auth"));
				user.setId(rs.getInt("id"));
				user.setMail(rs.getString("mail"));
				user.setRealName(rs.getString("realname"));
				user.setUserName(rs.getString("username"));
			}
		});
		return user;
	}

	@Override
	public List<User> findNormalUser() {
		String sql = "SELECT U.ID,U.USERNAME,U.REALNAME,U.MAIL,U.AUTH,U.ROLE FROM ZHYU_USER U WHERE U.STATUS=1";
		final List<User> users = new ArrayList<User>();
		this.jdbcTemplate.query(sql, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				User user = new User();
				user.setAuth(rs.getString("auth"));
				user.setId(rs.getInt("id"));
				user.setMail(rs.getString("mail"));
				user.setRealName(rs.getString("realname"));
				user.setUserName(rs.getString("username"));
				user.setRole(rs.getInt("role"));
				users.add(user);
			}
		});
		return users;
	}

	@Override
	public boolean delNormalUser(Integer id) {
		// String sql =
		// "DELETE FROM ZHYU_USER U WHERE U.ROLE=0 AND U.STATUS=1 AND U.ID=?";
		String sql = "UPDATE ZHYU_USER U SET U.STATUS=0 WHERE U.ID=?";
		int re = this.jdbcTemplate.update(sql, new Object[] { id });
		if (re > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean authNormalUser(Integer id, String auth) {
		String sql = "UPDATE ZHYU_USER U SET U.AUTH=? WHERE U.ROLE<>10 AND U.STATUS=1 AND U.ID=?";
		Object[] params = { auth, id };
		int re = this.jdbcTemplate.update(sql, params);
		if (re > 0) {
			return true;
		}
		return false;
	}

	@Override
	public User findUserByID(Integer id) {
		String sql = "SELECT U.ID,U.USERNAME,U.REALNAME,U.MAIL,U.AUTH,U.ROLE FROM ZHYU_USER U WHERE U.ID=? AND STATUS=1";
		final Integer[] params = { id };
		final User user = new User();
		this.jdbcTemplate.query(sql, params, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setAuth(rs.getString("auth"));
				user.setId(rs.getInt("id"));
				user.setMail(rs.getString("mail"));
				user.setRealName(rs.getString("realname"));
				user.setUserName(rs.getString("username"));
				user.setRole(rs.getInt("role"));
			}
		});
		return user;
	}

	@Override
	public boolean addUser(User user) {
		String sql = "INSERT INTO ZHYU_USER(USERNAME,PASSWORD,REALNAME,MAIL,AUTH,STATUS,ROLE) VALUES(?,?,?,?,?,1,?)";
		Object[] args = { user.getUserName(), user.getPassword(), user.getRealName(), user.getMail(), user.getAuth(), user.getRole() };
		int re = this.jdbcTemplate.update(sql, args);
		if (re > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUserPwd(Integer id, String newPwd) {
		String sql = "UPDATE ZHYU_USER U SET U.PASSWORD=? WHERE U.ID=?";
		Object[] args = { newPwd, id };
		int re = this.jdbcTemplate.update(sql, args);
		if (re > 0) {
			return true;
		}
		return false;
	}
}
