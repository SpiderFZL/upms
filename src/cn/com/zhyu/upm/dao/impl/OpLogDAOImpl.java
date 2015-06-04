package cn.com.zhyu.upm.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.zhyu.upm.dao.BaseDAO;
import cn.com.zhyu.upm.dao.OpLogDAO;
import cn.com.zhyu.upm.pojo.OpLog;

/**
 * 
 * @ClassName: OpLogDAOImpl
 * @author tangwe
 * @date 2015年1月8日 上午9:47:27
 * @Description: TODO(日志接口实现)
 * @version V1.0
 */
@Repository("OpLogDAO")
public class OpLogDAOImpl extends BaseDAO implements OpLogDAO {

	@Override
	public boolean addLog(OpLog log) {
		String sql = "INSERT INTO ZHYU_LOG(USERNAME,CONTENT,CREATETIME) VALUES(?,?,?)";
		Object[] args = { log.getUserName(), log.getContent(), log.getCreateTime() };
		int re = this.jdbcTemplate.update(sql, args);
		if (re > 0) {
			return true;
		}
		return false;
	}

}
