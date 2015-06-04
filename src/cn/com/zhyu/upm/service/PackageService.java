package cn.com.zhyu.upm.service;

import java.util.Date;
import java.util.Map;

import cn.com.zhyu.upm.common.Page;
import cn.com.zhyu.upm.dto.PackageDTO;
import cn.com.zhyu.upm.pojo.Package;

/**
 * 升级包业务接口
 * 
 * @ClassName: PackageService
 * @author tangwe
 * @date 2014年11月13日 上午9:19:47
 * @Description: TODO(有关于升级包业务方法)
 * @version V1.0
 */
public interface PackageService {
	/**
	 * 获取分页查询的升级包DTO关联信息
	 * 
	 * @param pageNow
	 *            当前页
	 * @param pageSize
	 *            每页显示大小
	 * @param projectID
	 *            项目ID
	 * @param tag
	 *            版本
	 * @return Page 分页信息实体
	 */
	public Page getPagedPackageDTO(int pageNow, int pageSize, Map<String, String> params);

	/**
	 * 获取指定ID的升级包DTO关联信息
	 * 
	 * @param id
	 * @return
	 */
	public PackageDTO getPackageDTOByID(Integer id);

	/**
	 * 删除升级包
	 * 
	 * @param id
	 * @return
	 */
	public boolean delPackage(Integer id);

	/**
	 * 获取指定ID升级包领域对象信息
	 * 
	 * @param id
	 * @return
	 */
	public Package getPackageByID(Integer id);

	/**
	 * 添加升级包
	 * 
	 * @param pa
	 * @return
	 */
	public Package addPackage(Package pa);

	/**
	 * 更新升级包
	 * 
	 * @param newPa
	 * @param oID
	 * @return
	 */
	public Package updatePackage(Package newPa, boolean withFile, Integer oID);

	/**
	 * 修改升级包状态 （0close，1open）
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	public boolean updatePackageStatus(Integer status, Date updateTime, Integer id);

	/**
	 * 获取版本号
	 * 
	 * @param proID
	 * @return
	 */
	public String getLastVersion(Integer proID, Integer packageType);

	/**
	 * 测试通过项目
	 * 
	 * @param proID
	 * @param tag
	 * @param version
	 * @return
	 */
	public boolean testPassPackage(Integer id);

	/**
	 * 废弃包
	 * 
	 * @MethodName: discardPackage
	 * @return
	 */
	public boolean discardPackage(Integer id);

	/**
	 * 添加升级包并返回主键
	 * 
	 * @param pa
	 * @return
	 */
	public Long addPackageGetKey(Package pa);

	/**
	 * 修改下载状态，升级包可用不可用1可用0不可用
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	public boolean updateDownloadStatus(Long id, int status);

	/**
	 * 修改升级包状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	public boolean updatePackageStatus(Long id, int status);

}
