package cn.com.zhyu.upm.pojo;

import java.util.Date;

/**
 * @ClassName: Download
 * @author tangwe
 * @date 2015年2月2日 下午1:13:21
 * @Description: TODO(下载)
 * @version V1.0
 */
public class Download extends BasePO {
	private static final long serialVersionUID = 8194439806540297938L;
	private Integer id;
	private Date downtime;
	private Integer packageID;
	private String userName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDowntime() {
		return downtime;
	}

	public void setDowntime(Date downtime) {
		this.downtime = downtime;
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

}
