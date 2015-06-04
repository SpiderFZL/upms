package cn.com.zhyu.upm.pojo;

/**
 * 用户实体类
 * 
 * @ClassName: User
 * @author tangwe
 * @date 2014年11月12日 下午1:57:02
 * @Description: TODO(用户领域对象)
 * @version V1.0
 */
public class User extends BasePO {
	private static final long serialVersionUID = 8960618024359120035L;

	private Integer id; // 默认序列
	private String userName; // 登陆用户名
	private String password; // 登陆用户密码
	private String realName; // 用户姓名
	private String mail; // 用户邮件
	private String auth; // 用户操作项目权限,0没有任何权限
	private int status; // 用户状态 0禁用1启用
	private int role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}
