package cn.com.zhyu.upm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import cn.com.zhyu.upm.dao.BaseDAO;
import cn.com.zhyu.upm.dao.ProjectDAO;
import cn.com.zhyu.upm.pojo.Project;

/**
 * ProjectDAO接口实现
 * 
 * @ClassName: ProjectDAOImpl
 * @author tangwe
 * @date 2014年11月13日 下午4:21:34
 * @Description: TODO(实现ProjectDAO规范方法，访问数据层，返回数据)
 * @version V1.0
 */
@Repository("ProjectDAO")
public class ProjectDAOImpl extends BaseDAO implements ProjectDAO {

	@Override
	public List<Project> findValidProjects(String auth) {
		String sql = "SELECT PRO.ID,PRO.PROJECTNAME FROM ZHYU_PROJECT PRO WHERE PRO.ID IN ("
				+ auth + ")";
		final List<Project> proList = new ArrayList<Project>();
		this.jdbcTemplate.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setProjectName(rs.getString("projectname"));
				proList.add(pro);
			}

		});
		return proList;
	}

	@Override
	public List<Project> findAllProjects() {
		String sql = "SELECT PRO.ID,PRO.PROJECTNAME FROM ZHYU_PROJECT PRO";
		final List<Project> proList = new ArrayList<Project>();
		this.jdbcTemplate.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setProjectName(rs.getString("projectname"));
				proList.add(pro);
			}

		});
		return proList;
	}

	@Override
	public Project findProByID(Integer id) {
		String sql = "SELECT PRO.ID,PRO.PROJECTNAME FROM ZHYU_PROJECT PRO WHERE PRO.ID=?";
		final Project pro = new Project();
		this.jdbcTemplate.query(sql, new Integer[] { id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						pro.setId(rs.getInt("id"));
						pro.setProjectName(rs.getString("projectname"));
					}

				});
		return pro;
	}

}
