package cn.com.zhyu.upm.service;

import java.util.List;

import cn.com.zhyu.upm.pojo.PackageType;

/**
 * @ClassName: PackageTypeService
 * @author tangwe
 * @date 2014年11月26日 下午3:49:49
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @version V1.0
 */
public interface PackageTypeService {
	/**
	 * 获取所属项目的升级包类型
	 * 
	 * @MethodName: getPackageTypeByProID
	 * @param projectID
	 * @return
	 */
	public List<PackageType> getPackageTypeByProID(Integer projectID);

	/**
	 * 获取所有升级包类型
	 * 
	 * @return
	 */
	public List<PackageType> getPackageTypes();
}
