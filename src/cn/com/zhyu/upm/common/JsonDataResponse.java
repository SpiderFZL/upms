package cn.com.zhyu.upm.common;

import java.util.List;

/**
 * json返回信息封装类
 * 
 * @ClassName: JsonDataResponse
 * @author tangwe
 * @date 2014年11月12日 上午11:07:52
 * @Description: TODO(封装json返回基本信息及list数据信息)
 * @version V1.0
 */
public class JsonDataResponse {
	private Integer status;// 返回状态
	private boolean success; // 操作成功失败标识
	private String msg;// 信息
	@SuppressWarnings("rawtypes")
	private List dataList; // 数据信息
	private int pageNow; // 当前页
	private long totalPage; // 总页数
	private long totalCount;// 总记录数

	/**
	 * 自定义设置返回数据信息
	 * 
	 * @MethodName: setJsonData
	 * @param status
	 * @param success
	 * @param msg
	 * @param dataList
	 * @param totalPage
	 * @param totalCount
	 */
	@SuppressWarnings("rawtypes")
	public void setJsonData(Integer status, boolean success, String msg, List dataList, int pageNow, long totalPage, long totalCount) {
		this.status = status;
		this.success = success;
		this.msg = msg;
		this.dataList = dataList;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
	}

	/**
	 * 返回成功数据信息，不添加msg说明
	 * 
	 * @MethodName: JsonDataSuccess
	 * @param dataList
	 * @param totalPage
	 * @param totalCount
	 */
	@SuppressWarnings("rawtypes")
	public void jsonDataSuccess(List dataList, int pageNow, long totalPage, long totalCount) {
		this.status = 200;
		this.success = true;
		this.msg = "";
		this.dataList = dataList;
		this.pageNow = pageNow;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
	}

	/**
	 * 返回失败数据信息，不添加msg说明
	 * 
	 * @MethodName: JsonDataSuccess
	 * @param dataList
	 * @param totalPage
	 * @param totalCount
	 */
	public void jsonDataFail() {
		this.status = 500;
		this.success = false;
		this.msg = "";
		this.dataList = null;
		this.pageNow = 0;
		this.totalPage = 0;
		this.totalCount = 0;
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

	@SuppressWarnings("rawtypes")
	public List getDataList() {
		return dataList;
	}

	@SuppressWarnings("rawtypes")
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
}
