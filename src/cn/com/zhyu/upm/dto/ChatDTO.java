package cn.com.zhyu.upm.dto;

import java.util.Date;

/**
 * @ClassName: ChatDTO
 * @author tangwe
 * @date 2014年11月24日 下午12:06:59
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @version V1.0
 */
public class ChatDTO {
	private Integer id; // 默认序列
	private String chatMsg; // 评论内容
	private Date createTime; // 评论时间
	private Integer userID; // 评论用户
	private Integer packageID; // 评论升级包

	private String userName; // 登陆用户名
	private String realName; // 用户姓名
	private String mail; // 用户邮件
	private String auth; // 用户操作项目权限,0没有任何权限
	private int status; // 用户状态 0禁用1启用

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChatMsg() {
		return chatMsg;
	}

	public void setChatMsg(String chatMsg) {
		this.chatMsg = chatMsg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getPackageID() {
		return packageID;
	}

	public void setPackageID(Integer packageID) {
		this.packageID = packageID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

}
