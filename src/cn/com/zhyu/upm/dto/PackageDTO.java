package cn.com.zhyu.upm.dto;

import java.util.Date;

/**
 * 升级包DTO
 * 
 * @ClassName: PackageDTO
 * @author tangwe
 * @date 2014年11月12日 下午4:17:25
 * @Description: TODO(传输升级包，项目，用户之间关联信息)
 * @version V1.0
 */
public class PackageDTO {
	private Integer id; // 默认序列
	private String packageName;// 升级包名称
	private String tag; // 版本tag
	private String version; // 版本号
	private String packgeSize; // 升级包大小
	private Date createTime; // 提交升级创建时间
	private String createTimeLabel;
	private Date updateTime; // 提交升级更新时间
	private String remark; // 提交升级备注
	private String downloadLink; // 下载链接
	private int packageType; // 升级包类型
	private int downloadStatus; // 0过期1可下载
	private int status; // 升级包状态 0close 1open
	private Integer projectID;// 项目关联ID，获取项目信息
	private Integer userID; // 用户关联ID，获取用户信息

	private String userName; // 登陆用户名
	private String realName; // 用户姓名
	private String mail; // 用户邮件
	private String auth; // 用户操作项目权限

	private String projectName; // 项目名称

	private String typeName;

	private long chatCount; // 评论统计数
	private long downloadCount;// 下载次数

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

	public String getCreateTimeLabel() {
		return createTimeLabel;
	}

	public void setCreateTimeLabel(String createTimeLabel) {
		this.createTimeLabel = createTimeLabel;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getProjectID() {
		return projectID;
	}

	public int getDownloadStatus() {
		return downloadStatus;
	}

	public void setDownloadStatus(int downloadStatus) {
		this.downloadStatus = downloadStatus;
	}

	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public int getPackageType() {
		return packageType;
	}

	public void setPackageType(int packageType) {
		this.packageType = packageType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public long getChatCount() {
		return chatCount;
	}

	public void setChatCount(long chatCount) {
		this.chatCount = chatCount;
	}

	public long getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(long downloadCount) {
		this.downloadCount = downloadCount;
	}

}
