package cn.com.zhyu.upm.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: CommUtil
 * @author tangwe
 * @date 2014年12月24日 下午4:01:19
 * @Description: TODO(通用工具类)
 * @version V1.0
 */
public class CommUtil {
	/**
	 * 格式化日期
	 * 
	 * @MethodName: formatDateToString
	 * @return
	 */
	public static String formatDateToString(Date d) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(d);
	}

	/**
	 * 格式化日期
	 * 
	 * @MethodName: formatDateToString
	 * @return
	 */
	public static String formatDateYYYYMMDDHHmm(Date d) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		return dateFormat.format(d);
	}

	/**
	 * 版本后缀
	 * 
	 * @param d
	 * @return
	 */
	public static String formatDateToVersionString(Date d) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String versionExt = "Build" + dateFormat.format(d);
		return versionExt;
	}

	/**
	 * 文件转换
	 * 
	 * @param size
	 * @return
	 */
	public static String convertFileSize(long size) {
		long kb = 1024;
		long mb = kb * 1024;
		long gb = mb * 1024;

		if (size >= gb) {
			return String.format("%.1f gb", (float) size / gb);
		} else if (size >= mb) {
			float f = (float) size / mb;
			return String.format(f > 100 ? "%.0f mb" : "%.1f mb", f);
		} else if (size >= kb) {
			float f = (float) size / kb;
			return String.format(f > 100 ? "%.0f kb" : "%.1f kb", f);
		} else {
			return String.format("%d b", size);
		}
	}

	/**
	 * 获取UUID文件名称
	 * 
	 * @param fileName
	 * @return
	 */
	public static String splitUUIDFileName(String fileName) {
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString();
		String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());// 获取后缀名.zip
		return uuidStr.substring(0, 18) + extension;// 截取18位

	}

	/**
	 * 项目版本叠加
	 * 
	 * @param version
	 *            0.0.0.0
	 * @param offset
	 * @return
	 */
	public static String versionOverlap(String version, int offset) {
		String[] vs = version.split("\\.");
		String reVersion = "";
		for (int i = 0; i < vs.length; i++) {
			if (i == (offset - 1)) {
				vs[i] = "" + (Integer.valueOf(vs[i]) + 1);// 叠加指定位置数字
			}
			reVersion = reVersion + vs[i] + ".";
		}
		return reVersion.substring(0, reVersion.length() - 1);
	}

	/**
	 * 去前后空格回车制表
	 * 
	 * @MethodName: replaceBlank
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	public static void main(String[] args) {
		String s = formatDateYYYYMMDDHHmm(new Date());
		System.out.println(s);
	}
}
