package cn.com.zhyu.upm.dao;

import java.util.List;

import cn.com.zhyu.upm.pojo.PackageType;

/**
 * @ClassName: PackageTypeDAO
 * @author tangwe
 * @date 2014年11月26日 下午3:38:55
 * @Description: TODO(升级包类型接口)
 * @version V1.0
 */
public interface PackageTypeDAO {
	/**
	 * 获取所属项目的升级包类型
	 * 
	 * @param projectID
	 * @return
	 */
	public List<PackageType> findPackageTypeByProID(Integer projectID);

	/**
	 * 加载所有升级包类型
	 * 
	 * @return
	 */
	public List<PackageType> findPackageTypes();
}
