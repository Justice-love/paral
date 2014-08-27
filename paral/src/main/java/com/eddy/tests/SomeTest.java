/**
 * 
 * @creatTime 下午2:42:40
 * @author XuYi
 */
package com.eddy.tests;

import com.eddy.annotations.ElementAction;
import com.eddy.annotations.TestUnit;
import com.eddy.entity.Action;
import com.eddy.entity.AssertType;
import com.eddy.entity.BasicJob;
import com.eddy.entity.LoginNeeded;
import com.eddy.entity.SeleniumChain;

/**
 * @author XuYi
 *
 */
@TestUnit(baseURL="http://itossdemo.siteview.com/itoss/webloader", isLogin=LoginNeeded.YES, openWait=4000, loginWait=10000)
public class SomeTest extends BasicJob{

	@ElementAction(action=Action.CLICK, sleepTime=1000)
	private String closeButton = "//html/body/div[5]/div[1]/div[1]/div[5]/div/img";
	
	@ElementAction(action=Action.CONTEXTCLICK, sleepTime=1000)
	private String closeButton2 = "//html/body/div[5]/div[1]/div[1]/div[5]/div/img";
	
	@ElementAction(action=Action.CLICK, sleepTime=1000)
	private String closeButton3 = "//html/body/div[5]/div[1]/div[1]/div[5]/div/img";
	
	@ElementAction(action=Action.CLICK, sleepTime=1000)
	private String closeButton4 = "//html/body/div[5]/div[1]/div[1]/div[5]/div/img";
	
	@ElementAction(action=Action.SETVALUE, sleepTime=1000, value="1234")
	private String closeButton5 = "//html/body/div[5]/div[1]/div[1]/div[5]/div/img";
	
	@ElementAction(action=Action.SETVALUE, sleepTime=1000, value="165765")
	private String closeButton6 = "//html/body/div[5]/div[1]/div[1]/div[5]/div/img";
	
	@ElementAction(action=Action.CLICK, sleepTime=1000)
	private String closeButton7 = "//html/body/div[5]/div[1]/div[1]/div[5]/div/img";
	
	@ElementAction(action=Action.ASSERT, sleepTime=1000, value="192.168.9.179", assertType=AssertType.EQUALS)
	private String closeButton8 = "//html/body/div[5]/div[1]/div[1]/div[5]/div/img";
	
	public static void main(String[] args) {
		SomeTest s = new SomeTest();
		BaiduTest q = new BaiduTest();
		SeleniumChain.addJob(s).addJob(q).execute();
	}
}
