package cn.com.zhyu.upm.pojo;

import java.util.Date;

/**
 * @ClassName: OpLog
 * @author tangwe
 * @date 2015年1月8日 上午9:40:34
 * @Description: TODO(操作日志)
 * @version V1.0
 */
public class OpLog extends BasePO {
	private static final long serialVersionUID = -1966561000835457546L;
	private Integer id;// 序列号
	private String userName;// 用户名
	private String content;// 内容
	private Date createTime;// 时间

	public Integer getId() {
		return id;
	}

	public OpLog(String userName, String content, Date createTime) {
		super();
		this.userName = userName;
		this.content = content;
		this.createTime = createTime;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
