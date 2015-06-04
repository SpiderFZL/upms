package cn.com.zhyu.upm.common;

/**
 * json返回信息封装类
 * 
 * @ClassName: JsonObjResponse
 * @author tangwe
 * @date 2014年11月12日 上午11:24:42
 * @Description: TODO(封装json返回基本信息及Obj数据信息)
 * @version V1.0
 */
public class JsonObjResponse {
	private Integer status;// 返回状态
	private boolean success; // 操作成功失败标识
	private String msg;// 信息
	private Object obj;// 实体对象

	/**
	 * 自定义设置返回实体信息
	 * 
	 * @MethodName: setJsonObj
	 * @param status
	 * @param success
	 * @param msg
	 * @param obj
	 */
	public void setJsonObj(Integer status, boolean success, String msg, Object obj) {
		this.status = status;
		this.success = success;
		this.msg = msg;
		this.obj = obj;
	}

	/**
	 * 返回成功实体信息，不添加msg说明
	 * 
	 * @MethodName: jsonObjSuccess
	 * @param obj
	 */
	public void jsonObjSuccess(Object obj) {
		this.status = 200;
		this.success = true;
		this.msg = "";
		this.obj = obj;
	}

	/**
	 * 返回失败实体信息，不添加msg说明
	 * 
	 * @MethodName: jsonObjSuccess
	 * @param obj
	 */
	public void jsonObjFail() {
		this.status = 500;
		this.success = false;
		this.msg = "";
		this.obj = null;
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

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
