package cn.com.zhyu.upm.dao;

import cn.com.zhyu.upm.pojo.TarTask;

/**
 * @ClassName: TarTaskDAO
 * @author tangwe
 * @date 2015年1月5日 下午3:36:52
 * @Description: TODO(打包任务接口)
 * @version V1.0
 */
public interface TarTaskDAO {
	/**
	 * 通过项目ID和类型寻找打包任务
	 * 
	 * @param proID
	 * @param packageType
	 * @return
	 */
	public TarTask findTaskByProIDAndType(Integer proID, Integer packageType);

	/**
	 * 修改状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	public boolean updateTaskStatusByID(Integer id, Integer status);

	/**
	 * 添加备注
	 * 
	 * @param id
	 * @param remark
	 * @return
	 */
	public boolean updateTaskRemark(Integer id, String remark);

	/**
	 * 修改所有打包任务状态
	 * 
	 * @return
	 */
	public boolean updateAllTaskStatus();

	/**
	 * 添加打包日志
	 * 
	 * @param packageId
	 * @param tarLogInfo
	 * @param logURL
	 * @return
	 */
	public boolean addTarLog(Long packageId, String tarLogInfo);

	/**
	 * 查询指定升级包的打包日志信息
	 * 
	 * @param packageId
	 * @return
	 */
	public String findTarLogByPackageId(Integer packageId);
}
