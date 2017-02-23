
package com.xye.service;

/**
 * ClassName:IUserService
 * 
 * Date: 2017年2月22日 下午9:08:28
 * 
 * @author guanchun.yu
 * @version 0.0.1
 */
public interface IUserService {

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login(String username, String password);
}
