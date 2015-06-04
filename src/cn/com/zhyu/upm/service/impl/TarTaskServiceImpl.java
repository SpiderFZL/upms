package cn.com.zhyu.upm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.zhyu.upm.dao.TarTaskDAO;
import cn.com.zhyu.upm.pojo.TarTask;
import cn.com.zhyu.upm.service.TarTaskService;

/**
 * @ClassName: TarTaskServiceImpl
 * @author tangwe
 * @date 2015年1月5日 下午3:55:09
 * @Description: TODO(打包任务服务实现接口)
 * @version V1.0
 */
@Service("TarTaskService")
public class TarTaskServiceImpl implements TarTaskService {
	@Autowired
	private TarTaskDAO tarTaskDAO;

	@Override
	public TarTask findTaskByProIDAndType(Integer proID, Integer packageType) {
		return tarTaskDAO.findTaskByProIDAndType(proID, packageType);
	}

	@Override
	public boolean updateTaskStatusByID(Integer id, Integer status) {
		return tarTaskDAO.updateTaskStatusByID(id, status);
	}

	@Override
	public boolean updateTaskRemark(Integer id, String remark) {
		return tarTaskDAO.updateTaskRemark(id, remark);
	}

	@Override
	public boolean resetTaskStatus() {
		return tarTaskDAO.updateAllTaskStatus();
	}

	@Override
	public boolean addTarLog(Long packageId, String tarLogInfo) {
		return tarTaskDAO.addTarLog(packageId, tarLogInfo);
	}

	@Override
	public String findTarLogByPackageId(Integer packageId) {
		return tarTaskDAO.findTarLogByPackageId(packageId);
	}

}
