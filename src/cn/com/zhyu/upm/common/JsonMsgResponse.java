package cn.com.zhyu.upm.common;

/**
 * json返回信息封装类
 * 
 * @ClassName: JsonResponse
 * @author tangwe
 * @date 2014年11月12日 上午10:57:03
 * @Description: TODO(封装json返回基本信息)
 * @version V1.0
 */
public class JsonMsgResponse {
	private Integer status;// 返回状态
	private boolean success; // 操作成功失败标识
	private String msg;// 信息

	/**
	 * 自定义设置返回信息结果
	 * 
	 * @MethodName: setJsonMsg
	 * @param status
	 * @param success
	 * @param msg
	 */
	public void setJsonMsg(Integer status, boolean success, String msg) {
		this.status = status;
		this.success = success;
		this.msg = msg;
	}

	/**
	 * 返回成功信息，不添加msg说明
	 * 
	 * @MethodName: jsonMsgSuccess
	 */
	public void jsonMsgSuccess() {
		this.status = 200;
		this.success = true;
		this.msg = "";
	}

	/**
	 * 返回失败信息，不添加msg说明
	 * 
	 * @MethodName: jsonMsgSuccess
	 */
	public void jsonMsgFail() {
		this.status = 500;
		this.success = false;
		this.msg = "";
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
}
