package cn.com.zhyu.upm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import cn.com.zhyu.upm.dao.BaseDAO;
import cn.com.zhyu.upm.dao.PackageTypeDAO;
import cn.com.zhyu.upm.pojo.PackageType;

/**
 * @ClassName: PackageTypeDAOImpl
 * @author tangwe
 * @date 2014年11月26日 下午3:43:39
 * @Description: TODO(升级包类型实现)
 * @version V1.0
 */
@Repository("PackageTypeDAO")
public class PackageTypeDAOImpl extends BaseDAO implements PackageTypeDAO {

	@Override
	public List<PackageType> findPackageTypeByProID(Integer projectID) {
		final List<PackageType> ptList = new ArrayList<PackageType>();
		String type = this.findTypeByProID(projectID);
		if (StringUtils.isEmpty(type)) {
			return ptList;
		}
		String sql = "SELECT T.ID,T.TYPENAME FROM ZHYU_DICTYPE T WHERE T.ID IN(" + type + ")";
		this.jdbcTemplate.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				PackageType pt = new PackageType();
				pt.setId(rs.getInt("id"));
				pt.setTypeName(rs.getString("typename"));
				ptList.add(pt);
			}

		});
		return ptList;
	}

	/**
	 * 查询项目type字段
	 * 
	 * @param projectID
	 * @return
	 */
	private String findTypeByProID(Integer projectID) {
		String sql = "SELECT PRO.TYPE FROM ZHYU_PROJECT PRO WHERE PRO.ID=?";
		Object[] params = { projectID };
		String type = "";
		type = this.jdbcTemplate.queryForObject(sql, params, String.class);
		return type;
	}

	@Override
	public List<PackageType> findPackageTypes() {
		final List<PackageType> ptList = new ArrayList<PackageType>();
		String sql = "SELECT T.ID,T.TYPENAME FROM ZHYU_DICTYPE T";
		this.jdbcTemplate.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				PackageType pt = new PackageType();
				pt.setId(rs.getInt("id"));
				pt.setTypeName(rs.getString("typename"));
				ptList.add(pt);
			}

		});
		return ptList;
	}
}
