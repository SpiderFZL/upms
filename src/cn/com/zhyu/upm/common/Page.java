package cn.com.zhyu.upm.common;

import java.util.List;

/**
 * 分页封装类
 * 
 * @ClassName: Page
 * @author tangwe
 * @date 2014年11月12日 上午10:33:50
 * @Description: TODO(分页类封装)
 * @version V1.0
 */
public class Page {
	public final static int DEFAULT_PAGESIZE = 10;// 默认每页显示记录数量
	private int pageNow;// 当前页
	private int pageSize;// 每页显示数量
	private List<?> data;// 数据集合
	private long totalCount;// 总记录数

	public Page() {
		super();
	}

	/**
	 * Page默认pageSize为10 的构造函数
	 * 
	 * @param pageNow
	 * @param data
	 * @param totalCount
	 */
	@SuppressWarnings("rawtypes")
	public Page(int pageNow, List data, long totalCount) {
		super();
		this.pageNow = pageNow;
		this.pageSize = DEFAULT_PAGESIZE;
		this.data = data;
		this.totalCount = totalCount;
	}

	/**
	 * Page构造函数
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @param data
	 * @param totalCount
	 */
	@SuppressWarnings("rawtypes")
	public Page(int pageNow, int pageSize, List data, long totalCount) {
		super();
		this.pageNow = pageNow;
		this.pageSize = pageSize;
		this.data = data;
		this.totalCount = totalCount;
	}

	/**
	 * 获取总页数
	 * 
	 * @MethodName: getTotalPage
	 * @return long 总页数
	 */
	public long getTotalPage() {
		if (totalCount % pageSize == 0) {
			return totalCount / pageSize;
		} else {
			return totalCount / pageSize + 1;
		}
	}

	/**
	 * 是否有上一页
	 * 
	 * @MethodName: ifHasPrePage
	 * @return
	 */
	public boolean ifHasPrePage() {
		return pageNow > 1;
	}

	/**
	 * 是否有上一页
	 * 
	 * @MethodName: ifHasNextPage
	 * @return
	 */
	public boolean ifHasNextPage() {
		return pageNow < this.getTotalPage();
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageNow() {
		return pageNow;
	}

	public List<?> getData() {
		return data;
	}

	public long getTotalCount() {
		return totalCount;
	}
}
