package cn.com.zhyu.upm.service;

import java.util.List;

import cn.com.zhyu.upm.pojo.Project;

/**
 * 所属项目业务接口
 * 
 * @ClassName: ProjectService
 * @author tangwe
 * @date 2014年11月13日 下午4:28:16
 * @Description: TODO(有关于所属项目业务方法)
 * @version V1.0
 */
public interface ProjectService {
	/**
	 * 获取权限内的所属项目
	 * 
	 * @param auth
	 * @return
	 */
	public List<Project> getAuthedPro(String auth);

	/**
	 * 获取所有项目
	 * 
	 * @return
	 */
	public List<Project> getAllPro();

	/**
	 * 获取指定ID项目
	 * 
	 * @param id
	 * @return
	 */
	public Project findProByID(Integer id);
}
