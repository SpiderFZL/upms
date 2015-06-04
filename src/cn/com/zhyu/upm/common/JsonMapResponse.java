package cn.com.zhyu.upm.common;

import java.util.Map;

/**
 * json返回信息封装类
 * 
 * @ClassName: JsonMapResponse
 * @author tangwe
 * @date 2014年11月12日 上午11:32:38
 * @Description: TODO(封装json返回基本信息及Map数据信息)
 * @version V1.0
 */
public class JsonMapResponse {
	private Integer status;// 返回状态
	private boolean success; // 操作成功失败标识
	private String msg;// 信息
	private Map<String, Object> dataMap;

	/**
	 * 自定义设置返回Map信息
	 * 
	 * @MethodName: setJsonMap
	 * @param status
	 * @param success
	 * @param msg
	 * @param dataMap
	 */
	public void setJsonMap(Integer status, boolean success, String msg,
			Map<String, Object> dataMap) {
		this.status = status;
		this.success = success;
		this.msg = msg;
		this.dataMap = dataMap;
	}

	/**
	 * 返回成功Map信息，不添加msg说明
	 * 
	 * @MethodName: jsonMapSuccess
	 * @param dataMap
	 */
	public void jsonMapSuccess(Map<String, Object> dataMap) {
		this.status = 200;
		this.success = true;
		this.msg = "";
		this.dataMap = dataMap;
	}

	/**
	 * 返回失败Map信息，不添加msg说明
	 * 
	 * @MethodName: jsonMapSuccess
	 * @param dataMap
	 */
	public void jsonMapFail(Map<String, Object> dataMap) {
		this.status = 500;
		this.success = false;
		this.msg = "";
		this.dataMap = dataMap;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

}
