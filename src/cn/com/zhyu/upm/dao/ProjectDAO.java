package cn.com.zhyu.upm.dao;

import java.util.List;

import cn.com.zhyu.upm.pojo.Project;

/**
 * 所属项目DAO接口
 * 
 * @ClassName: ProjectDAO
 * @author tangwe
 * @date 2014年11月13日 下午4:17:01
 * @Description: TODO(规范数据访问层接口，用于继承)
 * @version V1.0
 */
public interface ProjectDAO {
	/**
	 * 查询权限项目
	 * 
	 * @param auth
	 * @return
	 */
	public List<Project> findValidProjects(String auth);

	/**
	 * 查询所有项目
	 * 
	 * @return
	 */
	public List<Project> findAllProjects();

	/**
	 * 根据id找项目
	 * 
	 * @return
	 */
	public Project findProByID(Integer id);

}
