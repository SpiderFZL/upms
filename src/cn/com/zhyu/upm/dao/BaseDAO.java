package cn.com.zhyu.upm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @ClassName: BaseDAO
 * @author tangwe
 * @date 2014年11月12日 上午9:47:58
 * @Description: TODO(DAO基类，用于DAO继承类方法调用)
 * @version V1.0
 */
public class BaseDAO {
	@Autowired
	protected JdbcTemplate jdbcTemplate;
}
