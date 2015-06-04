package cn.com.zhyu.upm.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import cn.com.zhyu.upm.common.CommUtil;
import cn.com.zhyu.upm.common.JsonDataResponse;
import cn.com.zhyu.upm.common.JsonMsgResponse;
import cn.com.zhyu.upm.common.JsonObjResponse;
import cn.com.zhyu.upm.common.LinuxPackComm;
import cn.com.zhyu.upm.common.Page;
import cn.com.zhyu.upm.dto.PackageDTO;
import cn.com.zhyu.upm.pojo.OpLog;
import cn.com.zhyu.upm.pojo.Package;
import cn.com.zhyu.upm.pojo.PackageType;
import cn.com.zhyu.upm.pojo.Project;
import cn.com.zhyu.upm.pojo.TarTask;
import cn.com.zhyu.upm.pojo.User;
import cn.com.zhyu.upm.service.OpLogService;
import cn.com.zhyu.upm.service.PackageService;
import cn.com.zhyu.upm.service.PackageTypeService;
import cn.com.zhyu.upm.service.ProjectService;
import cn.com.zhyu.upm.service.TarTaskService;

/**
 * 升级包路由层（控制层）
 * 
 * @ClassName: PackageController
 * @author tangwe
 * @date 2014年11月13日 上午9:52:49
 * @Description: TODO(升级包模块url拦截，分发处理)
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/PackageController")
public class PackageController extends BaseController {
	private static final int MAX_SIZE = 1024 * 1024; // 上传最大文件大小
	private static final String ENCODING = "UTF-8"; // 上传编码
	private static final int BUFFER_SIZE = 2 * 1024; // 缓存大小
	private static final String RELATIVEPATH_UP = "upload/"; // 上传文件路径
	private String downLoadLinkName = ""; // 下载文件名称
	private String packageName = "";// 文件名称
	@Autowired
	private PackageService packageService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private PackageTypeService packageTypeService;
	@Autowired
	private TarTaskService tarTaskService;
	@Autowired
	private OpLogService opLogService;

	/**
	 * 加载升级包信息
	 * 
	 * @param pageNow
	 *            当前页
	 * @param pageSize
	 *            每页大小
	 * @param projectID
	 *            查询参数 项目ID
	 * @param tag
	 *            查询参数版本
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/loadUpgradePackages.do", method = RequestMethod.GET)
	@ResponseBody
	public JsonDataResponse loadUpgradePackages(String pageNow, String pageSize, String projectID, String tag, String pType, HttpServletRequest request) {
		JsonDataResponse dataResponse = new JsonDataResponse();
		User user = this.getSessionUser(request);
		if (user == null || user.getId() == null) {
			dataResponse.setJsonData(403, false, "未登录用户！", null, 0, 0, 0);
			return dataResponse;
		}
		int pageN = (!StringUtils.isEmpty(pageNow) && StringUtils.isNumeric(pageNow)) ? Integer.parseInt(pageNow) : 1;// 当前页
		int pageS = (!StringUtils.isEmpty(pageSize) && StringUtils.isNumeric(pageSize)) ? Integer.parseInt(pageSize) : Page.DEFAULT_PAGESIZE; // 每页大小默认10
		Map<String, String> params = this.setParams(projectID, tag, pType, user.getAuth());// 设置查询参数
		Page page = packageService.getPagedPackageDTO(pageN, pageS, params);// 获取page
		List<PackageDTO> dataList = (List<PackageDTO>) page.getData();// 数据
		long totalPage = page.getTotalPage();// 总页数
		long totalCount = page.getTotalCount();// 总记录数
		dataResponse.jsonDataSuccess(dataList, pageN, totalPage, totalCount);
		return dataResponse;
	}

	/**
	 * 加载指定ID的升级包信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/loadUpgradePackageByID.do", method = RequestMethod.GET)
	@ResponseBody
	public JsonObjResponse loadUpgradePackageByID(String id) {
		JsonObjResponse objResponse = new JsonObjResponse();
		if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
			objResponse.setJsonObj(403, false, "提交ID不能为空并且必须是数字！", null);
			return objResponse;
		}
		PackageDTO packageDTO = packageService.getPackageDTOByID(Integer.parseInt(id));
		if (null != packageDTO.getId()) {
			objResponse.jsonObjSuccess(packageDTO);
		} else {
			objResponse.jsonObjFail();
		}
		return objResponse;
	}

	/**
	 * 删除升级包
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delUpradePackage.do", method = RequestMethod.GET)
	@ResponseBody
	public JsonMsgResponse delUpradePackage(String id, HttpServletRequest request) {
		JsonMsgResponse msgResponse = new JsonMsgResponse();
		User user = this.getSessionUser(request);
		if (user == null || user.getId() == null) {
			msgResponse.setJsonMsg(403, false, "未登陆用户!");
			return msgResponse;
		}
		if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
			msgResponse.setJsonMsg(403, false, "提交ID不能为空并且必须是数字!");
			return msgResponse;
		}
		boolean isAuthed = this.checkAuth(Integer.parseInt(id), user.getAuth());// 判断用户权限
		if (!isAuthed) {
			msgResponse.setJsonMsg(403, false, "你没有对该升级包操作的权限!");
			return msgResponse;
		}
		Package pack = packageService.getPackageByID(Integer.parseInt(id));// 获取删除的发布包信息
		if (pack == null) {
			msgResponse.setJsonMsg(403, false, "你想要删除的发布包不存在!");
			return msgResponse;
		}
		boolean isDataDeleted = packageService.delPackage(Integer.parseInt(id));// 数据删除
		if (isDataDeleted) {
			this.delUpradePackageFile(this.toDelFileName(pack.getDownloadLink()), request);// 删除文件
			opLogService.writeLog(new OpLog(user.getUserName(), "删除了" + pack.getPackageName() + "升级包", new Date()));
			msgResponse.jsonMsgSuccess();
		} else {
			msgResponse.jsonMsgFail();
		}
		return msgResponse;

	}

	/**
	 * 获取用户权限内的项目
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/loadAuthedPro.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Project> loadAuthedPro(HttpServletRequest request) {
		User user = this.getSessionUser(request);
		List<Project> proList = new ArrayList<Project>();
		if (user != null) {
			proList = projectService.getAuthedPro(user.getAuth());
		}
		return proList;
	}

	/**
	 * 获取所有的项目
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/loadAllPro.do")
	@ResponseBody
	public List<Project> loadAllPro() {
		List<Project> proList = new ArrayList<Project>();
		proList = projectService.getAllPro();
		return proList;
	}

	/**
	 * 获取所关项目的升级包类型
	 * 
	 * @param projectID
	 * @return
	 */
	@RequestMapping(value = "/loadProedPackageType.do", method = RequestMethod.GET)
	@ResponseBody
	public List<PackageType> loadProedPackageType(String projectID) {
		List<PackageType> ptList = new ArrayList<PackageType>();
		ptList = packageTypeService.getPackageTypeByProID(Integer.valueOf(projectID));
		return ptList;
	}

	/**
	 * 获取所有的升级包类型
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/loadAllPackages.do", method = RequestMethod.GET)
	@ResponseBody
	public List<PackageType> loadAllPackages() {
		List<PackageType> ptList = new ArrayList<PackageType>();
		ptList = packageTypeService.getPackageTypes();
		return ptList;
	}

	/**
	 * 添加升级包
	 * 
	 * @param pa
	 *            from封装表单信息
	 * @return
	 */
	@RequestMapping(value = "/addUpradePackage.do", method = RequestMethod.POST)
	@ResponseBody
	public JsonMsgResponse addUpradePackage(Package pa, HttpServletRequest request) {
		JsonMsgResponse msgResponse = new JsonMsgResponse();
		User user = this.getSessionUser(request);
		if (user == null || user.getId() == null) {
			msgResponse.setJsonMsg(403, false, "未登陆用户!");
			return msgResponse;
		}
		pa.setPackageName(packageName);
		pa.setCreateTime(new Date()); // 设置上传时间
		pa.setDownloadLink(RELATIVEPATH_UP + downLoadLinkName); // 设置下载路径
		pa.setUserID(user.getId()); // 设置用户
		pa.setPackgeSize(pa.getPackgeSize().toUpperCase());// 设置文件大小
		Package pak = packageService.addPackage(pa);
		if (pak != null) {
			opLogService.writeLog(new OpLog(user.getUserName(), "新增了" + pa.getPackageName() + "升级包", new Date()));
			msgResponse.jsonMsgSuccess();
		} else {
			msgResponse.jsonMsgFail();
		}
		return msgResponse;
	}

	/**
	 * 修改升级包
	 * 
	 * @param newPa
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateUpradePackage.do", method = RequestMethod.POST)
	@ResponseBody
	public JsonMsgResponse updateUpradePackage(Package newPa, String id, boolean ifWithFile, HttpServletRequest request) {
		JsonMsgResponse msgResponse = new JsonMsgResponse();
		User user = this.getSessionUser(request);
		if (user == null || user.getId() == null) {
			msgResponse.setJsonMsg(403, false, "未登陆用户!");
			return msgResponse;
		}
		if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
			msgResponse.setJsonMsg(403, false, "提交ID不能为空并且必须是数字!");
			return msgResponse;
		}
		boolean isAuthed = this.checkAuth(Integer.parseInt(id), user.getAuth());// 判断用户权限
		if (!isAuthed) {
			msgResponse.setJsonMsg(403, false, "你没有对该升级包操作的权限!或该升级包不存在！");
			return msgResponse;
		}
		Package pa = packageService.getPackageByID(Integer.valueOf(id));// 判断操作数据是否存在
		if (null == pa || null == pa.getId()) {
			msgResponse.setJsonMsg(403, false, "修改数据不存在!");
			return msgResponse;
		}
		newPa.setUpdateTime(new Date()); // 设置上传时间
		// 修改附带修改文件
		if (ifWithFile) {
			newPa.setPackageName(packageName);// 设置文件
			newPa.setDownloadLink(RELATIVEPATH_UP + downLoadLinkName); // 设置下载路径
			this.delUpradePackageFile(this.toDelFileName(pa.getDownloadLink()), request);// 删除原有文件
		}
		Package pak = packageService.updatePackage(newPa, ifWithFile, Integer.valueOf(id));
		if (pak != null) {
			opLogService.writeLog(new OpLog(user.getUserName(), "修改了" + pa.getPackageName() + "升级包", new Date()));
			msgResponse.jsonMsgSuccess();
		} else {
			msgResponse.jsonMsgFail();
		}
		return msgResponse;
	}

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadUpgradePackage.do")
	@ResponseBody
	public JsonMsgResponse uploadUpgradePackage(HttpServletRequest request) {
		JsonMsgResponse msgResponse = new JsonMsgResponse();
		try {
			this.upload(request);// 上传文件
			msgResponse.jsonMsgSuccess();
		} catch (FileUploadException | IOException e) {
			e.printStackTrace();
			msgResponse.jsonMsgFail();
		}
		return msgResponse;
	}

	/**
	 * 修改升级包状态 0close 1open
	 * 
	 * @param status
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updatePackageStatus.do", method = RequestMethod.POST)
	@ResponseBody
	public JsonMsgResponse updatePackageStatus(String status, String id, HttpServletRequest request) {
		JsonMsgResponse msgResponse = new JsonMsgResponse();
		User user = this.getSessionUser(request);
		if (user == null || user.getId() == null) {
			msgResponse.setJsonMsg(403, false, "未登陆用户!");
			return msgResponse;
		}
		if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
			msgResponse.setJsonMsg(403, false, "提交ID不能为空并且必须是数字!");
			return msgResponse;
		}
		if (StringUtils.isEmpty(status) || !StringUtils.isNumeric(status)) {
			msgResponse.setJsonMsg(403, false, "提交状态值错误！");
			return msgResponse;
		}
		boolean ifStutasChanged = this.packageService.updatePackageStatus(Integer.valueOf(status), new Date(), Integer.valueOf(id));
		if (ifStutasChanged) {
			opLogService.writeLog(new OpLog(user.getUserName(), "修改了升级包状态。", new Date()));
			msgResponse.jsonMsgSuccess();
		} else {
			msgResponse.jsonMsgFail();
		}
		return msgResponse;
	}

	/**
	 * 测试通过
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/testPass.do", method = RequestMethod.GET)
	@ResponseBody
	public JsonMsgResponse testPass(String id, HttpServletRequest request) {
		JsonMsgResponse msgResponse = new JsonMsgResponse();
		User user = this.getSessionUser(request);
		if (user == null || user.getId() == null || user.getRole() != 3) {
			msgResponse.setJsonMsg(403, false, "你不是测试人员，无权执行此功能！");
			return msgResponse;
		}
		if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
			msgResponse.setJsonMsg(403, false, "提交ID不能为空并且必须是数字!");
			return msgResponse;
		}
		boolean ifPassed = packageService.testPassPackage(Integer.valueOf(id));
		if (ifPassed) {
			opLogService.writeLog(new OpLog(user.getUserName(), "测试通过id为" + id + "测试包", new Date()));
			msgResponse.jsonMsgSuccess();
		} else {
			msgResponse.jsonMsgFail();
		}
		return msgResponse;
	}

	/**
	 * 废弃包
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/discard.do", method = RequestMethod.GET)
	@ResponseBody
	public JsonMsgResponse discardPackage(String id, HttpServletRequest request) {
		JsonMsgResponse msgResponse = new JsonMsgResponse();
		User user = this.getSessionUser(request);
		if (user == null || user.getId() == null || user.getRole() != 3) {
			msgResponse.setJsonMsg(403, false, "你不是测试人员，无权执行此功能！");
			return msgResponse;
		}
		if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
			msgResponse.setJsonMsg(403, false, "提交ID不能为空并且必须是数字!");
			return msgResponse;
		}
		boolean ifDiscard = packageService.discardPackage(Integer.valueOf(id));
		if (ifDiscard) {
			opLogService.writeLog(new OpLog(user.getUserName(), "废弃id为" + id + "测试包", new Date()));
			msgResponse.jsonMsgSuccess();
		} else {
			msgResponse.jsonMsgFail();
		}
		return msgResponse;
	}

	/**
	 * 获取svn版本号
	 * 
	 * @param proID
	 * @return
	 */
	@RequestMapping(value = "/loadVersion.do", method = RequestMethod.POST)
	@ResponseBody
	public String[] loadVersion(String proID, String packageType) {
		String svnVersion = packageService.getLastVersion(Integer.valueOf(proID), Integer.valueOf(packageType));
		String[] svnVersionArray = { svnVersion };
		return svnVersionArray;
	}

	/**
	 * 一键打包
	 * 
	 * @param projectID
	 * @param packageType
	 * @param startRevision
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/oneKeyPack.do", method = RequestMethod.POST)
	@ResponseBody
	public JsonMsgResponse oneKeyPack(String projectID, String packageType, String startRevision, HttpServletRequest request) {
		JsonMsgResponse msgResponse = new JsonMsgResponse();
		if (StringUtils.isEmpty(projectID) || !StringUtils.isNumeric(projectID)) {
			msgResponse.setJsonMsg(403, false, "提交ID不能为空并且必须是数字!");
			return msgResponse;
		}
		if (StringUtils.isEmpty(packageType) || !StringUtils.isNumeric(packageType)) {
			msgResponse.setJsonMsg(403, false, "项目类型ID不能为空并且必须是数字!");
			return msgResponse;
		}
		if (StringUtils.isEmpty(startRevision) || !StringUtils.isNumeric(startRevision)) {
			msgResponse.setJsonMsg(403, false, "SVN版本号不能为空并且必须是数字!");
			return msgResponse;
		}
		User user = this.getSessionUser(request);
		if (user == null || user.getId() == null) {
			msgResponse.setJsonMsg(403, false, "未登陆用户!");
			return msgResponse;
		}
		Project pro = projectService.findProByID(Integer.valueOf(projectID));
		// 判断项目是否存在
		if (pro == null || pro.getId() == null) {
			msgResponse.setJsonMsg(403, false, "不存在项目!");
			return msgResponse;
		}
		TarTask task = tarTaskService.findTaskByProIDAndType(Integer.valueOf(projectID), Integer.valueOf(packageType));// 获取打包任务
		if (task == null || task.getId() == null) {
			msgResponse.setJsonMsg(403, false, "不存在打包任务!");
			return msgResponse;
		}
		int taskStatus = task.getStatus();// 获取打包状态
		if (taskStatus == 1) {
			msgResponse.setJsonMsg(403, false, "正在执行打包...");
			return msgResponse;
		}
		opLogService.writeLog(new OpLog(user.getUserName(), "执行了一键打包功能", new Date()));
		String dstParentPath = request.getServletContext().getRealPath("../") + "/" + RELATIVEPATH_UP;// 目标文件父路径
		System.out.println(dstParentPath + "====");
		// 启动线程
		new Thread(new PackRun(task.getId(), Integer.valueOf(projectID), pro.getProjectName(), dstParentPath, Long.valueOf(startRevision),
				Integer.valueOf(packageType), user.getId(), tarTaskService)).start();
		msgResponse.jsonMsgSuccess();
		return msgResponse;
	}

	/**
	 * 打包线程
	 * 
	 * @ClassName: PackRun
	 * @author tangwe
	 * @date 2015年1月5日 下午2:55:33
	 * @Description: TODO(用于打包的线程)
	 * @version V1.0
	 */
	class PackRun implements Runnable {
		private Integer taskID; // 任务ID
		private Integer projectID;// 项目ID
		private String proName;// 项目名称
		private String dstParentPath;// 服务器父级路径
		private long startRevision;// 起始版本号
		private Integer packageType;// 类型
		private Integer userID;// 用户ID
		private TarTaskService tarTaskService;

		public PackRun(Integer taskID, Integer projectID, String proName, String dstParentPath, long startRevision, Integer packageType, Integer userID,
				TarTaskService tarTaskService) {
			super();
			this.taskID = taskID;
			this.projectID = projectID;
			this.proName = proName;
			this.dstParentPath = dstParentPath;
			this.startRevision = startRevision;
			this.packageType = packageType;
			this.userID = userID;
			this.tarTaskService = tarTaskService;
		}

		@Override
		public void run() {
			try {
				tarTaskService.updateTaskStatusByID(taskID, LinuxPackComm.PACKING);// 标识正在执行
				System.out.println("打包程序开始...");
				Map<String, String> svn = getSVNCommitInfo(proName, packageType, Long.valueOf(startRevision));// 获取svn提交备注信息
				String nowTime = CommUtil.formatDateYYYYMMDDHHmm(new Date());// 当前时间
				String buildTime = CommUtil.formatDateToVersionString(new Date());// 服务端版本创建时间
				writeAntSQLXml(LinuxPackComm.ANT_SQLXML_PATH, svn.get("svnCommitInfo"), nowTime);// 更新sql.xml
				String proVersion = getAutoProVersion(projectID, packageType);// 项目版本号
				writeAntVersionConfig(LinuxPackComm.CONFIG_FILEPATH, svn.get("svnCommitInfo"), "V" + proVersion + " " + buildTime, "Build_" + nowTime);// 更新Version.config
				Map<String, String> fileInfoMap = cmdExcTar(proName, packageType, dstParentPath);// 打包上传
				if (fileInfoMap != null) {
					writeJIRA();// 写入到JIRA中，暂时没有操作
					Package pa = new Package();
					pa.setCreateTime(new Date());// 创建时间
					pa.setDownloadLink(RELATIVEPATH_UP + fileInfoMap.get("downloadFileName"));// 下载地址
					pa.setPackageName(fileInfoMap.get("srcFileName"));// 文件名
					pa.setPackageType(Integer.valueOf(packageType));// 文件类型
					pa.setPackgeSize(fileInfoMap.get("fileSize").toUpperCase());// 文件大小
					pa.setProjectID(Integer.valueOf(projectID));// 项目ID
					pa.setRemark(svn.get("svnCommitInfo"));// 备注信息，svn提交信息
					pa.setTag("beta");// 版本
					pa.setUserID(userID);// 用户
					pa.setVersion(proVersion + "-[" + svn.get("latestRevision") + "] " + CommUtil.formatDateToVersionString(new Date()));// 版本号
					Long keyId = packageService.addPackageGetKey(pa);// 存入数据库
					writeTarLog(keyId, fileInfoMap.get("tarLog"));// 打包信息存入数据库
					System.out.println("打包成功...");
				} else {
					System.out.println("脚本执行异常...");
				}

			} catch (Exception e) {
				System.out.println("打包异常...");
				tarTaskService.updateTaskStatusByID(taskID, LinuxPackComm.NONPACK);// 状态重置
				tarTaskService.updateTaskRemark(taskID, "");// 写入备注
				e.printStackTrace();
			} finally {
				tarTaskService.updateTaskStatusByID(taskID, LinuxPackComm.NONPACK);// 状态重置
			}
		}
	}

	/**
	 * 设置查询map参数值
	 * 
	 * @param projectID
	 * @param tag
	 * @param authPro
	 * @return
	 */
	private Map<String, String> setParams(String projectID, String tag, String pType, String authPro) {
		Map<String, String> params = new HashMap<String, String>();
		if (!StringUtils.isEmpty(projectID)) {// 项目ID不为空添加param参数
			params.put("PROJECTID", projectID.trim());
		} else {
			params.put("PROJECTID", authPro);
		}
		if (!StringUtils.isEmpty(tag)) {// 版本不为空添加param参数
			params.put("TAG", tag.trim());
		} else {
			params.put("TAG", "");
		}
		if (!StringUtils.isEmpty(pType)) {
			params.put("PACKAGETYPE", pType.trim());
		}
		return params;
	}

	/**
	 * 用户权限检查
	 * 
	 * @param packageID
	 * @param auth
	 * @return
	 */
	private boolean checkAuth(Integer packageID, String auth) {
		Package pa = packageService.getPackageByID(packageID);
		if (pa == null || pa.getId() == null) {
			return false;
		}
		// 判断是否有权限
		if (auth.indexOf(String.valueOf(pa.getProjectID())) > -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 处理pluupload上传文件
	 * 
	 * @MethodName: uploadZIP
	 * @param request
	 * @return
	 * @throws FileUploadException
	 * @throws IOException
	 */
	private boolean upload(HttpServletRequest request) throws FileUploadException, IOException {
		boolean flag = false;
		String fileName = "";
		Integer chunks = 0, chunk = 0;
		String dstPath = request.getServletContext().getRealPath("../") + "/" + RELATIVEPATH_UP;
		System.out.println(dstPath + "-------------");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			ServletFileUpload upload = new ServletFileUpload();
			FileItemIterator iter = upload.getItemIterator(request);
			upload.setSizeMax(MAX_SIZE); // 最大上传1G
			upload.setHeaderEncoding(ENCODING);
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				String name = item.getFieldName();
				InputStream input = item.openStream();
				if ("name".equals(name)) {
					fileName = Streams.asString(input);// 文件名
				}
				if ("chunk".equals(name)) {
					chunk = Integer.valueOf(Streams.asString(input));
				}
				if ("chunks".equals(name)) {
					chunks = Integer.valueOf(Streams.asString(input));
				}
				// 处理上传文件内容
				if (!item.isFormField()) {
					packageName = fileName;// 设置文件名称
					fileName = CommUtil.splitUUIDFileName(fileName);
					downLoadLinkName = fileName;// 下载文件名称设置
					File dstFile = new File(dstPath, fileName);
					// 目标文件不存在创建文件
					if (!dstFile.exists()) {
						dstFile.mkdirs();
					}
					// 文件已存在删除旧文件（上传了同名的文件）
					if (chunk == 0 && dstFile.exists()) {
						dstFile.delete();
						dstFile = new File(dstPath, fileName);
					}
					this.appendFile(input, dstFile);// 合成文件
					if (chunk == chunks - 1) {
						flag = true;
					} else {
						flag = false;
					}
				} else {
					flag = false;
				}
			}
		}
		return flag;
	}

	/**
	 * 合并文件
	 * 
	 * @param in
	 * @param dst
	 */
	private void appendFile(InputStream in, File dst) {
		OutputStream out = null;
		try {
			// plupload 配置了chunk的时候新上传的文件append到文件末尾
			if (dst.exists()) {
				out = new BufferedOutputStream(new FileOutputStream(dst, true), BUFFER_SIZE);
			} else {
				out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
			}
			in = new BufferedInputStream(in, BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 获取要删除文件的文件名称
	 * 
	 * @param downloadLink
	 * @return
	 */
	private String toDelFileName(String downloadLink) {
		return downloadLink.substring(downloadLink.indexOf("/") + 1, downloadLink.length());
	}

	/**
	 * 删除发布包文件
	 * 
	 * @param fileName
	 * @param request
	 */
	private void delUpradePackageFile(String fileName, HttpServletRequest request) {
		String dstPath = request.getServletContext().getRealPath("../") + "/" + RELATIVEPATH_UP;
		File dstFile = new File(dstPath, fileName);
		dstFile.delete();// 文件删除
	}

	/**
	 * 命令打包
	 * 
	 * @param proName
	 * @param packageType
	 * @param dstParentPath
	 * @return
	 * @throws IOException
	 */
	private Map<String, String> cmdExcTar(String proName, Integer packageType, String dstParentPath) throws IOException {
		BufferedReader br = null;
		String result = "";
		Map<String, String> map = null;
		System.out.println("运行ant脚本...");
		String cmd = LinuxPackComm.linuxAntCmd(proName, packageType); // linux命令
		try {
			Process exec = Runtime.getRuntime().exec(cmd);// 执行命令
			br = new BufferedReader(new InputStreamReader(exec.getInputStream(), "UTF-8"));
			StringBuffer sb = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");// 拼接打包信息
			}
			result = sb.toString();// 命令执行返回信息
			String dstPath = dstParentPath + LinuxPackComm.RESULT_FILE_IMG;// 处理得到目标文件路径
			File dst = new File(dstPath);// 目标文件
			if (!dst.exists()) {
				dst.mkdirs();
			}
			if (result.indexOf("OK", -1) != -1) {
				exec = Runtime.getRuntime().exec(LinuxPackComm.LINUX_RMFILE);// 移除文件
				map = new HashMap<String, String>();
				map.put("fileSize", CommUtil.convertFileSize(dst.length()));// 文件大小
				String dstUUIDFileName = CommUtil.splitUUIDFileName(LinuxPackComm.RESULT_FILE_IMG); // 目标文件名称
				dst.renameTo(new File(dstParentPath + dstUUIDFileName));// 文件重命名
				map.put("srcFileName", LinuxPackComm.RESULT_FILE_IMG); // 源文件名
				map.put("downloadFileName", dstUUIDFileName); // 目标文件名
				map.put("tarLog", result);// 打包日志
				System.out.println("ant脚本执行完成...");
				System.out.println("-----------------");
				return map;
			} else {
				System.out.println("ant脚本未能执行,脚本存在异常...");
				System.out.println("-----------------");
				return null;
			}
		} catch (IOException e) {
			System.out.println("运行ant脚本粗错...");
			e.printStackTrace();
			System.out.println("-----------------");
			throw e;// 抛出异常
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 获取svn提交记录(备注，最后版本等)
	 * 
	 * @param proName
	 * @param packageType
	 * @param startRevision
	 * @return
	 * @throws SVNException
	 */
	private Map<String, String> getSVNCommitInfo(String proName, Integer packageType, long startRevision) throws SVNException {
		String svnURL = "";
		String svnName = "";
		String svnPwd = "";
		Map<String, String> SVNMap = LinuxPackComm.getSVNConnetInfo(proName, packageType);// 获取匹配svn信息
		svnURL = SVNMap.get("svnURL");// svn地址
		svnName = SVNMap.get("svnName");// 用户名
		svnPwd = SVNMap.get("svnPwd");// 密码
		System.out.println("获取SVN基本信息...");
		try {
			System.out.println("开始获取SVN提交记录...");
			DAVRepositoryFactory.setup();// 支持http:// 和 https://
			SVNRepositoryFactoryImpl.setup();// 支持svn://和svn+ssh://
			FSRepositoryFactory.setup();// 支持file:///
			SVNRepository repository = null;
			repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(svnURL));
			ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(svnName, svnPwd);
			repository.setAuthenticationManager(authManager);// 认证
			SVNNodeKind nodeKind = repository.checkPath("", -1);
			if (nodeKind == SVNNodeKind.NONE) {
				System.err.println("There is no entry at '" + svnURL + "'.");
			} else if (nodeKind == SVNNodeKind.FILE) {
				System.err.println("The entry at '" + svnURL + "' is a file while a directory was expected.");
			}
			List<SVNLogEntry> entries = new ArrayList<SVNLogEntry>();
			repository.log(new String[] { "" }, entries, startRevision, -1, true, true);
			long latestRevision = repository.getLatestRevision(); // 最新版本
			StringBuffer sb = new StringBuffer("最新版本：" + latestRevision + "\n");
			sb.append("---------------------------" + "\n");
			for (SVNLogEntry entry : entries) {
				sb.append(entry.getAuthor() + "[" + CommUtil.formatDateToString(entry.getDate()) + "]" + "\n");
				sb.append(entry.getMessage() + "\n");
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("svnCommitInfo", sb.toString());
			map.put("latestRevision", "" + latestRevision);
			System.out.println("获取SVN提交记录成功,最新版本: " + latestRevision + "...");
			System.out.println("-----------------");
			return map;
		} catch (SVNException e) {
			System.out.println("获取SVN提交记录失败...");
			e.printStackTrace();
			System.out.println("-----------------");
			throw e;// 抛出异常
		}

	}

	/**
	 * 获取自动生成版本号
	 * 
	 * @MethodName: getAutoVersion
	 * @return
	 */
	private String getAutoProVersion(Integer proID, Integer packageType) {
		String lastVersion = packageService.getLastVersion(proID, packageType);
		if ("".equals(lastVersion) || lastVersion.indexOf("-") == -1) {
			return "0.0.0.0";
		}
		String proVersion = lastVersion.substring(0, lastVersion.indexOf("-"));// 1.0.0.1
		return CommUtil.versionOverlap(proVersion, 4);// 1.0.0.2
	}

	/**
	 * 数据添加到JIRA
	 * 
	 */
	private void writeJIRA() {
		System.out.println("写入数据到JIRA,暂时没有操作...");
	}

	/**
	 * 在sql.xml写入sql语句
	 * 
	 * @MethodName: writeAntSqlXML
	 * @param xmlPath
	 * @param svnInfo
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void writeAntSQLXml(String xmlPath, String svnCommitInfo, String dbVersion) throws DocumentException, IOException {
		System.out.println("循环截取sql语法块...");
		StringBuffer sqlBlockBuffer = new StringBuffer();
		String info = svnCommitInfo;// 保存原有提交记录
		svnCommitInfo.toLowerCase();// 有大写转换成为小写
		int sqlBeginOffset = 0;// sql开始位置
		int sqlEndOffset = 0;// sql结束位置
		while (sqlBeginOffset != -1) {
			sqlBeginOffset = svnCommitInfo.indexOf(LinuxPackComm.SQLREGEX_BEGIN, sqlEndOffset);// 获取标记sql开始位置
			sqlEndOffset = svnCommitInfo.indexOf(LinuxPackComm.SQLREGEX_END, sqlBeginOffset);// 获取标记sql结束位置
			if (sqlBeginOffset == -1 || sqlEndOffset == -1) {
				break;// 如果不存在退出循环
			}
			String sqlBlockFound = info.substring(sqlBeginOffset + LinuxPackComm.SQLREGEX_BEGIN.length(), sqlEndOffset);// 截取中间sql语句块
			sqlBlockBuffer.append(sqlBlockFound); // 保存sql语句块
		}
		String sqlBlock = sqlBlockBuffer.toString();
		// 没有sql标识直接返回
		if (StringUtils.isEmpty(sqlBlock)) {
			System.out.println("不存在sql语法块...");
			System.out.println("-----------------");
			return;
		}
		String sqls[] = sqlBlock.trim().split(";");// 分割sql语句
		System.out.println("将sql语法块写入XML...");
		SAXReader saxReader = new SAXReader();
		saxReader.setEncoding("UTF-8");
		XMLWriter writer = null;
		try {
			Document doc = saxReader.read(new File(xmlPath));
			Element root = doc.getRootElement();// 获取根节点root
			Element versionEle = root.addElement("version");// 添加版本节点
			versionEle.addAttribute("name", dbVersion);// 设置属性
			// 循环写入sqlCDATA
			for (int i = 0; i < sqls.length; i++) {
				Element sqlEle = versionEle.addElement("sql");
				String sql = sqls[i].trim() + ";";// 拼接sql
				sqlEle.setText("<![CDATA[" + sql + "]]>");
			}
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			writer = new XMLWriter(new FileWriter(xmlPath), format);
			writer.setEscapeText(false);
			writer.write(doc);// 写出xml文件
			System.out.println("sql语法块写入XML成功...");
			System.out.println("-----------------");
		} catch (DocumentException e) {
			System.out.println("sql语法块写入XML粗错了," + e.getMessage() + "...");
			e.printStackTrace();
			System.out.println("-----------------");
			throw e;
		} catch (IOException e) {
			System.out.println("sql语法块写入XML粗错了," + e.getMessage() + "...");
			e.printStackTrace();
			System.out.println("-----------------");
			throw e;
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 写入version配置文件
	 * 
	 * @param configPath
	 * @param softVersion
	 * @param dbVersion
	 * @throws IOException
	 */
	private void writeAntVersionConfig(String configPath, String svnCommitInfo, String softVersion, String dbVersion) throws IOException {
		System.out.println("将版本信息写入XML...");
		Properties pro = new Properties();
		InputStreamReader inputStreamReader = null;
		OutputStreamWriter outputStreamWriter = null;
		try {
			inputStreamReader = new InputStreamReader(new FileInputStream(new File(configPath)), "UTF-8");
			pro.load(inputStreamReader);// 读取配置文件
			if (pro.containsKey(LinuxPackComm.CONFIG_SOFTVERSION)) {
				pro.setProperty(LinuxPackComm.CONFIG_SOFTVERSION, softVersion);// 修改项目版本信息
			}
			int sqlBeginOffset = svnCommitInfo.indexOf(LinuxPackComm.SQLREGEX_BEGIN);// 获取标记sql开始位置
			int sqlEndOffset = svnCommitInfo.indexOf(LinuxPackComm.SQLREGEX_END);// 获取标记sql结束位置
			if (sqlBeginOffset != -1 && sqlEndOffset != -1 && pro.containsKey(LinuxPackComm.CONFIG_DBVERSION)) {
				pro.setProperty(LinuxPackComm.CONFIG_DBVERSION, dbVersion);// 修改数据库版本信息
			}
			outputStreamWriter = new OutputStreamWriter(new FileOutputStream(new File(configPath)), "UTF-8");
			pro.store(outputStreamWriter, "");
			System.out.println("将版本信息写入XML成功...");
		} catch (FileNotFoundException e) {
			System.out.println("版本信息写入XML粗错了...");
			e.printStackTrace();
			System.out.println("-----------------");
			throw e;
		} catch (IOException e) {
			System.out.println("版本信息写入XML粗错了...");
			e.printStackTrace();
			System.out.println("-----------------");
			throw e;
		} finally {
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStreamWriter != null) {
				try {
					outputStreamWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 打包日志存入数据库
	 * 
	 * @param keyId
	 * @param TsgLogDateTime
	 */
	private void writeTarLog(long keyId, String tarLog) {
		System.out.println("打包信息处理入库...");
		if (tarLog.indexOf("failed") != -1) {
			packageService.updateDownloadStatus(keyId, 0);// 设置该包不能下载
			packageService.updatePackageStatus(keyId, 0);// 设置包关闭
		}
		tarTaskService.addTarLog(keyId, tarLog);
		System.out.println("打包信息处理入库成功...");
		System.out.println("-----------------");

	}

	/**
	 * 获取svn版本号
	 * 
	 * @param proID
	 * @return
	 */
	@RequestMapping(value = "/loadTarLog.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> loadTarLog(String id) {
		Map<String, String> tarLogMap = new HashMap<String, String>();
		String tarLog = tarTaskService.findTarLogByPackageId(Integer.valueOf(id));
		tarLogMap.put("tarLog", tarLog);
		return tarLogMap;
	}
}
