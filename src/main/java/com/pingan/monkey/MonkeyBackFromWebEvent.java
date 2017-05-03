package com.pingan.monkey;

import com.alibaba.fastjson.JSONObject;
import macaca.client.MacacaClient;

/**
 * Created by hujiachun on 16/12/23.
 */
public class MonkeyBackFromWebEvent extends MonkeyEvent {
 
    private MacacaClient driver;

    public MonkeyBackFromWebEvent(MacacaClient driver) {
        super(MonkeyEvent.EVENT_TYPE_BACK_FROM_WEB);
       
        this.driver = driver;

    }

    public int injectEvent() throws Exception {
    	//int x=768;
    	//int y=4;
    	String name="返回“优酷 HD”";
        //System.out.println("sending Event : Back From Web->(" + x + "," + y + ")");
    	System.out.println("sending Event : Back From Web");
        JSONObject jSONObject = new JSONObject();
        //jSONObject.put("x", x);
        //jSONObject.put("y", y);
        jSONObject.put("name", name);
        driver.touch("tap", jSONObject);
        
        return MonkeyEvent.INJECT_SUCCESS;
    }
}
