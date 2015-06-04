package cn.com.zhyu.upm.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.zhyu.upm.common.JsonMsgResponse;
import cn.com.zhyu.upm.pojo.Download;
import cn.com.zhyu.upm.pojo.User;
import cn.com.zhyu.upm.service.DownloadService;

/**
 * @ClassName: DownloadController
 * @author tangwe
 * @date 2015年2月2日 下午1:36:52
 * @Description: TODO(下载控制器)
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/DownloadController")
public class DownloadController extends BaseController {
	@Autowired
	private DownloadService downloadService;

	@RequestMapping(value = "/addDownload.do", method = RequestMethod.POST)
	@ResponseBody
	public JsonMsgResponse addDownload(String packageID,
			HttpServletRequest request) {
		JsonMsgResponse msgResponse = new JsonMsgResponse();
		if (StringUtils.isEmpty(packageID)) {
			msgResponse.setJsonMsg(403, false, "提交的参数必须是数字！");
			return msgResponse;
		}
		User user = this.getSessionUser(request);
		if (user == null || user.getId() == null) {
			msgResponse.setJsonMsg(403, false, "未登陆用户!");
			return msgResponse;
		}
		Download d = new Download();
		d.setDowntime(new Date());
		d.setPackageID(Integer.parseInt(packageID));
		d.setUserName(user.getRealName());
		boolean isAdded = downloadService.addDownload(d);
		if (isAdded) {
			msgResponse.jsonMsgSuccess();
		} else {
			msgResponse.jsonMsgFail();
		}
		return msgResponse;
	}
}
