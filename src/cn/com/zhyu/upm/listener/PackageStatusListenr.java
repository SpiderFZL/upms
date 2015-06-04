package cn.com.zhyu.upm.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import cn.com.zhyu.upm.service.TarTaskService;

/**
 * @ClassName: PackageStatusListenr
 * @author tangwe
 * @date 2015年1月5日 下午6:18:35
 * @Description: TODO(spring加载完成后自动执行重置打包状态为0)
 * @version V1.0
 */
public class PackageStatusListenr implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private TarTaskService tarTaskService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			resetPackageStatus();
		}
	}

	/**
	 * 重置打包任务状态为0
	 */
	private void resetPackageStatus() {
		tarTaskService.resetTaskStatus();
		System.out.println("重置打包状态完成！");
	}

}
