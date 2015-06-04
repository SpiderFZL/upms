package cn.com.zhyu.upm.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: LinuxPackComm
 * @author tangwe
 * @date 2014年12月31日 下午12:02:34
 * @Description: TODO(Linux执行命令)
 * @version V1.0
 */
public class LinuxPackComm {
	public static final String RESULT_FILEPATH = "/usr/local/apache-tomcat-7.0.42/webapps/upm/upload/";// 命令执行结果文件路径
	public static final String RESULT_FILE_IMG = "update.img";// 命令执行结果文件路径
	public static final String ANT_SQLXML_PATH = "/usr/local/apache-ant/sql.xml";// 更新sqlXML文件
	public static final String SQLREGEX_BEGIN = "@b_sql";
	public static final String SQLREGEX_END = "@e_sql";
	public static final int PACKING = 1;// 正在执行打包
	public static final int NONPACK = 0;// 未执行打包

	public static final String CONFIG_FILEPATH = "/usr/local/apache-ant/Version.config";// 项目版本号
	public static final String CONFIG_SOFTVERSION = "softversion";// 项目版本号
	public static final String CONFIG_DBVERSION = "dbversion";// 数据库版本号

	// TSG SVN信息
	private static final String SVN_URL_TSGWEB = "https://svn.zhyu.com.cn/svn/TSG/TSG6.0/TSG6.0_server/SOURCE/TrustMore/";
	private static final String SVN_USERNAME_TSGWEB = "build";
	public static final String SVN_PWD_TSGWEB = "wantong@zhyu1009";
	// RTS SVN信息
	private static final String SVN_URL_RTSWEB = "https://svn.zhyu.com.cn/svn/RTS/RTS3.0/MAIN/SOURCE/rts_train/";
	private static final String SVN_USERNAME_RTSWEB = "tangwei";
	private static final String SVN_PWD_RTSWEB = "tw1027@zywt";

	public static final String LINUX_RMFILE = "rm -rf /usr/local/antTmp";

	/**
	 * linux ant打包命令
	 * 
	 * @param proName
	 * @param packageType
	 * @return
	 */
	public static String linuxAntCmd(String proName, Integer packageType) {
		String antCmd = "ant -buildfile /usr/local/apache-ant/build_" + proName.toLowerCase() + ".xml";
		return antCmd;
	}

	/**
	 * SVN连接信息
	 * 
	 * @param proName
	 * @param packageType
	 * @return
	 */
	public static Map<String, String> getSVNConnetInfo(String proName, Integer packageType) {
		Map<String, String> SVNMap = new HashMap<String, String>();
		if ("RTS2".equals(proName)) {
			if (packageType == 1) {
				SVNMap.put("svnURL", SVN_URL_RTSWEB);
				SVNMap.put("svnName", SVN_USERNAME_RTSWEB);
				SVNMap.put("svnPwd", SVN_PWD_RTSWEB);
			}
		} else if ("TSG6".equals(proName)) {
			if (packageType == 1) {
				SVNMap.put("svnURL", SVN_URL_TSGWEB);
				SVNMap.put("svnName", SVN_USERNAME_TSGWEB);
				SVNMap.put("svnPwd", SVN_PWD_TSGWEB);
			}
		}
		return SVNMap;
	}
}
