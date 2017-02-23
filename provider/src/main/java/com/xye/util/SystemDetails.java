
package com.xye.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * ClassName:SystemDetails
 * 
 * Date: 2017��2��22�� ����9:24:32
 * 
 * @author guanchun.yu
 * @version 0.0.1
 */
public class SystemDetails {

	/**
	 * ���ϵͳ������Ϣ
	 */
	public static void outputDetails() {
		timeZone();
		currentTime();
		os();
	}

	/**
	 * ���ϵͳʱ��
	 */
	private static void timeZone() {
		Calendar cal = Calendar.getInstance();
		TimeZone timeZone = cal.getTimeZone();
		System.out.println("ϵͳʱ��:" + timeZone.getDisplayName());
	}

	/**
	 * ���ϵͳʱ��
	 */
	private static void currentTime() {
		String fromFormat = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(fromFormat);
		Date myDate = new Date();
		System.out.println("ϵͳʱ��:" + format.format(myDate));
	}

	/**
	 * ���ϵͳ��������
	 */
	private static void os() {
		String osName = System.getProperty("os.name"); // ����ϵͳ����
		System.out.println("��ǰϵͳ:" + osName);
		String osArch = System.getProperty("os.arch"); // ����ϵͳ����
		System.out.println("��ǰϵͳ�ܹ�" + osArch);
		String osVersion = System.getProperty("os.version"); // ����ϵͳ�汾
		System.out.println("��ǰϵͳ�汾:" + osVersion);
	}
}
