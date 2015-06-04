package cn.com.zhyu.upm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import cn.com.zhyu.upm.common.CommUtil;
import cn.com.zhyu.upm.dao.BaseDAO;
import cn.com.zhyu.upm.dao.PackageDAO;
import cn.com.zhyu.upm.dto.PackageDTO;
import cn.com.zhyu.upm.pojo.Package;

/**
 * Upgrade Package 接口实现
 * 
 * @ClassName: PackageDAOImpl
 * @author tangwe
 * @date 2014年11月12日 下午3:55:16
 * @Description: TODO(实现PackageDAO规范方法，访问数据层，返回数据)
 * @version V1.0
 */
@Repository("PackageDAO")
public class PackageDAOImpl extends BaseDAO implements PackageDAO {

	@Override
	public List<PackageDTO> findPagedPackages(int pageNow, int pageSize, Map<String, String> params) {
		String sql = "SELECT PA.ID,PA.PACKAGENAME,PA.TAG,PA.VERSION,PA.PACKAGESIZE,PA.CREATETIME,PA.DOWNLOADLINK,PA.DOWNLOADSTATUS,PA.STATUS,PA.PACKAGETYPE,PA.PROJECTID,PA.USERID,U.USERNAME,U.REALNAME,U.MAIL,U.AUTH,PRO.PROJECTNAME,T.TYPENAME FROM ZHYU_PACKAGE PA "
				+ "LEFT JOIN ZHYU_USER U ON U.ID = PA.USERID LEFT JOIN ZHYU_PROJECT PRO ON PRO.ID = PA.PROJECTID LEFT JOIN ZHYU_DICTYPE T ON T.ID = PA.PACKAGETYPE WHERE PA.VISABLE=1 ";
		StringBuffer sb = new StringBuffer(sql);
		if (params != null && params.size() > 0) {
			// 查询参数，值拼接
			for (Entry<String, String> entry : params.entrySet()) {
				String param = entry.getKey();
				String value = entry.getValue();
				if ("PROJECTID".equals(param)) {
					sb.append("AND " + param + " IN (" + value + ") ");
				} else {
					if ("".equals(value)) {
						sb.append("AND " + param + " != 'discard' ");
					} else {
						sb.append("AND " + param + " = '" + value + "' ");
					}
				}
			}
		}
		sb.append("ORDER BY PA.CREATETIME DESC ");
		sb.append("LIMIT ?,?");
		final List<PackageDTO> paList = new ArrayList<PackageDTO>();
		this.jdbcTemplate.query(sb.toString(), new Object[] { (pageNow - 1) * pageSize, pageSize }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				PackageDTO pa = new PackageDTO();
				pa.setAuth(rs.getString("auth"));
				pa.setCreateTimeLabel(rs.getDate("createtime") + " " + rs.getTime("createtime"));
				pa.setDownloadLink(rs.getString("downloadlink"));
				pa.setId(rs.getInt("id"));
				pa.setMail(rs.getString("mail"));
				pa.setPackgeSize(rs.getString("packagesize"));
				pa.setProjectID(rs.getInt("projectid"));
				pa.setProjectName(rs.getString("projectname"));
				pa.setRealName(rs.getString("realname"));
				pa.setPackageType(rs.getInt("packagetype"));
				pa.setStatus(rs.getShort("status"));
				pa.setTag(rs.getString("tag"));
				pa.setUserID(rs.getInt("userid"));
				pa.setUserName(rs.getString("username"));
				pa.setVersion(rs.getString("version"));
				pa.setPackageName(rs.getString("packagename"));
				pa.setDownloadStatus(rs.getInt("downloadstatus"));
				pa.setTypeName(rs.getString("typename"));
				paList.add(pa);
			}
		});
		return paList;
	}

	@Override
	public long getPackageTotalCount(Map<String, String> params) {
		String sql = "SELECT COUNT(PA.ID) FROM ZHYU_PACKAGE PA LEFT JOIN ZHYU_USER U ON U.ID = PA.USERID LEFT JOIN ZHYU_PROJECT PRO ON PRO.ID = PA.PROJECTID LEFT JOIN ZHYU_DICTYPE T ON T.ID = PA.PACKAGETYPE WHERE PA.VISABLE=1 ";
		StringBuffer sb = new StringBuffer(sql);
		if (params != null && params.size() > 0) {
			// 查询参数，值拼接
			for (Entry<String, String> entry : params.entrySet()) {
				String param = entry.getKey();
				String value = entry.getValue();
				if ("PROJECTID" == param) {
					sb.append("AND " + param + " IN (" + value + ") ");
				} else {
					if ("".equals(value)) {
						sb.append("AND " + param + " != 'discard' ");
					} else {
						sb.append("AND " + param + " = '" + value + "' ");
					}
				}
			}
		}
		return this.jdbcTemplate.queryForLong(sb.toString());
	}

	@Override
	public PackageDTO findPackageByID(Integer id) {
		String sql = "SELECT PA.ID,PA.PACKAGENAME,PA.TAG,PA.VERSION,PA.PACKAGESIZE,PA.CREATETIME,PA.REMARK,PA.DOWNLOADLINK,PA.DOWNLOADSTATUS,PA.STATUS,PA.PACKAGETYPE,PA.PROJECTID,PA.USERID,U.USERNAME,U.REALNAME,U.MAIL,U.AUTH,PRO.PROJECTNAME FROM ZHYU_PACKAGE PA "
				+ "LEFT JOIN ZHYU_USER U ON U.ID = PA.USERID LEFT JOIN ZHYU_PROJECT PRO ON PRO.ID = PA.PROJECTID WHERE PA.VISABLE=1 AND PA.ID=?";
		final PackageDTO pa = new PackageDTO();
		this.jdbcTemplate.query(sql, new Integer[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				pa.setAuth(rs.getString("auth"));
				pa.setCreateTime(rs.getDate("createtime"));
				pa.setDownloadLink(rs.getString("downloadlink"));
				pa.setId(rs.getInt("id"));
				pa.setMail(rs.getString("mail"));
				pa.setPackgeSize(rs.getString("packagesize"));
				pa.setProjectID(rs.getInt("projectid"));
				pa.setProjectName(rs.getString("projectname"));
				pa.setRealName(rs.getString("realname"));
				pa.setRemark(rs.getString("remark"));
				pa.setPackageType(rs.getInt("packagetype"));
				pa.setStatus(rs.getShort("status"));
				pa.setTag(rs.getString("tag"));
				pa.setUserID(rs.getInt("userid"));
				pa.setUserName(rs.getString("username"));
				pa.setVersion(rs.getString("version"));
				pa.setPackageName(rs.getString("packagename"));
				pa.setDownloadStatus(rs.getInt("downloadstatus"));
			}
		});
		return pa;
	}

	@Override
	public boolean delPackage(Integer id) {
		String sql = "UPDATE ZHYU_PACKAGE PA SET PA.VISABLE=0 WHERE PA.ID=?";
		Object[] params = { id };
		int re = this.jdbcTemplate.update(sql, params);
		if (re > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Package fingPackageID(Integer id) {
		String sql = "SELECT PA.ID,PA.TAG,PA.PACKAGENAME,PA.VERSION,PA.PACKAGESIZE,PA.CREATETIME,PA.REMARK,PA.DOWNLOADLINK,PA.DOWNLOADSTATUS,PA.STATUS,PA.PACKAGETYPE,PA.PROJECTID,PA.USERID FROM ZHYU_PACKAGE PA WHERE PA.VISABLE=1 AND PA.ID=?";
		final Package pa = new Package();
		this.jdbcTemplate.query(sql, new Integer[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				pa.setCreateTime(rs.getDate("createtime"));
				pa.setDownloadLink(rs.getString("downloadlink"));
				pa.setId(rs.getInt("id"));
				pa.setPackgeSize(rs.getString("packagesize"));
				pa.setProjectID(rs.getInt("projectid"));
				pa.setRemark(rs.getString("remark"));
				pa.setPackageType(rs.getInt("packagetype"));
				pa.setStatus(rs.getShort("status"));
				pa.setTag(rs.getString("tag"));
				pa.setUserID(rs.getInt("userid"));
				pa.setVersion(rs.getString("version"));
				pa.setPackageName(rs.getString("packagename"));
				pa.setDownloadStatus(rs.getInt("downloadstatus"));
			}
		});
		return pa;
	}

	@Override
	public Package addPackage(Package pa) {
		String sql = "INSERT INTO ZHYU_PACKAGE(TAG,PACKAGENAME,VERSION,PACKAGESIZE,CREATETIME,REMARK,DOWNLOADLINK,VISABLE,DOWNLOADSTATUS,STATUS,PACKAGETYPE,PROJECTID,USERID) VALUES(?,?,?,?,?,?,?,1,1,1,?,?,?)";
		Object[] params = { pa.getTag(), pa.getPackageName(), pa.getVersion(), pa.getPackgeSize(), pa.getCreateTime(), pa.getRemark(), pa.getDownloadLink(),
				pa.getPackageType(), pa.getProjectID(), pa.getUserID() };
		int re = this.jdbcTemplate.update(sql, params);
		if (re > 0) {
			return pa;
		}
		return null;
	}

	@Override
	public Package updatePackage(Package newPa, boolean withFile, Integer oID) {
		String sql = "";
		int re = 0;
		if (withFile) {
			sql = "UPDATE ZHYU_PACKAGE PA SET PA.TAG=?,PA.VERSION=?,PA.UPDATETIME=?,PA.REMARK=?,PA.DOWNLOADSTATUS=?,PA.PACKAGETYPE=?,PA.PROJECTID=?,PA.PACKAGENAME=?,PA.PACKAGESIZE=?,PA.DOWNLOADLINK=? WHERE ID=?";
			Object[] params = { newPa.getTag(), newPa.getVersion(), newPa.getUpdateTime(), newPa.getRemark(), newPa.getDownloadStatus(),
					newPa.getPackageType(), newPa.getProjectID(), newPa.getPackageName(), newPa.getPackgeSize(), newPa.getDownloadLink(), oID };
			re = this.jdbcTemplate.update(sql, params);
		} else {
			sql = "UPDATE ZHYU_PACKAGE PA SET PA.TAG=?,PA.VERSION=?,PA.UPDATETIME=?,PA.REMARK=?,PA.DOWNLOADSTATUS=?,PA.PACKAGETYPE=?,PA.PROJECTID=? WHERE ID=?";
			Object[] params = { newPa.getTag(), newPa.getVersion(), newPa.getUpdateTime(), newPa.getRemark(), newPa.getDownloadStatus(),
					newPa.getPackageType(), newPa.getProjectID(), oID };
			re = this.jdbcTemplate.update(sql, params);
		}
		if (re > 0) {
			return newPa;
		}
		return null;
	}

	@Override
	public boolean updatePackageStatus(Integer status, Date updateTime, Integer id) {
		String sql = "UPDATE ZHYU_PACKAGE PA SET PA.STATUS=?,PA.UPDATETIME=? WHERE PA.ID=?";
		Object[] params = { status, updateTime, id };
		int re = this.jdbcTemplate.update(sql, params);
		if (re > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Package findLastPackageByProIDAndType(Integer proID, Integer packageType) {
		String sql = "SELECT PA.VERSION FROM ZHYU_PACKAGE PA WHERE PA.PROJECTID=? AND PACKAGETYPE=? AND PA.VISABLE=1 ORDER BY PA.CREATETIME DESC";
		final List<Package> paList = new ArrayList<Package>();
		this.jdbcTemplate.query(sql, new Integer[] { proID, packageType }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Package pa = new Package();
				pa.setVersion(rs.getString("version"));
				paList.add(pa);
			}
		});
		if (paList.size() > 0) {
			return paList.get(0);
		}
		return null;
	}

	@Override
	public boolean updatePackageTagAndVersion(Integer id, String tag, String version) {
		String sql = "UPDATE ZHYU_PACKAGE PA SET PA.TAG=?,PA.VERSION=? WHERE PA.ID=?";
		Object[] params = { tag, version, id };
		int re = this.jdbcTemplate.update(sql, params);
		if (re > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updatePackageTag(Integer id, String tag) {
		String sql = "UPDATE ZHYU_PACKAGE PA SET PA.TAG=? WHERE PA.ID=?";
		Object[] params = { tag, id };
		int re = this.jdbcTemplate.update(sql, params);
		if (re > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Long addPackageGetKey(final Package pa) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO ZHYU_PACKAGE(TAG,PACKAGENAME,VERSION,PACKAGESIZE,CREATETIME,REMARK,DOWNLOADLINK,VISABLE,DOWNLOADSTATUS,STATUS,PACKAGETYPE,PROJECTID,USERID) VALUES(?,?,?,?,?,?,?,1,1,1,?,?,?)";
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, pa.getTag());
				ps.setString(2, pa.getPackageName());
				ps.setString(3, pa.getVersion());
				ps.setString(4, pa.getPackgeSize());
				ps.setString(5, CommUtil.formatDateToString(pa.getCreateTime()));
				ps.setString(6, pa.getRemark());
				ps.setString(7, pa.getDownloadLink());
				ps.setInt(8, pa.getPackageType());
				ps.setInt(9, pa.getProjectID());
				ps.setInt(10, pa.getUserID());
				return ps;
			}
		}, keyHolder);
		Long generatedId = keyHolder.getKey().longValue();
		return generatedId;
	}

	@Override
	public boolean updateDownloadStatus(Long id, int status) {
		String sql = "UPDATE ZHYU_PACKAGE PA SET PA.DOWNLOADSTATUS=? WHERE PA.ID=?";
		Object[] params = { status, id };
		int re = this.jdbcTemplate.update(sql, params);
		return re == 1;
	}

	@Override
	public boolean updatePackageStatus(Long id, int status) {
		String sql = "UPDATE ZHYU_PACKAGE PA SET PA.STATUS=? WHERE PA.ID=?";
		Object[] params = { status, id };
		int re = this.jdbcTemplate.update(sql, params);
		return re == 1;
	}
}
