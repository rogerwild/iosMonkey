package com.pingan.monkey;

import com.alibaba.fastjson.JSONObject;
import macaca.client.MacacaClient;

/**
 * Created by hujiachun on 16/12/23.
 */
public class MonkeySwipeEvent extends MonkeyEvent {
    private int width, height;
    private MacacaClient driver;


    public MonkeySwipeEvent(MacacaClient driver, int width, int height) {
        super(MonkeyEvent.EVENT_TYPE_SWIPE);
        this.width = width;
        this.height = height;
        this.driver = driver;

    }

    public int injectEvent() throws Exception {
        double startX = Math.ceil(Math.random() * (width - 2));
        double endX = Math.ceil(Math.random()* (width-1));
        double startY = Math.ceil(Math.random() * (height - 2));
        //double endY = Math.ceil(Math.random() * (height - 1));
        System.out.println("sending Swipe Event : Swipe-> [start(" + startX + "," + startY + "), end(" + endX + "," + startY+")]");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("fromX", startX);
        jSONObject.put("fromY", startY);
        jSONObject.put("toX", endX);
        jSONObject.put("toY", startY);
        jSONObject.put("duration", 0.005);
        jSONObject.put("steps", 200);
        driver.touch("drag", jSONObject);
        //driver.touchAsync("tap", jSONObject);
        return MonkeyEvent.INJECT_SUCCESS;
    }
}
