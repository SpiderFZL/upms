package cn.com.zhyu.upm.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.zhyu.upm.dao.BaseDAO;
import cn.com.zhyu.upm.dao.DownloadDAO;
import cn.com.zhyu.upm.pojo.Download;

/**
 * @ClassName: DownloadDAOImpl
 * @author tangwe
 * @date 2015年2月2日 下午1:20:23
 * @Description: TODO(下载接口实现)
 * @version V1.0
 */
@Repository("DownloadDAO")
public class DownloadDAOImpl extends BaseDAO implements DownloadDAO {

	@Override
	public long getDownloadCountByPackageID(Integer pagekageID) {
		String sql = "SELECT COUNT(D.ID) FROM ZHYU_DOWNLOAD D WHERE D.PACKAGEID=?";
		return this.jdbcTemplate.queryForLong(sql, new Object[] { pagekageID });
	}

	@Override
	public boolean addDownload(Download d) {
		String sql = "INSERT INTO ZHYU_DOWNLOAD(DOWNTIME,PACKAGEID,USERNAME) VALUES(?,?,?)";
		Object[] params = { d.getDowntime(), d.getPackageID(), d.getUserName() };
		int re = this.jdbcTemplate.update(sql, params);
		return re > 0;
	}

}
