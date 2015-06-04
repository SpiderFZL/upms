package cn.com.zhyu.upm.dao;

import cn.com.zhyu.upm.pojo.Download;

/**
 * @ClassName: DownloadDAO
 * @author tangwe
 * @date 2015年2月2日 下午1:17:28
 * @Description: TODO(下载接口)
 * @version V1.0
 */
public interface DownloadDAO {
	/**
	 * 获取包的下载次数
	 * 
	 * @param pagekageID
	 * @return
	 */
	public long getDownloadCountByPackageID(Integer pagekageID);

	/**
	 * 添加下载记录
	 * 
	 * @param d
	 * @return
	 */
	public boolean addDownload(Download d);
}
