/**
 * 
 * @creatTime 上午10:44:47
 * @author XuYi
 */
package com.eddy.tests;

import com.eddy.annotations.ElementAction;
import com.eddy.annotations.TestUnit;
import com.eddy.entity.Action;
import com.eddy.entity.BasicJob;
import com.eddy.entity.LoginNeeded;
import com.eddy.entity.SeleniumChain;

/**
 * @author XuYi
 *
 */
@TestUnit(baseURL="http://itossdemo.siteview.com/itoss/webloader", isLogin=LoginNeeded.YES, timeOut=6, loginWait=3000)
public class MineTest extends BasicJob{
//	
//	@ElementAction(sleepTime=1000, action=Action.DOUBLECLICK)
//	private String groupXpath = "//html/body/div[4]/div[4]/div/div/div/div[2]/div/div/div/div[2]";
//	
//	@ElementAction(sleepTime=1000, action=Action.CONTEXTCLICK)
//	private String groupXpath2 = "//html/body/div[4]/div[4]/div/div/div/div[2]/div/div/div/div[2]";
//	
//	@ElementAction(sleepTime=1000, action=Action.CLICK)
//	private String newDeviceXpath = "//html/body/div[5]/div/div[8]/div[2]";
//	
//	@ElementAction(sleepTime=3000, action=Action.CLICK)
//	private String newDeviceChildXpath = "//html/body/div[6]/div/div[2]";
//	
//	@ElementAction(sleepTime=1000, action=Action.SETVALUE, value="192.168.9.182")
//	private String ipXpath = "//html/body/div[4]/div[4]/div[1]/div/div/div[6]/div[5]/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div/div[1]/input";
//	
//	@ElementAction(action=Action.SETVALUE, value="123")
//	private String pwdXpath = "//html/body/div[4]/div[4]/div[1]/div/div/div[6]/div[5]/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div/div[4]/input";
//	
//	@ElementAction(sleepTime=20000, action=Action.CLICK)
//	private String saveXpath = "//html/body/div[4]/div[4]/div[1]/div/div/div[6]/div[5]/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div/div[33]";
//	
//	@ElementAction(sleepTime=1000, action=Action.CLICK)
//	private String groupNodeXpath = "//html/body/div[4]/div[4]/div[1]/div/div/div[2]/div/div/div[1]/div[2]";
	
	@ElementAction(action=Action.CLICK, sleepTime=2000)
	private String closeButton = "//html/body/div[5]/div[1]/div[1]/div[4]/div/img";
	
	public static void main(String[] args) throws Exception {
		MineTest test = new MineTest();
		BaiduTest2 test2 = new BaiduTest2();
		//
		SeleniumChain.addJob(test).addJob(test2).execute();
	}
	
}

@TestUnit(baseURL="http://www.baidu.com", isLogin=LoginNeeded.NO, timeOut=6, loginWait=3000, openWait=2000)
class BaiduTest2 extends BasicJob {
	
	
}
