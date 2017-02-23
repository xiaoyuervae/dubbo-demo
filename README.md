# dubbo 服务的一个demo

项目地址[dubbo-demo](https://github.com/xiaoyuervae/dubbo-demo)

> 查看了官方的文档，dubbo一共有三种方式进行配置：

- XML文件配置方式
- properties配置方式
- annotion配置方式

这里介绍xml文件的配置方式，其他方式原理都相同，[dubbo用户指南](http://dubbo.io/User+Guide-zh.htm)

## XML文件配置方式

1. 新建一个maven项目dubbo-demo，打包方式选择pom，然后创建三个模块interface，provider，consumer。项目结构如下图：

![image](http://xiaoyuervae-1252527405.cosgz.myqcloud.com/dubbo-demo%E9%A1%B9%E7%9B%AE%E7%BB%93%E6%9E%84.png)

2.配置dubbo-demo的pom文件，引入junit及sl4j依赖。配置如下：


```
<!-- **************************** Properties 配置 **************************** -->
    <properties>
        <junit.version>3.8.1</junit.version>
    </properties>
    <!-- **************************** /Properties 配置 **************************** -->

    <dependencies>
        <!-- **************************** JUnit 依赖 **************************** -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
        <!-- **************************** /JUnit 依赖 **************************** -->

        <!-- **************************** 日志 依赖 **************************** -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.6</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.7.6</version>
        </dependency>
        <!-- **************************** /日志 依赖 **************************** -->
    </dependencies>
```

在provider和consumer里面加入log4j.properties文件：

```
#Console Log
log4j.rootLogger=info, console, file

# Write to Console
log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.Threshold=INFO
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=%5p %d{MM-dd HH:mm:ss}(%F:%L): %m%n

#Write to File
log4j.appender.file=org.apache.log4j.DailyRollingFileAppender
log4j.appender.file.File=${catalina.home}log/provider.log
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=%5p %d{MM-dd HH:mm:ss}(%F:%L): %m%n
```


3.配置interface

> 新建com.xye.service.IUserService.java类：


```
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
```

4.配置provider

- 配置pom文件，引入spring依赖，dubbo依赖及interface依赖：


```
<!-- **************************** Properties 配置 **************************** -->
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <junit.version>3.8.1</junit.version>
        <springframework.version>4.1.6.RELEASE</springframework.version>
        <commonsLogging.version>1.2</commonsLogging.version>
    </properties>
    <!-- **************************** /Properties 配置 **************************** -->

    <dependencies>
        <!-- **************************** Spring 依赖 **************************** -->
        <!-- 添加Spring-core包 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${springframework.version}</version>
        </dependency>
        <!-- 添加spring-tx包 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>${springframework.version}</version>
        </dependency>
        <!-- Spring ORM 相关 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-orm</artifactId>
            <version>${springframework.version}</version>
        </dependency>
        <!-- 添加spring-jdbc包 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${springframework.version}</version>
        </dependency>
        <!--添加spring-web包 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>${springframework.version}</version>
        </dependency>
        <!-- 添加spring-context包 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${springframework.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>${springframework.version}</version>
        </dependency>
        <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
            <version>${commonsLogging.version}</version>
        </dependency>
        <!--添加aspectjweaver包 -->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.8.5</version>
        </dependency>
        <!-- **************************** /Spring 依赖 **************************** -->

        <!-- **************************** Dubbo 依赖 **************************** -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo</artifactId>
            <version>2.5.3</version>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.apache.zookeeper</groupId>
            <artifactId>zookeeper</artifactId>
            <version>3.3.3</version>
            <exclusions>
                <exclusion>
                    <groupId>com.sun.jmx</groupId>
                    <artifactId>jmxri</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>com.sun.jdmk</groupId>
                    <artifactId>jmxtools</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>javax.jms</groupId>
                    <artifactId>jms</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>com.github.sgroschupf</groupId>
            <artifactId>zkclient</artifactId>
            <version>0.1</version>
        </dependency>
        <!-- **************************** /Dubbo 依赖 **************************** -->

        <!-- **************************** interface 依赖 **************************** -->
        <dependency>
            <groupId>com.xye</groupId>
            <artifactId>interface</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
        <!-- **************************** /interface 依赖 **************************** -->
    </dependencies>
```

-  新建com.xye.service.impl.UserServiceImpl类并注入bean（在类前面加上@Service('userService')）


```
package com.xye.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xye.service.IUserService;

/**
 * ClassName:UserServiceImpl
 * 
 * Date: 2017年2月22日 下午9:12:41
 * 
 * @author guanchun.yu
 * @version 0.0.1
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

		
	public boolean login(String username, String password) {
		logger.info("用户登录：[username:{}, password:{}]", username, password);
		if (username != null && password != null && username.equals(password)) {
			logger.info("用户校验通过。[username:{}]", username);
			return true;
		}
		logger.info("用户校验失败！[username:{}]", username);
		return false;
	}

}
```

- 在resource里面加入ApplicationContext.xml文件及spring-provider.xml文件

Application.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

<!-- **************************** 注解扫描 **************************** -->
<context:component-scan base-package="com.xye.service.impl"/>
<!-- **************************** /注解扫描 **************************** -->

<!-- **************************** 导入其他XML文件 **************************** -->
<import resource="spring-provider.xml"/>
<!-- **************************** /导入其他XML文件 **************************** -->
</beans>
```

spring-provider.xml


```
<?xml version="1.0" encoding="UTF-8"?>
<!-- 添加 DUBBO SCHEMA -->
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd http://code.alibabatech.com/schema/dubbo
        http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

    <!-- 应用名 -->
    <dubbo:application name="dubbo-demo-provider"/>
    <!-- 连接到哪个本地注册中心 -->
    <dubbo:registry id="dubbo-demo" address="zookeeper://localhost:2181"/>
    <!-- 用dubbo协议在20880端口暴露服务 -->
    <dubbo:protocol name="dubbo" port="28080"/>
    <!-- 声明需要暴露的服务接口 -->
    <dubbo:service registry="dubbo-demo" timeout="3000" interface="com.xye.service.IUserService" ref="userService"/>
</beans>
```

我们看到 dubbo:service 标签里面配置的就是要暴露出去的服务接口

- 加入util文件，创建 com.xye.util.BeanFactoryUtil.java 和 com.xye.util.SystemUtil

BeanFactoryUtil.java


```

package com.xye.util;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName:BeanFactoryUtil
 * 
 * Date: 2017年2月22日 下午9:20:56
 * 
 * @author guanchun.yu
 * @version 0.0.1
 */
public class BeanFactoryUtil {
	private static ApplicationContext ctx_producer = null;

	public final static String ApplicationContextRoot = "";
	public final static String ApplicationContextPath = ApplicationContextRoot + "applicationContext.xml";

	public static void init() {
		if (ctx_producer == null) {
			synchronized (BeanFactoryUtil.class) {
				if (ctx_producer == null) {
					String[] configLocations = new String[] { ApplicationContextPath };
					ctx_producer = new ClassPathXmlApplicationContext(configLocations);
				}
			}
		}
	}

	public static ApplicationContext getContext() {
		init();
		return ctx_producer;
	}

}

```

SystemDetails.java


```

package com.xye.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * ClassName:SystemDetails
 * 
 * Date: 2017年2月22日 下午9:24:32
 * 
 * @author guanchun.yu
 * @version 0.0.1
 */
public class SystemDetails {

	/**
	 * 输出系统基本信息
	 */
	public static void outputDetails() {
		timeZone();
		currentTime();
		os();
	}

	/**
	 * 输出系统时区
	 */
	private static void timeZone() {
		Calendar cal = Calendar.getInstance();
		TimeZone timeZone = cal.getTimeZone();
		System.out.println("系统时区:" + timeZone.getDisplayName());
	}

	/**
	 * 输出系统时间
	 */
	private static void currentTime() {
		String fromFormat = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(fromFormat);
		Date myDate = new Date();
		System.out.println("系统时间:" + format.format(myDate));
	}

	/**
	 * 输出系统基本配置
	 */
	private static void os() {
		String osName = System.getProperty("os.name"); // 操作系统名称
		System.out.println("当前系统:" + osName);
		String osArch = System.getProperty("os.arch"); // 操作系统构架
		System.out.println("当前系统架构" + osArch);
		String osVersion = System.getProperty("os.version"); // 操作系统版本
		System.out.println("当前系统版本:" + osVersion);
	}
}

```

- 创建启动文件com.xye.boot.Launcher.java


```

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

```

- 下载zookeeper注册中心并运行bin/zkServer.cmd, [点击这里下载](http://pan.baidu.com/s/1mhnFP4O),运行launcher.java.main()方法
![image](http://xiaoyuervae-1252527405.cosgz.myqcloud.com/provider%E6%9C%8D%E5%8A%A1%E5%90%AF%E5%8A%A8.png)


5.配置consumer

- 配置pom文件


```
<!-- **************************** Properties 配置 **************************** -->
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <junit.version>3.8.1</junit.version>
        <springframework.version>4.1.6.RELEASE</springframework.version>
        <commonsLogging.version>1.2</commonsLogging.version>
    </properties>
    <!-- **************************** /Properties 配置 **************************** -->

    <dependencies>
        <!-- **************************** Spring 依赖 **************************** -->
        <!-- 添加Spring-core包 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${springframework.version}</version>
        </dependency>
        <!-- 添加spring-tx包 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>${springframework.version}</version>
        </dependency>
        <!-- Spring ORM 相关 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-orm</artifactId>
            <version>${springframework.version}</version>
        </dependency>
        <!-- 添加spring-jdbc包 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${springframework.version}</version>
        </dependency>
        <!--添加spring-web包 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>${springframework.version}</version>
        </dependency>
        <!-- 添加spring-context包 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${springframework.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>${springframework.version}</version>
        </dependency>
        <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
            <version>${commonsLogging.version}</version>
        </dependency>
        <!--添加aspectjweaver包 -->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.8.5</version>
        </dependency>
        <!-- **************************** /Spring 依赖 **************************** -->

        <!-- **************************** Dubbo 依赖 **************************** -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo</artifactId>
            <version>2.5.3</version>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.apache.zookeeper</groupId>
            <artifactId>zookeeper</artifactId>
            <version>3.3.3</version>
            <exclusions>
                <exclusion>
                    <groupId>com.sun.jmx</groupId>
                    <artifactId>jmxri</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>com.sun.jdmk</groupId>
                    <artifactId>jmxtools</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>javax.jms</groupId>
                    <artifactId>jms</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>com.github.sgroschupf</groupId>
            <artifactId>zkclient</artifactId>
            <version>0.1</version>
        </dependency>
        <!-- **************************** /Dubbo 依赖 **************************** -->

        <!-- **************************** interface 依赖 **************************** -->
        <dependency>
            <groupId>com.xye</groupId>
            <artifactId>interface</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
        <!-- **************************** /interface 依赖 **************************** -->
    </dependencies>
```

- 在resource下新建applicationContext.xml和spring-consumer.xml文件

applicationContext.xml


```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd ">

    <!-- **************************** 导入其他XML文件 **************************** -->
    <import resource="spring-consumer.xml" />
    <!-- **************************** /导入其他XML文件 **************************** -->
</beans>
```

spring-consumer.xml


```
<?xml version="1.0" encoding="UTF-8"?>
<!-- 添加 DUBBO SCHEMA -->
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd http://code.alibabatech.com/schema/dubbo
        http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

    <!-- 应用名 -->
    <dubbo:application name="dubbo-demo-consumer"/>
    <!-- 连接到哪个注册中心（连接到本机的2181端口zookeeper） -->
    <dubbo:registry address="zookeeper://localhost:2181"/>
    <!-- 消费方用什么协议获取服务（用dubbo协议在20880端口暴露服务） -->
    <dubbo:protocol port="28080"/>
    <!-- 提供哪些接口给消费者调用 -->
    <dubbo:reference id="userService" interface="com.xye.service.IUserService"/>
</beans>
```

- 新建com.xye.UserServiceConsumer.java文件



```
  
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
  
```

- 运行UserServiceConsumer.java.main()就可以看到consumer调用provider。

![image](http://xiaoyuervae-1252527405.cosgz.myqcloud.com/dubbo%E8%B0%83%E7%94%A8.png)

参考: http://blog.csdn.net/huangjin0507/article/details/52234178















