package cn.com.zhyu.upm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.zhyu.upm.common.JsonMsgResponse;
import cn.com.zhyu.upm.common.MailUtil;
import cn.com.zhyu.upm.dto.ChatDTO;
import cn.com.zhyu.upm.pojo.Chat;
import cn.com.zhyu.upm.pojo.OpLog;
import cn.com.zhyu.upm.pojo.User;
import cn.com.zhyu.upm.service.ChatService;
import cn.com.zhyu.upm.service.OpLogService;
import cn.com.zhyu.upm.service.UserService;

/**
 * @ClassName: ChatController
 * @author tangwe
 * @date 2014年11月24日 下午1:33:20
 * @Description: TODO(评论控制层,URL拦截，分发)
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/ChatController")
public class ChatController extends BaseController {
	@Autowired
	private ChatService chatService;
	@Autowired
	private UserService userService;
	@Autowired
	private OpLogService opLogService;

	/**
	 * 添加评论
	 * 
	 * @param chat
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addChat.do", method = RequestMethod.POST)
	@ResponseBody
	public JsonMsgResponse addChat(Chat chat, HttpServletRequest request) {
		JsonMsgResponse msgResponse = new JsonMsgResponse();
		User user = this.getSessionUser(request);
		if (user == null || user.getId() == null) {
			msgResponse.setJsonMsg(403, false, "未登陆用户!");
			return msgResponse;
		}
		chat.setCreateTime(new Date());
		chat.setUserID(user.getId());
		Chat c = chatService.addChat(chat);
		if (c != null) {
			opLogService.writeLog(new OpLog(user.getUserName(), "添加了一条评论", new Date()));
			msgResponse.jsonMsgSuccess();
		} else {
			msgResponse.jsonMsgFail();
		}
		return msgResponse;
	}

	/**
	 * 删除评论
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delChat.do", method = RequestMethod.GET)
	@ResponseBody
	public JsonMsgResponse delChat(String chatID, HttpServletRequest request) {
		JsonMsgResponse msgResponse = new JsonMsgResponse();
		User user = this.getSessionUser(request);
		if (user == null || user.getId() == null) {
			msgResponse.setJsonMsg(403, false, "未登陆用户!");
			return msgResponse;
		}
		if (StringUtils.isEmpty(chatID) || !StringUtils.isNumeric(chatID)) {
			msgResponse.setJsonMsg(403, false, "提交ID不能为空并且必须是数字!");
			return msgResponse;
		}
		boolean isDataDeleted = chatService.deleteChat(Integer.valueOf(chatID));// 删除评论
		if (isDataDeleted) {
			opLogService.writeLog(new OpLog(user.getUserName(), "删除了一条评论", new Date()));
			msgResponse.jsonMsgSuccess();
		} else {
			msgResponse.jsonMsgFail();
		}
		return msgResponse;
	}

	/**
	 * 加载有关于该升级包的所有评论
	 * 
	 * @param packageID
	 * @return
	 */
	@RequestMapping(value = "/loadPackagedChats.do")
	@ResponseBody
	public List<ChatDTO> loadPackagedChats(String packageID) {
		List<ChatDTO> packagedChatList = new ArrayList<ChatDTO>();
		packagedChatList = chatService.getChatDTOByPackageID(Integer.valueOf(packageID));
		return packagedChatList;
	}

	/**
	 * 发送邮件
	 * 
	 * @param chatMsg
	 */
	@RequestMapping(value = "/mailToUser.do", method = RequestMethod.POST)
	@ResponseBody
	public JsonMsgResponse mailToUser(String chatMsg) {
		JsonMsgResponse msgResponse = new JsonMsgResponse();
		String realNameStr = this.findSubString(chatMsg, "@", ":");
		// 判断是否存在@用户
		if (!StringUtils.isEmpty(realNameStr)) {
			String[] realNames = realNameStr.split(":");
			String address = "";
			for (int i = 0; i < realNames.length; i++) {
				String mail = userService.findMailByRealName(realNames[i]);// 获取用户合法邮箱地址
				if (!StringUtils.isEmpty(mail)) {
					address = address + mail + ";";
				}
			}
			// 判断是否有该@用户邮箱地址
			if (!StringUtils.isEmpty(address)) {
				MailUtil mailUtil = new MailUtil();
				boolean ifSend = mailUtil.sendHTMLMail("通知", chatMsg, address);// 发送邮件
				if (ifSend) {
					msgResponse.jsonMsgSuccess();
				} else {
					msgResponse.jsonMsgFail();
				}
			} else {
				msgResponse.setJsonMsg(100, true, "");
			}

		} else {
			msgResponse.setJsonMsg(100, true, "");
		}
		return msgResponse;
	}

	/**
	 * 截取目标内容中以标识开始到结束的字段
	 * 
	 * @param targetStr
	 * @param beginRexp
	 * @param endRexp
	 * @return
	 */
	private String findSubString(String targetStr, String beginRexp, String endRexp) {
		String reString = "";
		int beginIndex = 0;
		int endIndex = 0;
		while (beginIndex != -1) {
			beginIndex = targetStr.indexOf(beginRexp, endIndex);
			endIndex = targetStr.indexOf(endRexp, beginIndex);
			if (beginIndex == -1 || endIndex == -1) {
				break;
			}
			reString += targetStr.substring(beginIndex + 1, endIndex + 1);
		}
		return reString;
	}
}
