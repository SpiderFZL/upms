package cn.com.zhyu.upm.dao;

import java.util.List;

import cn.com.zhyu.upm.dto.ChatDTO;
import cn.com.zhyu.upm.pojo.Chat;

/**
 * @author tangwe
 * @date 2014年11月24日 上午11:55:36
 * @Description: TODO(评论接口)
 * @version V1.0
 */
public interface ChatDAO {
	/**
	 * 添加评论信息
	 * 
	 * @param c
	 * @return
	 */
	public Chat addChat(Chat c);

	/**
	 * 查询有关升级包的所有评论
	 * 
	 * @param packageID
	 * @return
	 */
	public List<ChatDTO> findChatByPackageID(Integer packageID);

	/**
	 * 删除评论
	 * 
	 * @param chatID
	 * @return
	 */
	public boolean deleteChat(Integer chatID);

	/**
	 * 获取包的评论统计
	 * 
	 * @param pagekageID
	 * @return
	 */
	public long getChatCountByPackageID(Integer pagekageID);
}
