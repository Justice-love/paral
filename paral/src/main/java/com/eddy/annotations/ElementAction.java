/**
 * 
 * @creatTime 上午10:16:36
 * @author XuYi
 */
package com.eddy.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.eddy.entity.Action;
import com.eddy.entity.AssertType;
import com.eddy.entity.ByType;
import com.eddy.entity.Exist;

/**
 * 定义节点的动作
 * @author XuYi
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ElementAction {

	/**
	 * 线程休眠时间, 默认1s, 单位是毫秒
	 * @return
	 * @creatTime 上午10:29:13
	 * @author XuYi
	 */
	long sleepTime() default 1000;
	
	/**
	 * 节点动作, 默认为设置值
	 * @return
	 * @creatTime 上午10:31:16
	 * @author XuYi
	 */
	Action action() default Action.SETVALUE;
	
	/**
	 * 需要设置的值
	 * @return
	 * @creatTime 上午10:43:13
	 * @author XuYi
	 */
	String value() default "";
	
	/**
	 * 选择器类型
	 * @return
	 * @creatTime 下午1:26:27
	 * @author XuYi
	 */
	ByType type() default ByType.XPATH;
	
	/**
	 * 匹配类型选择
	 * @return
	 * @creatTime 下午3:58:19
	 * @author XuYi
	 */
	AssertType assertType() default AssertType.EQUALS;
	
	/**
	 * 页面元素是否存在
	 * @return
	 * @creatTime 上午1:03:49
	 * @author XuYi
	 */
	Exist isExist() default Exist.SURE;
}
