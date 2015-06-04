package cn.com.zhyu.upm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.zhyu.upm.dao.ChatDAO;
import cn.com.zhyu.upm.dto.ChatDTO;
import cn.com.zhyu.upm.pojo.Chat;
import cn.com.zhyu.upm.service.ChatService;

/**
 * @ClassName: ChatServiceImpl
 * @author tangwe
 * @date 2014年11月24日 下午1:30:38
 * @Description: TODO(评论service接口实现)
 * @version V1.0
 */
@Service("ChatService")
public class ChatServiceImpl implements ChatService {
	@Autowired
	private ChatDAO chatDAO;

	@Override
	public Chat addChat(Chat c) {
		return chatDAO.addChat(c);
	}

	@Override
	public List<ChatDTO> getChatDTOByPackageID(Integer packageID) {
		return chatDAO.findChatByPackageID(packageID);
	}

	@Override
	public boolean deleteChat(Integer chatID) {
		return chatDAO.deleteChat(chatID);
	}

}
