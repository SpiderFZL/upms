package cn.com.zhyu.upm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.zhyu.upm.dao.ProjectDAO;
import cn.com.zhyu.upm.pojo.Project;
import cn.com.zhyu.upm.service.ProjectService;

/**
 * ProjectService实现
 * 
 * @ClassName: ProjectServiceImpl
 * @author tangwe
 * @date 2014年11月13日 下午4:31:09
 * @Description: TODO(所属项目业务实现)
 * @version V1.0
 */
@Service("ProjectService")
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectDAO projectDAO;

	@Override
	public List<Project> getAuthedPro(String auth) {
		return projectDAO.findValidProjects(auth);
	}

	@Override
	public List<Project> getAllPro() {
		return projectDAO.findAllProjects();
	}

	@Override
	public Project findProByID(Integer id) {
		return projectDAO.findProByID(id);
	}

}
