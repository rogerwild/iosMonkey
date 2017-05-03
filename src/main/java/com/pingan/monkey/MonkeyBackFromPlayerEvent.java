package com.pingan.monkey;

import com.alibaba.fastjson.JSONObject;
import macaca.client.MacacaClient;

/**
 * Created by hujiachun on 16/12/23.
 */
public class MonkeyBackFromPlayerEvent extends MonkeyEvent {
    private MacacaClient driver;

    public MonkeyBackFromPlayerEvent(MacacaClient driver) {
        super(MonkeyEvent.EVENT_TYPE_BACK_FROM_PLAYER);
        
        this.driver = driver;

    }

    public int injectEvent() throws Exception {
    	int x=739;
    	int y=9;
        System.out.println("sending Event : Back From Player->(" + x + "," + y + ")");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("x", x);
        jSONObject.put("y", y);
        driver.touch("tap", jSONObject);
        //driver.touchAsync("tap", jSONObject);
        return MonkeyEvent.INJECT_SUCCESS;
    }
}
