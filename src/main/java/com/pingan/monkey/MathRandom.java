package com.pingan.monkey;

/**
 * Created by hujiachun on 16/12/23.
 */

public class MathRandom {

	/**
     * 0出现的概率为%60 点击
     */
    public static double EVENT_TYPE_TAP = 0.60;
    /**
     * 1出现的概率为%30 滑动
     */
    public static double EVENT_TYPE_SWIPE = 0.30;
    /**
     * 2出现的概率为%10 挂起再返回app
     */
    public static double EVENT_TYPE_LAUNCH = 0.10;
    
    //从播放页返回
    //public static double EVENT_TYPE_BACK_FROM_PLAYER = 0.10;
    
    //从头条返回
    //public static double EVENT_TYPE_BACK_FROM_TOUTIAO = 0.10;
    
    //从h5返回
    //public static double EVENT_TYPE_BACK_FROM_WEB = 0.20;
    


    public int PercentageRandom(double tapevent, double swipeevent, double lunchevent) {
    	EVENT_TYPE_TAP=tapevent;
    	EVENT_TYPE_SWIPE=swipeevent;
    	EVENT_TYPE_LAUNCH=lunchevent;
        double randomNumber;
        randomNumber = Math.random();
        if (randomNumber >= 0 && randomNumber <= EVENT_TYPE_TAP) {
            return 0;
        } else if (randomNumber >= EVENT_TYPE_TAP / 100 && randomNumber <= EVENT_TYPE_TAP + EVENT_TYPE_SWIPE) {
            return 1;
        } else if (randomNumber >= EVENT_TYPE_TAP + EVENT_TYPE_SWIPE
                && randomNumber <= EVENT_TYPE_TAP + EVENT_TYPE_SWIPE + EVENT_TYPE_LAUNCH) {
            return 2;
        } /*else if (randomNumber >= EVENT_TYPE_TAP + EVENT_TYPE_SWIPE + EVENT_TYPE_LAUNCH
                && randomNumber <= EVENT_TYPE_TAP + EVENT_TYPE_SWIPE + EVENT_TYPE_LAUNCH + EVENT_TYPE_BACK_FROM_PLAYER) {
            return 3;
        } else if (randomNumber >= EVENT_TYPE_TAP + EVENT_TYPE_SWIPE + EVENT_TYPE_LAUNCH + EVENT_TYPE_BACK_FROM_PLAYER
                && randomNumber <= EVENT_TYPE_TAP + EVENT_TYPE_SWIPE + EVENT_TYPE_LAUNCH + EVENT_TYPE_BACK_FROM_PLAYER + EVENT_TYPE_BACK_FROM_TOUTIAO) {
            return 4;
        } else if (randomNumber >= EVENT_TYPE_TAP + EVENT_TYPE_SWIPE + EVENT_TYPE_LAUNCH + EVENT_TYPE_BACK_FROM_PLAYER + EVENT_TYPE_BACK_FROM_TOUTIAO
                && randomNumber <= EVENT_TYPE_TAP + EVENT_TYPE_SWIPE + EVENT_TYPE_LAUNCH + EVENT_TYPE_BACK_FROM_PLAYER + EVENT_TYPE_BACK_FROM_TOUTIAO
                + EVENT_TYPE_BACK_FROM_WEB) {
            return 5;
        }*/
        return -1;
    }
}
