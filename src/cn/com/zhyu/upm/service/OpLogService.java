package cn.com.zhyu.upm.service;

import cn.com.zhyu.upm.pojo.OpLog;

/**
 * @ClassName: OpLogService
 * @author tangwe
 * @date 2015年1月8日 上午9:55:57
 * @Description: TODO(日志业务)
 * @version V1.0
 */
public interface OpLogService {
	/**
	 * 写入日志
	 * 
	 * @return
	 */
	public boolean writeLog(OpLog log);
}
