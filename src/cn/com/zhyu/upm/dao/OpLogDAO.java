package cn.com.zhyu.upm.dao;

import cn.com.zhyu.upm.pojo.OpLog;

/**
 * @ClassName: OpLogDAO
 * @author tangwe
 * @date 2015年1月8日 上午9:45:16
 * @Description: TODO(日志接口)
 * @version V1.0
 */
public interface OpLogDAO {
	/**
	 * 添加日志
	 * 
	 * @param log
	 * @return
	 */
	public boolean addLog(OpLog log);
}
