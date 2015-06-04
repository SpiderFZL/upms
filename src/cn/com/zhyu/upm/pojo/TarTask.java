package cn.com.zhyu.upm.pojo;

/**
 * @ClassName: TarTask
 * @author tangwe
 * @date 2015年1月5日 下午3:34:49
 * @Description: TODO(打包任务领域对象)
 * @version V1.0
 */
public class TarTask extends BasePO {
	private static final long serialVersionUID = -7926122169635057840L;
	private Integer id;
	private Integer projectID;
	private Integer packageType;
	private int status;
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjectID() {
		return projectID;
	}

	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}

	public Integer getPackageType() {
		return packageType;
	}

	public void setPackageType(Integer packageType) {
		this.packageType = packageType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
