package cn.com.zhyu.upm.service;

import cn.com.zhyu.upm.pojo.Download;

/**
 * @ClassName: DownloadService
 * @author tangwe
 * @date 2015年2月2日 下午1:30:09
 * @Description: TODO(下载业务接口)
 * @version V1.0
 */
public interface DownloadService {
	/**
	 * 获取下载统计次数
	 * 
	 * @param packageID
	 * @return
	 */
	public long getDownloadCountByPackageID(Integer packageID);

	/**
	 * 添加下载记录
	 * 
	 * @MethodName: addDownload
	 * @param d
	 * @return
	 */
	public boolean addDownload(Download d);
}
