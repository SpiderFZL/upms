package cn.com.zhyu.upm.pojo;

import java.util.Date;

/**
 * 升级包信息实体类
 * 
 * @ClassName: Package
 * @author tangwe
 * @date 2014年11月12日 下午2:01:19
 * @Description: TODO(升级包信息领域对象)
 * @version V1.0
 */
public class Package extends BasePO {
	private static final long serialVersionUID = -1444942055159671667L;
	private Integer id; // 默认序列
	private String packageName;// 升级包名称
	private String tag; // 版本tag
	private String version; // 版本号
	private String packgeSize; // 升级包大小
	private Date createTime; // 提交升级创建时间
	private Date updateTime; // 提交升级更新时间
	private String remark; // 提交升级备注
	private String downloadLink; // 下载链接
	private int packageType; // 升级包类型
	private int visable;// 逻辑删除标识0删除1不删除
	private int downloadStatus; // 0过期1可下载
	private int status; // 升级包状态 0close 1open
	private Integer projectID;// 项目关联ID，获取项目信息
	private Integer userID; // 用户关联ID，获取用户信息

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPackgeSize() {
		return packgeSize;
	}

	public void setPackgeSize(String packgeSize) {
		this.packgeSize = packgeSize;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getProjectID() {
		return projectID;
	}

	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getPackageType() {
		return packageType;
	}

	public void setPackageType(int packageType) {
		this.packageType = packageType;
	}

	public int getDownloadStatus() {
		return downloadStatus;
	}

	public void setDownloadStatus(int downloadStatus) {
		this.downloadStatus = downloadStatus;
	}

	public int getVisable() {
		return visable;
	}

	public void setVisable(int visable) {
		this.visable = visable;
	}

}
