package com.pingan.monkey;

import com.alibaba.fastjson.JSONObject;
//import com.pingan.monkey.util.Shell;
import com.pingan.monkey.util.Shell;

import macaca.client.MacacaClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * Created by hujiachun on 16/12/21.
 */
public class Monkey {

	//$ java -jar [iosMonkey.jar Path] -u [设备的UDID] -b [测试App的BundleID] -port [macaca服务端口,可选] -proxyport[usb代理端口,可选]
    //idevicecrashreport -u ecab65eca01ae1d42874c26e645a33aee78296b6 -e -k /Users/hujiachun/Downloads/carshlog

    private MacacaClient driver;
    private int width, height;
    private static boolean needhelp = false;
    private static String UDID="770422b569b906811995669e98e682cf5a6d3dab", BUNDLEID="com.youku.hd";
    private static String PORT = "3456";
    private static String PROXYPORT = "8900";
    //定义事件概率
    private static String EVENT_TYPE_TAP = "0.60";
    private static String EVENT_TYPE_SWIPE = "0.30";
    private static String EVENT_TYPE_LAUNCH = "0.10";
    
    private int eventcount = 0;
    

    public static void main(String[] args) throws IOException, InterruptedException {
        executeParameter(args);

    }


    private static void executeParameter(String[] args) {
        int optSetting = 0;

        for (; optSetting < args.length; optSetting++) {
            if ("-u".equals(args[optSetting])) {
                UDID = args[++optSetting];
            } else if ("-b".equals(args[optSetting])) {
                BUNDLEID = args[++optSetting];
            } else if ("-port".equals(args[optSetting])) {
            	PORT = args[++optSetting];
            } else if ("-proxyport".equals(args[optSetting])) {
            	PROXYPORT = args[++optSetting];
            } else if ("-tapevent".equals(args[optSetting])) {
            	EVENT_TYPE_TAP = args[++optSetting];
            } else if ("-swipeevent".equals(args[optSetting])) {
            	EVENT_TYPE_SWIPE = args[++optSetting];
            } else if ("-lunchevent".equals(args[optSetting])) {
            	EVENT_TYPE_LAUNCH = args[++optSetting];
            } else if ("-h".equals(args[optSetting])) {
                needhelp = true;
                System.out.println(
                        "-u:设备的UDID\n" +
                        "-b:测试App的BUNDLEID\n"+
                        "-port:macaca服务的端口，默认3456\n" +
                        "-proxyport:usb代理端口，默认8900\n" +
                        "-tapevent:点击事件的概率，默认0.6 即60%\n" +
                        "-swipeevent:滑动事件的概率，默认0.3 即30%\n"+
                        "-lunchevent:挂起返回事件的概率，默认0.1 即10%\n"+
                        "/***tapevent+swipeevent+lunchevent应该等于1***/");
                break;
            }

        }
        if (!needhelp) {
            try {
                System.out.println("测试设备:" + UDID + "\n" + 
                        "测试app:" + BUNDLEID + "\n" + 
                        "点击事件的概率:" + EVENT_TYPE_TAP + "\n" + 
                        "滑动事件的概率:" + EVENT_TYPE_SWIPE + "\n" + 
                        "挂起返回事件的概率:" + EVENT_TYPE_LAUNCH + "\n" + 
                        "*******************************************");
                System.out.println(
                		"请确认以上默认的参数配置是否满意？\n" +
                        "y:马上开启Monkey之旅\n" +
                		"n:重新填写参数信息");
                
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                String line = null;
                //String line1 = null;
                //Scanner input = new Scanner(System.in);
                
                while ((line = input.readLine()) != null ){
                	if(line.contentEquals("y")){
                		org.testng.Assert.assertTrue((!UDID.equals(null)) && (!BUNDLEID.equals(null)));
                		System.out.println("正在启动Monkey...稍等...");
                        new Monkey().run();	
                	}else if(line.contentEquals("n")){
                		//重新读入用户输入的UDID，BUNDLEID，以及事件的概率
                        System.out.println("请输入测试设备的UDID");
                		UDID =input.readLine();
                		System.out.println("请输入测试app的BUNDLEID");
                		BUNDLEID = input.readLine();
                		System.out.println("请依次输入点击事件的概率、滑动事件的概率、挂起返回事件的概率，事件概率应为小于1的小数，且三者之和必须等于1");
                		String[] event= new String[3];
                		for(int i=0;i<3;i++){
                			event[i]=input.readLine();
                		
                		}
         
                		EVENT_TYPE_TAP = event[0];
                		EVENT_TYPE_SWIPE = event[1];
                		EVENT_TYPE_LAUNCH = event[2];
                		
                		System.out.println("*******************************************\n" +
                		        "新的参数配置如下：\n" +
                		        "测试设备:" + UDID + "\n" + 
                                "测试app:" + BUNDLEID + "\n" + 
                                "点击事件的概率:" + EVENT_TYPE_TAP + "\n" + 
                                "滑动事件的概率:" + EVENT_TYPE_SWIPE + "\n" + 
                                "挂起返回事件的概率:" + EVENT_TYPE_LAUNCH + "\n" + 
                                "*******************************************");
                		System.out.println(
                        		"请再次确认以上参数配置是否满意？\n" +
                                "y:马上开启Monkey之旅\n" +
                        		"n:重新填写参数信息");
                	
                		/*while ((line=input.readLine())!=null){
                			if(line.contentEquals("y")){
                        		org.testng.Assert.assertTrue((!UDID.equals(null)) && (!BUNDLEID.equals(null)));
                        		System.out.println("正在启动Monkey...稍等...");
                                new Monkey().run();	
                        	}else if(line.contentEquals("n")){
                        		
                        		break;
                        		
                        	}else {
                        		System.out.println("请输入y或者n");
                        	}
                			
                		}*/
                		
                		
                	}else {
                		System.out.println("请输入y或者n");
                	}
                	
                }
                input.close();
                
            } catch (Exception e) {
                System.out.println("请确认参数配置,需要帮助请在终端输入 java -jar iosMonkey.jar -h\n"
                		+ "ERROR信息"+ e.toString());
                
            }
            
        }
        
    }


    private void run() throws Exception {
        init();
        width = (Integer) driver.getWindowSize().get("width");
        height = (Integer) driver.getWindowSize().get("height");
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);


        while (true) {

            switch (new MathRandom().PercentageRandom(Double.valueOf(EVENT_TYPE_TAP),Double.valueOf(EVENT_TYPE_SWIPE),Double.valueOf(EVENT_TYPE_LAUNCH))) {
                case 0: {
                    new MonkeyTapEvent(driver, width, height).injectEvent();
                    eventcount = eventcount+1;
                    System.out.println("---EVENT执行了："+eventcount+"次---");
                    
                    break;
                }
                case 1: {
                    new MonkeySwipeEvent(driver, width, height).injectEvent();
                    eventcount = eventcount+1;
                    System.out.println("---EVENT执行了："+eventcount+"次---");
                    break;
                }
                case 2: {
                	
                	new MonkeyLaunchEvent(driver,UDID,BUNDLEID).injectEvent();
                    eventcount = eventcount+1;
                    System.out.println("---EVENT执行了："+eventcount+"次---");
                    
                    break;
                    
                }
                /*case 3: {
                	new MonkeyBackFromPlayerEvent(driver).injectEvent();
                    eventcount = eventcount+1;
                    System.out.println("---EVENT执行了："+eventcount+"次---");
                    break;
                }
                case 4: {
                	new MonkeyBackFromTouEvent(driver).injectEvent();
                    eventcount = eventcount+1;
                    System.out.println("---EVENT执行了："+eventcount+"次---");
                    break;
                }
                case 5: {
                	new MonkeyBackFromWebEvent(driver).injectEvent();
                    eventcount = eventcount+1;
                    System.out.println("---EVENT执行了："+eventcount+"次---");
                    break;
                }*/
                
                
            }

        }
    }


    private void init() throws IOException, InterruptedException {
    	//Shell.exec("/usr/local/bin/ideviceinstaller -i /Users/roger/git/iosMonkey/app/YoukuHD-NG-Release.ipa");
    	//Thread.sleep(20000);
    	
        driver = new MacacaClient();
        JSONObject porps = new JSONObject();
        porps.put("platformName", "ios");
        porps.put("reuse", 3);
        porps.put("bundleId", BUNDLEID);
        porps.put("udid", UDID);
        porps.put("autoAcceptAlerts", true);
        porps.put("proxyPort", Integer.parseInt(PROXYPORT));
        JSONObject desiredCapabilities = new JSONObject();
        desiredCapabilities.put("desiredCapabilities", porps);
        desiredCapabilities.put("host", "127.0.0.1");
        desiredCapabilities.put("port", Integer.parseInt(PORT));
        try {
            driver.initDriver(desiredCapabilities);
          //启动app守护进程并收集.crash日志
            Shell.launchAPP(UDID,BUNDLEID);
            
        } catch (Exception e) {
            System.out.println("*******************************************\n\n" +
                    "请确认已启动macaca server 在终端输入 macaca server --verbose 启动服务\n" +
                    "如果udid为真机，请确认已连接真机设备\n\n" +
                    "*******************************************\n");
        }
        
    }
}
