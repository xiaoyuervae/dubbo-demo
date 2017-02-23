
package com.xye.boot;

import com.xye.util.BeanFactoryUtil;
import com.xye.util.SystemDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ClassName:Launcher
 * 
 * Date: 2017年2月22日 下午9:26:15
 * 
 * @author guanchun.yu
 * @version 0.0.1
 */
public class Launcher {

	private static Log logger = LogFactory.getLog(Launcher.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("=======================");
		System.out.println("        Core包启动          ");
		SystemDetails.outputDetails();
		System.out.println("=======================");

		getLocalip();
		// 初始化spring
		logger.info("开始初始化core服务");
		BeanFactoryUtil.init();

		try {
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得本机ip地址 注意：Spring RmiServiceExporter取得本机ip的方法：InetAddress.getLocalHost()
	 */
	private static void getLocalip() {
		try {
			System.out.println("服务暴露的ip: " + java.net.InetAddress.getLocalHost().getHostAddress());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
