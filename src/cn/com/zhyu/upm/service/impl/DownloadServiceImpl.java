package cn.com.zhyu.upm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.zhyu.upm.dao.DownloadDAO;
import cn.com.zhyu.upm.pojo.Download;
import cn.com.zhyu.upm.service.DownloadService;

/**
 * @ClassName: DownloadService
 * @author tangwe
 * @date 2015年2月2日 下午1:33:30
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @version V1.0
 */
@Service("DownloadService")
public class DownloadServiceImpl implements DownloadService {
	@Autowired
	private DownloadDAO downloadDAO;

	@Override
	public long getDownloadCountByPackageID(Integer packageID) {
		return downloadDAO.getDownloadCountByPackageID(packageID);
	}

	@Override
	public boolean addDownload(Download d) {
		return downloadDAO.addDownload(d);
	}

}
