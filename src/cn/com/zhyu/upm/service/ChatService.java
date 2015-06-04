package cn.com.zhyu.upm.service;

import java.util.List;

import cn.com.zhyu.upm.dto.ChatDTO;
import cn.com.zhyu.upm.pojo.Chat;

/**
 * @ClassName: ChatService
 * @author tangwe
 * @date 2014年11月24日 下午1:25:15
 * @Description: TODO(评论Service接口)
 * @version V1.0
 */
public interface ChatService {
	/**
	 * 添加评论
	 * 
	 * @param c
	 * @return
	 */
	public Chat addChat(Chat c);

	/**
	 * 获取相关升级包的所有评论信息
	 * 
	 * @param packageID
	 * @return
	 */
	public List<ChatDTO> getChatDTOByPackageID(Integer packageID);

	/**
	 * 删除评论
	 * 
	 * @param chatID
	 * @return
	 */
	public boolean deleteChat(Integer chatID);
}
