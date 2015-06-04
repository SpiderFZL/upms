package cn.com.zhyu.upm.pojo;

/**
 * 所属项目实体类
 * 
 * @ClassName: Project
 * @author tangwe
 * @date 2014年11月12日 下午1:53:59
 * @Description: TODO(所属项目领域对象)
 * @version V1.0
 */
public class Project extends BasePO {
	private static final long serialVersionUID = -5826213390032555519L;
	private Integer id; // 默认序列号
	private String projectName; // 项目名称

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
