package cn.com.zhyu.upm.pojo;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @ClassName: BasePo
 * @author tangwe
 * @date 2014年11月12日 上午9:33:53
 * @Description: TODO(实现Serializable接口，以便JVM序列化PO实例)
 * @version V1.0
 */
public class BasePO implements Serializable {
	private static final long serialVersionUID = -6188842236420583317L;

	/**
	 * 统一toString方法
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
