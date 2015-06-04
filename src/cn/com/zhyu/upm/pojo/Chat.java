package cn.com.zhyu.upm.pojo;

import java.util.Date;

/**
 * @ClassName: Chat
 * @author tangwe
 * @date 2014年11月24日 上午11:52:09
 * @Description: TODO(评论记录领域对象 )
 * @version V1.0
 */
public class Chat extends BasePO {
	private static final long serialVersionUID = -3740494268333317682L;
	private Integer id; // 默认序列
	private String chatMsg; // 评论内容
	private Date createTime; // 评论时间
	private Integer userID; // 评论用户
	private Integer packageID; // 评论升级包

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

}
