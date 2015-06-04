package cn.com.zhyu.upm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.zhyu.upm.dao.OpLogDAO;
import cn.com.zhyu.upm.pojo.OpLog;
import cn.com.zhyu.upm.service.OpLogService;

/**
 * @ClassName: OpLogServiceImpl
 * @author tangwe
 * @date 2015年1月8日 上午10:04:04
 * @Description: TODO(写入日志)
 * @version V1.0
 */
@Service("OpLogService")
public class OpLogServiceImpl implements OpLogService {
	@Autowired
	private OpLogDAO opLogDAO;

	@Override
	public boolean writeLog(OpLog log) {
		return opLogDAO.addLog(log);
	}

}
