
package com.xye.boot;

import com.xye.util.BeanFactoryUtil;
import com.xye.util.SystemDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ClassName:Launcher
 * 
 * Date: 2017��2��22�� ����9:26:15
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
		System.out.println("        Core������          ");
		SystemDetails.outputDetails();
		System.out.println("=======================");

		getLocalip();
		// ��ʼ��spring
		logger.info("��ʼ��ʼ��core����");
		BeanFactoryUtil.init();

		try {
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ȡ�ñ���ip��ַ ע�⣺Spring RmiServiceExporterȡ�ñ���ip�ķ�����InetAddress.getLocalHost()
	 */
	private static void getLocalip() {
		try {
			System.out.println("����¶��ip: " + java.net.InetAddress.getLocalHost().getHostAddress());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
