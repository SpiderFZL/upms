package cn.com.zhyu.upm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import cn.com.zhyu.upm.dao.BaseDAO;
import cn.com.zhyu.upm.dao.ChatDAO;
import cn.com.zhyu.upm.dto.ChatDTO;
import cn.com.zhyu.upm.pojo.Chat;

/**
 * @ClassName: ChatDAOImpl
 * @author tangwe
 * @date 2014年11月24日 下午12:14:00
 * @Description: TODO(chat接口实现)
 * @version V1.0
 */
@Repository("ChatDAO")
public class ChatDAOImpl extends BaseDAO implements ChatDAO {

	@Override
	public Chat addChat(Chat c) {
		String sql = "INSERT INTO ZHYU_CHAT(CHATMSG,CREATETIME,USERID,UPPACKAGEID) VALUES(?,?,?,?)";
		Object[] params = { c.getChatMsg(), c.getCreateTime(), c.getUserID(), c.getPackageID() };
		int re = this.jdbcTemplate.update(sql, params);
		if (re > 0) {
			return c;
		}
		return null;
	}

	@Override
	public List<ChatDTO> findChatByPackageID(Integer packageID) {
		String sql = "SELECT C.ID,C.CHATMSG,C.CREATETIME,C.USERID,C.UPPACKAGEID,U.USERNAME,U.REALNAME,U.MAIL,U.AUTH FROM ZHYU_CHAT C "
				+ "LEFT JOIN ZHYU_USER U ON C.USERID = U.ID WHERE C.UPPACKAGEID = ? ORDER BY C.CREATETIME DESC";
		final List<ChatDTO> chatList = new ArrayList<ChatDTO>();
		Object[] param = { packageID };
		this.jdbcTemplate.query(sql, param, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ChatDTO chat = new ChatDTO();
				chat.setAuth(rs.getString("auth"));
				chat.setChatMsg(rs.getString("chatmsg"));
				chat.setCreateTime(rs.getDate("createtime"));
				chat.setId(rs.getInt("id"));
				chat.setMail(rs.getString("mail"));
				chat.setPackageID(rs.getInt("uppackageid"));
				chat.setRealName(rs.getString("realname"));
				chat.setUserID(rs.getInt("userid"));
				chat.setUserName(rs.getString("username"));
				chatList.add(chat);
			}
		});
		return chatList;
	}

	@Override
	public boolean deleteChat(Integer chatID) {
		String sql = "DELETE FROM ZHYU_CHAT WHERE ID = ?";
		int re = this.jdbcTemplate.update(sql, new Object[] { chatID });
		if (re > 0) {
			return true;
		}
		return false;
	}

	@Override
	public long getChatCountByPackageID(Integer pagekageID) {
		String sql = "SELECT COUNT(C.ID) FROM ZHYU_CHAT C WHERE C.UPPACKAGEID=?";
		return this.jdbcTemplate.queryForLong(sql, new Object[] { pagekageID });
	}
}
