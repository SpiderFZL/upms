package cn.com.zhyu.upm.pojo;

/**
 * @ClassName: PackageType
 * @author tangwe
 * @date 2014年11月26日 下午3:39:55
 * @Description: TODO(升级包类型领域对象)
 * @version V1.0
 */
public class PackageType extends BasePO {
	private static final long serialVersionUID = -3888758589653610675L;
	private Integer id; // 序列号
	private String typeName; // 升级包名称

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
