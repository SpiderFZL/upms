package cn.com.zhyu.upm.listener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import cn.com.tsg.openid.OpenIdManager;
import cn.com.zhyu.upm.common.AuthConfig;
import cn.com.zhyu.upm.common.AuthConstant;

/**
 * @ClassName: OpenIdConfigListener
 * @author tangwe
 * @date 2015年6月3日 上午10:31:35
 * @Description: TODO(spring加载完成后加载openId配置文件)
 * @version V1.0
 */
public class OpenIdConfigListener implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			initOpenIdConfig();
		}
	}

	/**
	 * 加载openId配置信息
	 */
	private void initOpenIdConfig() {
		// 读取配置信息
		Properties p = null;
		InputStream is = null;
		try {
			is = this.getClass().getClassLoader().getResourceAsStream("openIdAuth.properties");
			p = new Properties();
			p.load(is);
			AuthConfig.LOCAL_ROOT = p.getProperty("LOCAL_ROOT");
			AuthConfig.LOCAL_AUTH = p.getProperty("LOCAL_AUTH");
			AuthConfig.OPENID_SERVER = p.getProperty("OPENID_SERVER");
			AuthConfig.OPENID_SERVER_LOGOUT = p.getProperty("OPENID_SERVER_LOGOUT");
			AuthConfig.OPENID_LOCAL_LOGOUT = p.getProperty("OPENID_LOCAL_LOGOUT");
			AuthConstant.manager = new OpenIdManager();
			AuthConstant.manager.setRealm(AuthConfig.LOCAL_ROOT);
			AuthConstant.manager.setReturnTo(AuthConfig.LOCAL_AUTH);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("初始化openId配置信息完成!");
	}

}
