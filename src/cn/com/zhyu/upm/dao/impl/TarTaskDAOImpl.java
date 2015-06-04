package cn.com.zhyu.upm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import cn.com.zhyu.upm.dao.BaseDAO;
import cn.com.zhyu.upm.dao.TarTaskDAO;
import cn.com.zhyu.upm.pojo.TarTask;

/**
 * 
 * @ClassName: TarTaskDAOImpl
 * @author tangwe
 * @date 2015年1月5日 下午3:39:43
 * @Description: TODO(打包任务DAO实现)
 * @version V1.0
 */

@Repository("TarTaskDAO")
public class TarTaskDAOImpl extends BaseDAO implements TarTaskDAO {

	@Override
	public TarTask findTaskByProIDAndType(Integer proID, Integer packageType) {
		String sql = "SELECT T.ID,T.PROJECTID,T.PACKAGETYPE,T.STATUS,T.REMARK FROM ZHYU_TARTASK T WHERE T.PROJECTID=? AND T.PACKAGETYPE=?";
		final Integer[] params = { proID, packageType };
		final TarTask tarTask = new TarTask();
		this.jdbcTemplate.query(sql, params, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				tarTask.setId(rs.getInt("id"));
				tarTask.setPackageType(rs.getInt("packagetype"));
				tarTask.setProjectID(rs.getInt("projectid"));
				tarTask.setStatus(rs.getInt("status"));
				tarTask.setRemark(rs.getString("remark"));
			}
		});
		return tarTask;
	}

	@Override
	public boolean updateTaskStatusByID(Integer id, Integer status) {
		String sql = "UPDATE ZHYU_TARTASK T SET T.STATUS=? WHERE T.ID=?";
		Object[] args = { status, id };
		int re = this.jdbcTemplate.update(sql, args);
		if (re > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateTaskRemark(Integer id, String remark) {
		String sql = "UPDATE ZHYU_TARTASK T SET T.REMARK=? WHERE T.ID=?";
		Object[] args = { remark, id };
		int re = this.jdbcTemplate.update(sql, args);
		if (re > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateAllTaskStatus() {
		String sql = "UPDATE ZHYU_TARTASK T SET T.STATUS=0";
		int re = this.jdbcTemplate.update(sql);
		if (re > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addTarLog(Long packageId, String tarLogInfo) {
		String sql = "INSERT INTO ZHYU_TARLOG(PACKAGEID,TARLOG) VALUES(?,?)";
		Object[] args = { packageId, tarLogInfo };
		int re = this.jdbcTemplate.update(sql, args);
		return re == 1;
	}

	@Override
	public String findTarLogByPackageId(Integer packageId) {
		String sql = "SELECT TL.TARLOG FROM ZHYU_TARLOG TL WHERE TL.PACKAGEID=?";
		Object[] args = { packageId };
		try {
			String result = this.jdbcTemplate.queryForObject(sql, String.class, args);
			return result;
		} catch (DataAccessException e) {
			return "";
		}

	}
}
