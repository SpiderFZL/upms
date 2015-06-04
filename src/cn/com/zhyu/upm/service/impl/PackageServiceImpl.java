package cn.com.zhyu.upm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.zhyu.upm.common.CommUtil;
import cn.com.zhyu.upm.common.Page;
import cn.com.zhyu.upm.dao.ChatDAO;
import cn.com.zhyu.upm.dao.DownloadDAO;
import cn.com.zhyu.upm.dao.PackageDAO;
import cn.com.zhyu.upm.dto.PackageDTO;
import cn.com.zhyu.upm.pojo.Package;
import cn.com.zhyu.upm.service.PackageService;

/**
 * PackageService实现
 * 
 * @ClassName: PackageServiceImpl
 * @author tangwe
 * @date 2014年11月13日 上午9:30:01
 * @Description: TODO(升级包业务实现，有关于升级包业务方法实现)
 * @version V1.0
 */
@Service("PackageService")
public class PackageServiceImpl implements PackageService {
	@Autowired
	private PackageDAO packageDAO;
	@Autowired
	private ChatDAO chatDAO;
	@Autowired
	private DownloadDAO downloadDAO;

	@Override
	public Page getPagedPackageDTO(int pageNow, int pageSize, Map<String, String> params) {
		List<PackageDTO> data = packageDAO.findPagedPackages(pageNow, pageSize, params);// 获取分页数据
		for (PackageDTO packDTO : data) {
			Integer packageID = packDTO.getId();
			long chatCount = chatDAO.getChatCountByPackageID(packageID); // 获取评论统计
			long downloadCount = downloadDAO.getDownloadCountByPackageID(packageID); // 获取下载统计
			packDTO.setChatCount(chatCount);
			packDTO.setDownloadCount(downloadCount);
		}
		long totalCount = packageDAO.getPackageTotalCount(params);// 获取数据总数
		return new Page(pageNow, pageSize, data, totalCount);
	}

	@Override
	public PackageDTO getPackageDTOByID(Integer id) {
		return packageDAO.findPackageByID(id);
	}

	@Override
	public boolean delPackage(Integer id) {
		return packageDAO.delPackage(id);
	}

	@Override
	public Package getPackageByID(Integer id) {
		return packageDAO.fingPackageID(id);
	}

	@Override
	public Package addPackage(Package pa) {
		return packageDAO.addPackage(pa);
	}

	@Override
	public Package updatePackage(Package newPa, boolean withFile, Integer oID) {
		return packageDAO.updatePackage(newPa, withFile, oID);
	}

	@Override
	public boolean updatePackageStatus(Integer status, Date updateTime, Integer id) {
		return packageDAO.updatePackageStatus(status, updateTime, id);
	}

	@Override
	public String getLastVersion(Integer proID, Integer packageType) {
		Package pa = packageDAO.findLastPackageByProIDAndType(proID, packageType);
		if (pa != null) {
			String version = pa.getVersion();
			if (version.indexOf(" ") == -1) {
				return "";
			} else {
				return version.substring(0, version.indexOf(" "));// 截取版本号
			}
		}
		return "";
	}

	@Override
	public boolean testPassPackage(Integer id) {
		Package pa = this.getPackageByID(id);
		if (pa == null) {
			return false;
		}
		String versionInDB = pa.getVersion();// 获取版本号
		if (versionInDB.indexOf("-") == -1 || versionInDB.indexOf("[") == -1 || versionInDB.indexOf("]") == -1) {
			return false;
		}
		String version_pre = versionInDB.substring(0, versionInDB.indexOf("-"));// 1.0.0.1
		String version_aft = versionInDB.substring(versionInDB.indexOf("-"), versionInDB.length());
		version_pre = CommUtil.versionOverlap(version_pre, 3);// 叠加项目版本值
		version_pre = version_pre.substring(0, version_pre.lastIndexOf(".") + 1) + "0";
		String version = version_pre + version_aft;// 拼接完整版本
		return packageDAO.updatePackageTagAndVersion(id, "release", version);
	}

	@Override
	public boolean discardPackage(Integer id) {
		return packageDAO.updatePackageTag(id, "discard");
	}

	@Override
	public Long addPackageGetKey(Package pa) {
		return packageDAO.addPackageGetKey(pa);
	}

	@Override
	public boolean updateDownloadStatus(Long id, int status) {
		return packageDAO.updateDownloadStatus(id, status);
	}

	@Override
	public boolean updatePackageStatus(Long id, int status) {
		return packageDAO.updatePackageStatus(id, status);
	}
}
