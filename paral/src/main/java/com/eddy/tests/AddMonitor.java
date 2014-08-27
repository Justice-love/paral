package com.eddy.tests;

import com.eddy.annotations.ElementAction;
import com.eddy.annotations.TestUnit;
import com.eddy.entity.Action;
import com.eddy.entity.BasicJob;
import com.eddy.entity.LoginNeeded;
import com.eddy.entity.SeleniumChain;

//增加监测器
@TestUnit(baseURL = "http://itoss.siteview.com/itoss/webloader",isLogin=LoginNeeded.YES, loginWait=8000, openWait=6000)
public class AddMonitor extends BasicJob {

	@ElementAction(action=Action.CLICK,sleepTime=8000)
	private String Test_0="//html/body/div[4]/div[2]/div[2]/div";
	
	@ElementAction(action=Action.CLICK,sleepTime=5000)
	private String Test_1="//html/body/div[5]/div[1]/div[1]/div[4]/div/img";
	
	//点击“测试组”的下拉箭头
	@ElementAction(action=Action.CLICK,sleepTime=4000)
	private String Test_2="//html/body/div[4]/div[4]/div[1]/div/div/div[7]/div/div/div[1]/div[10]/div[1]";
	
	//点击任一设备，如“192.168.9.18”,右键，点击“增加监测器”
	@ElementAction(action=Action.CLICK,sleepTime=4000)
	private String Test_3="//html/body/div[4]/div[4]/div[1]/div/div/div[7]/div/div/div[1]/div[12]/div[3]";
	
	@ElementAction(action=Action.CONTEXTCLICK,sleepTime=3000)
	private String Test_4="//html/body/div[4]/div[4]/div[1]/div/div/div[7]/div/div/div[1]/div[12]/div[3]";
	
	@ElementAction(action=Action.CLICK,sleepTime=3000)
	private String Test_5="//html/body/div[5]/div/div[6]/div[2]";
	
	//快速搜索：ping监测器,双击ping监测器
	@ElementAction(action=Action.SETVALUE,value="ping",sleepTime=3000)
	private String Test_6="//html/body/div[6]/div[2]/div[1]/div/div[2]/div/input";
	
//	@ElementAction(action=Action.CLICK,sleepTime=4000)
//	private String Test_7="//html/body/div[6]/div[2]/div[1]/div/div[6]/div[1]/div[4]";
	
	@ElementAction(action=Action.DOUBLECLICK,sleepTime=5000000)
	private String Test_8="//html/body/div[6]/div[2]/div[1]/div/div[6]/div[1]/div[4]";
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SeleniumChain.addJob(new AddMonitor()).execute();

		
		
	}

}
