
package com.xye.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xye.service.IUserService;

/**
 * ClassName:UserServiceImpl
 * 
 * Date: 2017��2��22�� ����9:12:41
 * 
 * @author guanchun.yu
 * @version 0.0.1
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

		
	public boolean login(String username, String password) {
		logger.info("�û���¼��[username:{}, password:{}]", username, password);
		if (username != null && password != null && username.equals(password)) {
			logger.info("�û�У��ͨ����[username:{}]", username);
			return true;
		}
		logger.info("�û�У��ʧ�ܣ�[username:{}]", username);
		return false;
	}

}
