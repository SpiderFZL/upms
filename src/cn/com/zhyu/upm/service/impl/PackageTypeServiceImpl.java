package cn.com.zhyu.upm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.zhyu.upm.dao.PackageTypeDAO;
import cn.com.zhyu.upm.pojo.PackageType;
import cn.com.zhyu.upm.service.PackageTypeService;

/**
 * @ClassName: PackageTypeServiceImpl
 * @author tangwe
 * @date 2014年11月26日 下午5:15:34
 * @Description: TODO(PackageTypeService实现)
 * @version V1.0
 */
@Service
public class PackageTypeServiceImpl implements PackageTypeService {
	@Autowired
	private PackageTypeDAO packageTypeDAO;

	@Override
	public List<PackageType> getPackageTypeByProID(Integer projectID) {
		return packageTypeDAO.findPackageTypeByProID(projectID);
	}

	@Override
	public List<PackageType> getPackageTypes() {
		return packageTypeDAO.findPackageTypes();
	}

}
