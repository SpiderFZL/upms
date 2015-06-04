package cn.com.zhyu.upm.common;

import cn.com.zhyu.upm.util.mail.MailSenderInfo;
import cn.com.zhyu.upm.util.mail.SimpleMailSender;

/**
 * @ClassName: MailUtil
 * @author tangwe
 * @date 2014年11月26日 下午1:22:37
 * @Description: TODO(邮件发送)
 * @version V1.0
 */
public class MailUtil {

	public boolean sendHTMLMail(String title, String Content, String address) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.exmail.qq.com");
		mailInfo.setMailServerPort("465");
		mailInfo.setValidate(true);
		mailInfo.setUserName("info@zhyu.com.cn");
		mailInfo.setPassword("zywt12345");// 邮箱密码
		mailInfo.setFromAddress("info@zhyu.com.cn");
		mailInfo.setSubject(title);
		mailInfo.setContent(Content);
		mailInfo.setToAddress(address);
		// 邮件 发送html格式
		boolean ifSend = SimpleMailSender.sendHtmlMail(mailInfo);
		return ifSend;
	}

	public boolean sendTextMail(String title, String Content, String address) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.exmail.qq.com");
		mailInfo.setMailServerPort("465");
		mailInfo.setValidate(true);
		mailInfo.setUserName("info@zhyu.com.cn");
		mailInfo.setPassword("zywt12345");// 邮箱密码
		mailInfo.setFromAddress("info@zhyu.com.cn");
		mailInfo.setSubject(title);
		mailInfo.setContent(Content);
		mailInfo.setToAddress(address);
		// 邮件 发送text格式
		boolean ifSend = SimpleMailSender.sendHtmlMail(mailInfo);
		return ifSend;
	}

	// 测试
	public static void main(String[] args) {
		new MailUtil().sendHTMLMail("测试邮件", "hello", "tangwei@zhyu.com.cn");
	}
}
