/**
 * 
 * @creatTime 上午11:52:08
 * @author XuYi
 */
package com.eddy.tests;

import com.eddy.annotations.TestUnit;
import com.eddy.entity.BasicJob;
import com.eddy.entity.LoginNeeded;

/**
 * @author XuYi
 *
 */
@TestUnit(baseURL="http://www.baidu.com", isLogin=LoginNeeded.NO, timeOut=6, loginWait=1500, openWait=1500)
public class BaiduTest extends BasicJob {

}
