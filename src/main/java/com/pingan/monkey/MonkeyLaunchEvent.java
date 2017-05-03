package com.pingan.monkey;

import macaca.client.common.Keycode;

import java.io.IOException;

import com.pingan.monkey.util.Shell;

import macaca.client.MacacaClient;

public class MonkeyLaunchEvent extends MonkeyEvent{
	private String UDID, BUNDLEID;	
    private MacacaClient driver;

    public MonkeyLaunchEvent(MacacaClient driver,String udid, String bundleid) {
        super(MonkeyEvent.EVENT_TYPE_LAUNCH);
        this.driver = driver;
        this.UDID = udid;
        this.BUNDLEID = bundleid;

    }


    public int injectEvent() throws Exception {
    	System.out.println("sending HOMEKEY Event.");
    	driver.keys("\uE105");//Home键
    	Thread.sleep(3000);
    	new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                    Shell.exec("pkill idevicedebug");
                    System.out.println("idevicedebug stop");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println("launch App:" + BUNDLEID);
                try {
                    Shell.exec("/usr/local/bin/idevicedebug -u " + UDID + " run " + BUNDLEID);
                    Thread.sleep(3000);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        return MonkeyEvent.INJECT_SUCCESS;
    } 
}