/**
 * 
 * @creatTime 上午10:07:56
 * @author XuYi
 */
package com.eddy.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.eddy.entity.LoginNeeded;

/**
 * 定义单元测试属性
 * @author XuYi
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestUnit {

	/**
	 * 是否需要登录
	 * @return
	 * @creatTime 上午10:12:46
	 * @author XuYi
	 */
	LoginNeeded isLogin() default LoginNeeded.YES;
	
	/**
	 * url
	 * @return
	 * @creatTime 上午10:14:51
	 * @author XuYi
	 */
	String baseURL();
	
	/**
	 * 打开网页等待时间, 默认4S
	 * @return
	 * @creatTime 上午10:33:21
	 * @author XuYi
	 */
	long openWait() default 4000;
	
	/**
	 * 登陆成功后等待时间, 默认10S, 如果不需要登录, 该值不需要设置
	 * @return
	 * @creatTime 上午10:40:09
	 * @author XuYi
	 */
	long loginWait() default 10000;
	
	/**
	 * 超时时间, 单位为秒, 默认为10S
	 * @return
	 * @creatTime 下午1:46:09
	 * @author XuYi
	 */
	long timeOut() default 10;
}
