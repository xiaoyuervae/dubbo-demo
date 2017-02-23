  
package com.xye;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xye.service.IUserService;

/** 
 * ClassName:UserServiceConsumer
 * 
 * Date:     2017年2月23日 上午9:07:49
 * @author   guanchun.yu
 * @version  0.0.1
 */
public class UserServiceConsumer {

	private static Logger logger = LoggerFactory.getLogger(UserServiceConsumer.class);

    public static void main(String args[]) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        IUserService userService = (IUserService) ctx.getBean("userService");
        logger.info("执行结果：" + userService.login("hello", "hello"));
    }
    
}
  