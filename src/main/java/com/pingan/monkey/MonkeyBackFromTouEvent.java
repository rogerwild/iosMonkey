package com.pingan.monkey;

import com.alibaba.fastjson.JSONObject;
import macaca.client.MacacaClient;

//import java.util.Random;

/**
 * Created by hujiachun on 16/12/23.
 */
public class MonkeyBackFromTouEvent extends MonkeyEvent {
    private MacacaClient driver;
    
    public MonkeyBackFromTouEvent(MacacaClient driver) {
        super(MonkeyEvent.EVENT_TYPE_BACK_FROM_TOUTIAO);
        this.driver = driver;

    }

    public int injectEvent() throws Exception {
        int x = 726;
        int y = 37;
        System.out.println("sending Event : Back From Toutiao Event->(" + x + "," + y + ")");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("x", x);
        jSONObject.put("y", y);
        driver.touch("tap", jSONObject);
        //driver.touchAsync("tap", jSONObject);
        return MonkeyEvent.INJECT_SUCCESS;
    }


}
