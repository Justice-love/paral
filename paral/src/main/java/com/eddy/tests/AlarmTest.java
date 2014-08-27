/**
 * 
 * @creatTime 下午3:46:20
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
@TestUnit(baseURL="http://itossdemo.siteview.com/itoss/webloader", isLogin=LoginNeeded.YES, timeOut=6, loginWait=1000000)
public class AlarmTest extends BasicJob {

	
	public static void main(String[] args) throws Exception {
		AlarmTest test = new AlarmTest();
		test.excute();
	}
}
