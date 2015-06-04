package cn.com.zhyu.upm.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.com.zhyu.upm.dto.PackageDTO;
import cn.com.zhyu.upm.pojo.Package;

/**
 * Upgrade Package DAO接口
 * 
 * @ClassName: PackageDAO
 * @author tangwe
 * @date 2014年11月12日 下午3:43:15
 * @Description: TODO(规范数据访问层接口，用于继承)
 * @version V1.0
 */
public interface PackageDAO {
	/**
	 * 分页查询升级包数据
	 * 
	 * @param pageNow
	 *            当前页
	 * @param pageSize
	 *            每页显示数据大小
	 * @param params
	 *            查询参数
	 * @return List<PackageDTO> 升级包信息数据集合
	 */
	public List<PackageDTO> findPagedPackages(int pageNow, int pageSize, Map<String, String> params);

	/**
	 * 获取查询总记录数
	 * 
	 * @param keyWords
	 *            查询参数
	 * @return 总记录数
	 */
	public long getPackageTotalCount(Map<String, String> params);

	/**
	 * 根据ID获取指定升级包数据
	 * 
	 * @param id
	 * @return PackageDTO 升级包信息数据
	 */
	public PackageDTO findPackageByID(Integer id);

	/**
	 * 删除升级包数据
	 * 
	 * @param id
	 *            删除序列号
	 * @return
	 */
	public boolean delPackage(Integer id);

	/**
	 * 获取升级包领域对象信息
	 * 
	 * @MethodName: fingPackageID
	 * @param id
	 * @return
	 */
	public Package fingPackageID(Integer id);

	/**
	 * 添加升级包数据
	 * 
	 * @param pa
	 *            升级对象
	 * @return
	 */
	public Package addPackage(Package pa);

	/**
	 * 更新升级包数据
	 * 
	 * @param newPa
	 *            最新对象
	 * @param oID
	 *            修改ID
	 * @return
	 */
	public Package updatePackage(Package newPa, boolean withFile, Integer oID);

	/**
	 * 修改升级包状态
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	public boolean updatePackageStatus(Integer status, Date updateTime, Integer id);

	/**
	 * 查询指定项目最后提交包记录
	 * 
	 * @MethodName: findLastPackageByProID
	 * @param proID
	 * @param packageType
	 * @return
	 */
	public Package findLastPackageByProIDAndType(Integer proID, Integer packageType);

	/**
	 * 修改版本及版本号（用于测试通过）
	 * 
	 * @param proID
	 * @param tag
	 * @param version
	 * @return
	 */

	public boolean updatePackageTagAndVersion(Integer id, String tag, String version);

	/**
	 * 修改版本(用于废弃)
	 * 
	 * @MethodName: updatePackageTag
	 * @param id
	 * @param tag
	 * @return
	 */
	public boolean updatePackageTag(Integer id, String tag);

	/**
	 * 添加升级包并返回主键
	 * 
	 * @param pa
	 * @return
	 */
	public Long addPackageGetKey(final Package pa);

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
